var tbdArray = new Array();
var grid;
var villageCodeTreeSetting={				
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeTreeClick,
			onClick: myTreeClick
		}
};

function myTreeClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId),
	nodes = zTree.getSelectedNodes(),
	v = "";
	h = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		h += nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (h.length > 0 ) h = h.substring(0, h.length-1);
	if(treeId.indexOf("ContentTree")>-1){
		var inputId = treeId.split("ContentTree")[0];
		var cityObj = $("#"+inputId);
		cityObj.attr("value", h);
		var cityObjSHOW = $("#"+inputId+"Show");
		cityObjSHOW.attr("value", v);
		setName(v);
	}
	var len = (h+"").length;
	if(len != 12){
		//top.$.ligerDialog.warn("请选择村");
	}
	//initTreeCustom("poorIdnumber","basicdataPoorhousehold","normal","areaCode",h,idNumberTreeTreeSetting,true);
}	
$(function ()
    {
		grid=$("#craProSeqRelationManageList").ligerGrid({
			url : basePath + "rest/craProSeqRelationManageAction/dataGridPage",
	        checkbox: true,
	        allowAdjustColWidth :true,
	        delayLoad:false,
	        alternatingRow:true,
	        rownumbers:true,
	        rownumbersColWidth:60,
	        columns: [
	            { display: 'id', name: 'id',width:1,  hide:true},
	            { display: '产品工艺编码', name: 'proCraftCode'},
	            { display: '工序编码', name: 'seqCode'},
	            { display: '工艺路线编码', name: 'routeCode'},
	            { display: '规格型号', name: 'proGgxh'},
	            { display: '颜色', name: 'proColor'},
	            { display: '填报人', name: 'createBy'},
	            { display: '填报时间', name: 'createDate'},
	        ], 
	        
	        
	        pageSize:10,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '99.8%',
	        height:'70%',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
		
		//$("#pageloading").hide(); 
		
		$("#111005_btn_add").click(function(){
			var url = basePath+"rest/bPoorManageAction/toAddEditPage";//height=100, width=400, 
			var p = 'top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no';
			window.open(url);/*,'新增贫困户',p*/
			/*top.$.ligerDialog.open({ 
				url: basePath+"rest/bPoorManageAction/toAddEditPage", 
				height: 600,
				width: 1080,
				modal:true,
				title:"添加"
				});*/
		});

		creatTreeDiv("proId");
		initTreeCustom("proId","mauProduct","normal","","",villageCodeTreeSetting);
		
		creatTreeDiv("proColor");
		initTree("proColor","sysCommdic","common","clas","产品颜色");
		
		creatTreeDiv("seqCode");
		initTree("seqCode","craSeq","common","","",villageCodeTreeSetting);
		
		
		/*if(areaCode){
			var treeObj = $.fn.zTree.getZTreeObj("areaCodeContentTree");
			if(treeObj){
				var node = treeObj.getNodeByParam("id",areaCode);
				if(node){
					treeObj.selectNode(node,false);
					//设置选中节点后右边编辑内容的载入
					myTreeClick(event,"areaCodeContentTree",node,true);
				}
			}
		}*/
		
		//$('#btn_query').click();
    }
);


function generate(){
	$.ajax({
		url:basePath+'rest/craBomTheoryManageAction/initializeTheroy',
		type:'POST',
		dataType:'JSON',
		success:function(map){
			if(map.success){
				$.ligerDialog.confirm('生成成功，是否继续', function (yes) { 
					if(yes){
						window.open(basePath+"rest/craBomTheoryManageAction/toTablePage", "BOM参数");
					}
				});
    			
			}else{
				$.ligerDialog.error("数据错误");
			}
		},
		error:function(){
			$.ligerDialog.error("发生了未知错误");
			//grid();
		}
		
		
		
		
	});
}

function toDetails(){
	window.open(basePath+"rest/craBomTheoryManageAction/toTablePage", "BOM参数");
}



function edit(id,idNumber){
	var url = basePath+"rest/bPoorManageAction/toAddEditPage?id="+id+"&idNumber="+idNumber;
	//window.open(url,'_blank');
	//window.open(url,window);
	window.open(url);
	/*top.$.ligerDialog.open({ 
		url: basePath+"rest/bPoorManageAction/toAddEditPage?id="+id+"&idNumber="+idNumber, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"编辑"
	});*/
}

/*function reloadData(){
	var pa = $("#"+routeName+"ListForm").serialize();
	grid.loadServerData(decodeURIComponent(pa,true));
}*/

function show(id,idNum){
	
	dataYear = $('#dataYear').val();
	/*top.$.ligerDialog.open({ 
		url: basePath+"rest/bPoorManageAction/toDetailPage?show=1&id="+id+"&idNum="+idNum+"&dataYear=" + dataYear,
		height: 600,
		width: 1080,
		modal:true, 
		title:"详细信息"
		});*/
	//户卡
	//var url = basePath+"rest/bPoorManageAction/toDetailPage?show=1&id="+id+"&idNum="+idNum+"&dataYear=" + dataYear;
	//户编辑页面的处理
	var url = basePath+"rest/bPoorManageAction/toAddEditPage?show=1&id="+id+"&idNumber="+idNum+"&dataYear=" + dataYear;
	window.open(url);
} 


function sbtc(rid){
	return false;
}

function initPlaceholderSupport(){
	if(!placeholderSupport()){   // 判断浏览器是否支持 placeholder
	    $('[placeholder]').focus(function() {
	        var input = $(this);
	        if (input.val() == input.attr('placeholder')) {
	            input.val('');
	            input.removeClass('placeholder');
	        }
	    }).blur(function() {
	        var input = $(this);
	        if (input.val() == '' || input.val() == input.attr('placeholder')) {
	            input.addClass('placeholder');
	            input.val(input.attr('placeholder'));
	        }
	    }).blur();
	}
}
function placeholderSupport() {
    return 'placeholder' in document.createElement('input');
}

/**
 * 删除
 */
function pub_del_poor(routeName) {
	var ids = f_getChecked();
	if (ids.length == 0) {
		//alert("请选择要删除的数据");
		top.$.ligerDialog.warn("请选择要删除的数据！");
		return;
	}
	var deleteCount=ids.split(",").length;
	// top.$.ligerDialog.confirm('确定要删除选定的数据吗？', function(yes) {
		var yes = window.confirm("确定要删除选定的 "+deleteCount+" 条数据吗?");
				if (yes) {
					var list_ck = $("input:[name='list_ck']");
					/*var ids = f_getChecked();
					if (ids.length == 0) {
						//alert("请选择要删除的数据");
						top.$.ligerDialog.warn("请选择要删除的数据！");
						return;
					}*/

					var url = basePath + "rest/" + routeName
							+ "Action/delete2?ids=" + ids;
					// 提交
					$.ajax({
								url : url,
								type : "post",
								dataType : "json",
								async : true,
								success : function(json) {
									delSuccess(json);
									checkedData = [];//删除成功后清空选中数组
								},
								error : function(json) {
									ajaxError(json);
								}
							});
				 
				}
			// });
}


/*
//导出功能
function XExportToExcel(){
	//var data =$("#bPoorManageListForm").serializeArray();
	//window.location.href = basePath + "rest/vBpoorManageAction/exportToExcel?"+ $.param(data);
}*/
