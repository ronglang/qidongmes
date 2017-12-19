

$(function() {
	var form;
	//重置按钮触发
	$("#" + routeName).ligerForm();
	$(".l-button-test").click(function() {
		alert(v.element($("#txtName")));
	});
  //判断是保存还是修改
	//var row_id=grid.getSelected().id;
	if (row_id) {
		var parm = "id=" + row_id;
		pub_initForm(routeName, parm);
	}
});
/**
 * 重写提交数据成功后的回调函数
 */
/*function submitSuccess(json) {
	if (json.success) {
		alert("保存成功");
		if (json.data.id != null)
			row_id = json.data.id;
	} else {
		alert("保存失败");  
	}
}
var selectEdName= $("#selectName");
var	url = basePath + "rest/"+ routeName + "Action/getSelectOption";
$.ajax({
    url: url,
    type:"post",
    dataType:"json",
    async:false,
    success: function(json){
    
    	//var selectEdName=document.getElementById("selectName");
    	var matels=[];
    	var obj=json.select;
    	for (var int = 0; int < obj.length; int++) {
    		var matel = new Object();
    		matel.id=obj[int];
    		matel.text=obj[int];
    		matels.push(matel);
    		
		}
    },  
    error: function(json) {
        ajaxError(json);
    }
});
*/


function saveFormData() {
	var parm = "";
	var rfid =$("#rfidCardNumber").val();
	var materialType=$("#materialType").val();
	if(parseInt(rfid)<=1000){
		

		url = basePath + "rest/" + routeName + "Action/toAddRfid" + "?rfidCardNumber=" + rfid+"&materialType="+materialType; 
		//debugger;
		/*serializeArray()方法可以将表单数据序列化成一个键值对数组对象*/  
		var data = $("#" + routeName).serializeArray(); 
		$.ajax({
			url : url, 
			type : "post", 
			dataType : "json", 
			//$,param()可以将数组对象的数据处理成查询字符串的形式
			//data : $.param(data), 
			success : function(data) { 
				//alert("提交成功" + data);
				$.ligerDialog.success('保存成功');
	            $(".l-bar-button.l-bar-btnload",window.parent.document).click();  
				parent.$.ligerDialog.close();
				parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层
			},  
			error : function(data) {  
				$.ligerDialog.error('保存失败,发生了未知错误！');
			}  
		});
	
	}else if(rfid.length==0){
		alert("输入数字不能为空");
		return;
	}else if(parseInt(rfid)>1000){
		alert("输入数字不能大于1000");
		return;
	}
	
}
function reloadData() {
	var pa = $("#" + routeName + "ListForm").serialize(); 
	//js解码函数  
	//alert(pa);
	grid.loadServerData(decodeURIComponent(pa, true));
}



