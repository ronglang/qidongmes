package com.css.common.web.workflow.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.JDOMException;

import com.css.common.util.PropertiesHelper;
import com.css.common.util.SeparatorUtil;
import com.css.common.util.workflow.PathUtil;
import com.css.common.util.workflow.SimpleXMLWorkShop;
import com.css.common.util.workflow.SingleIOFilenameFilter;
import com.css.common.util.workflow.domain.Process;
import com.css.common.util.workflow.domain.ProcessResult;
import com.css.common.util.workflow.helper.IWorkflowService;


public class WorkflowService   implements IWorkflowService{
	String db_config_properties_name="config.properties";
	
	private String webAppRootKey;
    private String baseDirName;
    //private String defaultFileName;
    private String fileType;
    
    private String webAppRoot;
    
    //@Resource(name = "sysAreaManageDAO")
	//private SysMenuManageDAO dao;
    //@Resource(name = "wfNodeManageDAO")
   // private WfNodeManageDAO nodeDao;
    //@Resource(name="wfNodeTransManageDAO")
    //private WfNodeTransManageDAO nodeTransDao;
    //@Resource(name="wfForksetManageDAO")
   //private WfForksetManageDAO forkDao;
    //@Resource(name="wfMenuProcessManageDAO")
   // private WfMenuProcessManageDAO menuProcessDao;
    //@Resource(name="wfShlogManageDAO")
   // private WfShlogManageDAO shlogDao;
    //@Resource(name="wfSqlcManageDAO")
    //private WfSqlcManageDAO sqlcDao;
   // @Resource(name="wfYewuProcessManageDAO")
   // private WfYewuProcessManageDAO yewuProcess;
    //@Resource(name="wfProcessManageDAO")
    //private WfProcessManageDAO processDao;
    //@Resource(name="wfNodeRoleManageDAO")
    //private WfNodeRoleManageDAO nodeRoleDao;
    //@Resource(name="wfWorkdayManageDAO")
    //private WfWorkdayManageDAO workdayDao;
    

    public WorkflowService(){
    	//xiorkflow.root
    	webAppRootKey=PropertiesHelper.getString(db_config_properties_name,"WEB_APP_ROOT_KEY");
    	//processes
    	baseDirName=PropertiesHelper.getString(db_config_properties_name,"BASE_DIR_NAME");
    	//default
       // defaultFileName=PropertiesHelper.getString(db_config_properties_name,"DEFAULT_FILE_NAME");
        //xml
        fileType=PropertiesHelper.getString(db_config_properties_name,"FILE_TYPE");
        
//        System.out.println(webAppRootKey);
        webAppRoot=System.getProperty(webAppRootKey);
//        System.out.println(webAppRoot);
    }
    
