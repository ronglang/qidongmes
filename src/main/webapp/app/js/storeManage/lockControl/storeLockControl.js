var grid;
var ligerDialogOpen;
$(function() {
	grid = $("#myGrid").ligerGrid({
		url:basePath+"rest/storeLockControlManageAction/lockControlPageData",
		title:"原材料锁控记录",
		columns : [ {
			display : '订单编号',
			name : 'orderCode',
			align : 'left'
//			width : 120
		}, {
			display : '原材料名称',
			name : 'proName',
//			minWidth : 60
		}, {
			display : '原材料规格型号',
			name : 'proGgxh',
//			width : 50,
			align : 'left'
		}, {
			display : '单轴长度',
			name : 'als',
//			width : 50,
			align : 'left'
		}, {
			display : '轴数',
			name : 'axisNumber',
//			width : 50,
			align : 'left'
		}, {
			display : '锁控操作时间',
			name : 'lockControlTime',
//			minWidth : 140
		}, {
			display : '锁控结束时间',
			name : 'lockEndTime'
		}, {
			display : '生产通知单编号',
			name : 'producNoticeCode'
		} ,{
			display:"操作",
			render: function (row)
		    {
				debugger;
		    	var html = '';
	    		html += "<a href=\"javascript:void(0);\" onclick=\"edit('"+row.id+"');\">编辑</a>"
		        return html;
		    }
		}],
//		data : CustomersData,
		pageSize : 20,
		sortName : 'lockEndTime',
		width : '100%',
		height : '99%',
		checkbox : false
//		rowAttrRender : function(rowdata, rowid) {
//			if (rowdata.CustomerID.indexOf('A') == 0) {
//				return "style='background:#F1D3F7;'";
//			}
//			return "";
//		},
		
	});
});
function edit(id){
	
	debugger;
	
	ligerDialogOpen = $.ligerDialog.open({
		url:basePath+"rest/storeLockControlManageAction/edit?id="+id,
		type:'question' ,
		width :1000,
		height:500,
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
	}) ;
}

function save(i,d){
	debugger;
	var data = d.frame.form.getData();
	$.ajax({
		url: basePath+"rest/storeLockControlManageAction/saveBean",
		dataType: 'json',
		data: data,
		type: "post",
		success:function(data){
			debugger;
		}
	});
}
