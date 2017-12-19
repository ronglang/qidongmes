<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String sysImportModel_id = request.getParameter("id");
%>


<!DOCTYPE HTML>
<html>
    <head>
        

        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>sysDataImport</title>
        <link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
        
        <link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
        <link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
        
        <script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
      
        
        <script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
        <script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
        <script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
        <script src="<%=basePath%>core/plugin/json.js" type="text/javascript"></script>
        <script src="<%=basePath%>core/plugin/swfupload/swfupload.js" type="text/javascript"></script>
        <script src="<%=basePath%>core/plugin/swfupload/swfupload.queue.js" type="text/javascript"></script>
        <script src="<%=basePath%>core/plugin/swfupload/fileprogress.js" type="text/javascript"></script>
        <script src="<%=basePath%>core/plugin/swfupload/swfuploadProperties.js" type="text/javascript"></script>
        <script src="<%=basePath%>core/plugin/swfupload/handlers.js" type="text/javascript"></script>
        <script>
            var basePath  = '<%=basePath%>';
            var routeName = "sysDataImport";
            var row_id = <%=sysImportModel_id%>;
            var uploadurl = "";
            var uploadurlorigname = "";
            var filePath = "";
        </script>
    </head>

    <body style="padding:0px; margin:0px overflow:hidden;">
        <div id="message" style="width:800px"></div>
        <div class="l-loading" style="display:block" id="pageloading"></div> 
            <form id="sysDataImportManageListForm">
                <br/>
                <table style="margin-top:30px ; margin-left:30px; ">
                    <tr class="l-table-edit-tr" >
                        <td align="right" class="l-table-edit-td" style="width:80px;">上传文件：</td>
                        <td align="left" class="l-table-edit-td-input" style="width:300px;">
                            <div class="swfdiv">
                                <div id="uploadurlShow" style="overflow:hidden;"></div>
                                <div class="fieldset flash" id="fsUploadProgress1" >
                                <input type="hidden" id="saveDataId1"/></div>
                                <div >
                                <span id="spanButtonPlaceholder1" style="background-color:red;"></span>
                                <input id="btnCancel1" type="button" value="取消上传" onclick="cancelQueue(upload1);" disabled="disabled" style="margin-left: 2px; height: 22px; font-size: 8pt; vertical-align:top;" />
                                <br /></div>
                            </div>
                        </td>
                        <td align="left" style="width:100px;"></td>
                    </tr>
                    <tr class="l-table-edit-tr">
                        <td align="right" class="l-table-edit-td">模板选择：<span style="color: red;">*</span></td>
                        <td align="left" class="l-table-edit-td-input">
                            <input id="modelID" name="modelID" type="hidden" value="" />
                            <div style="width:177px; height:22px;position:relative;">
                            <input id="modelIDShow" name="modelIDShow" type="text" ztree="true" validate="{required:true}"
                                style="margin: 0px;padding-left:5px; width: 170px; height: 17px;  color: rgb(34, 34, 34);" /><img
                                src="<%=basePath%>core/img/btn_03.png"
                                style="position:absolute; right:0px; top:2px;" id="modelIDBtn"
                                href="#" onclick="showMenu('modelID'); return false;" />
                            </div>
                        </td>
                        <td align="left"><span id="hint" style="margin-left: -106px;color: red;"></span></td>
                    </tr>
                    <tr class="l-table-edit-tr">
                        <td align="right" class="l-table-edit-td">添加方式：</td>
                        <td align="left" class="l-table-edit-td">
                            <div id="ra">
                                <input id="radioAdd" name="addType" type="radio" value="" selected>在原数据上追加</input>
                                <input name="addType" type="radio" value=""/>删除原数据后添加</input>
                            </div></td>
                        <td align="left"></td>
                    </tr>
                </table>
            </form>
            <input type="button" value="开始导入" onclick="javascript:importData();return false;" style="margin-left:125px;"> 
            <div style="display:none;">
        </div>
    </body>
    <script type="text/javascript">
        //pub_initUpload(routeName,parm);
        function importData(){
        var id = $("#modelID").attr("value");
        if(id==""){
        	 $("#modelIDShow").focus();
        	 $("#hint").html("模版不能为空");
        	return;
        }
        var url = basePath+"rest/sysImportModelManageAction/importData?id="+id+"&path="+filePath;
            $.ajax({
                url: url,
                type:"post",
               dataType:"json",
                async:true,
               // data:$.param(data),
                success: function(){
                    //submitSuccess();
                },
                error: function(json) {
                    //ajaxError(json);
                }
            });
        }
        creatTreeDiv("modelID");
        initTree("modelID","sysImportModel","normal","","");
        function addUploadData(path,origName)
        {
            if( isNullObject(path) && isNullObject(origName) )
            {
                filePath = path;
                uploadurl += path + ",";
                uploadurlorigname += origName + ",";
            }
        }
        
        function submitSuccess(){
            alert("导入成功");
        }
        
        function ajaxError(json){
            alert(json);
        }
    
    </script>
</html>

