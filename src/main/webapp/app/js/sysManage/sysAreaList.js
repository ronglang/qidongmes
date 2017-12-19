var tbdArray = new Array();
var grid;
$(function() {
	// pub_initList(routeName);
	grid = $("#sysAreaManageList")
			.ligerGrid(
					{
						url : basePath + "rest/" + routeName
								+ "Action/dataGridPage",
						checkbox : true,
						columns : [
								{
									display : '序号',
									name : 'id',
									width : 120
								//	hide:true
								},
								{
									display : '上级区域ID',
									name : 'pcode',
									width : 120
								},
								{
									display : '区划名称',
									name : 'aname',
									width : 120
								},
								{
									display : '区划代码',
									name : 'areaCode',
									width :120
									//hide:true
								},
								{
									display : '创建人',
									name : 'createBy',
									width : 120
								},
								{
									display : '创建时间',
									name : 'createDate',
									width : 120
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
							     //   usePager: false,
							        isChecked: f_isChecked, 
							        onCheckRow: f_onCheckRow, 
							        onCheckAllRow: f_onCheckAllRow,
							   //     onAfterAppend: function () {g.collapseAll();},
							   //     checkbox:true,
						            alternatingRow: true,
						       //     tree: { columnName: 'aname' },
						       //     onAfterShowData: function() {  
						       //         var l = $(".l-grid-tree-link-open").length;  
						        //        for (var i = l - 1; i >= 0; i--)  
						     //               $(".l-grid-tree-link-open")[i].click();  
						      //      },
						            rownumbers: false,
						width : '100%',
						height : '97%',
						onSuccess : function(json, grid) {
							$("#pageloading").hide();
						},
						onError : function(json, grid) {
							$("#pageloading").hide();
						}
					});
	$("#add").click(function() {
		$.ligerDialog.open({
			url : basePath + "rest/sysAreaManageAction/toAddEditPage",
			height : 600,
			width : 1000,
			modal : true});
	});
});

function edit(id) {
	$.ligerDialog.open({
		url : basePath + "rest/sysAreaManageAction/toAddEditPage?id=" + id,
		height : 600,
		width : 900,
		modal : true});
}

function show(id) {
	$.ligerDialog.open({
		url : basePath + "rest/sysAreaManageAction/toDetailPage?id=" + id,
		height : 600,
		width : 900,
		modal : true});
}
//重写列表页面查询和重置调用方法
function reloadData1(colNameArr,isReset){
	var i=0;
	var pa;
	var value;
	if(!isReset){
		pa = $("#"+routeName+"ListForm").serialize();
		grid.loadServerData(decodeURIComponent(pa,true));
		for(i=0;i<colNameArr.length;i++)
		{
			grid.setParm(colNameArr[i],$("#"+colNameArr[i]).val());
		}
	}else{
		for(i=0;i<colNameArr.length;i++)
		{
		//	alert(111111);	
				grid.setParm(colNameArr[i],'');
				$("#"+colNameArr[i]).val("");
				if($("#"+colNameArr[i]+"Show"))$("#"+colNameArr[i]+"Show").val("");
		
		}
		grid.loadServerData();
	}
	grid.changePage("first");
}

