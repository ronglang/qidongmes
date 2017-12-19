var tree;
$(function(){
	loadTree();
});


function loadTree(){
	$("#myTree").ligerTree({
		url: basePath+"rest/processModuleManageAction/toTree",  
		nodeWidth : 100,
//		onBeforeExpand: onBeforeExpand,
		isExpand :true,
		checkbox :false,
		parentIcon :'',
		onClick :function(data,target){
			debugger;
//			alert(data.data.id);
//			alert("层级"+data.data.level);
			if(data.data.level==3){//层级为3,跳到材料初始化页面
				$("#f1").attr("src",basePath+"rest/craBomProductMaterManageAction/toListPage1?relationId="+data.data.id);
			}else if(data.data.level == 2){//层级为2
				$("#f1").attr("src",basePath+"rest/craProSeqRelationManageAction/toListPageCore?proCraftCode="+data.data.id);
			}
		}
	});	
}

