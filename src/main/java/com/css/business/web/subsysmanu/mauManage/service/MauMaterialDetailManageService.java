package com.css.business.web.subsysmanu.mauManage.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsysmanu.bean.MauMaterialDetail;
import com.css.business.web.subsysmanu.bean.MauMaterialRecord;
import com.css.business.web.subsysmanu.mauManage.dao.MauMaterialDetailManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMaterialRecordManageDAO;
import com.css.business.web.subsysmanu.vo.SendForkliftVo;
import com.css.business.web.subsysplan.bean.PlaMachinePlanMater;
import com.css.business.web.subsysstore.bean.StoreDgCk;
import com.css.business.web.subsysstore.bean.StoreDgCkDetail;
import com.css.business.web.subsysstore.storeManage.dao.StoreDgCkDetailManageDAO;
import com.css.business.web.subsysstore.storeManage.dao.StoreDgCkManageDAO;
import com.css.common.util.CodedFormatParseUtil;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.MqDisplayVo;
import com.css.common.web.apachemq.handle.ApacheMqSender;
import com.css.common.web.apachemq.handle.test.MqttBroker;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;

@Service("mauMaterialDetailManageService")
public class MauMaterialDetailManageService extends BaseEntityManageImpl<MauMaterialDetail, MauMaterialDetailManageDAO> {
	@Resource(name="mauMaterialDetailManageDAO")
	//@Autowired
	private MauMaterialDetailManageDAO dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MauMaterialRecordManageDAO mauMaterialRecordManageDAO;
	
	@Autowired
	private StoreDgCkManageDAO storeDgCkManageDAO;
	
	@Autowired
	private StoreDgCkDetailManageDAO storeDgCkDetailManageDAO;
	
	@Override
	public MauMaterialDetailManageDAO getEntityDaoInf() {
		return dao;
	}

	@Transactional(readOnly=false)
	public void updateByJdbc(final List<MauMaterialDetail> list) {
		
		//更新领料明细记录
		String sql = " update mau_material_detail set mater_amount = ?  where id = ? ";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MauMaterialDetail bean = list.get(i);
				ps.setBigDecimal(1, bean.getMaterAmount());
				ps.setInt(2, bean.getId());
				
			}
			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
		//TODO 确认领取物料调用的接口，此处需要注释掉
		confirmReceive(list);
		//但是此处需要生成出库单的啊，需要发送到仓库电子看板
//		updateMauMaterialDetail(list);
		//发送仓库看板
		sendKanban();
		
		
	}

	/**
	 * 更新领料单明细的领料状态为已领取，并生成新的领料模板记录
	 * @param list
	 * @return
	 */
	public List<MauMaterialDetail> updateMauMaterialDetail(final List<MauMaterialDetail> list) {
		//TODO 更新领料明细单据的领料状态为1，表示已领取
		String sql = " update mau_material_detail set is_receive = ?  where id = ? ";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MauMaterialDetail bean = list.get(i);
				ps.setString(1, "1");
				ps.setInt(2, bean.getId());
				
			}
			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
		
		
		//TODO 生成新的领料记录模板
		List<Integer> idList = new ArrayList<Integer> ();
		for(MauMaterialDetail bean : list){
			idList.add(bean.getId());
		}
		String hql = " from MauMaterialDetail where id in (:ids) ";
		List<MauMaterialDetail> ll =  dao.getHibernateTemplate().findByNamedParam(hql, "ids", idList);//将这两条数据的内容复制一下
