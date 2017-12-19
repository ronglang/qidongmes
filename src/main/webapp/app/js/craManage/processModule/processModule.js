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
				$("#f1").attr("src",basePath+"rest/craSeqParamManageAction/toListPage?id="+data.data.id);
				alert("层级3"+data.data.id);
			}else if(data.data.level == 2){
				$("#f1").attr("src",basePath+"rest/craProSeqRelationManageAction/toListPageCore?proCraftCode="+data.data.id);
				alert("层级2"+data.data.id);
			}
		}
	});	
}

