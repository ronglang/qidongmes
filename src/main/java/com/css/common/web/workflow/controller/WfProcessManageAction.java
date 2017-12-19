package com.css.common.web.workflow.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysMenu;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.service.SysMenuManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.PropertiesHelper;
import com.css.common.util.workflow.SimpleXMLWorkShop;
import com.css.common.util.workflow.domain.Process;
import com.css.common.util.workflow.domain.ProcessResult;
import com.css.common.util.workflow.helper.Activity;
import com.css.common.util.workflow.helper.Transition;
import com.css.common.util.workflow.helper.WorkflowProcess;
import com.css.common.util.workflow.helper.WorkflowProcessSaxService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.workflow.bean.WfMenuProcesVo;
import com.css.common.web.workflow.bean.WfNode;
import com.css.common.web.workflow.bean.WfNodeRoleVO;
import com.css.common.web.workflow.bean.WfNodeTrans;
import com.css.common.web.workflow.bean.WfProcess;
import com.css.common.web.workflow.bean.WfProcessVO;
import com.css.common.web.workflow.bean.WfSqlc;
import com.css.common.web.workflow.service.WfDaimaManageService;
import com.css.common.web.workflow.service.WfForksetManageService;
import com.css.common.web.workflow.service.WfMenuProcessManageService;
import com.css.common.web.workflow.service.WfNodeManageService;
import com.css.common.web.workflow.service.WfNodeRoleManageService;
import com.css.common.web.workflow.service.WfNodeTransManageService;
import com.css.common.web.workflow.service.WfProcessManageService;
import com.css.common.web.workflow.service.WfShlogManageService;
import com.css.common.web.workflow.service.WfSqlcManageService;
import com.css.common.web.workflow.service.WfWorkdayManageService;
import com.css.common.web.workflow.service.WfYewuProcessManageService;
import com.css.common.web.workflow.service.WorkflowService;

@Controller
@RequestMapping("/wfProcessManageAction")
public class WfProcessManageAction extends BaseSpringSupportAction<WfProcess, WfProcessManageService> {
	String db_config_properties_name="config.properties";
	//@Autowired
	private WfProcessManageService service;
	
	@Resource(name="wfNodeManageService")
	private WfNodeManageService nodeService;
	
	@Resource(name="wfNodeRoleManageService")
	private WfNodeRoleManageService wfNodeRoleManageService;
	
	@Resource(name="wfNodeTransManageService")
	private WfNodeTransManageService nodeTransService;
	
	@Resource(name="wfShlogManageService")
	private WfShlogManageService shlogService;
	
	@Resource(name="wfMenuProcessManageService")
	private WfMenuProcessManageService menuProcessService;
	
	@Resource(name="wfSqlcManageService")
	private WfSqlcManageService sqlcService;
	
	@Resource(name="sysMenuManageService")
	private SysMenuManageService menuService;
	
	@Resource(name="wfYewuProcessManageService")
	private WfYewuProcessManageService wfYewuProcessManageService;
	
	@Resource(name="wfForksetManageService")
	private WfForksetManageService wfForksetManageService;
	
	@Resource(name="wfWorkdayManageService")
	private WfWorkdayManageService wfWorkdayManageService;
	
	@Resource(name="wfDaimaManageService")
	private WfDaimaManageService wfDaimaManageService;
	
	@Override
	public WfProcessManageService getEntityManager() {
		return service;
	}

	public WfProcessManageService getService() {
		return service;
	}

	@Resource(name="wfProcessManageService")
	public void setService(WfProcessManageService service) {
		this.service = service;
	}
	