    public static void main(String[] args) {
    	WorkflowService service=new WorkflowService();
//    	ProcessResult bean=service.getProcessByName("default");
//    	System.out.println("getProcess:"+bean.getProcess().getName());
//    	System.out.println("==================");
//    	service.listProcess();
	}
    

    
    /**
     * 列出指定上当下所有定义的XML文件 工作流
     * @return
     */
    public List listProcess() {
//    	System.out.println(webAppRoot);
//    	System.out.println(baseDirName);
        File baseDir = new File(webAppRoot,baseDirName);
        File[] files = baseDir.listFiles(new SingleIOFilenameFilter(fileType));

        List list = new ArrayList();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            System.out.println(file.getName());
            list.add(file);
        }
        return list;
    }
    
    /**
     * ������ ��ȡ���̶���,���ڲ鿴������
     * @param processName ��Ҫ��.xml��׺
     * @return
     */
	public ProcessResult getProcessByName(String processName,String projectDeployPath){
		ProcessResult bean= new ProcessResult();
//      File baseDir = new File(webAppRoot,baseDirName);
//      File file = new File(baseDir, processName+ PathUtil.SEPARATOR_FORMAT+fileType);
        File file = new File(projectDeployPath+SeparatorUtil.getFileSeparator()+baseDirName, processName+ PathUtil.SEPARATOR_FORMAT +fileType);
//      System.out.println(projectDeployPath+SeparatorUtil.getFileSeparator()+baseDirName+ PathUtil.SEPARATOR_FORMAT +fileType);
      
        if (!file.exists()) {
            bean.setStatus(FILE_NOT_FOUND);
            return bean;
        }

        Process process = new Process();
        process.setName(processName);
        try {
            process.setDoc(SimpleXMLWorkShop.file2Doc(file));
        } catch (IOException e) {
            bean.setStatus(IO_ERROR);
            bean.setMes(e.getMessage());
            process = null;
        } catch (JDOMException e) {
            bean.setStatus(XML_PARSER_ERROR);
            bean.setMes(e.getMessage());
            process = null;
        }

        bean.setProcess(process);
		return bean;
	}
	
	/**
	 * ����������̶��嵽ָ��Ŀ¼
	 * @param process
	 * @return
	 */
	 public ProcessResult addProcess(Process process,String projectDeployPath) {
        ProcessResult bean= new ProcessResult();

//        File baseDir = new File(webAppRoot,baseDirName);
        File file = new File(projectDeployPath+SeparatorUtil.getFileSeparator()+baseDirName, process.getName()+ PathUtil.SEPARATOR_FORMAT +fileType);
//       System.out.println(projectDeployPath+SeparatorUtil.getFileSeparator()+baseDirName+ PathUtil.SEPARATOR_FORMAT +fileType);
       
        if (file.exists()) {
        	bean.setStatus(FILE_EXIST);
            return bean;
        }

        Document doc = process.getDoc();
        try {
            SimpleXMLWorkShop.outputXML(doc, file);
        } catch (FileNotFoundException e) {
        	bean.setStatus(FILE_NOT_FOUND);
        	bean.setMes(e.getMessage());
            return bean;
        } catch (IOException e) {
        	bean.setStatus(IO_ERROR);
        	bean.setMes(e.getMessage());
            return bean;
        }
        
        bean.setStatus(SUCCESS);
        return bean;
	}
	 
	 /**
	 * ���¹���������
	 * @param process
	 * @return
	 */
    public ProcessResult updateProcess(Process process,String projectDeployPath) {
        ProcessResult result = new ProcessResult();
//      File baseDir = new File(webAppRoot, baseDirName);
//      File file = new File(baseDir, process.getName()+ PathUtil.SEPARATOR_FORMAT +fileType);
        File file = new File(projectDeployPath+SeparatorUtil.getFileSeparator()+baseDirName, process.getName()+ PathUtil.SEPARATOR_FORMAT +fileType);
//      System.out.println(projectDeployPath+SeparatorUtil.getFileSeparator()+baseDirName+ PathUtil.SEPARATOR_FORMAT +fileType);

        if (!file.exists()) {
            result.setStatus(FILE_NOT_FOUND);
            return result;
        }

        Document doc = process.getDoc();
        try {
            SimpleXMLWorkShop.outputXML(doc, file);
        } catch (FileNotFoundException e) {
            result.setStatus(FILE_NOT_FOUND);
            result.setMes(e.getMessage());
            return result;
        } catch (IOException e) {
            result.setStatus(IO_ERROR);
            result.setMes(e.getMessage());
            return result;
        }
        result.setStatus(SUCCESS);
        return result;
    }
	/**
	 * ����ļ���ɾ��processĿ¼�µ�xml�ļ�
	 * @param fileName
	 */
    public void deleteFileByName(String fileName){
    	String xmlFileSavePath=PropertiesHelper.getString(db_config_properties_name,"xmlFileSavePath");
    	String filePath=xmlFileSavePath+fileName+".xml";
    	File xmlFile=new File(filePath);
    	xmlFile.deleteOnExit();
    }
	 /**
	  * ɾ����������
	  * @param process
	  * @return
	  */
	 public ProcessResult deleteProcess(Process process) {
        ProcessResult result = new ProcessResult();

        File baseDir = new File(webAppRoot, baseDirName);
        File file = new File(baseDir, process.getName()+ PathUtil.SEPARATOR_FORMAT + fileType);

        if (!file.exists()) {
            result.setStatus(FILE_NOT_FOUND);
            return result;
        }

        if (file.delete()) {
            result.setStatus(SUCCESS);
        }
        else {
            result.setStatus(FAIL);
        }
        return result;
	}
	
	

}
