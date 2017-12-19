//定义全局变量
var ligerDialogOpen;
var grid;
var form;
var reset;

$(function(){
	
	loadSearch();
	loadGrid();//加载Grid
	
//	loadSearchButton();//初始化搜索按钮
	
    
});

//function loadSearchButton (){
//	reset = $("#search").ligerButton({
//        width: 50,
//        click: function () {
//        	alert("asd");
//        }
//	});
//    reset.setValue("搜索");//设置ligerButton的显示值
//    
//}
function loadGrid(data){
	grid = $("#myGrid").ligerGrid({
		url : basePath+'rest/storeInventoryManageAction/storeInventoryList',
		height : '100%',
		checkbox :true,
		delayLoad :true,
		parms:data,
		columns : [
//		           {
//			display : 'id',
//			name : 'id',
//			align : 'left',
////			width : 100,
//			minWidth : 10
//		},
		{
			display : '盘点时间',
			name : 'inv_timeFormat',
			minWidth : 30,
		}, {
			display : '盘点物料',
			name : 'inv_materiel',
			minWidth : 30
		}, {
			display : '盘点明细',
			render:function(item,index){
				debugger;
				return "<a href=\"javascript:inv('"+item.inv_materiel+"')\">查看明细</a>";
			}
		}],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			
		},
		pageSize : 10,
//		rownumbers : true,
		toolbar : {
			items : [ {
				text :'添加',
	            click :add,
	            icon :'add'
	          },
	          {text:"编辑",click:edit,icon:"edit"},
	          {text:"删除",click:del,icon:"delete"}
			]
		}
	});
	grid.reload() ;
}
function loadSearch( ){
	$.ajax({
		url: basePath+"rest/storeInventoryManageAction/getMaterielType",
		dataType: 'json',
		data: "",
		type: "post",
		delayLoad:true,
		success:function(data){
			var arr = data.data;
			var datas = [];
			for(var i = 0;i < arr.length;i++){
				var obj = new Object();
				obj.id = arr[i].value;
				obj.text = arr[i].value;
				datas.push(obj);
				
			}
			//options:{format:"yyyy-MM-dd HH:mm:ss"},
			debugger;
			form = $("#search").ligerForm({
				inputWidth: 150, 
				labelWidth: 60, 
				space: 50,
				fields: [
				    { display: "盘点时间", name: "inv_time",   newline :false,icon:'search'
				    	,type:"date"
				    },
				    { display: "盘点材料", id:"inv_materiel",name: "inv_materiel",
				    	newline: false, type: "select", comboboxName: "inv_materiel" ,
				    	options:{data: datas}
				    }
				],
				buttons:[
				         {text:"搜索",click:function(){search();}}
				         ]
			});
		}
	});
	
//	$("#searchBtn").ligerButton({
//		click: function ()
//        {
//            alert("asda");
//        }
//	});
}
function search(){
	
	var data = form.getData();
	
	data.inv_time = parse(data.inv_time);
	loadGrid(data);
	
}
function inv(id){
	debugger;
	window.open(basePath+"rest/storeInventoryManageAction/getStoreInventoryDetailed?materielType="+id);
}
/**
 * 添加盘点任务
 */
function add(){
	
}
function edit(){}
function del(){}
function test(){
	var val = form.getData();
	debugger;
}

function parse(date){
	if(date==null){
		return "";
	}else {
		var year = date.getFullYear(); 
		var month = (date.getMonth()+1)>=10?(date.getMonth()+1):"0"+(date.getMonth()+1);
		var day = date.getDate()>=10?date.getDate():"0"+date.getDate();
		var dateString = year+"-"+month+"-"+day;
		return dateString;
	}
}