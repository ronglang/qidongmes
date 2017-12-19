$(document).ready(function(){
	
	grid();
	
});


function grid(){
	window['g'] =
	$("#maingrid").ligerGrid({
        height: '93%',
        title:'产品类型管理',
        url: basePath+'rest/mauProductManageAction/getProTypes',
       	checkbox: true,
        columns: [
        { display: '产品类型', name: 'pro_type'},
        { display: '类型', name: 'p_type'}
        ],
        toolbar: {
            items: [
                    { text: '增加', click: addType, icon: 'add'},
                    { text: '修改', click: updateType, icon: 'modify' },
                    { text: '删除', click: deleteType, icon: 'delete'},
            { line: true }
            ]
        },
        rownumbers: true,
        enabledEdit: true
    });
    $("#pageloading").hide();
	
}

//修改产品类型
function updateType(){
	var manager = $("#maingrid").ligerGetGridManager();
	var li = manager.getSelectedRows();
	
	debugger;
	if(li.length>1 || li.length<1 ){
		$.ligerDialog.error("请选择一行修改");
		return;
	}
	$("#txtKey").val(li[0].pro_type);
	$("#sel_type").val(li[0].p_type);
	$.ligerDialog.open({
        target:$("#showKey"),
        title: '修改产品类型',
        width: 480,
        height: 120,
        isResize: true,
        modal: true,
        buttons: [  { text: '修改', onclick: function (i, d) { modifyType(d,li); }},
                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
                 ]                                  
   });
	
}

function modifyType(d,li){
	var dataArray = [];
	var obj = new Object();
	obj.id = li[0].id;
	obj.pro_type = $("#txtKey").val();
	obj.p_type = $("#sel_type").val();
	var beforValue = li[0].pro_type;
	if($("#txtKey").val() == "" || $("#txtKey").val() == null){
		$.ligerDialog.error("请填写产品类型！");
		return ;
	}
	dataArray.push(obj);
	var param = JSON.stringify(dataArray);
//	debugger;
	$.ajax({
		url:basePath+'rest/mauProductManageAction/modifyProType',
		type: "post",
		dataType: "json",
		data: "param="+param+"&beforValue="+beforValue,
		success: function(map){
			if(map.success != "" && map.success != null){
				$.ligerDialog.success(map.success);
				d.hide();
   				grid();
   				$("#txtKey").val("");
			}else{
				$.ligerDialog.error(map.error);
			}
		},
		error: function(){
			$.ligerDialog.error("添加失败，发生未知错误！");
		}
		
	});
	
}


function addType(){
//	debugger;
//	var manager = $("#maingrid").ligerGetGridManager();
//	var row = manager.getSelectedRow();
//	manager.addRow({
//		pro_type:''
//	});
	//添加产品类型
	$("#txtKey").val("");
	$.ligerDialog.open({
        target:$("#showKey"),
        title: '增加产品类型',
        width: 480,
        height: 120,
        isResize: true,
        modal: true,
        buttons: [  { text: '添加', onclick: function (i, d) { saveType(d); }},
                    { text: '关闭', onclick: function (i, d) { $("input").ligerHideTip(); d.hide(); }}
                 ]                                  
   });
		
		
	
	
}

//保存类型
function saveType(d){
	var p_type = $("#sel_type").val();
	var typeName = $("#txtKey").val();
	
	if(typeName == "" || typeName == null){
		$.ligerDialog.error("请填写产品类型！");
		return;
	}
	$.ajax({ 
		url: basePath+'rest/mauProductManageAction/saveProType',
   		type:"post",
   		dataType:'json',
   		data:"typeName="+typeName+"&p_type="+p_type,
   		success: function(map){
   			//debugger;
   			if(map.msg == "添加成功"){
   				$.ligerDialog.success(map.msg);
   				d.hide();
   				grid();
   				$("#txtKey").val("");
   			}else{
   				$.ligerDialog.error("【"+map.msg+"】");
   			}
   	    },
   	    error : function() {
   	    	$.ligerDialog.error("添加失败，发生未知错误！");
		}
	});
}


function deleteType(){
	
	var manager = $("#maingrid").ligerGetGridManager();
	var li = manager.getSelectedRows();
	
	debugger;
	if(li.length>1 || li.length<1 ){
		$.ligerDialog.error("请选择一行删除，禁止多行删除");
		return;
	}
	var arr = [];
	var obj = new Object();
	obj.id = li[0].id;
	obj.pro_type = li[0].pro_type;
	obj.p_type = li[0].pro_type;
	arr.push(obj);
	var param = JSON.stringify(arr);
	
	$.ligerDialog.confirm("温馨提示：是否确定删除？",function(yes){
		if(yes){
			$.ajax({
				url: basePath + "rest/mauProductManageAction/clearProType",
				type: "post",
				dataType: "json",
				data: "param="+param,
				success: function(map){
					if(map.success != "" && map.success != null){
						$.ligerDialog.success(map.success);
		   				grid();
		   			}else{
		   				$.ligerDialog.error("【"+map.error+"】");
		   			}
				},
				error: function(){
					$.ligerDialog.error("删除失败，发生未知错误，请求维护！");
				}
				
			});
		}else{
			return ;
		}
	});

	
}







