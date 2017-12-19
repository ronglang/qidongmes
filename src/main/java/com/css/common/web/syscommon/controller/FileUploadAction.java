package com.css.common.web.syscommon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.css.common.annotation.Auth;
import com.css.common.util.PropertyFileUtil;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.controller.support.JsonWrapper;

/**
 * 
 * TODO 文件 上传
 * 
 * @author huangaho 2015-4-15上午11:50:08
 */
@Controller
@RequestMapping("/fileUploadAction")
public class FileUploadAction {
	public final transient Log log = LogFactory.getLog(FileUploadAction.class);
	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	@RequestMapping({ "uploadfile" })
	@ResponseBody
	public  void uploadfile(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out = null;
		response.setCharacterEncoding("utf-8");
		try {
			upload(request,response);
			out =  response.getWriter();
		} catch (Exception e) {
			if(out != null)out.print("{\"error\":\"文件上传失败！\"}");
			log.error("文件上传失败",e);
		}
	}
	
	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	private  void upload(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String saveDataId = request.getParameter("saveDataId");
		PropertyFileUtil property = new PropertyFileUtil();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		HashMap<String, Object> res = new HashMap<String, Object>();
		//文件保存位置，当前项目下的upload/attachment
				String uploadDir = property.getProp("upload_name") + File.separatorChar + "attachment" + File.separatorChar;
				//每天上传的文件根据日期存放在不同的文件夹
				String autoCreatedDateDirByParttern = "yyyy" + File.separatorChar + "MM" + File.separatorChar + "dd"
						+ File.separatorChar;
				String autoCreatedDateDir = DateFormatUtils.format(new java.util.Date(), autoCreatedDateDirByParttern);
				String ctxDir = request.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
				if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
					ctxDir = ctxDir + File.separatorChar;
				}
//				File savePath = new File(ctxDir + uploadDir + autoCreatedDateDir);
				File savePath = new File(property.getProp("image_path") + uploadDir + autoCreatedDateDir);
				if (!savePath.exists()) {
					savePath.mkdirs();
				}
//				String saveDirectory = ctxDir + uploadDir + autoCreatedDateDir;
				String saveDirectory = property.getProp("image_path") + uploadDir + autoCreatedDateDir;
				String path = request.getContextPath();
				//String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			StringBuilder sb = new StringBuilder();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名
						String fileName = file.getOriginalFilename();
						// 定义上传路径
						 sb.replace(0, sb.length(), "");
						 sb.append(calendar.get(Calendar.YEAR));
						 sb.append(calendar.get(Calendar.MONTH+1));
						 sb.append(calendar.get(Calendar.DAY_OF_MONTH));
						 sb.append(calendar.getTimeInMillis());
						 path = saveDirectory + sb.toString()+fileName;
						File localFile = new File(path);
						file.transferTo(localFile);
						log.info("上传成功："+myFileName+" 大小："+file.getSize()+" 服务务器存放位路径："+path);
						//res.put("uploadUrl", basePath+uploadDir + autoCreatedDateDir+ sb.toString()+fileName);
						//res.put("uploadUrlorigname", fileName);
						res.put("success", true);
						res.put("filenameindisk", uploadDir + autoCreatedDateDir+ sb.toString()+fileName);
						res.put("filename", fileName);
						res.put("contenttype", "application/octet-stream");
						res.put("saveDataId", saveDataId);
						res.put("memberID", "111");
						res.put("needwatermark", 1);
						res.put("id", "");
						res.put("description", "上传成功");
					}
				}
				
			}

		}
		JSONObject jsonObj = JSONObject.fromObject(res);
		//返回前台的数据
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(jsonObj.toString());
		//return res;
	}
	
	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	@RequestMapping({ "deleteFile" })
	@ResponseBody
	public HashMap<String, Object>  deleteFile(HttpServletRequest request,HttpServletResponse response,String filePath){
		String ctxDir = request.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
			ctxDir = ctxDir + File.separatorChar;
		}
		HashMap<String, Object> res = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(filePath)){
			String fullpath = ctxDir+filePath;
				response.setCharacterEncoding("utf-8");
				File file = new File(fullpath);
				if(file.exists()){
					boolean flg = file.delete();
					if(flg){
						res.put("success", true);
						res.put("msg", "删除文件成功："+fullpath);
						log.info("删除文件成功："+fullpath);
						return res;
					}else{
						res.put("success", false);
						res.put("msg", "删除文件失败："+fullpath);
						log.info("删除文件失败："+fullpath);
						return res;
					}
					
				}else{
					res.put("success", false);
					res.put("msg", "删除文件失败,文件路径不存在！"+fullpath);
					log.info("删除文件失败,文件路径不存在！"+fullpath);
					return res;
				}
			
		}else{
			res.put("success", false);
			res.put("msg", "删除文件失败,文件路径不能为空！");
			return res;
		}
	}
	
	
	@RequestMapping({ "downloadValidate" })
	@ResponseBody
	public HashMap<String, Object> downloadValidate(HttpServletRequest request,String filePath){
		String ctxDir = request.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		PropertyFileUtil property = new PropertyFileUtil();
		if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
			ctxDir = ctxDir + File.separatorChar;
		}
		File file = new File(property.getProp("image_path") + filePath );
		if(!file.exists()){
			return JsonWrapper.failureWrapperMsg("附件文件不存在！");
		}
		HashMap<String, Object> map = JsonWrapper.successWrapper(null, "有效的附件文件地址！");
		map.put("filePath", filePath);
		return map;
	}
	
	
	@RequestMapping("downloadfile")  
	public void downloadfile(HttpServletRequest reques,HttpServletResponse res,String filePath) throws IOException {
	    OutputStream os = res.getOutputStream();  
	    String ctxDir = reques.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
		if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
			ctxDir = ctxDir + File.separatorChar;
		}
		File file = new File(ctxDir + filePath );
	    try {
	    	if(file.exists()){
	    		res.reset();  
	 	        res.setHeader("Content-Disposition", "attachment; filename="+filePath);  
	 	        res.setContentType("application/octet-stream; charset=utf-8");
	 	        FileInputStream fis = new FileInputStream(file);
			    byte [] content = new byte[fis.available()];
			    fis.read(content);
			    fis.close();
	 	        os.write(content);  
	 	        os.flush();  
	    	}
	       
	    } finally {  
	        if (os != null) {  
	            os.close();  
	        }  
	    }  
	}
	
	@RequestMapping("download")  
	public ResponseEntity<byte[]> download(HttpServletRequest reques,HttpServletResponse res,String filePath) throws IOException {  
		 String ctxDir = reques.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
			if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
				ctxDir = ctxDir + File.separatorChar;
			}
			File file = new File(ctxDir + filePath );
			if(file.exists()){
				HttpHeaders headers = new HttpHeaders();  
			    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
			    headers.setContentDispositionFormData("attachment", filePath);  
			    FileInputStream fis = new FileInputStream(file);
			    byte [] content = new byte[fis.available()];
			    fis.read(content);
			    fis.close();
			    return new ResponseEntity<byte[]>(content,HttpStatus.CREATED);
			}else{
				return new ResponseEntity<byte[]>(new byte[8],HttpStatus.CREATED);
			}
		  
	}
	
	/**
	 * App上传文件接口，处理了手机一次上传多张图的情况
	 * @param request
	 * @return
	 * @throws Exception
	 * @author leitao
	 */
	@SuppressWarnings("unused")
	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public  HashMap<String, Object> uploadForApp(HttpServletRequest request)throws Exception {
		String saveDataId = request.getParameter("saveDataId");
		PropertyFileUtil property = new PropertyFileUtil();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		HashMap<String, Object> res = new HashMap<String, Object>();
		//文件保存位置，当前项目下的upload/attachment
				String uploadDir = property.getProp("upload_name") + File.separatorChar + "attachment" + File.separatorChar;
				//每天上传的文件根据日期存放在不同的文件夹
				String autoCreatedDateDirByParttern = "yyyy" + File.separatorChar + "MM" + File.separatorChar + "dd"
						+ File.separatorChar;
				String autoCreatedDateDir = DateFormatUtils.format(new java.util.Date(), autoCreatedDateDirByParttern);
				String ctxDir = request.getSession().getServletContext().getRealPath(String.valueOf(File.separatorChar));
				if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
					ctxDir = ctxDir + File.separatorChar;
				}
