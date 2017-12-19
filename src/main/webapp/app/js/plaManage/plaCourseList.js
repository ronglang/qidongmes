//debugger;
var tbdArray = new Array();
var grid;
var ligerDialogOpen;
$(function() {
	// pub_initList(routeName);
	//alert(1);
	var data={};	
	data.wsCode="";
	data.scCode="";
	data.wsType="";
	
	var param=JSON.stringify(data);
	grid = $("#plaCourseManageList")
			.ligerGrid(
					{
						url : basePath + "rest/" + routeName
								+ "Action/plaCourseDataGridPage?param="+param,
						checkbox : false,
						columns : [
								{
									display : '序号',
									name : 'id',
									width : 120
								},
								{
									display : '创建日期',
									name : 'createDate',
									width : 120
								},
								{
									display : '创建人',
									name : 'createBy',
									width : 120
								},
								{
									display : '描述信息',
									name : 'remark',
									width : 120
								},
								{
									display : '工作单类型',
									name : 'wsType',
									width : 120
								},
								{
									display : '工作单编码',
									name : 'wsCode',
									width : 120
								},
								{
									display : '合同编号',
									name : 'scCode',
									width : 120
								},
								{
									display : '批次号',
									name : 'batCode',
									width : 120
								},
								{
									display : '型号规格',
									name : 'headGgxh',
									width : 120
								},
								{
									display : '颜色',
									name : 'color',
									width : 120
								},
								{
									display : '扎装段长',
									name : 'headZzdc',
									width : 120
								},
								{
									display : '扎装段数',
									name : 'headZzds',
									width : 120
								},
								{
									display : '总数量',
									name : 'totalAmount',
									width : 120
								},
								{
									display : '生产通知单ID',
									name : 'manuNoticeId',
									width : 120
								},
								{
									display : '客户id',
									name : 'cusId',
									width : 120
								},
								{
									display : '开单日期',
									name : 'billDate',
									width : 120
								},
								{
									display : '是否完成',
									name : 'isFinish',
									width : 120
								},
								{
									display : '工艺id',
									name : 'craftId',
									width : 120
								},
								{
									display : '审核标志',
									name : 'auditFlag',
									width : 120
								},
								{
									display : '审核时间',
									name : 'auditTime',
									width : 120
								},
								{
									display : '过程名称',
									name : 'courseName',
									width : 120
								},
								{
									display : '启用标志，是否启用',
									name : 'useFlag',
									width : 120
								},
								{
									display : '生产原则',
									name : 'manuTenet',
									width : 120
								},
								{
									display : '过期标志，是否已过期',
									name : 'pastFlag',
									width : 120
								},
								{
									display : '计划启用日期',
									name : 'planEnableDate',
									width : 120
								},
								{
									display : '操作',
									isAllowHide : false,
									width : 120,
									render : function(row) {
										var html = '<a href="javascript:void(0);" onclick="edit('+row.id
												+');">编辑</a>&nbsp;&nbsp;&nbsp;';
										html += '<a href="javascript:void(0);" onclick="show('+row.id+');">查看</a>';
										return html;
									}
								} ],
						pageSize : 10,
						isChecked : f_isChecked,
						onCheckRow : f_onCheckRow,
						onCheckAllRow : f_onCheckAllRow,
						width : '100%',
						height : '80%',
						onSuccess : function(json, grid) {
							$("#pageloading").hide();
						},
						onError : function(json, grid) {
							$("#pageloading").hide();
						}
						,
						/*toolbar :{
							items : [
					          {text:"编辑",click:editUpdate,icon:"edit"},
					          { line: true },
// ,			
// {text:"删除",click:del,icon:"delete"}
							]
						}*/
					});
	$("#add").click(function() {
		top.$.ligerDialog.open({
			url : basePath + "rest/plaCourseManageAction/toAddEditPage",
			height : 600,
			width : 1080,
			modal : true,
			title : "添加"
		/*
		 * buttons: [ { text: '确定', onclick: function (item, dialog) {
		 * alert(item.text); } },
		 * 
		 * {text: '取消', onclick: function (item, dialog) { dialog.close(); } } ]
		 */});
	});
	
	getWsType();
	
});

