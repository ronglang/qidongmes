var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#orgHelpVillagePlanManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
						        columns : [
								{
									display : '标题',
									name : 'title',
									width : 250
								},
								{
									display : '开始年',
									name : 'startYear',
									width : 80
								},
								{
									display : '结束年',
									name : 'endYear',
									width : 80
								},
								{
									display : '村',
									name : 'show_areaName',
									width : 220
								}, 
								{
									display : '操作',
									isAllowHide : false,
									width : 120,
									render : function(row) {
										var html = '<a href="#" onclick="javascript:edit('
												+ row.id
												+ ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
//										html += '<a href="#" onclick="javascript:show('
//												+ row.id
//												+ ');return false;">查看</a>';
										return html;
									}
								} ], 
	        pageSize:30,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '100%',height:'97%',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
		$("#add").click(function(){
			top.$.ligerDialog.open({ 
				url: basePath+"rest/orgHelpVillagePlanManageAction/toAddEditPage", 
				height: 600,
				width: 1080,
				modal:true,
				title:"添加"
				/*buttons: [ { text: '确定', onclick: function (item, dialog) { 
						alert(item.text); 
					} 
				}, 
			                                                                                   
				{text: '取消', onclick: function (item, dialog) { 
					dialog.close(); 
				} } 
				]*/ });
		});
    }
);

function edit(id){
	
	top.$.ligerDialog.open({ 
		url: basePath+"rest/orgHelpVillagePlanManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"编辑"
		});
//	findYsarTable(id);
}

function findYsarTable(oid){
	$.ajax({
		type : 'post',
		url : basePath+"rest/orgHelpVillageYeartargetManageAction/listByEntity",
		data: "id="+oid,
		dataType : 'json',
		error : function() {
		},
		success : function(json) {
			//alert(json.data.year);
			var out = "<tr><td>年份</td><td>减贫计划(人)</td><td>农民人均纯收入</td><td>集体经济收入</td></tr>";
			datas = json.data;
			for(var i = 0;i<datas.length;i++){
				//alert(datas[i].year);
				out += "<tr><td>"+(datas[i].year)+"</td><td><input value='"+datas[i].offPoorNum+"' type='text'></td><td><input value='"+datas[i].peopleIncome+"' type='text'></td><td><input  value='"+datas[i].groupIncome+"' type='text'></td></tr>";
			}
			$("#guiHuaTable").html(out);
		}
	});
}

//function reloadData(){
//	var pa = $("#"+routeName+"ListForm").serialize();
//	grid.loadServerData(decodeURIComponent(pa,true));
//}

//function reloadData(arr,isReset){
//	var pa;
//	if(!isReset){
//		pa = $("#"+routeName+"ListForm").serialize();
//		var pas = $("#title").val();
//		var pas2 = $("#areaCode").val();
//		grid.loadServerData(decodeURIComponent(pa,true));
//		grid.setParm("title",pas);
//		grid.setParm("areaCode",pas2);
//	}else
//	{
//		$("#title").val("");
//		$("#areaCode").val("");
//		$("#areaCodeShow").val("");
//		pa = $("#"+routeName+"ListForm").serialize();
//		grid.loadServerData();
//		grid.setParm("title","");
//		grid.setParm("areaCode","");
//	}
//}


function show(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/orgHelpVillagePlanManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"详细信息"
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

