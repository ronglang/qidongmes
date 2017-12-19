package com.css.business.web.subsysplan.plaManage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaUnitConMater;
import com.css.business.web.subsysplan.plaManage.bean.PlaContractVo;
import com.css.business.web.subsysplan.plaManage.service.PlaCourseManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaUnitConMaterManageService;
import com.css.business.web.subsysstore.storeManage.service.StoreMrecordManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/plaUnitConMaterManageAction")
public class PlaUnitConMaterManageAction extends BaseSpringSupportAction<PlaUnitConMater, PlaUnitConMaterManageService> {
	
	//@Autowired
	private PlaUnitConMaterManageService service;
	
	@Resource(name = "plaCourseManageService")
	private PlaCourseManageService ser;
	
	@Resource(name = "storeMrecordManageService")
	private StoreMrecordManageService serStore;
	
	@Override
	public PlaUnitConMaterManageService getEntityManager() {
		return service;
	}

	public PlaUnitConMaterManageService getService() {
		return service;
	}

	@Resource(name="plaUnitConMaterManageService")
	public void setService(PlaUnitConMaterManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaUnitConMaterEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaUnitConMaterForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaUnitConMaterList";
	}
	
	@RequestMapping({"getObj"})
	@ResponseBody
	public HashMap<String, Object> getObj(){
		List<PlaContractVo> vo = ser.getContractVo();
		List<PlaUnitConMater> list = new ArrayList<PlaUnitConMater>();
		String msg = null;
		Double Jl = 0.0;
		Double Cu = 0.0;
		Double jlStock = 0.0;
		Double cuStock = 0.0;
		for(int i = 0;i<vo.size();i++){
			String ggxh = vo.get(i).getProGgxh();
			List<PlaUnitConMater> ll = service.getPlaUnitConMater(ggxh);
			//list.add(ll.get(i));
			for(int j = 0;j<ll.size();j++){
				if("胶料".equals(ll.get(j).getObjName())){
					Jl = vo.get(i).getLength()*ll.get(j).getObjCount();
					jlStock = serStore.getActStock(ll.get(j).getObjGgxh()); //得到胶料的实际库存
				}else if("铜料".equals(ll.get(j).getObjName())){
					Cu = vo.get(i).getLength()*ll.get(j).getObjCount();
					cuStock = serStore.getActStock(ll.get(j).getObjGgxh()); //得到铜料的实际库存
				}
				list.add(ll.get(j));
			}
		}
		if(Jl>=jlStock){
			msg = "胶料库存不足";
			return JsonWrapper.failureWrapperMsg(list, msg);
		}else if(Cu>=cuStock){
			msg = "铜料库存不足";
			return JsonWrapper.failureWrapperMsg(list, msg);
		}else{
			msg = "材料充足，请排产" ; 
			return JsonWrapper.successWrapper(list, msg);
		}
	}

}
