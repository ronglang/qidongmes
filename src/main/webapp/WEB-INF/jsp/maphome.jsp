<%@page import="com.css.commcon.util.SessionUtils"%>
<%@page import="com.css.business.web.sysManage.bean.SysUser"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Object status = request.getAttribute("status");
	SysUser user = SessionUtils.getUser(request);
	String userArea = user.getAreaCode();
%>

<title>一张图</title>
<link rel="shortcut icon" type="image/x-icon" href="<%=basePath%>app/images/fupin.ico" media="screen" />
<script type="text/javascript">
	var basePath  = '<%=basePath%>';
	var isSimpleMap = false;
	var userArea = <%=userArea%>;
</script>
<script type="text/javascript" src="<%=basePath %>core/js/jquery-1.7.2.js"></script>
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>flexmap/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath %>flexmap/embedMap.js"></script>

<div id="flashContent">
					<p>To view this page ensure that Adobe Flash Player version
						11.1.0 or greater is installed.</p>
					<script type="text/javascript"> 
            var pageHost = ((document.location.protocol == "https:") ? "https://" : "http://"); 
            document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='" 
                            + pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>" ); 
        </script>
</div>