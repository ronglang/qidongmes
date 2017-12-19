var grid;
var grid2;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#orgHelpVillagePlanManageList").ligerGrid({
			url : basePath + "rest/orgHelpVillagePlanManageAction/dataGridPage",
			data:"orgHelpVillageId="+orgHelpVillageId+"&requestType="+"mapuser",
	        checkbox: false,
						        columns : [
								{
									display : '标题',
									name : 'title',
									width : 120
								},
								{
									display : '部门包村ID',
									name : 'orgHelpVillageId',
									width : 120
								},
								{
									display : '规划内容',
									name : 'content',
									width : 120
								},
								{
									display : '开始年',
									name : 'startYear',
									width : 120
								},
								{
									display : '结束年',
									name : 'endYear',
									width : 120
								},
								{
									display : '备注',
									name : 'remark',
									width : 120
								}], 
	        pageSize:10,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '60%',height:'200px',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
		
		
		grid2=$("#orgHelpVillageYearplanManageList").ligerGrid({
			url : basePath + "rest/orgHelpVillageYearplanManageAction/dataGridPage",
			data:"orgHelpVillageId="+orgHelpVillageId+"&requestType="+"mapuser",
			checkbox: false,
						        columns : [
								{
									display : '标题',
									name : 'title',
									width : 120
								},
								{
									display : '部门包村ID',
									name : 'orgHelpVillageId',
									width : 120
								},
								{
									display : '规划内容',
									name : 'content',
									width : 120
								},
								{
									display : '年度',
									name : 'planYear',
									width : 120
								},
								{
									display : '备注',
									name : 'remark',
									width : 120
								} ], 
	        pageSize:10,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '60%',height:'200px',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
    });