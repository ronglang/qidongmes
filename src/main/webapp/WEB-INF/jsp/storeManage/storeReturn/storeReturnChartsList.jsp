<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>资源管理系统 --- 首页</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=basePath%>app/images/cetc.png" media="screen" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="mylink" />
<link href="<%=basePath%>core/plugin/bootstrap.css" />
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>

<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/jquery.cookie.js"></script>
<script src="<%=basePath%>core/plugin/json.js"></script>
<script src="<%=basePath%>core/plugin/indexdata.js"
	type="text/javascript"></script>
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "macAreaManage";
	var row_id = "";
</script>
<script type="text/javascript">
	var tab = null;
	var accordion = null;
	var tree = null;
	var tabItems = [];
	$(function() {

		//布局
		$("#layout1").ligerLayout({
			leftWidth : 190,
			height : '100%',
			heightDiff : -34,
			space : 4,
			onHeightChanged : f_heightChanged,
			onLeftToggle : function() {
				tab && tab.trigger('sysWidthChange');
			},
			onRightToggle : function() {
				tab && tab.trigger('sysWidthChange');
			}
		});

		var height = $(".l-layout-center").height();

		//Tab
		tab = $("#framecenter").ligerTab({
			height : height,
			showSwitchInTab : true,
			showSwitch : true,
			onAfterAddTabItem : function(tabdata) {
				tabItems.push(tabdata);
				saveTabStatus();
			},
			onAfterRemoveTabItem : function(tabid) {
				for (var i = 0; i < tabItems.length; i++) {
					var o = tabItems[i];
					if (o.tabid == tabid) {
						tabItems.splice(i, 1);
						saveTabStatus();
						break;
					}
				}
			},
			onReload : function(tabdata) {
				var tabid = tabdata.tabid;
				addFrameSkinLink(tabid);
			}
		});

		//面板
		$("#accordion1").ligerAccordion({
			height : height - 24,
			speed : null
		});

		$(".l-link").hover(function() {
			$(this).addClass("l-link-over");
		}, function() {
			$(this).removeClass("l-link-over");
		});
		//树
		$("#tree1").ligerTree(
				{
					data : indexdata,
					checkbox : false,
					slide : false,
					nodeWidth : 120,
					attribute : [ 'nodename', 'url' ],
					render : function(a) {
						if (!a.isnew)
							return a.text;
						return '<a href="' + a.url + '" target="_blank">'
								+ a.text + '</a>';
					},
					onSelect : function(node) {
						if (!node.data.url)
							return;
						if (node.data.isnew) {
							return;
						}
						var tabid = $(node.target).attr("tabid");
						if (!tabid) {
							tabid = new Date().getTime();
							$(node.target).attr("tabid", tabid);
						}
						f_addTab(tabid, node.data.text, node.data.url);
					}
				});

		function openNew(url) {
			var jform = $('#opennew_form');
			if (jform.length == 0) {
				jform = $('<form method="post" />').attr('id', 'opennew_form')
						.hide().appendTo('body');
			} else {
				jform.empty();
			}
			jform.attr('action', url);
			jform.attr('target', '_blank');
			jform.trigger('submit');
		}
		;

		tab = liger.get("framecenter");
		accordion = liger.get("accordion1");
		tree = liger.get("tree1");
		$("#pageloading").hide();

		css_init();
		pages_init();
	});
	function f_heightChanged(options) {
		if (tab)
			tab.addHeight(options.diff);
		if (accordion && options.middleHeight - 24 > 0)
			accordion.setHeight(options.middleHeight - 24);
	}
	function f_addTab(tabid, text, url) {
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : url,
			callback : function() {
				addShowCodeBtn(tabid);
				addFrameSkinLink(tabid);
			}
		});
	}
	function addShowCodeBtn(tabid) {
		var viewSourceBtn = $('<a class="viewsourcelink" href="javascript:void(0)">查看源码</a>');
		var jiframe = $("#" + tabid);
		viewSourceBtn.insertBefore(jiframe);
		viewSourceBtn.click(function() {
			showCodeView(jiframe.attr("src"));
		}).hover(function() {
			viewSourceBtn.addClass("viewsourcelink-over");
		}, function() {
			viewSourceBtn.removeClass("viewsourcelink-over");
		});
	}
	function showCodeView(src) {
		$.ligerDialog.open({
			title : '源码预览',
			url : 'dotnetdemos/codeView.aspx?src=' + src,
			width : $(window).width() * 0.9,
			height : $(window).height() * 0.9
		});

	}
	function addFrameSkinLink(tabid) {
		var prevHref = getLinkPrevHref(tabid) || "";
		var skin = getQueryString("skin");
		if (!skin)
			return;
		skin = skin.toLowerCase();
		attachLinkToFrame(tabid, prevHref + skin_links[skin]);
	}
	var skin_links = {
		"aqua" : "lib/ligerUI/skins/Aqua/css/ligerui-all.css",
		"gray" : "lib/ligerUI/skins/Gray/css/all.css",
		"silvery" : "lib/ligerUI/skins/Silvery/css/style.css",
		"gray2014" : "lib/ligerUI/skins/gray2014/css/all.css"
	};
	function pages_init() {
		var tabJson = $.cookie('liger-home-tab');
		if (tabJson) {
			var tabitems = JSON2.parse(tabJson);
			for (var i = 0; tabitems && tabitems[i]; i++) {
				f_addTab(tabitems[i].tabid, tabitems[i].text, tabitems[i].url);
			}
		}
	}
	function saveTabStatus() {
		$.cookie('liger-home-tab', JSON2.stringify(tabItems));
	}
	function css_init() {
		var css = $("#mylink").get(0), skin = getQueryString("skin");
		$("#skinSelect").val(skin);
		$("#skinSelect").change(function() {
			if (this.value) {
				location.href = "index.htm?skin=" + this.value;
			} else {
				location.href = "index.htm";
			}
		});

		if (!css || !skin)
			return;
		skin = skin.toLowerCase();
		$('body').addClass("body-" + skin);
		$(css).attr("href", skin_links[skin]);
	}
	function getQueryString(name) {
		var now_url = document.location.search.slice(1), q_array = now_url
				.split('&');
		for (var i = 0; i < q_array.length; i++) {
			var v_array = q_array[i].split('=');
			if (v_array[0] == name) {
				return v_array[1];
			}
		}
		return false;
	}
	function attachLinkToFrame(iframeId, filename) {
		if (!window.frames[iframeId])
			return;
		var head = window.frames[iframeId].document
				.getElementsByTagName('head').item(0);
		var fileref = window.frames[iframeId].document.createElement("link");
		if (!fileref)
			return;
		fileref.setAttribute("rel", "stylesheet");
		fileref.setAttribute("type", "text/css");
		fileref.setAttribute("href", filename);
		head.appendChild(fileref);
	}
	function getLinkPrevHref(iframeId) {
		if (!window.frames[iframeId])
			return;
		var head = window.frames[iframeId].document
				.getElementsByTagName('head').item(0);
		var links = $("link:first", head);
		for (var i = 0; links[i]; i++) {
			var href = $(links[i]).attr("href");
			if (href && href.toLowerCase().indexOf("ligerui") > 0) {
				return href.substring(0, href.toLowerCase().indexOf("lib"));
			}
		}
	}
