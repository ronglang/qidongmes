package com.css.common.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baidu.ueditor.ConfigManager;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;

public class UeditorActionEnter {
	
	private HttpServletRequest request = null;
	
	private String rootPath = null;
	private String contextPath = null;  
	
	private String actionType = null; 
	
	private ConfigManager configManager = null;

	public UeditorActionEnter ( HttpServletRequest request, String rootPath ) {
		
		this.request = request;
		this.rootPath = rootPath;
		this.actionType = request.getParameter( "action" );
		this.contextPath = request.getContextPath();
		this.configManager = ConfigManager.getInstance( this.rootPath, this.contextPath, request.getRequestURI() );
		
	}
	
	public String exec () {
		
		String callbackName = this.request.getParameter("callback");
		
		if ( callbackName != null ) {

			if ( !validCallbackName( callbackName ) ) {
				return new BaseState( false, AppInfo.ILLEGAL ).toJSONString();
			}
			
			return callbackName+"("+this.invoke()+");";
			
		} else {
			return this.invoke();
		}

	}
	
	public String invoke() {
		
		if ( actionType == null || !ActionMap.mapping.containsKey( actionType ) ) {
			return new BaseState( false, AppInfo.INVALID_ACTION ).toJSONString();
		}
		
		if ( this.configManager == null || !this.configManager.valid() ) {
			return new BaseState( false, AppInfo.CONFIG_ERROR ).toJSONString();
		}
		
		State state = null;
		
		int actionCode = ActionMap.getType( this.actionType );
		
		Map<String, Object> conf = null;
		
		switch ( actionCode ) {
		
			case ActionMap.CONFIG:
				return  this.configManager.getAllConfig().toString();
				
			case ActionMap.UPLOAD_IMAGE:
			{	try {
					UploaderPub up = uploadImage();
					System.out.println(this.request.getContextPath());
					return "{\"original\":\""+up.getOriginalName()+"\",\"url\":\""+up.getUrl()+"\",\"title\":\""+up.getTitle()+"\",\"state\":\""+up.getState()+"\"}";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			case ActionMap.UPLOAD_SCRAWL:
			case ActionMap.UPLOAD_VIDEO:
			{
				try {
					UploaderPub up = uploadVideo();
					System.out.println(this.request.getContextPath());
					return "{\"original\":\""+up.getOriginalName()+"\",\"url\":\""+up.getUrl()+"\",\"title\":\""+up.getTitle()+"\",\"state\":\""+up.getState()+"\"}";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			case ActionMap.UPLOAD_FILE:
				/*conf = this.configManager.getConfig( actionCode );
				state = new Uploader( request, conf ).doExec();
				break;*/
			{	try {
					UploaderPub up = uploadField();
					System.out.println(this.request.getContextPath());
					return "{\"original\":\""+up.getOriginalName()+"\",\"url\":\""+up.getUrl()+"\",\"title\":\""+up.getTitle()+"\",\"state\":\""+up.getState()+"\"}";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			case ActionMap.CATCH_IMAGE:
				conf = configManager.getConfig( actionCode );
				String[] list = this.request.getParameterValues( (String)conf.get( "fieldName" ) );
				state = new ImageHunter( conf ).capture( list );
				break;
				
			case ActionMap.LIST_IMAGE:
			case ActionMap.LIST_FILE:
				conf = configManager.getConfig( actionCode );
				int start = this.getStartIndex();
				state = new FileManager( conf ).listFile( start );
				break;
				
		}
		
		return state.toJSONString();
		
	}
	
	public int getStartIndex () {
		
		String start = this.request.getParameter( "start" );
		
		try {
			return Integer.parseInt( start );
		} catch ( Exception e ) {
			return 0;
		}
		
	}
	
	/**
	 * callback参数验证
	 */
	public boolean validCallbackName ( String name ) {
		
		if ( name.matches( "^[a-zA-Z_]+[\\w0-9_]*$" ) ) {
			return true;
		}
		return false;
		
	}
	
	public UploaderPub uploadImage() throws Exception{
		
		UploaderPub up = new UploaderPub(this.request);
		up.setSavePath("uploadfiles/image");
		String[] fileType = { ".bmp",".png",".gif",".jpg",".jpeg"};
		up.setAllowFiles(fileType);
		up.setMaxSize(10000); // 单位KB
		up.upload();
		return up;
	}
	
	public UploaderPub uploadField() throws Exception{
		
		UploaderPub up = new UploaderPub(this.request);
		up.setSavePath("uploadfiles/field");
		String[] fileType = { ".doc",".docx",".xls",".xlxs",".ppt",".pptx",".pdf",".txt",".bmp",".png",".gif",".jpg",".jpeg",".rar",".zip" };
		up.setAllowFiles(fileType);
		up.setMaxSize(512000); // 单位KB
		up.upload();
		return up;
	}
	
	public UploaderPub uploadVideo() throws Exception{
		
		UploaderPub up = new UploaderPub(this.request);
		up.setSavePath("uploadfiles/video");
		String[] fileType = { ".avi", ".wma", ".rmvb",".rm", ".flash",".mp4",".mid", ".3GP" };
		up.setAllowFiles(fileType);
		up.setMaxSize(512000); // 单位KB
		up.upload();
		return up;
	}
}