//				File savePath = new File(ctxDir + uploadDir + autoCreatedDateDir);
				File savePath = new File(property.getProp("image_path") + uploadDir + autoCreatedDateDir);
				if (!savePath.exists()) {
					savePath.mkdirs();
				}
//				String saveDirectory = ctxDir + uploadDir + autoCreatedDateDir;
				String saveDirectory = property.getProp("image_path") + uploadDir + autoCreatedDateDir;
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			StringBuilder sb = new StringBuilder();
			//记录上传文件的个数
			int x=0;
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					x++;
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名（获取上传文件的原名）
						String fileName = file.getOriginalFilename();
						// 定义上传路径
						 sb.replace(0, sb.length(), "");
						 sb.append(calendar.get(Calendar.YEAR));
						 sb.append(calendar.get(Calendar.MONTH+1));
						 sb.append(calendar.get(Calendar.DAY_OF_MONTH));
						 sb.append(calendar.getTimeInMillis());
						 path = saveDirectory + sb.toString()+fileName;
						File localFile = new File(path);
						file.transferTo(localFile);
						log.info("上传成功："+myFileName+" 大小："+file.getSize()+" 服务务器存放位路径："+path);
						res.put("success_"+x, true);
						res.put("filenameindisk_"+x, uploadDir + autoCreatedDateDir+ sb.toString()+fileName);
						res.put("filename_"+x, fileName);
						//获取表单中文件组件的名字，name属性，用这个属性可以实现分类别多图上传
						res.put("name_"+x, file.getName());
					}
				}
			}
			res.put("fileNumber", x);
		}
		return res;
	}
}