</script>
<style type="text/css">
body,html {
	height: 100%;
}

body {
	padding: 0px;
	margin: 0;
	overflow: hidden;
}

.l-link {
	display: block;
	height: 26px;
	line-height: 26px;
	padding-left: 10px;
	text-decoration: underline;
	color: #333;
}

.l-link2 {
	text-decoration: underline;
	color: white;
	margin-left: 2px;
	margin-right: 2px;
}

.l-layout-top {
	background: #102A49;
	color: White;
}

.l-layout-bottom {
	background: #E5EDEF;
	text-align: center;
}

#pageloading {
	position: absolute;
	left: 0px;
	top: 0px;
	background: white url('loading.gif') no-repeat center;
	width: 100%;
	height: 100%;
	z-index: 99999;
}

.l-link {
	display: block;
	line-height: 22px;
	height: 22px;
	padding-left: 16px;
	border: 1px solid white;
	margin: 4px;
}

.l-link-over {
	background: #FFEEAC;
	border: 1px solid #DB9F00;
}

.l-winbar {
	background: #2B5A76;
	height: 30px;
	position: absolute;
	left: 0px;
	bottom: 0px;
	width: 100%;
	z-index: 99999;
}

.space {
	color: #E7E7E7;
}
/* 顶部 */
.l-topmenu {
	margin: 0;
	padding: 0;
	height: 50px;
	font-size: -webkit-xxx-large;
	line-height: 100px;
	background-color: #6495ED;
	position: relative;
	border-top: 1px solid #1D438B;
}

