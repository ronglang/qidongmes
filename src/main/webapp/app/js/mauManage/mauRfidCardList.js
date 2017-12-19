var tbdArray = new Array();
var groupicon = "../../../lib/ligerUI/skins/icons/communication.gif";
var form;
var jsonObj={};
function itemclick(item) { 
	if (item.text == "增加") { 
winAdd = $.ligerDialog.open({
					// dialog中添加form表单
					url : basePath 
							+ "rest/mauRfidCardManageAction/toAddRfidCardEdit",
					height : 300,
					width : 500,
					modal : true,
					title : "添加RFID卡",
					isResize : true,
				});
		//window.open(basePath+"rest/mauRfidCardManageAction/toAddRfidCardEdit");  
	} 
	if (item.text == "修改") {
		var selected = grid.getSelecteds();
		if(selected.length==0){
			$.ligerDialog.warn('您还没有选择，请选择一条修改');
			return;
		}if(selected.length>1){
			$.ligerDialog.warn('您选了多条，请选择一条修改');
		    return;
		}
		else{
			var id = selected[0].id;
			/*winEdit = top.$.ligerDialog.open({
				url : basePath
						+ "rest/mauRfidCardManageAction/toUpdateRfid?id="
						+ id,
				height : 300,
				width : 500,
				modal : true,
				title : "编辑"
			});*/
			//window.open(basePath+ "rest/mauRfidCardManageAction/toUpdateRfid?id="+id);  
		}
	} 
	if (item.text == "删除") {
		var selected = grid.getSelecteds();
		if (selected.length == 0) {
			$.ligerDialog.warn('请选择要删除的数据');
			return; 
		}
		var strIds = [];
		for (var i = 0; i < selected.length; i++) {
			strIds.push(selected[i].id);
			if(selected[i].status=="在用"){
				$.ligerDialog.warn('你选择的卡正在使用，不能被删除');
				return; 
			}
		}
		// debugger;
		var id = strIds.join(",");
		// var id = selected.id;
		$.ligerDialog
				.confirm(
						"您确认要删掉这<font color=red>" + selected.length
								+ "</font>条数据吗？",
						function(result) {
							if (result == true) {
								
										$.ajax({
											url : basePath
													+ "rest/mauRfidCardManageAction/toDelete?id="
													+ id,
											type : "post",
											dataType : "json",
											// data:data,
											success : function(data) {
												$.ligerDialog.success('删除成功');
												//$(".l-bar-button.l-bar-btnload",window.parent.document)
													//	.click();
											     window.location.reload();
											},
											error : function(data) {
												$.ligerDialog.error('删除失败,发生了未知错误！');
											}
										});
							} else {
								return;
							}
						});
	}
}
$(function() {
	var url=basePath + "rest/" + routeName + "Action/toListPage";
	getDataGrid(url);
	getSelectFunction();
	
});


function getDataGrid(url){

	grid = $("#mauRfidCardManageList").ligerGrid({ 
		title : "Rfid卡信息管理", 
		url : url,
		checkbox : true,
		columns : [ {
			display : '序号',
			name : 'id'
		}, {
			display : 'Rfid卡号',  
			name : 'rfidNO' 
		}, {
			display : '状态',
			name : 'status'
		},  {
			display : '卡类型',
			name : 'materialType'
		},{
			display:"创建时间",
			name:'createDate'
		}
		],
		pageSize : 15,
		width : '100%',
		height : '80%',
		toolbar : {
			items : [/*{
				text : '修改',
				click : itemclick,
				icon : 'modify'
			}, {
				line : true
			},  */{
				text : '增加',
				click : itemclick,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '删除',
				click : itemclick,
				icon : 'delete'
			} ]
		},
		
	});
	
	

}
function getSelectFunction(){
	 $.ajax({
	        url: basePath + "rest/"+ routeName + "Action/getSelectOption",
	        type:"post",
	        dataType:"json",
	        async:false,
	        success: function(json){
	        	var matels=[];
	        	var obj=json.select;
	        	for (var int = 0; int < obj.length; int++) {
	        		var matel = new Object();
	        		/*selectEdName.append( "<option value='"+obj[int]+"'>'"+obj[int]+"'</option>" );*/
	        		matel.id=obj[int];
	        		matel.text=obj[int];
	        		matels.push(matel);
	        		//alert(1);
	        		//debugger;
	        	
				}
	        	form=$("#queryForm").ligerForm({
	       		 inputWidth: 150, labelWidth: 90, 
	       		                  fields: [
	       		                  { name: "id", type: "hidden" },
	       		                  { display: "Rfid卡号", name: "rfidNO", newline: false, type: "text",width:130}, 
	       		                  { display: "卡类型", name: "materialType", newline: false, type: "select", comboboxName: "materialType", options: {data: matels } },
	       		                  { display: "创建起始时间", name: "starttime",editor:{showTime:true}, newline: false, type: "date"}, 
	       		                  { display: "创建结束时间", name: "endtime",editor:{showTime:true}, newline: false, type: "date"}
	       		                  ]
	           });
	       
	        },  
	        error: function(json) {
	            return null;
	        }
	        
	    });
	
};
$("#pageloading").hide();
function reloadData() {
	var pa = $("#" + routeName + "ListForm").serialize();
	// js解码函数
	grid.loadServerData(decodeURIComponent(pa, true));
}
function show(id) {
	top.$.ligerDialog.open({
		url : basePath + "rest/mauHandlingChoresManageAction/toDetailPage?id="
				+ id,
		height : 600,
		width : 1080,
		modal : true,
		title : "详细信息"  
	});
}
function searchForm(){
	var rfidNO=$("[name=rfidNO]").val();
	var materialType=$("[name=materialType]").val();
	var starttime=$("[name=starttime]").val();
	var endtime=$("[name=endtime]").val();
	var jsonQuery="rfidNO="+rfidNO+"&materialType="+materialType+"&starttime="+starttime+"&endtime="+endtime;
	//alert(jsonStr);
		url = basePath + "rest/" + routeName + "Action/toListPage"+ "?" + jsonQuery;
		getDataGrid(url);
}
function resetForm(){
	$("[name=rfidNO]").val("");
	$("[name=status]").val("");
	$("[name=starttime]").val("");
	$("[name=endtime]").val("");
}
