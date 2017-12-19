package com.css.business.web.sysManage.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.css.business.web.subsysmanu.bean.MauOEEHistory;
import com.css.business.web.subsysmanu.mauManage.service.MauOEEHistoryManageService;
import com.css.business.web.sysManage.bean.APP;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.syscommon.controller.support.JsonWrapper;

@Controller
@RequestMapping("/aPPManageAction")
public class APPManageAction{

	
	private PropertyFileUtil pfUtil = new PropertyFileUtil();
	
	@RequestMapping("toListPage")
	public String toListPage(HttpServletRequest request){
		HashMap<String, Object> allAPP = getAllAPP( request);
		request.setAttribute("apps",allAPP.get("data") );
		return "sysManage/APPListPage";
		
	}
	
	@RequestMapping("toManageListPage")
	public String toManageListPage(HttpServletRequest request){
		HashMap<String, Object> allAPP = getAllAPP( request);
		request.setAttribute("apps",allAPP.get("data") );
		return "sysManage/APPManageListPage";
	}
	
	@RequestMapping("appUpload")
	public String appUpload(HttpServletRequest request,MultipartFile file){
		try {
			//String path = request.getSession().getServletContext().getRealPath("upload");  
			String path = pfUtil.getProp("APP_PATH");
	        String fileName = file.getOriginalFilename();    
	        File dir = new File(path,fileName);          
	        if(!dir.exists()){  
	            dir.mkdirs();  
	        }  
	        file.transferTo(dir);  
	        request.setAttribute("uploadFlag", "true");
		} catch (Exception e) {
			request.setAttribute("uploadFlag", "false");
			e.printStackTrace();
		}
		return "sysManage/APPManageListPage";
	}
	
	@RequestMapping("getAllAPP")
	@ResponseBody
	public HashMap<String, Object>getAllAPP(HttpServletRequest request){
		String path = pfUtil.getProp("APP_PATH");
		File file = new File(path);
		File[] tempList = file.listFiles();
		List<APP>list = new ArrayList<>();
		if (tempList == null || tempList.length == 0) {
			return JsonWrapper.failureWrapperMsg("未找到相关数据");
		}
		for (File file2 : tempList) {
			APP app = new APP();
			if (file2.getName().endsWith("apk")) {
				app.setAppName(file2.getName());
				app.setAppUrl(file2.getAbsolutePath() );
				list.add(app);
			}
		}
		
		return JsonWrapper.successWrapper(list);
		
	}
	
	@RequestMapping("downAPP")
	public void downAPP(HttpServletRequest request,HttpServletResponse response,String fileName) throws IOException{
		 //模拟文件，myfile.txt为需要下载的文件  
		String path = pfUtil.getProp("APP_PATH")+"\\"+fileName;
		InputStream bis = null;
		try {
	        //获取输入流  
	        bis = new BufferedInputStream(new FileInputStream(new File(path)));  
	        //假如以中文名下载的话  
	        //转码，免得文件名中文乱码  
	        fileName = URLEncoder.encode(fileName,"UTF-8");  
	        //设置文件下载头  
	        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);    
	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
	        response.setContentType("multipart/form-data");   
	        
	       /* BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
	        int len = 0;  
	        while((len = bis.read()) != -1){  
	            out.write(len);  
	            out.flush();  
	        }  
	        out.close();  */
        
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
			byte b[] = new byte[1024];
			int len = 0 ;
			while((len = bis.read(b)) > 0){
				out.write(b, 0, len);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(bis != null)
				bis.close();
		}
    }  
	
	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String, Object> clearBean(HttpServletRequest request,String fileName){
		String path = pfUtil.getProp("APP_PATH")+"\\"+fileName;
		File file = new File(path);
		if (!file.exists()) {
			return JsonWrapper.failureWrapperMsg("app不存在");
		}
		file.delete();
		return JsonWrapper.successWrapper(null, "删除成功");
	}
	
	/**
	 * 
	 * @Description: 临时保存Echache
	 * @return
	 */
	EhCacheFactory factory = EhCacheFactory.getInstance();
	@Resource(name="mauOEEHistoryManageService")
	private MauOEEHistoryManageService mhService;
	@RequestMapping("saveE")
	@ResponseBody
	public HashMap<String, Object>saveE(){
		String[] macCodes = {"DA","DE","VB","ED","SI","RC"};
		for (String code : macCodes) {
			String cmdCache = factory.getWebsocketCmdCache(YorkUtil.Memcache_机台_实时_Echart+"_"+code);
			if (cmdCache != null ) {
				MauOEEHistory h = new MauOEEHistory();
				h.setCourseCode(String.valueOf(System.currentTimeMillis()).substring(8));
				h.setCreateDate(new Date());
				h.setProgxh(String.valueOf(System.currentTimeMillis()).substring(10));
				h.setEchartsVO(cmdCache);
				h.setMacCode(code);
				try {
					mhService.save(h);
					factory.removeWebsocketCmdCache(YorkUtil.Memcache_机台_实时_Echart+"_"+code);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}
}
