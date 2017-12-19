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
			if(data.data.level==3){//层级为3
				//TODO 当点击集绞工序的时候，跳转到指定的grid
//				$("#f1").attr("src",basePath+"rest/craSeqParamManageAction/toListPage?id="+data.data.id);
//				http://localhost:8080/mes/rest/craGatheringModeManageAction/getCraGatheringModeMaterial
				$("#f1").attr("src",basePath+"rest/craGatheringModeManageAction/getCraGatheringModeMaterial?relationId="+data.data.id);
			}else if(data.data.level == 2){
				$("#f1").attr("src",basePath+"rest/craProSeqRelationManageAction/toListPageCore?proCraftCode="+data.data.id);
			}
		}
	});	
}

