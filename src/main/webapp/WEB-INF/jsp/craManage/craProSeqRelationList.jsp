<%@ page language="java" 
 import="com.css.business.web.sysManage.bean.SysConfig,com.css.common.util.Constant,com.css.business.web.sysManage.bean.SysUser,com.css.commcon.util.SessionUtils"
 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	SysUser user = SessionUtils.getUser(request);
%>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品工艺的工序工艺关系管理</title>
		
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
		<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
		
	   <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/craManage/craProSeqRelationList.js" type="text/javascript"></script>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "craProSeqRelationManage";    
			var row_id = null;
    	</script>
	<style type="text/css">
		::-moz-placeholder{color:red;}              //ff
    ::-webkit-input-placeholder{color:red;}     //chrome,safari
    :-ms-input-placeholder{color:red;}
    
    	.cion50
		{
			width:80px;
			height:30px;
			float:left;
			margin-right:5px;
		}
    	.cion80
		{
			width:80px;
			height:18px;
			float:left;
			margin-right:5px;
		}
    	.cion100
		{
			width:100px;
			height:30px;
			float:left;
			margin-right:5px;
		}
    	.cion120
		{
			width:120px;
			height:30px;
			float:left;
			margin-right:5px;
		}
			table td{word-break: keep-all;white-space:nowrap;}
    		
			.bfpart1
			{
				border:1px solid #333333;
			}
			.bfpart1 td
			{
				border:1px solid #333333;
				font-size:13px;
				height:35px;
				background-color:white;
			}
			.l-table-edit {
			    border:1px solid #9a9a9a;
			    width:99%;
			  /*   table-layout:fixed; */
			}
			.l-table-edit-td{
				border-bottom: 1px solid #6b6b6b;
				text-overflow: ellipsis;
				word-break: keep-all;
				overflow: hidden;
				color: #6b6b6b;
			}
			.table-edi {
				width: 99%;
			}
			.tdnowrap{
				text-overflow:ellipsis; 
				word-break:keep-all; 
				overflow:hidden;
				display:inline;
			}
			.tdcenter{
				align:center;
				text-align:center;
			}
			.ta{width:100%;}
	</style>
	</head>
	<body style="padding-top:0px;padding-left:6px; overflow:hidden;">
		<div id="message" style="width:800px"></div>
		<div class="l-loading" style="display:block" id="pageloading"></div> 
			<form id="craProSeqRelationManageListForm">
				<table cellpadding="0" cellspacing="0" class="l-table-edit" >
					<tr class="table-edit-tr">
						<td align="right" class="l-table-edit-td">产品:</td>
						<td class="l-table-edit-td-input">
						    <input id="proId" name="proId" type="hidden" ztree="true" value="" />
							<div style="width: 177px; height: 22px; position: relative;">
									<input id="proIdShow" name="proIdShow" onfocus="this.blur()" type="text" ztree="true" 
										validate="{required:false}" placeholder="请选择产品" title="请选择产品"
										style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);"/>
										<img
										src="<%=basePath%>core/img/btn_03.png"
										style="position: absolute; right: 0px; top: 2px;"
										id="proIdBtn" href="#"
										onclick="showMenu('proId'); return false;" />
							</div>
						</td>
						<td align="left">&nbsp;</td>
						<td align="right" class="l-table-edit-td">产品颜色:</td> 
						<td align="left" class="l-table-edit-td-input">
							 <input id="proColor" name="proColor" type="hidden" ztree="true" value="" />
							<div style="width: 177px; height: 22px; position: relative;">
									<input id="proColorShow" name="proColorShow" onfocus="this.blur()" type="text" ztree="true" 
										validate="{required:false}" placeholder="请选择产品颜色" title="请选择产品颜色"
										style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);"/>
										<img
										src="<%=basePath%>core/img/btn_03.png"
										style="position: absolute; right: 0px; top: 2px;"
										id="proColorBtn" href="#"
										onclick="showMenu('proColor'); return false;" />
							</div>
						</td>
						<td align="left">&nbsp;</td>
						<td align="right" class="l-table-edit-td">产品规格型号:</td> 
						<td align="left" class="l-table-edit-td-input"><input type="text" id="proGgxh" name="proGgxh" placeholder="产品规格型号"  validate="{required:false,maxlength:32}" maxlength="32"></td>
						<td align="left">&nbsp;</td>
						<td align="right" class="l-table-edit-td">工序:</td>
						<td align="left" class="l-table-edit-td-input" >
						 	<input id="seqCode" name="seqCode" type="hidden" ztree="true" value=""/>
							<div style="width:177px; height:22px;position:relative;">
								<input id="seqCodeShow" name="seqCodeShow" type="text" ztree="true"  validate="{required:false}" placeholder="工序"  title="工序"
									style="margin: 0px;padding-left:5px; width:170px; height: 20px;  color: rgb(34, 34, 34);"/>
									<img src="<%=basePath%>core/img/btn_03.png" style="position:absolute; right:0px; top:2px;"
										 id="seqCodeBtn" href="#" onclick="showMenu('seqCode'); return false;"/>
							</div>
						 </td>
						<td align="left">&nbsp;</td>
						<td style="align:right;text-align:right;">
							<button type="button" id="btn_query" class="btn-search" onclick="reloadData(['proId','proColor','proGgxh','seqCode']); "></button>
							<button type="button" class="btn-reset" onclick="reloadData(['proId','proColor','proGgxh','seqCode'],true);"></button>
						</td>
					</tr>
				</table>
				<br/>
				<button type="button" onclick="toDetails()" class="btn-normal" >展示详情</button>
				<button type="button" onclick="generate()" class="btn-normal" >生成参数</button>
				<div style="margin:0; padding:0;width:100%;height:100%;overflow:hidden;">
					<div id="craProSeqRelationManageList" style="margin:0; padding:0;overflow:auto;"></div>
				</div>
			</form>
		  	<div style="display:none;">
		     </div>
	</body>
</html>

