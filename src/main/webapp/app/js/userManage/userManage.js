/**
 * 用户管理
 */
 
 if(!userManage)
 var userManage = {
    j:1,		 
 	queryByid:function(){
 		$.ajax( {
			type : 'post',
			url : 'http://localhost:8080/yaecp/rest/sysUserManageAction/detail',
			data:"id=1",
			dataType : 'json',
			error : function() {
				alert("error");
			},
			success : function(data) {
				alert("success"+data.data.username);
			}
		});
 	},
	addMode : function() {
		
			$.ajax( {
				type : 'post',
				url : 'http://localhost:8080/yaecp/rest/sysUserManageAction/save',
				data : "{\"username\":\"huanghao\",\"password\":999999}",
				contentType : 'application/json',
				dataType : 'json',
				error : function() {
					alert("error");
				},
				success : function(data) {
					alert("success");
				}
			});
		
	},
	jsonObject:function(){
		$.ajax( {
			type : 'post',
			url : 'http://localhost:8080/yaecp/rest/sysUserManageAction/getById',
			data:"{\"id\":1}",
			contentType : 'application/json',
			dataType : 'json',
			error : function() {
				alert("error");
			},
			success : function(data) {
				alert("success"+data.data.username);
			}
		});
	},
	jsonArray:function(){
		$.ajax( {
			type : 'post',
			url : 'http://localhost:8080/yaecp/rest/sysUserManageAction/getByIdArray',
			data:"[{\"id\":1},{\"id\":12,\"username\":\"huanghao\",\"password\":999999}]",
			contentType : 'application/json',
			dataType : 'json',
			error : function() {
				alert("error");
			},
			success : function(data) {
				alert("success"+data.data.username);
			}
		});
	},
	delImg: function(o){
		 document.getElementById("imgesContainer").removeChild(document.getElementById("div_"+o));
	},
	saveBlog:function(){
		$.ajax( {
			type : 'post',
			url : 'http://localhost:8080/yaecp/rest/blogManageAction/save',
			data:"title=9997&addDate=2015-11-12 12:12:12",
			dataType : 'json',
			error : function() {
				alert("error");
			},
			success : function(data) {
				alert("success");
			}
		});
	}

 };
 
 
$(document).ready(function(){
	//userManage.queryByid();
	//userManage.jsonObject();
	//userManage.jsonArray();
	
	$('#fileUploadForm').submit(function(){ 
			var options = { 
			url:'<%=basePath %>rest/fileUploadAction/uploadfile', //提交给哪个执行 
			type:'POST',
			contentType : 'application/json',
			success: function(res){ 
				alert(res);} //显示操作提示 
			}; 
			$('#fileUploadForm').ajaxSubmit(options); 
			return false; //为了不刷新页面,返回false，反正都已经在后台执行完了，没事！ 
			}
	); 
			
	
	$("#btn_add2").click(function(){
			document.getElementById("imgesContainer").innerHTML+='<div id="div_'+userManage.j+'"><input  name="file_'+userManage.j+'" type="file"  /><input type="button" value="删除"  onclick="userManage.delImg('+userManage.j+')"/></div>';
			userManage.j = userManage.j + 1;
	});
	console.log("userManage init");
});
