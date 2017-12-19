//定义全局变量
var ligerDialogOpen;
var grid;
var form;
var reset;

$(function(){
	
	loadSearch();
	loadGrid();//加载Grid
	
    
});

function loadGrid(data){
	debugger;
	grid = $("#myGrid").ligerGrid({
		url : basePath+'rest/storeInventoryManageAction/getInventoryDetailedData',
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
			display : '材料',
			name : 'materiel'
//				,
//			minWidth : 30,
		}, {
			display : '型号',
			name : 'model'
//				,
//			minWidth : 30
		}, {
			display : '颜色',
			name : 'color'
//				,
//			minWidth : 30
		}, {
			display : '库存量',
			name : 'inventory'
//				,
//			minWidth : 30
		},  {
			display : '出库量',
			name : 'theLibrary'
//				,
//			minWidth : 30
		},{
			display : '入库量',
			name : 'storageNumber'
//				,
//			minWidth : 30
		}, {
			display : '退料量',
			name : 'returnOfMaterial'
//				,
//			minWidth : 30
		}, {
			display : '废料量',
			name : 'waste'
//				,
//			minWidth : 30
		}, {
			display : '盘点人员',
			name : 'inventory_by'
//				,
//			minWidth : 30
		}, {
			display : '盘点时间',
			name : 'inv_time'
//				,
//			minWidth : 30
		}, {
			display : '盘点结果',
			name : 'conclusion'
//				,
//			minWidth : 30
		}, {
			display : '缺料量',
			name : 'lackNumber'
//				,
//			minWidth : 30
		}, {
			display : '盘点数量',
			name : 'inv_count'
//				,
//			minWidth : 30
		}, {
			display : '计量单位',
			name : 'unit'
//				,
//			minWidth : 30
		}],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			
		},
		pageSize : 10
		,
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
		url: basePath+"rest/storeInventoryManageAction/getSearchType",
		dataType: 'json',
		data: "",
		type: "post",
		delayLoad:true,
		success:function(data){
			var materies = [];//材料
			var ggxh = [];//规格型号
			var colors = [];//颜色
			var inventoryResult = [];//盘点结果
			if(data.success){
				var arr = data.data;
				var materielList = arr.材料;
				for(var i = 0;i < materielList.length;i++){
					var obj = new Object();
					obj.id = materielList[i].value;
					obj.text = materielList[i].value;
					materies.push(obj) ;
				}
				var colorList = arr.颜色;
				for(var i = 0;i < colorList.length;i++){
					var obj = new Object();
					obj.id = colorList[i].value;
					obj.text = colorList[i].value;
					colors.push(obj);
				}
				var inventoryList = arr.盘点结果;
				for(var i = 0;i < inventoryList.length;i++){
					var obj = new Object();
					obj.id = inventoryList[i].value;
					obj.text = inventoryList[i].value;
					inventoryResult.push(obj);
				}
				//TODO 规格型号暂时封装
				var obj = new Object();
				obj.id = "GGXH_TG",
				obj.text = "铜杆";
				ggxh.push(obj);
				var obj1 = new Object();
				obj1.id = "GGXH_JL";
				obj1.text = "胶料";
				ggxh.push(obj1);
				debugger;
				form = $("#search").ligerForm({
					inputWidth: 150, 
					labelWidth: 70, 
					space: 50,
					fields: [
					    { display: "材料", name: "materie",  
					    	newline :false,icon:'search',type:"select",comboboxName: "materie" ,
					    	options:{data:materies}
					    },
					    { display: "规格型号", name: "ggxh",  
					    	newline :false,icon:'search',type:"select",comboboxName: "ggxh" ,
					    	options:{data:ggxh}
					    }
					    ,{ display: "颜色", name: "color",  
					    	newline :false,icon:'search',type:"select",comboboxName: "color" ,
					    	options:{data:colors}
					    }
//					    ,
//					    { display: "盘点结果", name: "inventory",  
//					    	newline :false,icon:'search',type:"select",comboboxName: "inventory" ,
//					    	options:{data:inventoryResult}
//					    }
					],
					buttons:[
					         {text:"搜索",click:function(){search();}}
					        ]
				});
			}else{
				alert("获取数据失败");
				return;
			}
//			var datas = [];
//			for(var i = 0;i < arr.length;i++){
//				var obj = new Object();
//				obj.id = arr[i].value;
//				obj.text = arr[i].value;
//				datas.push(obj);
//				
//			}
			//options:{format:"yyyy-MM-dd HH:mm:ss"},
			
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
	debugger;
	data.inv_time = parse(data.inv_time);
	loadGrid(data);
	//TODO 根据原材料类型请求饼状图
	//materielType
//	alert(data.materie);
	$("#iframe").attr("src","rest/storeInventoryManageAction/getEchartRubber?materielType="+data.materie);
	
	
}
function inv(id){
	
}
function add(){}
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