.l-topmenu-logo {
	color: rgb(212, 211, 224);
	align-content: center;
	margin-top: 3px;
	padding-left: 35px;
	line-height: 26px;
}

.l-topmenu-welcome {
	position: absolute;
	margin-top: 30px;
	font-size: large;
	height: 24px;
	line-height: 24px;
	right: 30px;
	top: 2px;
	color: #070A0C;
}

.l-topmenu-welcome a {
	color: rgb(212, 211, 224);
	text-decoration: underline
}

.body-gray2014 #framecenter {
	margin-top: 3px;
}

.viewsourcelink {
	background: #B3D9F7;
	display: block;
	position: absolute;
	right: 10px;
	top: 3px;
	padding: 6px 4px;
	color: #333;
	text-decoration: underline;
}

.viewsourcelink-over {
	background: #81C0F2;
}

#skinSelect {
	margin-right: 6px;
}
</style>
</head>
<body style="padding:0px;width: 95%;margin: auto;overflow: false;  " >
	<div id="layout1"
		style="width:99.2%; margin:0 auto; margin-top:1px; overflow :auto;overflow-x:hidden;">
		<div style="font-size: 30px;">今日退料记录：</div>
		<div class="top"
			style="width: 100%;height:300px;margin:0 auto; margin-top:0;">
			<div class="top-a"
				style="float:left;width:33%;height: 290px;margin:0 auto; margin-top:0;margin-left:1px;">
				<iframe style="width:100%;height: 290px;border: 0;" scrolling="no"
				align="middle"
				src="<%=basePath%>rest/storeReturnManageAction/toHistogram_al">
			</iframe>
			</div>
			<div class="top-b"
				style="float:left;width:33%;height: 290px;margin:0 auto; margin-top:0;margin-left:1px;">
				<iframe style="width:100%;height: 290px;border: 0;" scrolling="no"
				align="middle"
				src="<%=basePath%>rest/storeReturnManageAction/toHistogram_cop">
			</iframe>
			</div>
			<div class="top-c"
				style="float:left;width:33%;height: 290px;margin:0 auto; margin-top:0;margin-left:1px;">
				<iframe style="width:100%;height: 290px;border: 0;" scrolling="no"
				align="middle"
				src="<%=basePath%>rest/storeReturnManageAction/toHistogram_glue">
			</iframe>
			</div>
		</div>
		<div class="top"
			style="width: 100%;height:600px;margin:0 auto; margin-top:0;">
			<div style="font-size: 30px;">历史退料记录：</div>
			<div class="two-a"
				style="width:100%;height: 590px;margin:0 auto; margin-top:0;margin-left:1px;margin-right:1px;">
				<iframe style="width:100%;height: 590px;border: 0;" scrolling="no"
					align="middle"
					src="<%=basePath%>rest/storeReturnManageAction/toReturnTable">
				</iframe>
			</div>
		</div>
	</div>
	<script>
		function outLog() {
			$.ajax({
				type : "POST",
				url : basePath + "/rest/sysUserManageAction/logOutWebUser",
				data : "",
				dataType : "json",
				success : function(data) {
					if (data.success = true) {
						window.location.href = basePath + "login.jsp";
					}
				}
			});
		}
	</script>
</body>
</html>