function plaCourseGrid(url){
	grid = $("#plaCourseManageList")
	.ligerGrid(
			{
				url :url,
				checkbox : false,
				columns : [
						{
							display : '序号',
							name : 'id',
							width : 120
						},
						{
							display : '创建日期',
							name : 'createDate',
							width : 120
						},
						{
							display : '创建人',
							name : 'createBy',
							width : 120
						},
						{
							display : '描述信息',
							name : 'remark',
							width : 120
						},
						{
							display : '工作单类型',
							name : 'wsType',
							width : 120
						},
						{
							display : '工作单编码',
							name : 'wsCode',
							width : 120
						},
						{
							display : '合同编号',
							name : 'scCode',
							width : 120
						},
						{
							display : '批次号',
							name : 'batCode',
							width : 120
						},
						{
							display : '型号规格',
							name : 'headGgxh',
							width : 120
						},
						{
							display : '颜色',
							name : 'color',
							width : 120
						},
						{
							display : '扎装段长',
							name : 'headZzdc',
							width : 120
						},
						{
							display : '扎装段数',
							name : 'headZzds',
							width : 120
						},
						{
							display : '总数量',
							name : 'totalAmount',
							width : 120
						},
						{
							display : '生产通知单ID',
							name : 'manuNoticeId',
							width : 120
						},
						{
							display : '客户id',
							name : 'cusId',
							width : 120
						},
						{
							display : '开单日期',
							name : 'billDate',
							width : 120
						},
						{
							display : '是否完成',
							name : 'isFinish',
							width : 120
						},
						{
							display : '工艺id',
							name : 'craftId',
							width : 120
						},
						{
							display : '审核标志',
							name : 'auditFlag',
							width : 120
						},
						{
							display : '审核时间',
							name : 'auditTime',
							width : 120
						},
						{
							display : '过程名称',
							name : 'courseName',
							width : 120
						},
						{
							display : '启用标志，是否启用',
							name : 'useFlag',
							width : 120
						},
						{
							display : '生产原则',
							name : 'manuTenet',
							width : 120
						},
						{
							display : '过期标志，是否已过期',
							name : 'pastFlag',
							width : 120
						},
						{
							display : '计划启用日期',
							name : 'planEnableDate',
							width : 120
						},
						{
							display : '操作',
							isAllowHide : false,
							width : 120,
							render : function(row) {
								var html = '<a href="javascript:void(0);" onclick="edit('+row.id
										+');">编辑</a>&nbsp;&nbsp;&nbsp;';
								html += '<a href="javascript:void(0);" onclick="show('+row.id+');">查看</a>';
								return html;
							}
						} ],
				pageSize : 10,
				isChecked : f_isChecked,
				onCheckRow : f_onCheckRow,
				onCheckAllRow : f_onCheckAllRow,
				width : '100%',
				height : '80%',
				onSuccess : function(json, grid) {
					$("#pageloading").hide();
				},
				onError : function(json, grid) {
					$("#pageloading").hide();
				}
				,
				/*toolbar :{
					items : [
			          {text:"编辑",click:editUpdate,icon:"edit"},
			          { line: true },
//,			
//{text:"删除",click:del,icon:"delete"}
					]
				}*/
			});
}
function editUpdate(){
	var data = grid.getSelecteds() ;
	if(data.length == 1){
		edit(data[0].id);
	}else if(data.length==0){
		$.ligerDialog.warn("请选择要修改的记录");
	}else if(data.length>1){
		$.ligerDialog.warn("编辑数据时，只能选择一条");
	}
	
}
function edit(id) {
	ligerDialogOpen = $.ligerDialog.open({
		url : basePath + "rest/plaCourseManageAction/toAddEditPage?id=" + id,
		height : 600,
		width : 1080,
		modal : true,
		title : "编辑",
		buttons :[
	         { 
	        	 text: '保存', onclick: function(i,d){save(i,d);}
	         },
	         {
	        	 text:"取消",onclick:function(){
	        		 ligerDialogOpen.close();
	        	 }
	         }
		]
	});
}

function save(i,d){
	var bean = d.frame.form.getData() ;
	var data = bean;
	$.ajax({
		url: basePath+"rest/plaCourseManageAction/saveOrUpdate",
		dataType: 'json',
		data: data,
		type: "post",
		success:function(data){
			if(data.success){
				$.ligerDialog.success("保存成功", "提示内容", function(){});
				ligerDialogOpen.close();
				grid.reload() ;
			}else{
				$.ligerDialog.success("保存失败", "提示内容", function(){});
			}
		}
	
	});
}

function reloadData() {
	var pa = $("#" + routeName + "ListForm").serialize();
	grid.loadServerData(decodeURIComponent(pa, true));
}

function show(id) {
	$.ligerDialog.open({
		url : basePath + "rest/plaCourseManageAction/toDetailPage?id=" + id,
		height : 600,
		width : 1080,
		modal : true,
		title : "详细信息"
	/*
	 * buttons: [ {text: '取消', onclick: function (item, dialog) {
	 * dialog.close(); } } ]
	 */});
}

function conditionSelect(){
	var wsCode=$("#condition input[name='workCode']").val();
	var scCode=$("#condition input[name='contractCode']").val();
	var wsType=$("#condition select[name='wsType']").val();
	
	var data={};	
	data.wsCode=wsCode;
	data.scCode=scCode;
	data.wsType=wsType;
	
	var param=JSON.stringify(data);
	var url= basePath + "rest/" + routeName+ "Action/plaCourseDataGridPage?param="+param;
	plaCourseGrid(url);
}
function getWsType(){
	$.ajax({
		type:"post",
		dataType:"json",
		url:basePath + "rest/" + routeName+ "Action/plaCourseWsType",
		success:function(data){
			var str="";
			str+="<option value=''>--请选择--</option>";
			for(var i=0;i<data.length;i++){
				if(data!=null){
					str+="<option value='"+data[i]+"'>"+data[i]+"</option>";
				}
			}
			
			$("#condition select[name='wsType']").html(str);
		}
	})
}