	 private void printChar(HttpServletRequest req,HttpServletResponse res,char lc_result){
	    	PrintWriter pw=null;
	    	try {
	    		/*getRequest().setCharacterEncoding("UTF-8");
	    		getResponse().setContentType("text/html; charset=UTF-8");
	    		pw=getResponse().getWriter();*/
	    		req.setCharacterEncoding("UTF-8");
	    		res.setContentType("text/html; charset=UTF-8");
	    		pw = res.getWriter();
	    		pw.write(lc_result);
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}finally{
	    		pw.flush();
	    		pw.close();
	    	}
	 }
	 private void printObj(HttpServletRequest req,HttpServletResponse res,Object obj){
		 PrintWriter pw=null;
		 try {
			 /*getRequest().setCharacterEncoding("UTF-8");
	    		getResponse().setContentType("text/html; charset=UTF-8");
	    		pw=getResponse().getWriter();*/
			 req.setCharacterEncoding("UTF-8");
			 res.setContentType("text/html; charset=UTF-8");
			 pw = res.getWriter();
			 String str = JSONObject.fromObject(obj).toString();
			 pw.write(str);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }finally{
			 pw.flush();
			 pw.close();
		 }
	 }
	    /**
	     * @todo 输出json字符串到页面。要求：字符串符合json格式
	     * @author zhaichunlei
	     */
	    public void printJsonStr(HttpServletRequest req,HttpServletResponse res,String jsonStr){
	    	PrintWriter pw=null;
	    	try {
	    		req.setCharacterEncoding("UTF-8");
	    		res.setContentType("text/html; charset=UTF-8");
	    		pw=res.getWriter();
	    		pw.write(jsonStr);
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}finally{
	    		pw.flush();
	    		pw.close();
	    	}
	    }
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfProcessEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfProcessForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfProcessList";
	}
	
	
	/**
	 * 工作流管理页面
	 * @throws Exception 
	 */
	@RequestMapping({ "admin" })
	@ResponseBody
	public  Page  admin(HttpServletRequest request, Page page,WfProcess pro) throws Exception{
	/*	String ls_sql="select ps_id,ps_dm,ps_mc,ps_xml,ps_tjrid,ps_tjrxm,to_char(ps_tjsj,'yyyy-mm-dd hh24:mi:ss') as ps_tjsj,ps_sfzf from wf_process order by ps_id desc";
		JSONObject jsonObj = commonPageQuery(ls_sql,WfProcess.class);
		printJsonStr(jsonObj.toString());*/
		return service.admin(page,pro);
	}
	

	
	/**
	 * 列出process文件夹下 所有定义的工作流xml
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "Listprocess" })
	//@ResponseBody
	public void Listprocess(HttpServletRequest request, HttpServletResponse res) throws Exception {
		res.setContentType("text/xml");
		res.setHeader("Cache-Control", "no-cache");
       /* getResponse().setContentType("text/xml");
        getResponse().setHeader("Cache-Control", "no-cache");*/
        WorkflowService service=new WorkflowService();
        List list = service.listProcess();

        SimpleXMLWorkShop.outputXML(ProcessResult.convertFilesToXml(list),res.getOutputStream());
	}
	

	/**
	 * 根据工作流名称 获取某一个工作流
	 * @throws Exception
	 */
	@RequestMapping({ "getprocess" })
	@ResponseBody
	public void getprocess(HttpServletRequest request,HttpServletResponse res,String name,boolean isFugaiExistFile) throws Exception{
		WorkflowService wservice=new WorkflowService();
		//boolean makeSuccessFlag=false;
		if(service.checkProcessNameIsExist(name)){
			String xmlFileSavePath= com.css.common.util.PropertiesHelper.getString("config.properties","xmlFileSavePath");
			WfProcess wp = service.findProcessByPsMc(name);
			String fileName = wp.getPsMc();
			String ps_xml= wp.getPsXml();
			
			String filePath=xmlFileSavePath+fileName+".xml";
			java.io.File file = new java.io.File(filePath);  //自定义文件路径
			if(isFugaiExistFile == false){//文件存在时，不重新生成
				if(new File(filePath).exists()){
					//makeSuccessFlag=true;
				}
			}else{//直接覆盖原XML文件
				java.io.OutputStreamWriter write= new java.io.OutputStreamWriter(new java.io.FileOutputStream(file), "UTF-8"); 
				java.io.BufferedWriter writer = new java.io.BufferedWriter(write); 
				writer.write(ps_xml);  // 输出流写入到文件中 
				writer.close(); 
//				System.out.println("生成"+filePath+"成功");
				//makeSuccessFlag=true;
			}
			
			
			//BaseDao dao=new BaseDao();
			//String ls_sql="select ps_mc,ps_xml from wf_process where ps_mc='"+name+"'";
			//dao.makeProcessXmlFile(ls_sql,true);
		}
        String projectDeployPath = request.getSession().getServletContext().getRealPath("/").toString();
		ProcessResult processResult = wservice.getProcessByName(name,projectDeployPath);

		com.css.common.util.workflow.domain.Process process = processResult.getProcess();

        res.setContentType("text/xml");
        res.setHeader("Cache-Control", "no-cache");

        Document doc = null;
        if (process != null) {
            doc = process.getDoc();
        }
        doc = (doc == null) ? ProcessResult.convertXml(processResult) : doc;

        SimpleXMLWorkShop.outputXML(doc,res.getOutputStream());
	}
	

	/**
	 * 保存工作流程
	 * @throws Exception
	 */
	@RequestMapping({ "addprocess" })
	@ResponseBody
	 public void addprocess(HttpServletRequest request,HttpServletResponse res,String name,String xml) throws Exception {
	//	System.out.println(name);
	//	System.out.println(xml);
	    String projectDeployPath = "";
	    SysUser user = SessionUtils.getUser(request);
        ProcessResult processResult = new ProcessResult();
        Document doc;
        try {
            doc = SimpleXMLWorkShop.str2Doc(xml);
        } catch (IOException e) {
            processResult.setStatus(WorkflowService.IO_ERROR);
            processResult.setMes(e.getMessage());
            doc = null;
        } catch (JDOMException e) {
            processResult.setStatus(WorkflowService.XML_PARSER_ERROR);
            processResult.setMes(e.getMessage());
            doc = null;
            System.out.println("jdom error on addprocess str2Doc:" + e.getMessage());
        }
        WorkflowService wservice=new WorkflowService();
        if (doc != null) {
        	//检查流程名称是否已经存在
        	if( service.checkProcessNameIsExist(name) ){//不存在，保存到硬盘，并写数据入库
        		com.css.common.util.workflow.domain.Process process = new com.css.common.util.workflow.domain.Process();
                process.setName(name);
                process.setDoc(doc);
                projectDeployPath = request.getSession().getServletContext().getRealPath("/").toString();
                processResult = wservice.addProcess(process,projectDeployPath);
                if(processResult.getStatus()==0 && service.checkProcessNameIsExist(name)==false){
               	    res.setContentType("text/xml");
                    res.setHeader("Cache-Control", "no-cache");
                    boolean saveOk=SimpleXMLWorkShop.outputXMLWithResult(ProcessResult.convertXml(processResult), res.getOutputStream());
                    if(saveOk){//文件保存到硬盘后，写数据库
                   	 //写入数据到数据库wf_process wf_node wf_node_trans
                        String xmlFileSavePath=PropertiesHelper.getString(db_config_properties_name,"xmlFileSavePath");
                      //加载资源文件 转化为一个输入流 book.xml需要放在src根目录下
                        InputStream is=new FileInputStream(new File(xmlFileSavePath+name+".xml"));//WorkflowProcessSaxService.class.getClassLoader().getResourceAsStream("com/xml/workflow/Ա�������������.xml");
                        WorkflowProcessSaxService saxService=new WorkflowProcessSaxService();
                        WorkflowProcess bean= saxService.getWorkflowProcessData(is);
                        List<Activity> activityList=null;
                    	 List<Transition> transList=null;
                    	 List<String> sqlList=new ArrayList();
                        if(null!=bean){
                        	Object o = service.getUniqueResult("select fun_get_wf_psdm()");
                        	//本函数返回不会为空。不做判断了
                        	String ps_dm = o.toString();
                        	WfProcess wp = new WfProcess();
                        	wp.setPsDm(ps_dm);
                        	wp.setPsMc(name);
                        	wp.setPsXml(xml);
                        	wp.setPsTjrid(user.getId());
                        	wp.setPsTjrxm(user.getName());
                        	service.save(wp);
                        	
                        	/*BaseDao dao=new BaseDao();
                        	String ps_dm=dao.getUniqueValue("select fun_get_wf_psdm() from dual").toString();
                        	
                        	BtUser loginUser = getSessionUser("loginUser");
                        	
//                        	写入主表wf_process
                        	sqlList.add("insert into wf_process(ps_dm,ps_mc,ps_xml,ps_tjrid,ps_tjrxm) values('"+ps_dm+"','"+name+"','"+xml+"',"+loginUser.getUserId()+",'"+loginUser.getUserXm()+"')");
                        	*/
//                        	写入表wf_node
                        	activityList = bean.getActivityList();
                        	transList = bean.getTransitionList();
                        	
                        	for(Activity act:activityList){
                        		WfNode wn = new WfNode();
                        		wn.setPsDm(ps_dm);
                        		wn.setNodeId(act.getId() == null ? null : Integer.parseInt(act.getId()));
                        		wn.setNodeType(act.getType());
                        		wn.setNodeName(act.getName());
                        		wn.setNodeX(act.getXCoordinate());
                        		wn.setNodeY(act.getYCoordinate());
                        		wn.setNodeWidth(act.getWidth());
                        		wn.setNodeHeight(act.getHeight());
                        		nodeService.save(wn);
                        		
                        		//sqlList.add("insert into wf_node(ps_dm,node_id,node_type,node_name,node_x,node_y,node_width,node_height) values('"+ps_dm+"',"+act.getId()+",'"+act.getType()+"','"+act.getName()+"','"+act.getXCoordinate()+"','"+act.getYCoordinate()+"','"+act.getWidth()+"','"+act.getHeight()+"')");
//                        		System.out.println("id:"+act.getId()+" type:"+act.getType()+" name:"+act.getName()+" xCoordinate:"+act.getXCoordinate()+" yCoordinate:"+act.getYCoordinate()+" width:"+act.getWidth()+" height:"+act.getHeight());
                        	}
                        	
                        	for(Transition trans:transList){
                        		WfNodeTrans t = new WfNodeTrans();
                        		t.setPsDm(ps_dm);
                        		t.setTsId(Integer.parseInt(trans.getId()));
                        		t.setTsFrom(Integer.parseInt(trans.getFrom()));
                        		t.setTsTo(Integer.parseInt(trans.getTo()));
                        		nodeTransService.save(t);
                        		//sqlList.add("insert into wf_node_trans(ps_dm,ts_id,ts_name,ts_from,ts_to) values('"+ps_dm+"',"+trans.getId()+",'"+trans.getName()+"','"+trans.getFrom()+"','"+trans.getTo()+"')");
//                        		System.out.println("id:"+trans.getId()+" name:"+trans.getName()+" from:"+trans.getFrom()+" to:"+trans.getTo());
                        	}
                        	
                        	/*if(dao.executeBatUpdate(sqlList.toArray(new String[sqlList.size()]))==false){//数据库没保存成功时，删除文件夹下的文件
//                        		service.deleteFileByName(name);//根据文件名，删除process目录下的xml文件
                        		File xmlFile=new File(xmlFileSavePath+name+".xml");
                        		xmlFile.deleteOnExit();
                        	}*/
                        }
                    }
               }
        	}else{//流程名称是否已经存在,不能保存
        		 processResult.setStatus(WorkflowService.FILE_EXIST);
                 processResult.setMes("文件已经存在！");
                 doc = null;
                 System.out.println("文件已经存在" );
        	}
        }
        //最后检查此文件名在数据库中是否存在，不存在时，检查目录下是否有此文件名的文件，有了就删除
       if(!service.checkProcessNameIsExist(name)){
    	   wservice.deleteFileByName(name);//根据文件名，删除process目录下的xml文件
       }
	}
	 
	 /**
	  * 更新定义的工作流
	  * @throws Exception
	  */
	@RequestMapping({ "updateprocess" })
	@ResponseBody
	 public void updateprocess(HttpServletRequest req,HttpServletResponse res,String name,String xml) throws Exception {
        req.setCharacterEncoding("UTF-8");

        //String name = getRequest().getParameter("name");
       // String xml = getRequest().getParameter("xml");
//        System.out.println("update process:" + name);

        ProcessResult processResult = new ProcessResult();
        Document doc;
        try {
            doc = SimpleXMLWorkShop.str2Doc(xml);
        } catch (IOException e) {
            processResult.setStatus(WorkflowService.IO_ERROR);
            processResult.setMes(e.getMessage());
            doc = null;
            System.out.println("io error on onupdateprocess str2Doc:" + e.getMessage());
        } catch (JDOMException e) {
            processResult.setStatus(WorkflowService.XML_PARSER_ERROR);
            processResult.setMes(e.getMessage());
            doc = null;
            System.out.println("jdom error onupdateprocess str2Doc:" + e.getMessage());
        }

        if (doc != null) {
//          根据流程名称查找流程定义
        	WorkflowService wservice=new WorkflowService();
        	Object obj = service.findProcessByPsMc(name);
        	WfProcess bean=null;
        	bean=null==obj?null:(WfProcess)obj;
        	if(null!=bean){
        		String ps_dm=bean.getPsDm();
//        		System.out.println("update ps_dm="+ps_dm);
        		//判断此流程当前是否已使用，正在使用时禁止更新
        		if(shlogService.checkProcessIsUseByPsDm(ps_dm)==false){
        			com.css.common.util.workflow.domain.Process process = new com.css.common.util.workflow.domain.Process();
                    process.setName(name);
                    process.setDoc(doc);
                    String projectDeployPath = req.getSession().getServletContext().getRealPath("/").toString();
                    processResult = wservice.updateProcess(process,projectDeployPath);
                    //更新数据库
                    if(processResult.getStatus()==0){//输出更新到硬盘XML文件成功
                    	List<String> sqlList=new ArrayList<String>();
                		//更新方法：先删除，再插入
                		sqlList.add("delete from wf_node where ps_dm='"+ps_dm+"'");
                		sqlList.add("delete from wf_node_trans where ps_dm='"+ps_dm+"'");
                		
                        String xmlFileSavePath=PropertiesHelper.getString(db_config_properties_name,"xmlFileSavePath");
//                      System.out.println(xmlFileSavePath+name+".xml");
                      //解析更新后的XML文件，并从中获取node和trans
                        InputStream is=new FileInputStream(new File(xmlFileSavePath+name+".xml"));//WorkflowProcessSaxService.class.getClassLoader().getResourceAsStream("com/xml/workflow/Ա�������������.xml");
                        WorkflowProcessSaxService saxService=new WorkflowProcessSaxService();
                        WorkflowProcess saxBean= saxService.getWorkflowProcessData(is);
                        List<Activity> activityList=null;
                    	List<Transition> transList=null;
                        if(null!=saxBean){
//                        	System.out.println("update@@@@@ ps_dm="+ps_dm);
//                        	更新主表wf_process
                        	sqlList.add("update wf_process set ps_xml='"+xml+"' where ps_dm='"+ps_dm+"'");
//                        	写入表wf_node
                        	activityList=saxBean.getActivityList();
                        	transList=saxBean.getTransitionList();
                        	
                        	for(Activity act:activityList){
//                        		System.out.println("update##### ps_dm="+ps_dm);
                        		sqlList.add("insert into wf_node(ps_dm,node_id,node_type,node_name,node_x,node_y,node_width,node_height) values('"+ps_dm+"',"+act.getId()+",'"+act.getType()+"','"+act.getName()+"','"+act.getXCoordinate()+"','"+act.getYCoordinate()+"','"+act.getWidth()+"','"+act.getHeight()+"')");
//                        		System.out.println("id:"+act.getId()+" type:"+act.getType()+" name:"+act.getName()+" xCoordinate:"+act.getXCoordinate()+" yCoordinate:"+act.getYCoordinate()+" width:"+act.getWidth()+" height:"+act.getHeight());
                        	}
                        	
                        	for(Transition trans:transList){
                        		sqlList.add("insert into wf_node_trans(ps_dm,ts_id,ts_name,ts_from,ts_to) values('"+ps_dm+"',"+trans.getId()+",'"+trans.getName()+"','"+trans.getFrom()+"','"+trans.getTo()+"')");
//                        		System.out.println("id:"+trans.getId()+" name:"+trans.getName()+" from:"+trans.getFrom()+" to:"+trans.getTo());
                        	}
                       }
                      // BaseDao dao=new BaseDao();
                       for(String str:sqlList){
                    	   System.out.println(str);
                       }
                       service.batchExeSql(sqlList.toArray(new String[sqlList.size()]));
                       //dao.executeBatUpdate(sqlList.toArray(new String[sqlList.size()]));
                    }//输出xml更新over
        		}else{//此流程当前正在使用时禁止更新
        			 processResult.setStatus(WorkflowService.XML_PARSER_ERROR);
        	         processResult.setMes("此流程当前正在使用,禁止更新!");
        	         doc = null;
        		}
        	}
        }

        res.setContentType("text/xml");
        res.setHeader("Cache-Control", "no-cache");
        SimpleXMLWorkShop.outputXML(ProcessResult.convertXml(processResult),res.getOutputStream());
    }
	

	/**
	 * 删除process文件下 定义的工作流
	 * 
	 * @throws Exception
	 */
	@RequestMapping({ "deleteprocess" })
	@ResponseBody
	public void deleteprocess(HttpServletRequest req,HttpServletResponse res,String name) throws Exception {
		req.setCharacterEncoding("UTF-8");

		//String name = getParameter("name");
		System.out.println("delete process:" + name);

		Process process = new Process();
		process.setName(name);
		WorkflowService service = new WorkflowService();
		ProcessResult processResult = service.deleteProcess(process);

		res.setContentType("text/xml");
		res.setHeader("Cache-Control", "no-cache");
		SimpleXMLWorkShop.outputXML(ProcessResult.convertXml(processResult),
				res.getOutputStream());
	}
	
	 /**
     * 删除定义的工作流
     * @throws Exception
     */
	@RequestMapping({ "delete_process" })
	@ResponseBody
	 public HashMap<String, Object> delete(HttpServletRequest req,HttpServletResponse res,String ps_dm) throws Exception{
		 char lc_result='0';
		 //String ps_dm=getParameter("ps_dm");
		 //WorkflowService wservice=new WorkflowService();
		 String ps_mc="";
		 WfProcess bean = service.findProcessByPsDm(ps_dm);
		 ps_dm=null==bean?"":bean.getPsDm();
		 ps_mc=null==bean?"":bean.getPsMc();
		
		 if(!"".equals(ps_dm) && !"".equals(ps_mc)){
			 //BaseDao dao=new BaseDao();
			 if(menuProcessService.checkProcessIsBindMenuByPsDm(ps_dm)){//绑定有菜单，禁止删除
				 lc_result='2';
			 }else if(shlogService.checkProcessIsUseByPsDm(ps_dm)){//正在使用，禁止删除
				 lc_result='3';
			 }else{//绑定无菜单，删除流程定义
				 String[] sqlArr=new String[3];
				 sqlArr[0]="delete from wf_process where ps_dm='"+ps_dm+"'";
				 sqlArr[1]="delete from wf_node where ps_dm='"+ps_dm+"'";
				 sqlArr[2]="delete from wf_node_trans where ps_dm='"+ps_dm+"'";
				 service.batchExeSql(sqlArr);
				//删除process目录下的流程xml文件
				 if(!"".equals(ps_dm)){
					 String xmlFileSavePath=PropertiesHelper.getString(db_config_properties_name,"xmlFileSavePath");
					 File f=new File(xmlFileSavePath+ps_mc+".xml");
					 f.deleteOnExit();
					 lc_result='1';
				 }
				 /*if(dao.executeBatUpdate(sqlArr)){
					 //删除process目录下的流程xml文件
					 if(!"".equals(ps_dm)){
						 String xmlFileSavePath=PropertiesHelper.getString(db_config_properties_name,"xmlFileSavePath");
						 File f=new File(xmlFileSavePath+ps_mc+".xml");
						 f.deleteOnExit();
					 }
					 lc_result='1';
				 }*/
			 }
		 }
		 //printChar(lc_result);
		 return JsonWrapper.successWrapper(lc_result);
	 }
	
	 /**
     * 作废定义的工作流
     * @throws Exception
     */
	@RequestMapping({ "zuofei" })
	@ResponseBody
	 public HashMap<String, Object> zuofei(HttpServletRequest req,HttpServletResponse res,String ps_dm,String ps_sfzf){
		 char lc_result='0';
		 //String ps_dm=getParameter("ps_dm");
		 //String ps_sfzf=getParameter("ps_sfzf");
		 
		 //BaseDao dao=new BaseDao();
		 //WorkflowService wservice=new WorkflowService();
		 if("Y".equals(ps_sfzf)){//作废
			 //只有所有流程走完时才可以作废
			 if(sqlcService.checkProcessIsUseByMenuIdPsDm(ps_dm)==false){
				 lc_result='2';//正在使用
			 }else{
				 String ls_sql="update wf_process set ps_sfzf='"+ps_sfzf+"' where ps_dm='"+ps_dm+"'";
				 //if(dao.executeUpdate(ls_sql))
				 //	 lc_result='1';
				 service.exeSql(ls_sql);
				 lc_result='1';
			 }
		 }else{//取消作废
			 String ls_sql="update wf_process set ps_sfzf='"+ps_sfzf+"' where ps_dm='"+ps_dm+"'";
			 /*if(dao.executeUpdate(ls_sql))
				 lc_result='1';*/
			 service.exeSql(ls_sql);
			 lc_result='1';
		 }
		
		 //printChar(lc_result);
		 return JsonWrapper.successWrapper(lc_result);
	 }
	
	 /**
	  * 检查流程的状态
	  */
	@RequestMapping({ "checkState" })
	@ResponseBody
	 public HashMap<String, Object> checkState(HttpServletRequest req,HttpServletResponse res,String name) throws Exception{
		 char lc_result='0';
		 req.setCharacterEncoding("UTF-8");
	     //String name = getParameterEncode("name");
//      根据流程名称查找流程定义
    	WorkflowService wservice=new WorkflowService();
    	Object obj = service.findProcessByPsMc(name);
    	WfProcess bean=null;
    	bean=null==obj?null:(WfProcess)obj;
    	if(null!=bean){
    		String ps_dm=bean.getPsDm();
//   		System.out.println("update ps_dm="+ps_dm);
	   		//判断此流程当前是否已使用，正在使用时禁止更新
	   		if(shlogService.checkProcessIsUseByPsDm(ps_dm)){
	   			lc_result='1';
	   		}
    	}
		 //printChar(lc_result);
		 
		 return JsonWrapper.successWrapper(lc_result);
	 }
	 
	 /**
	  * 获取流程菜单树
	  * @return
	  * @throws Exception
	  */	 
	@RequestMapping({ "menu_process_bind" })
	@ResponseBody
	 public String menu_process_bind(HttpServletRequest req,HttpServletResponse res) throws Exception{
		//BtMenuService service = new BtMenuService();
		//List main_list = service.getShenpiMenuTreeData();
		 SysMenu m = new SysMenu();
		 List<SysMenu> main_list = menuService.listQuery(req, m, null);
//			组装成这样的数据格式，供页面树显示
//			[
//			{id:1981,pId:1791,name:"系统单位管理",nodedm:"411700",isParent:true,open:true},
//			{id:1982,pId:1981,name:"驻马店市市本级",nodedm:"411799",parentNodedm:"411700",isParent:false},
//			{id:1983,pId:1981,name:"驿城区",nodedm:"411702",parentNodedm:"411700",isParent:false},
//			{id:1984,pId:1981,name:"西平县",nodedm:"411721",parentNodedm:"411700",isParent:false},
//			{id:1993,pId:1981,name:"高新区",nodedm:"411740",parentNodedm:"411700",isParent:false}
//			]
		
		String root_id = "0";//信阳市的区域代码ID
		String root_dm = "0";//信阳市的区域代码
		StringBuffer sb = new StringBuffer("");
		sb.append("[{id:"+root_id+",pId:-1,name:\"审批申请业务\",nodedm:\""+root_dm+"\",isParent:true,open:true},");
		String menu_id = "";
		String menu_mc = "";
		SysMenu menu=null;
		if (null!=main_list && main_list.size()>0) {
			for (int i = 0; i < main_list.size(); i++) {//遍历一级行政区域
				menu=(SysMenu)main_list.get(i);
				menu_id = menu.getId() + "";
				menu_mc= menu.getName();

				//{id:1,pId:0,name:"中国移动通信集团河南有限公司驻马店分公司",nodedm:"1",parentNodedm:"0",isParent:false},
				sb.append("{id:"+ menu_id+ ",pId:"+ root_id+ ",name:\""+ menu_mc+ "\",nodedm:\""+ menu_id+ "\",parentNodedm:\""+ root_id+ "\",isParent:false},");
			}
		}

		String simpleData = sb.toString();
		if (simpleData.lastIndexOf(",") != -1)
			simpleData = simpleData.substring(0, simpleData.lastIndexOf(","));
		simpleData += "]";
//			System.out.println(simpleData);
		req.setAttribute("ShenPiMenuData", simpleData);
		return "wfcz_menu_process_bind";
	}
	 
	 /**
		 * 根据审批申请菜单，查询其绑定的工作流
		 * @throws Exception
		 */
	@RequestMapping({ "getProcessDataByMenuId" })
	@ResponseBody
	public void getProcessDataByMenuId(HttpServletRequest req,HttpServletResponse res,Page page ,String menu_id) throws Exception{
		
		String sql = "select a.ml_id,a.menu_id,a.ps_dm,b.ps_mc,to_char(ml_bdsj,'yyyy-mm-dd hh24:mi:ss') as ml_bdsj from wf_menu_process a,wf_process b where a.ps_dm=b.ps_dm and a.menu_id="+menu_id;
		Page p =  service.getEntityDaoInf().pageSqlQueryByVO(sql, page, new WfMenuProcesVo());
		printObj(req,res,p);
		//@SuppressWarnings("unchecked")
		//List<WfMenuProcesVo> lst = p.getData();
		//int page = 1;
		/*if (null != getParameter("page"))
			page = Integer.parseInt(getParameter("page"));

		int row = 20;
		if (null != getParameter("rows"))
			row = Integer.parseInt(getParameter("rows"));*/
		
	/*	String ls_sql="select a.ml_id,a.menu_id,a.ps_dm,b.ps_mc,to_char(ml_bdsj,'yyyy-mm-dd hh24:mi:ss') as ml_bdsj from wf_menu_process a,wf_process b where a.ps_dm=b.ps_dm and a.menu_id="+menu_id;
			JSONObject jsonObj = commonPageQuery(ls_sql,WfMenuProcess.class);
//			System.out.println(jsonObj.toString());
			printJsonStr(jsonObj.toString());*/
	}
	
	 
	 /**
		 * 根据审批申请菜单，查询其绑定的工作流
		 * @throws Exception
		 */
		/*@RequestMapping({ "getProcessDataByMenuId" })
		@ResponseBody
		public void getProcessDataByMenuId(HttpServletRequest req,HttpServletResponse res,String menu_id,Page p) throws Exception{
			String menu_id = getParameter("menu_id");
			int page = 1;
			if (null != getParameter("page"))
				page = Integer.parseInt(getParameter("page"));

			int row = 20;
			if (null != getParameter("rows"))
				row = Integer.parseInt(getParameter("rows"));
			
			String ls_sql="select a.ml_id,a.menu_id,a.ps_dm,b.ps_mc,to_char(ml_bdsj,'yyyy-mm-dd hh24:mi:ss') as ml_bdsj from wf_menu_process a,wf_process b where a.ps_dm=b.ps_dm and a.menu_id="+menu_id;
			//JSONObject jsonObj = commonPageQuery(ls_sql,WfMenuProcess.class);
//			System.out.println(jsonObj.toString());
			//printJsonStr(jsonObj.toString());
			Page pa = service.getEntityDaoInf().pageSqlQueryByVO(ls_sql,p,new WfMenuProcesVo());
			printObj(req,res,pa);
		}*/
		
		/**
		 *检查当前菜单是否已经绑定过有 流程 
		 */
		@RequestMapping({ "checkMenuHavedBindProcess" })
		@ResponseBody
		public void checkMenuHavedBindProcess(HttpServletRequest req,HttpServletResponse res,String menu_id){
			//char lc_result='0';
			//String menu_id= getParameter("menu_id");
			//BaseDao dao=new BaseDao();
		    String ls_sql="select count(ml_id) as totalCount from wf_menu_process where menu_id="+menu_id;
		    Object o = service.getUniqueResult(ls_sql);
		    if(o != null && Integer.parseInt(o.toString()) > 0)
		    	printChar(req,res,'1');
	    	 //判断此流程当前是否已使用，正在使用时禁止更新
	    	/*if(dao.getRecordCountBySql(ls_sql)>0){
	    		lc_result='1';
	    	}
			printChar(lc_result);*/
		}
		
		 /**
		  * 根据menu_id ps_dm检查流程的状态
		  */
		@RequestMapping({ "checkStateByMenuIdPsDm" })
		@ResponseBody
		 public void checkStateByMenuIdPsDm(HttpServletRequest req,HttpServletResponse res,String menu_id,String ps_dm) throws Exception{
		  /* char lc_result='0';
	       String menu_id= getParameter("menu_id");
	       String ps_dm=getParameter("ps_dm");
//	       根据流程名称查找流程定义
	     	WorkflowService service=new WorkflowService();
   		//判断此流程当前是否已使用，正在使用时禁止更新
   		if(service.checkProcessIsUseByMenuIdPsDm(menu_id,ps_dm)){
   			lc_result='1';
   		}
			 printChar(lc_result);
		*/
			if(nodeService.checkProcessIsUseByMenuIdPsDm(menu_id,ps_dm))
				printChar(req,res,'1');
			else
				printChar(req,res,'0');
		 }
		 
		 
		 /**
		  * 查询所有的流程
		 * @throws Exception 
		  */
		 @RequestMapping({ "queryAllProcess" })
		 @ResponseBody
		 public Page queryAllProcess(HttpServletRequest req,HttpServletResponse res,Page p,String psMc) throws Exception{
			/* int page = 1;
				if (null != getParameter("page"))
					page = Integer.parseInt(getParameter("page"));

				int row = 20;
				if (null != getParameter("rows"))
					row = Integer.parseInt(getParameter("rows"));*/
				
				String conditionStr="";
				//String psMc=getParameter("psMc");
				/*if(!"".equals(psMc)){
					conditionStr=" and ps_mc like '%"+psMc+"%' ";
				}*/
				if(psMc != null && psMc.length() > 0)
					conditionStr=" and ps_mc like '%"+psMc+"%' ";
				
				String ls_sql="select ps_id,ps_dm,ps_mc,ps_xml,ps_tjrid,ps_tjrxm,to_char(ps_tjsj,'yyyy-mm-dd hh24:mi:ss') as ps_tjsj from wf_process where 1=1 "+conditionStr+" order by ps_id desc";
				//JSONObject jsonObj = commonPageQuery(ls_sql,WfProcess.class);
				//printJsonStr(jsonObj.toString());
				//WfProcess
				//service.get
				//Page pp = service.getEntityDaoInf().page(ls_sql, p.getPageNo(), p.getPagesize(), vo, values)eSqlQuery(ls_sql, p, entity)
				WfProcess wp = new WfProcess();
				wp.setPsMc(psMc);
				return dataGridPage(req, p, wp);
		 }
		 
		 /**
		  * 把业务菜单 绑定 到流程
		  * @throws Exception
		  */
		 @RequestMapping({ "bind" })
		 @ResponseBody
		 public void bind(HttpServletRequest req,HttpServletResponse res,String menu_id,String ps_dm) throws Exception{
			 char lc_result='0';
		     //String menu_id= getParameter("menu_id");
		     //String ps_dm=getParameter("ps_dm");
		     //此流程中定义的有fork分支节点，请先为每个fork分支节点进行设置后再进行此绑定操作！
		     WorkflowService service=new WorkflowService();
		     if(nodeService.checkProcessForkNodeIsSetted(ps_dm)){
//			      根据流程名称查找流程定义
			     //BaseDao dao=new BaseDao();
			     String ls_sql="insert into wf_menu_process(menu_id,ps_dm)values("+menu_id+",'"+ps_dm+"')";
		    	 //判断此流程当前是否已使用，正在使用时禁止更新
			     menuProcessService.getEntityDaoInf().createSQLQuery(ls_sql).executeUpdate();
		    	/*if(dao.executeUpdate(ls_sql)){
		    		lc_result='1';
		    	}*/
			     lc_result='1';
		     }else{
		    	 lc_result='2';
		     }

			printChar(req,res,lc_result);
		 }
		 
		 /**
		  * 把业务的功能 绑定 到流程
		  * @throws Exception
		  */
		 @RequestMapping({ "bindYewu" })
		 @ResponseBody
		 public void bindYewu(HttpServletRequest req,HttpServletResponse res,String yp_id,String ps_dm) throws Exception{
			 char lc_result='0';
		     //String yp_id= getParameter("yp_id");
		     //String ps_dm=getParameter("ps_dm");
		     //此流程中定义的有fork分支节点，请先为每个fork分支节点进行设置后再进行此绑定操作！
		     //WorkflowService service=new WorkflowService();
		     if(nodeService.checkProcessForkNodeIsSetted(ps_dm)){
//			      根据流程名称查找流程定义
			     //BaseDao dao=new BaseDao();
			     String ls_sql="update wf_yewu_process set ps_dm='"+ps_dm+"',yp_bdsj=sysdate where yp_id="+yp_id;
		    	/*if(dao.executeUpdate(ls_sql)){
		    		lc_result='1';
		    	}*/
			    wfYewuProcessManageService.getEntityDaoInf().deleteBySql(ls_sql);
			    lc_result='1';
		     }else{
		    	 lc_result='2';
		     }

			printChar(req,res,lc_result);
		 }
		 
		 /**
		  * 解除菜单所绑定的工作流程
		  * @throws Exception
		  */
		 @RequestMapping({ "unbind" })
		 @ResponseBody
		 public void unbind(HttpServletRequest req,HttpServletResponse res,String menu_id,String ps_dm) throws Exception{
			 char lc_result='0';
		     //String menu_id= getParameter("menu_id");
		     //String ps_dm=getParameter("ps_dm");
//		      根据流程名称查找流程定义
		     //BaseDao dao=new BaseDao();
		     String ls_sql="delete from wf_menu_process where menu_id="+menu_id+" and ps_dm='"+ps_dm+"'";
	    	/*if(dao.executeUpdate(ls_sql)){
	    		lc_result='1';
	    	}*/
		     menuProcessService.getEntityDaoInf().deleteBySql(ls_sql);
		     lc_result='1';
			printChar(req,res,lc_result);
		 }
		 
		 /**
		  * 解除业务功能  所绑定的工作流程
		  * @throws Exception
		  */
		 @RequestMapping({ "unbindYewu" })
		 @ResponseBody
		 public void unbindYewu(HttpServletRequest req,HttpServletResponse res,String yp_id,String ps_dm) throws Exception{
			 char lc_result='0';
			 //String yp_id= getParameter("yp_id");
			 //String ps_dm= getParameter("ps_dm");
			 if(!"".equals(yp_id) && !"".equals(ps_dm)){
				 //WorkflowService service=new WorkflowService();
				 if(sqlcService.checkProcessIsUseByMenuIdPsDm(ps_dm)){
					lc_result = '2';
				 }else{
				     //BaseDao dao=new BaseDao();
				     String ls_sql="update wf_yewu_process set ps_dm='' where yp_id="+yp_id;
			    	/*if(dao.executeUpdate(ls_sql))
			    		lc_result='1';*/
				     sqlcService.getEntityDaoInf().deleteBySql(ls_sql);
				     lc_result='1';
				 }
			 }
			printChar(req,res,lc_result);
		 }
		 
		 
		 /**
		 * 获取所有的流程组装成树
		 * @return
		 * @throws Exception
		 */
		 @RequestMapping({ "node_role_admin" })
		 @ResponseBody
		public String node_role_admin(HttpServletRequest req,HttpServletResponse res) throws Exception {
			//生成单位树的数据
			//WorkflowService service = new WorkflowService();
			List main_list = service.getAllProcessTreeData();
			
			String root_id = "0";//信阳市的区域代码ID
			String root_dm = "0";//信阳市的区域代码
			StringBuffer sb = new StringBuffer("");
			sb.append("[{id:"+root_id+",pId:-1,name:\"系统流程列表\",nodedm:\""+root_dm+"\",isParent:true,open:true},");
			String ps_id = "";
			String ps_dm = "";
			String ps_mc = "";
			WfProcess wpBean=null;
			if (null!=main_list && main_list.size()>0) {
				for (int i = 0; i < main_list.size(); i++) {//遍历一级行政区域
					wpBean=(WfProcess)main_list.get(i);
					ps_id=dowithNull(wpBean.getId());
					ps_dm = dowithNull(wpBean.getPsDm());
					ps_mc = dowithNull(wpBean.getPsMc());

					//{id:1,pId:0,name:"中国移动通信集团河南有限公司驻马店分公司",nodedm:"1",parentNodedm:"0",isParent:false},
					sb.append("{id:"+ ps_id+ ",pId:"+ root_id+ ",name:\""+ ps_mc+ "\",nodedm:\""+ dowithNull(ps_dm)+ "\",parentNodedm:\""+ root_id+ "\",isParent:false},");
				}
			}

			String simpleData = sb.toString();
			if (simpleData.lastIndexOf(",") != -1)
				simpleData = simpleData.substring(0, simpleData.lastIndexOf(","));
			simpleData += "]";
//				System.out.println(simpleData);
			//setAttribute("wfProcessData", simpleData);
			req.setAttribute("wfProcessData", simpleData);
			return "wfcz_node_role_admin";
		}
		 

			/**
			 * 根据ps_id 取此流程的 所有节点 树 的数据
			 * @throws Exception
			 */
		 @RequestMapping({ "getNodeTreeDatabyPsId" })
		 @ResponseBody
			public void getNodeTreeDatabyPsId(HttpServletRequest req,HttpServletResponse res,String ps_id){
		          List main_list =null;
		          String simpleData="";
		          if("0".equals(ps_id) || "".equals(ps_id)){
		          	simpleData="[]";
//		          	simpleData="[{id:\"0\",pId:-1,name:\"单位部门列表\",isParent:false,open:true}]";
		          }else{
		        	  //WorkflowService service = new WorkflowService();
		        	  Object obj=null;
		        	  WfProcess bean=null;
		        	  String ps_dm="";
		        	  obj= service.findProcessByPsId(ps_id);
		        	  bean=null==obj?null:(WfProcess)obj;
		        	  if(null!=bean)
		        		  ps_dm=bean.getPsDm();
		        	 //根据ps_dm获取所有节点
		          	 main_list= nodeService.getNodeTreeData(ps_dm);
		          	  
		          	 String root_id = "0";//信阳市的区域代码ID
					 StringBuffer sb = new StringBuffer("");
					 sb.append("[{id:"+root_id+",pId:-1,psDm:\"0\",name:\"流程节点列表\",isParent:true,open:true},");
					 String node_id = "";
					 String node_name = "";
					 String node_ps_dm="";
					 String node_type="";
					 WfNode nodeBean=null;
					 if (null!=main_list && main_list.size()>0) {
						for (int i = 0; i <main_list.size(); i++) {//遍历一级行政区域
							nodeBean=(WfNode)main_list.get(i);
							node_id = dowithNull(nodeBean.getNodeId());
							node_name = dowithNull(nodeBean.getNodeName());
							node_ps_dm=dowithNull(nodeBean.getPsDm());
							node_type=dowithNull(nodeBean.getNodeType());
							//{id:1,pId:0,name:"中国移动通信集团河南有限公司驻马店分公司",nodedm:"1",parentNodedm:"0",isParent:false},
							sb.append("{id:"+node_id+",pId:"+ root_id+ ",psDm:\""+node_ps_dm+"\",nodeType:\""+ node_type+ "\",name:\""+ node_name+ "\",parentNodedm:\""+ root_id+ "\",isParent:false},");
						}
					 }
			      	
			      	 simpleData=sb.toString();
			         if(simpleData.lastIndexOf(",")!=-1)
			        	simpleData=simpleData.substring(0,simpleData.lastIndexOf(","));
			        simpleData+="]";
		          }
//		          System.out.println("获取所有的node节点"+simpleData);
		         printJsonStr(req,res,simpleData);
			}
		 
		 /**
			 * 根据node_id,ps_dm获取其绑定的角色
		 * @throws Exception 
			 */
		 @RequestMapping({ "getRoleDataByNodeIdPsDm" })
		 @ResponseBody
			public Page getRoleDataByNodeIdPsDm(HttpServletRequest req,HttpServletResponse res,Page p,String node_id,String ps_dm) throws Exception{
				if(!"".equals(node_id) && !"".equals(ps_dm)){
					//WorkflowService service = new WorkflowService();
					//String ls_sql="select distinct a.nr_id,a.ps_dm,a.role_id,a.node_id,b.role_name,c.dep_name,d.node_sx,d.node_url from wf_node_role a,bt_role b,bt_depart c,wf_node d where a.role_id=b.role_id and b.dep_id=c.dep_id and a.ps_dm=d.ps_dm and a.node_id=d.node_id and a.node_id="+node_id+" and a.ps_dm='"+ps_dm+"' order by nr_id desc";
//					System.out.println(ls_sql);
					Page page = wfNodeRoleManageService.getRoleDataByNodeIdPsDm(p, node_id, ps_dm);
					//JSONObject jsonObj = commonPageQuery(ls_sql,WfNodeRole.class);
//					System.out.println(jsonObj.toString());
					//printJsonStr(jsonObj.toString());
					return page;
				}
				return p;
			}
		 
		 /**
			 * 获取系统所有的角色权限：部门名称---角色名称,除去已经绑定的
			 */
		 @RequestMapping({ "queryAllRoleData" })
		 @ResponseBody
			public void queryAllRoleData(HttpServletRequest req,HttpServletResponse res,Page p,String node_id,String ps_dm){
				//String node_id =getParameter("node_id");
				//String ps_dm=getParameter("ps_dm");
				/*if(!"".equals(node_id) && !"".equals(ps_dm)){//除去已经绑定的
					String ls_sql="select a.dep_id,dep_name,b.role_id,b.role_name from bt_depart a,bt_role b where a.dep_id=b.dep_id and b.role_id not in(select role_id from wf_node_role where ps_dm='"+ps_dm+"' and node_id="+node_id+")";
					JSONObject jsonObj = commonPageQuery(ls_sql,WfNodeRole.class);
					printJsonStr(jsonObj.toString());
				}
				if("".equals(node_id) || "".equals(ps_dm)){
					String ls_sql="select a.dep_id,dep_name,b.role_id,b.role_name from bt_depart a,bt_role b where a.dep_id=b.dep_id";
					JSONObject jsonObj = commonPageQuery(ls_sql,WfNodeRole.class);
					printJsonStr(jsonObj.toString());
				}*/
			}
		 
		 /**
			 * 流程节点 绑定 角色
			 */
		 @RequestMapping({ "nodeBindRole" })
		 @ResponseBody
			public void nodeBindRole(HttpServletRequest req,HttpServletResponse res,Page p,String node_id,String ps_dm,String bind_role_ids){
				char lc_result='0';
				//String node_id =getParameter("node_id");
				//String ps_dm=getParameter("ps_dm");
				//String bind_role_ids=getParameter("bind_role_ids");
				if(!"".equals(bind_role_ids) && !"".equals(node_id) && !"".equals(ps_dm)){
					//BaseDao dao=new BaseDao();
					if(bind_role_ids.indexOf(":")>0){//选择多个角色
						String[] role_id_arr=bind_role_ids.split(":");
						String[] sqlArr=new String[role_id_arr.length];
						for(int i=0;i<role_id_arr.length;i++){
							sqlArr[i]="insert into wf_node_role(ps_dm,node_id,role_id) values('"+ps_dm+"',"+node_id+","+role_id_arr[i]+")";
						}
						wfNodeRoleManageService.batchExeSql(sqlArr);
						//if(dao.executeBatUpdate(sqlArr))
						//	lc_result='1';
						lc_result='1';
					}else{//只选一个角色
						String ls_sql="insert into wf_node_role(ps_dm,node_id,role_id) values('"+ps_dm+"',"+node_id+","+bind_role_ids+")";
						/*if(dao.executeUpdate(ls_sql))
							lc_result='1';*/
						wfNodeRoleManageService.getEntityDaoInf().deleteBySql(ls_sql);
					}
				}
				printChar(req,res,lc_result);
			}
		 

			/**
			 * 更新节点时限
			 */
		 @RequestMapping({ "updateNodeSx" })
		 @ResponseBody
			public void updateNodeSx(HttpServletRequest req,HttpServletResponse res,String node_id,String ps_dm,String node_sx){
				char lc_result='0';
				//String node_id =getParameter("node_id");
				//String ps_dm=getParameter("ps_dm");
				//String node_sx=getParameter("node_sx");
				if(!"".equals(node_sx) && !"".equals(node_id) && !"".equals(ps_dm)){
					String ls_sql="update wf_node set node_sx='"+node_sx+"' where ps_dm='"+ps_dm+"' and node_id="+node_id;
					/*BaseDao dao=new BaseDao();
					if(dao.executeUpdate(ls_sql))
						lc_result='1';*/
					nodeService.getEntityDaoInf().deleteBySql(ls_sql);
					lc_result='1';
				}
				printChar(req,res,lc_result);
			}
		 
			/**
			 * 更新节点URL
			 */
		 @RequestMapping({ "updateNodeUrl" })
		 @ResponseBody
			public void updateNodeUrl(HttpServletRequest req,HttpServletResponse res,String node_id,String ps_dm,String node_url){
				char lc_result='0';
				//String node_id =getParameter("node_id");
				//String ps_dm=getParameter("ps_dm");
				//String node_url=getParameter("node_url");
				if(!"".equals(node_url) && !"".equals(node_id) && !"".equals(ps_dm)){
					String ls_sql="update wf_node set node_url='"+node_url+"' where ps_dm='"+ps_dm+"' and node_id="+node_id;
					/*BaseDao dao=new BaseDao();
					if(dao.executeUpdate(ls_sql))
						lc_result='1';*/
					nodeService.getEntityDaoInf().deleteBySql(ls_sql);
					lc_result='1';
				}
				printChar(req,res,lc_result);
			}
		 
		 /**
			 * 更新fork设置
			 */
		 @RequestMapping({ "updateForkSet" })
		 @ResponseBody
			public void updateForkSet(HttpServletRequest req,HttpServletResponse res,String node_id,String ps_dm,String fork_desc[],
					String fork_desc_val[],String fork_to_node_id[]){
				char lc_result='0';
				/*String node_id =getParameter("node_id");
				String ps_dm=getParameter("ps_dm");
				String[] fork_desc=getParameters("fork_desc");
				String[] fork_desc_val=getParameters("fork_desc_val");
				String[] fork_to_node_id=getParameters("fork_to_node_id");*/
				if(fork_desc.length>0){
					List<String> dataList=new ArrayList();
					//先删除再插入，实现更新
					dataList.add("delete from wf_forkset where ps_dm='"+ps_dm+"' and node_id="+node_id);
					for(int i=0;i<fork_desc.length;i++){
						dataList.add("insert into wf_forkset(ps_dm,node_id,fk_desc,fk_desc_val,fk_to_node_id) values('"+ps_dm+"',"+node_id+",'"+fork_desc[i]+"','"+fork_desc_val[i]+"',"+fork_to_node_id[i]+")");
					}
					/*BaseDao dao=new BaseDao();
					if(dao.executeBatUpdate(dataList.toArray(new String[dataList.size()])))
						lc_result='1';*/
					wfForksetManageService.batchExeSql(dataList.toArray(new String[dataList.size()]));
				}
				printChar(req,res,lc_result);
			}
		 
		 /**
			 * 解除绑定前，检查节点已经绑定的角色的数量，至少应该保留一条绑定记录
			 */
		 @RequestMapping({ "unbindNodeRole" })
		 @ResponseBody
			public void unbindNodeRole(HttpServletRequest req,HttpServletResponse res,String node_id,String ps_dm,String role_id){
				char lc_result='0';
				/*String node_id =getParameter("node_id");
				String ps_dm=getParameter("ps_dm");
				String role_id=getParameter("role_id");*/
				if(!"".equals(node_id) && !"".equals(ps_dm)){
					/*WorkflowService service=new WorkflowService();
					int bind_count=service.getNodeBindRoleShuLiang(ps_dm,node_id);*/
					int bind_count = wfNodeRoleManageService.getNodeBindRoleShuLiang(ps_dm, node_id);
					if(bind_count<=1){//当前绑定剩下最后一条,不能解除绑定
						lc_result='2';
					}else{
						String ls_sql="delete wf_node_role where ps_dm='"+ps_dm+"' and node_id="+node_id+" and role_id="+role_id;
						/*BaseDao dao=new BaseDao();
						if(dao.executeUpdate(ls_sql))
							lc_result='1';*/
						wfNodeRoleManageService.getEntityDaoInf().deleteBySql(ls_sql);
						lc_result='1';
					}
				}
				printChar(req,res,lc_result);
			}
		 
		 @RequestMapping({ "workday_admin" })
		 @ResponseBody
		 public Page workday_admin(HttpServletRequest req,HttpServletResponse res,Page p) throws Exception {
				String ls_sql="select distinct wd_year from wf_workday";
				//JSONObject jsonObj = commonPageQuery(ls_sql,WfWorkday.class);
//				System.out.println(jsonObj.toString());
			 //WfNodeRoleVO
				Page pp = wfWorkdayManageService.getEntityDaoInf().pageSQLQuery(ls_sql, p.getPageNo(),p.getPagesize(),WfNodeRoleVO.class);
				//printJsonStr(jsonObj.toString());
				return pp;
			}
		 
		 /**
			 * 查看设置的某年的工作日
			 * @return
			 */
		 @RequestMapping({ "work_day_view" })
		 //@ResponseBody
			public String work_day_view(HttpServletRequest req,HttpServletResponse res,String getCurYear,String yearfirstDay,String yearlastDay){
				/*String getCurYear=getParameter("getCurYear");//2013
				String yearfirstDay=getParameter("yearfirstDay");//2013-01-01
				String yearlastDay=getParameter("yearlastDay");//2013-12-31
*/				
//				System.out.println(yearlastDay);
//				System.out.println(yearfirstDay);
//				System.out.println(getCurYear);
				//WorkflowService service=new WorkflowService();
				String workday=wfWorkdayManageService.getWorkdayByYear(getCurYear);
				//setAttribute("workdays",workday);
				req.setAttribute("workdays",workday);
				//从数据库中提取此年的工作日
				//setAttribute("getCurYear",getCurYear);
				req.setAttribute("getCurYear",getCurYear);
				return "workflowcz/wfcz_work_day_view";
			}
		 
		 /**
			 * 查看设置的某年的工作日
			 * @return
			 */
		 @RequestMapping({ "work_day" })
			public String work_day(HttpServletRequest req,HttpServletResponse res,String getCurYear,String yearfirstDay,String yearlastDay){
				/*String getCurYear=getParameter("getCurYear");//2013
				String yearfirstDay=getParameter("yearfirstDay");//2013-01-01
				String yearlastDay=getParameter("yearlastDay");//2013-12-31
*/				
//				System.out.println(yearlastDay);
//				System.out.println(yearfirstDay);
//				System.out.println(getCurYear);
				//从数据库中提取此年的工作日
				//setAttribute("getCurYear",getCurYear);
				req.setAttribute("getCurYear",getCurYear);
				return "workflowcz/wfcz_work_day";
			}
		 

			/**
			 * 某年的工作日修改更新
			 * @return
			 */
		 @RequestMapping({ "work_day_update" })
			public String work_day_update(HttpServletRequest req,HttpServletResponse res,String getCurYear,String yearfirstDay,String yearlastDay){
				/*String getCurYear=getParameter("getCurYear");//2013
				String yearfirstDay=getParameter("yearfirstDay");//2013-01-01
				String yearlastDay=getParameter("yearlastDay");//2013-12-31
*/				
//				System.out.println(yearlastDay);
//				System.out.println(yearfirstDay);
				//System.out.println(getCurYear);
				//WorkflowService service=new WorkflowService();
				String workday=wfWorkdayManageService.getWorkdayByYear(getCurYear);
				//setAttribute("workdays",workday);
				//从数据库中提取此年的工作日
				//setAttribute("getCurYear",getCurYear);
				req.setAttribute("workdays",workday);
				req.setAttribute("getCurYear",getCurYear);
				return "workflowcz/wfcz_work_day_update";
			}
		 
		 @RequestMapping({ "workdaySave" })
			public void workdaySave(HttpServletRequest req,HttpServletResponse res,String curr_year,String yearfirstDay,String yearlastDay,String new_workday,
						String restOneToFive,String workSixToSun){
				char lc_result='0';
				/*String curr_year=getParameter("curr_year");
				String yearfirstDay=getParameter("yearfirstDay");//2013-01-01
				String yearlastDay=getParameter("yearlastDay");//2013-12-31
				String workday=getParameter("new_workday");//getParameter("workday");//
*/			
				String workday = new_workday;
				if(workday.indexOf("end")>0)
					workday=workday.substring(0,workday.indexOf("end"));
				//String restOneToFive=getParameter("restOneToFive");//
				//String workSixToSun=getParameter("workSixToSun");//
				
//				System.out.println(yearlastDay);
//				System.out.println(yearfirstDay);
//				System.out.println("工作日:"+workday);//以,分隔的多个日期
//				System.out.println("restOneToFive:"+restOneToFive);
//				System.out.println("workSixToSun:"+workSixToSun);
//				根据年份更新工作日
				if(!"".equals(workday) && workday.indexOf(",")>0){
					//WorkflowService service=new WorkflowService();
					//BaseDao dao=new BaseDao();
					if(wfWorkdayManageService.checkWorkdayDataIsExist(curr_year)){//存在，先删除，再插入
						String[] dataArr=workday.split(",");
						List<String> dataList=new ArrayList();
						dataList.add("delete wf_workday where wd_year='"+curr_year+"'");
						for(int i=0;i<dataArr.length;i++){
							dataList.add("insert into wf_workday(wd_year,wd_day)values('"+curr_year+"','"+dataArr[i]+"')");
						}
						//if(dao.executeBatUpdate(dataList.toArray(new String[dataList.size()])));
						wfWorkdayManageService.batchExeSql(dataList.toArray(new String[dataList.size()]));
							lc_result='1';
					}else{//不存在就插入
						String[] sqlArr=workday.split(",");
						for(int i=0;i<sqlArr.length;i++){
							sqlArr[i]="insert into wf_workday(wd_year,wd_day)values('"+curr_year+"','"+sqlArr[i]+"')";
						}
						//if(dao.executeBatUpdate(sqlArr))
						wfWorkdayManageService.batchExeSql(sqlArr);
							lc_result='1';
					}
				}
				printChar(req,res,lc_result);
			}
		 
		 @RequestMapping({ "wf_yewu_process_admin" })
		 @ResponseBody
		 public Page wf_yewu_process_admin(HttpServletRequest req,HttpServletResponse res,Page p){
			 //WfProcessVO
				String ls_sql="(select a.*,b.ps_mc from wf_yewu_process a,wf_process b where a.ps_dm=b.ps_dm and a.ps_dm is not null)";
				ls_sql+=" union ";
				ls_sql+="(select a.*,'' as ps_mc from wf_yewu_process a where ps_dm is null)";
//				System.out.println(ls_sql);
				/*JSONObject jsonObj = commonPageQuery(ls_sql,WfYewuProcess.class);
				printJsonStr(jsonObj.toString());*/
				Page pp = wfYewuProcessManageService.getEntityDaoInf().pageSQLQuery(ls_sql, p.getPageNo(), p.getPagesize(), WfProcessVO.class);
				return pp;
			}
		 
		//保存新添加的业务wf_yewu_process
		 @RequestMapping({ "saveYewuProcess" })
		 @ResponseBody
			public void saveYewuProcess(HttpServletRequest req,HttpServletResponse res,String yewuMc,String yewuPyzm){
				char lc_result = '0';
				//String yewu_mc=dowithNull(getParameter("yewuMc"));
				//String yewu_pyzm=dowithNull(getParameter("yewuPyzm"));
				if(!"".equals(yewuMc) && !"".equals(yewuPyzm)){
					WorkflowService service=new WorkflowService();
					//if(service.checkYewuProcessPyzmIsExist(yewuPyzm)){//检查yewu_pyzm是否存在
					if(wfYewuProcessManageService.checkYewuProcessPyzmIsExist(yewuPyzm)){
						lc_result = '2';
					}else{
						String ls_sql = "insert into wf_yewu_process (yewu_mc,yewu_pyzm) values('"+yewuMc+"','"+yewuPyzm+"')";
						//if(new BaseDao().executeUpdate(ls_sql))
						wfYewuProcessManageService.getEntityDaoInf().deleteBySql(ls_sql);
							lc_result='1';
					}
				}
				printChar(req,res,lc_result);
			}
		 
		//启用/禁用 
		 @RequestMapping({ "qiyong" })
		 @ResponseBody
			public void qiyong(HttpServletRequest req,HttpServletResponse res,String yp_id,String state,String ps_dm) throws Exception{
				char lc_result = '0';
				/*String yp_id=dowithNull(getParameter("yp_id"));
				String state = dowithNull(getParameter("state"));
				String ps_dm=dowithNull(getParameter("ps_dm"));*/
				if("1".equals(state))
					state="Y";
				if("2".equals(state))
					state="N";
				
				if(!"".equals(yp_id)){
					if("N".equals(state)){//在停用时要判断当前流程是否在使用中，使用中时不能停止
						//WorkflowService service=new WorkflowService();
						//if(service.checkProcessIsUseByMenuIdPsDm(ps_dm)){
						if(sqlcService.checkProcessIsUseByMenuIdPsDm(ps_dm)){
							lc_result='2';
						}else{
							String ls_sql = "update wf_yewu_process set yewu_sfqygzl='" + state + "' where yp_id=" + yp_id;
							//System.out.println("禁用单位"+ls_sql);
							//if(new BaseDao().executeUpdate(ls_sql))
							wfYewuProcessManageService.getEntityDaoInf().deleteBySql(ls_sql);
								lc_result='1';
						}
					}else{
						String ls_sql = "update wf_yewu_process set yewu_sfqygzl='" + state + "' where yp_id=" + yp_id;
						//System.out.println("启用"+ls_sql);
						//if(new BaseDao().executeUpdate(ls_sql))
						wfYewuProcessManageService.getEntityDaoInf().deleteBySql(ls_sql);
							lc_result='1';
					}
					
				}
				printChar(req,res,lc_result);
			}
		 
		//工作流节点配置
		 @RequestMapping({ "config_admin" })
		 @ResponseBody
			public Page config_admin(HttpServletRequest req,HttpServletResponse res,Page p){
				String ls_sql="select * from wf_daima where dm_dmlb='WORKFLOW_YEWU_NODE_CONFIG'";
				Page pp = wfDaimaManageService.getEntityDaoInf().pageSQLQuery(ls_sql, p.getPageNo(), p.getPagesize());
				/*JSONObject jsonObj = commonPageQuery(ls_sql,WfDaima.class);
				printJsonStr(jsonObj.toString());*/
				return pp;
			}
		 
		//保存工作流节点配置
		 @RequestMapping({ "config_save" })
		 @ResponseBody
			public void config_save(HttpServletRequest req,HttpServletResponse res,String dmDmz,String dmDmmc,String nodePyzm){
				char lc_result='0';
				/*String dmDmz=getParameter("dmDmz");
				String dmDmmc=getParameter("dmDmmc");
				String nodePyzm=getParameter("nodePyzm");*/
				if(!"".equals(dmDmz) && !"".equals(dmDmmc) && !"".equals(nodePyzm)){
					//BaseDao dao=new BaseDao();
					String ls_sql="select count(*) as totalCount from bs_daima where dm_dmlb='WORKFLOW_YEWU_NODE_CONFIG' and dm_dmz='"+ dmDmz+"'";
					//if(dao.getRecordCountBySql(ls_sql)>0){
					Object o = wfDaimaManageService.getEntityDaoInf().createSQLQuery(ls_sql).uniqueResult();
					if(Integer.parseInt(o.toString()) > 0){
						lc_result='2';
					}else{
						ls_sql="insert into bs_daima(dm_dmlb,dm_dmz,dm_dmmc,dm_dmmcjx) values('WORKFLOW_YEWU_NODE_CONFIG','"+dmDmz+"','"+dmDmmc+"','"+nodePyzm+"')";
						//if(dao.executeUpdate(ls_sql))
						wfDaimaManageService.getEntityDaoInf().deleteBySql(ls_sql);
							lc_result='1';
					}
				}
				printChar(req,res,lc_result);
			}
		 
		//更新工作流节点配置
		 @RequestMapping({ "config_update" })
		 @ResponseBody
			public void config_update(HttpServletRequest req,HttpServletResponse res,String dm_id,String dmDmz,String dmDmmc,String nodePyzm){
				char lc_result='0';
				/*String dmId=getParameter("dm_id");
				String dmDmz=getParameter("dmDmz");
				String dmDmmc=getParameter("dmDmmc");
				String nodePyzm=getParameter("nodePyzm");*/
				if(!"".equals(dm_id) && !"".equals(dmDmz) && !"".equals(dmDmmc) && !"".equals(nodePyzm)){
					//BaseDao dao=new BaseDao();
					String ls_sql="select count(*) as totalCount from wf_daima where dm_dmlb='WORKFLOW_YEWU_NODE_CONFIG' and dm_dmz='"+ dmDmz+"' and dm_id!="+dm_id;
					//if(dao.getRecordCountBySql(ls_sql)>0){
					if(Integer.parseInt(wfDaimaManageService.getEntityDaoInf().createSQLQuery(ls_sql).toString()) > 0){
						lc_result='2';
					}else{
						ls_sql="update bs_daima set dm_dmz='"+dmDmz+"',dm_dmmc='"+dmDmmc+"',dm_dmmcjx='"+nodePyzm+"' where dm_id="+dm_id;
						//if(new BaseDao().executeUpdate(ls_sql))
						wfDaimaManageService.getEntityDaoInf().deleteBySql(ls_sql);
							lc_result='1';
					}
					
				}
				printChar(req,res,lc_result);
			}
		 
		 /**
			 * @TODO: 查询当前工作流节点前的节点
			 * @author: zhaichunlei
			 & @DATE : 2015-9-12
			 */
			 @RequestMapping({ "queryWorkFlow" })
			 @ResponseBody
			public void queryWorkFlow(HttpServletRequest req,HttpServletResponse res,String sqSqbh){
				try {
					//String sqSqbh = this.getParameter("sqSqbh");
					sqSqbh = URLDecoder.decode(sqSqbh, "utf-8");
					
					//com.xml.service.yewu.spsc.BsSpscService service = new com.xml.service.yewu.spsc.BsSpscService();
					List list = sqlcService.queryWorkFlow_Java(sqSqbh);
					JSONArray jsonArr = JSONArray.fromObject(list);
					String jsonStr = jsonArr.toString();
					printJsonStr(req,res,jsonStr);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/**
			 * 工作流打回:把当前工作流，打回到 前面的某一步 ../../workflowcz/wfcz_workflow_dahui.action
			 */
			 @RequestMapping({ "workflow_dahui" })
			 @ResponseBody
			public void workflow_dahui(HttpServletRequest req,HttpServletResponse res,String sqSqbh,String tuihuiYy,String nodeId){
				char lc_result='0';
				//BtUser user=null;
			    //user=(BtUser)getSessionAttribute("loginUser");
				SysUser user = SessionUtils.getUser(req);
			    
				//String sq_sqbh=getParameterEncode("sqSqbh");//申请编号
				//String tuihuiYy=getParameterEncode("tuihuiYy");//打回原因
				//String dh_node_id=getParameter("nodeId");//打回到的节点ID
				
				//BaseDao dao=new BaseDao();
				String max_ver_add_one="";
				//根据申请编号，查找ps_dm
				String sql = "select ps_dm from WF_SQLC where sq_sqbh='"+sqSqbh+"'";
				//String ps_dm = dao.getUniqueValue(sql).toString();
				WfSqlc sq  = sqlcService.getEntityDaoInf().findUniqueBy("sqSqbh", sqSqbh);
				String ps_dm = sq.getPsDm();
				
				//max_ver_add_one=dao.getUniqueValue("select F_GET_OSVER_by_sqbh('"+ sqSqbh +"')+1 from dual").toString();  //取版本号
				sql  = "select f_get_osver_by_sqbh('"+ sqSqbh +"')+1 "; //
				max_ver_add_one = service.getUniqueResult(sql).toString();
				
				String[] sqlArr=new String[4];
				//更新工作流节点当前位置
				sqlArr[0]="update wf_sqlc set SL_CURR_POSTION='"+nodeId+"',os_ver='"+max_ver_add_one+"' where ps_dm='"+ps_dm+"' and sq_sqbh='"+sqSqbh+"'";
				//把打回到的节点 以后的 记录 更新为无效
				sqlArr[1]="update wf_shlog set LOG_TZJD='0' where ps_dm='"+ps_dm+"' and sq_sqbh='"+sqSqbh+"' and node_id>"+nodeId+" and node_pyzm=F_GET_NODEPYZM_BY_PSDM_NODEID('"+ps_dm+"','"+nodeId+"')";
				//插入打回日志
				sqlArr[2]="insert into wf_shlog select * from wf_shlog where log_id in (select max(log_id) from wf_shlog where SQ_SQBH='"+sqSqbh+"')";
				sqlArr[3]="update wf_shlog set log_shbz='打回',LOG_DHYY='"+tuihuiYy+"',LOG_DHSJ=sysdate,LOG_DHR_USER_ID="+user.getId()+",LOG_DH_PS_DM='"+ps_dm+"',LOG_DH_NODE_ID="+nodeId+",os_ver='"+max_ver_add_one+"' where SQ_SQBH='"+sqSqbh+"'";
				//if(dao.executeBatUpdate(sqlArr))
				sqlcService.batchExeSql(sqlArr);
					lc_result='1';
				printChar(req,res,lc_result);
			}
}
