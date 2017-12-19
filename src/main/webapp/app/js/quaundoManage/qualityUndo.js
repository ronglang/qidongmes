var tbdArray = new Array();
var groupicon = "../../../lib/ligerUI/skins/icons/communication.gif";
var form;
var jsonObj={};

$(function() {
	var url=basePath + "rest/" + routeName + "Action/getPage";
	$("#queryForm").ligerForm({
  		 inputWidth: 150, labelWidth: 90, 
  		                  fields: [
  		                  { name: "id", type: "hidden" },
  		                  { display: "起始时间", name: "starttime",editor:{showTime:true}, newline: false, type: "date"}, 
  		                  { display: "结束时间", name: "endtime",editor:{showTime:true}, newline: false, type: "date"}
  		                  ]
      });
	getDataGrid(url);
	
});


function getDataGrid(url){

	grid = $("#qualityUndoList").ligerGrid({ 
		url : url,
		checkbox : false,
		columns : [ {
			display : '序号',
			name : 'indexid'
		}, {
			display : '工单号',  
			name : 'wscode' 
		}, {
			display : '轴名称',
			name : 'axiscode'
		},{
			display:"时间",
			name:'end_date'
		},
	     { display:'回溯',width:90,
			
        	render:function(rows,idx){
            		var ur='<a href="void(0)" onClick=\"javascript:detailInfo(\''+rows.wscode+'\'); return false;\">详情'+'</a>';
        		return ur;
        	}	
        }
		],
		pageSize : 15,
		width : '100%',
		height : '80%',	
	});
	
	

}
$("#pageloading").hide();



function getAllm(){
	var macinfo;
	var jsonstr="";
	var manager = $("#qualityUndoList").ligerGetGridManager();  
	var rows = manager.rows;  
	for (var i = 0, l = rows.length; i < l; i++) {  
		macinfo = rows[i].mac_code ;
		if(i<l-1){
			macinfo = macinfo+";";
		}
		jsonstr = jsonstr + macinfo;
		
	}
	return jsonstr;

	

	
	
}

function searchForm(){
	var starttime=$("[name=starttime]").val();
	var endtime=$("[name=endtime]").val();
	var jsonQuery="&starttime="+starttime+"&endtime="+endtime;
		url = basePath + "rest/" + routeName + "Action/queryPage"+ "?" + jsonQuery;
		getDataGrid(url);
}
function resetForm(){
	$("[name=starttime]").val("");
	$("[name=endtime]").val("");
}


function detailInfo(wscode){
	  
	   var url = basePath + "rest/qualityUndoDetailManageAction/axisPage?wscode="+wscode;
	
	   var w = window.screen.width * 0.8;
	   var h = window.screen.height * 0.5;
	   var p = 'width='+w+',height='+h;
	   $.ligerDialog.open({ 
			url: decodeURI(url), 
			height: 500,
			width: w,
			modal:true, 
			buttons: [ 
			{text: '关闭', onclick: function (item, dialog) { 
				dialog.close(); 
			} } 
			] });
}
