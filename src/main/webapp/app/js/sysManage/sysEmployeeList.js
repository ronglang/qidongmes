var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#sysEmployeeManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
			
	        checkbox: true,
						        columns : [
								{
									display : '姓名',
									name : 'name',
									width : 120
								},
								{
									display : '所在机构',
									name : 'workUnit',
									width : 120
								},
							/*	{
									display : '政治面貌',
									name : 'zzmms',
									width : 120
								},
								{
									display : '健康状况',
									name : 'statusName',
									width : 120
								},*/
								{
									display : '性别',
									name : 'sex',
									width : 120
								},
								/*{
									display : '学历',
									name : 'educations',
									width : 120
								},
								{
									display : '学位',
									name : 'degrees',
									width : 120
								},
								{
									display : '学校名称',
									name : 'schoolName',
									width : 120
								},
								{
									display : '毕业时间',
									name : 'graduateTime',
									width : 120
								},
								{
									display : 'EMAIL',
									name : 'email',
									width : 120
								},*/
//								{
//									display : '民族',
//									name : 'nations',
//									width : 120
//								},
//								{
//									display : '籍贯',
//									name : 'nativePlace',
//									width : 120
//								},
								/*{
									display : '出生地',
									name : 'birthPlace',
									width : 120
								},
								{
									display : '现居地',
									name : 'currentPlace',
									width : 120
								},
								{
									display : '工作时间',
									name : 'hiredDate',
									width : 120
								},*/
								{
									display : '身份证号',
									name : 'idNo',
									width : 120
								},
								/*{
									display : '婚姻状况',
									name : 'maritalStatusName',
									width : 120
								},
								{
									display : '出生日期',
									name : 'birthDate',
									width : 120
								},*/
								{
									display : '手机',
									name : 'tel',
									width : 120
								},
								/*{
									display : '办公电话',
									name : 'officeTel',
									width : 120
								},
								{
									display : '照片',
									name : 'picture',
									width : 120
								},
								{
									display : '入党时间',
									name : 'joinCommyTime',
									width : 120
								},
								{
									display : '岗位级别',
									name : 'levels',
									width : 120
								},*/
								{
									display : '职位',
									name : 'position',
									width : 300
								},
								/*{
									display : '维护时间',
									name : 'createDate',
									width : 120
								},
								{
									display : '维护人',
									name : 'createBy',
									width : 120
								},
								{
									display : '备注',
									name : 'remark',
									width : 120
								},*/
								{
									display : '操作',
									isAllowHide : false,
									width : 120,
									render : function(row) {
										var html = '<a href="#" onclick="javascript:edit('
												+ row.id
												+ ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
										html += '<a href="#" onclick="javascript:show('
												+ row.id
												+ ');return false;">查看</a>';
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
			$.ligerDialog.open({ 
				url: basePath+"rest/sysEmployeeManageAction/toAddEditPage", 
				height: 600,
				width: 1000,
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
	$.ligerDialog.open({ 
		url: basePath+"rest/sysEmployeeManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 1000,
		modal:true, 
		title:"编辑"
		/*buttons: [ { text: '确定', onclick: function (item, dialog) { 
				alert(item.text); 
			} 
		}, 
	                                                                                   
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		] */});
}

function show(id){
	$.ligerDialog.open({ 
		url: basePath+"rest/sysEmployeeManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1000,
		modal:true, 
		title:"详细信息"
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