//		Integer mainId = null; 
		//TODO ，将领料的数据复制一份
		List<MauMaterialDetail> resultList = new ArrayList<MauMaterialDetail>();
		for(MauMaterialDetail bean : ll){
//			mainId = bean.getMainId();
			MauMaterialDetail resultBean = new MauMaterialDetail();
			resultBean.setCreateBy(bean.getCreateBy());
			resultBean.setCreateDate(new Date());
			resultBean.setDocType(bean.getDocType());
			resultBean.setIsReceive("0");//新增的默认为未领取
			resultBean.setMainId(bean.getMainId());
			resultBean.setMaterAmount(new BigDecimal(0));
			resultBean.setMaterCode(bean.getMaterCode());
			resultBean.setMaterName(bean.getMaterName());
			resultBean.setMmrCode(bean.getMmrCode());
			resultBean.setPlanCount(bean.getPlanCount());
			resultBean.setRemark(null);
			resultBean.setUnit(bean.getUnit());
			resultBean.setMacId(bean.getMacId());
			resultList.add(resultBean);
		}
		dao.saveOrUpdateBatch(resultList);
		return ll;
	}
	/**
	 * 确认领取物料调用的接口
	 * @param list
	 */
	public void confirmReceive(final List<MauMaterialDetail> list) {
		//TODO 将状态改为已领取
		List<MauMaterialDetail> ll = updateMauMaterialDetail(list);//更新了领料明细，新增领料明细模板数据
		Integer mainId = ll.get(0).getMainId();
		
		//TODO 判断是否超领
		
		//1.查询所有mainId的记录,并且是已领取的记录
		if(mainId==null){
			throw new RuntimeException("领料单明细中有记录没有存贮主表id");
		}
		String hslq = " from MauMaterialDetail where mainId = ? and isReceive = '1' ";
		List<MauMaterialDetail> deList = dao.getHibernateTemplate().find(hslq,mainId);
		//按照规格型号来分组，方便判断是否超领
		Map<String,List<MauMaterialDetail>> map = new HashMap<String,List<MauMaterialDetail>>();
		for(MauMaterialDetail bean : deList){
			String key = bean.getMaterCode();//物料规格型号
			
			if(map.get(key)==null){
				map.put(key, new ArrayList<MauMaterialDetail>());
			}
			map.get(key).add(bean);
		}
		boolean boo = false;//false,表示未超领，true表示超领，超领，需要更改主表状态
		for(String key : map.keySet()){
			List<MauMaterialDetail> tmepList = map.get(key);
			BigDecimal d = new BigDecimal(0);
			for(MauMaterialDetail bean:tmepList){
				d = d.add(bean.getMaterAmount());//将所有领料记录相加
				if(d.compareTo( bean.getPlanCount())==1){//表示大于领料计划数量，就算是超领
					boo = true;
					break;
				}
			}
			if(boo){
				break;
			}
		}
		
		String mauMaterialRecordCode = null;//领料单号
		MauMaterialRecord b = null;
		b  = mauMaterialRecordManageDAO.get(mainId);
		if(boo){//TODO 更新主表为超领
			
			mauMaterialRecordCode = b.getMmrCode();
			b.setIsOver("是");
			mauMaterialRecordManageDAO.save(b);
		}
		
		//TODO 生成出库单
		
		genChuKu(ll, mauMaterialRecordCode, b);
		
		//TODO 发送消息队列
		//1.电子看板
//		sendKanban();//确认领料不需要通知仓库
		//2.叉车
		sendForklift(ll,"已领取");
	}

	/**
	 * 发送到叉车的消息队列
	 * @param list
	 */
	public void sendForklift(List<MauMaterialDetail> list,String isReceive) {
		List<SendForkliftVo> voList = new ArrayList<SendForkliftVo>();
		for(MauMaterialDetail bean : list){
			SendForkliftVo vo = new SendForkliftVo();
			vo.setStartPlace("仓库");
			vo.setDestination(bean.getMacCode());//目的地
			vo.setCount(bean.getMaterAmount());//数量
			vo.setUnit(bean.getUnit());
			vo.setMaterialGgxh(bean.getMaterCode());//物料编号，规格型号
			vo.setMaterialGgxh(isReceive);
			voList.add(vo);
		}
		//TODO 在此处组装VO就行了
		Gson gson = new Gson();
		String msg = gson.toJson(voList);
		MqttBroker br = MqttBroker.getInstance();
		try {
			msg = new String(CodedFormatParseUtil.getUTF8ByteFromGBK(msg),CodedFormatParseUtil.UTF_8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		br.sendMessage("cc", msg);
	}
	/**
	 * 发送到看板的消息
	 */
	public void sendKanban() {
		PropertyFileUtil pfu = new PropertyFileUtil();
		ApacheMqSender sender = ApacheMqSender.getInstance();
		String queue = pfu.getProp("QUE_P2P_KANBAN");

		//封装看板消息队列需要的消息格式
//		Map<String, String> dataMap = new HashMap<String,String>();
		MqDisplayVo mdv = new MqDisplayVo();
		mdv.setType("msg");
		mdv.setTarget("仓库");
		mdv.setMsg("出库");
		mdv.setCrewId(null);
//		mdv.setPaMap(dataMap);
		JSONObject json = JSONObject.fromObject(mdv);
		String msg = json.toString();
		try {
			sender.sendMsg_point_to_point(queue, msg);//发送信息到队列
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 发送到
	 * @param ll
	 * @param mauMaterialRecordCode
	 * @param b
	 */
	private void genChuKu(List<MauMaterialDetail> ll,
			String mauMaterialRecordCode, MauMaterialRecord b) {
		// 1. 生成出库单主表
		String ckdh = "出库单号";//出库单号，需要调用数据库接口生成
		StoreDgCk mainBean = new StoreDgCk();
		mainBean.setCreateBy("创建人");
		mainBean.setCreateDate(new Date());
		mainBean.setObjGgxh("出库单主表不应该有物料规格这个字段");
		mainBean.setOperator("操作人");
		mainBean.setOperatTime(new Timestamp(System.currentTimeMillis()));
		mainBean.setOutboundOrderCode(ckdh);
		mainBean.setPickListCode(mauMaterialRecordCode);//领料单编号
		mainBean.setPicktor(b.getMatermanageBy());//领料人
		mainBean.setStatus("待出库");//待出库
		//TODO 保存出库，得到返回的id
		storeDgCkManageDAO.save(mainBean);
		
		//2.出库单明细
		List<StoreDgCkDetail> detailList = new ArrayList<StoreDgCkDetail>();
		for(MauMaterialDetail bean : ll){//一条领料单明细生成一条出库单明细记录
			String materCode = bean.getMaterCode();//原材料编号
			StoreDgCkDetail detail = new StoreDgCkDetail();
			detail.setAmount(bean.getMaterAmount());//数量
			detail.setCreateBy("创建人");//创建人
			detail.setCreateDate(new Date());
			detail.setFactAmount(null);//这个字段在我这里是无用字段
			detail.setMaterialId(null);//原材料id, TODO 有了规格编号，感觉id可以不需要
			detail.setObjGgxh(materCode);
			detail.setOutboundOrderCode(ckdh);//出库单号
			detail.setPickDgId(bean.getId());//领料单id，但是此处应该是领料单明细id
			detail.setUnit(bean.getUnit());//物料单位
			detailList.add(detail);
		}
		
		//TODO 保存出库明细
		storeDgCkDetailManageDAO.saveOrUpdateBatch(detailList);
	}
	
}
