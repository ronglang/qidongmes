<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
      + request.getServerName() + ":" + request.getServerPort()
      + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysDictionaryList</title>
<!-- sdsss -->
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->

<!-- sdsss -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/json.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sysManage/sysCommdicList.js" type="text/javascript"></script>
<!-- 默认引用end -->



<script>
  var basePath  = '<%=basePath%>';
  var routeName = "sysCommdicManage";
</script>
<script type="text/javascript">

  $(document).ready(function(){
    $.ajax( {
      type : 'post',
       url : basePath+'rest/sysCommdicManageAction/nodeTreeClas',
    // url : basePath+'rest/sysCommdicManageAction/nodeTree?qcolName=isleaf&qcolValue=0',
      dataType : 'json',
      error : function(res) {
        alert("error");
      },
      success : function(res) {
        $.fn.zTree.init($("#treeEventInfoAuditOrg"), setting, res.data);
      }
    });
  });
  

var setting = {
    data: {
      simpleData: {
        enable: true
      }
    },
    /* view:{
    	filter:true,  //是否启动过滤
    	expandLevel:0,  //展开层级
    	showFilterChildren:true, //是否显示过滤数据孩子节点
    	showFilterParent:true, //是否显示过滤数据父节点
    	showLine : false
    }, */
    callback:{onClick:function(event, treeId, treeNode){
      $("#hiddenValue").val(treeNode.id);
      $("#clas").val(treeNode.id);
      grid.loadServerData("clas="+treeNode.id);
    }}
  };

</script>
</head>

<body style="padding:0px; overflow: hidden;">

  <table style="margin:20px;width: 100%">
    <tr>
      <td style="width: 20%">
        <label>数据字典类别：<input type="text" id="word" name="word" style="width:120px;" onChange="searchIt()" keyPress="searchIt()"/></label>
        <div class="zTreeDemoBackground260 left" style="height: 630px;width: 256px;">
              <ul id="treeEventInfoAuditOrg" class="ztree" style="height: 80%;width: 256px;"></ul>
        </div>
      </td>
      
      <td style="width: 70%">
     <!--    <div id="message" ></div> --> 
        <div class="l-loading" style="display: block" id="pageloading"></div>
          <div style="height: 630px;" >
        <form id="sysDictionaryManageListForm" style="margin-bottom: 20px;">
         	<input type="hidden" id="hiddenValue" name="hiddenValue" />
         <!--  <fieldset class="l-fieldset">
            <legend class="l-legend"> </legend>
            <label>类别名称:</label>
            <input id="clas" name="clas" type="text" ltype="text">
            <label>编码:</label>
            <input id="code" name="code" type="text" ltype="text">
            <label>值:</label>
            <input id="value" name="value" type="text" ltype="text">
            <input type="button" class="btn-search" onclick="reloadData();""(['clas','value']); "
              value="查询" />
          </fieldset>
          <br /> -->
          <button type="button" onclick="pub_del(routeName)" class="btn-del"></button>
          <button type="button" id="add" class="btn-add"></button>
          <div id="sysCommdicManageList" style="margin: 0; padding: 0"></div>
        </form>
          </div>
        <div style="display: none;"></div>
      </td>
    </tr>
  </table>

</body>
</html>

