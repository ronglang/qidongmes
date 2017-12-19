var form1;
var grid1;
var grid2;
var form2;
$(function() {
	loadTab();
});

function loadTab(){
	$("#navtab1").ligerTab({
		onAfterSelectTabItem :function (targettabid){
			if(targettabid == "yesProduced"){//已生产
				loadYes();
			}else if(targettabid=="notProduced"){//未生产
				loadNot();
			}
	});
}
/**
 * 加载已生产
 * @returns
 */
function loadYes(){
	if(!form1){
		loadForm1();
	}
	if(!grid1){
		loadGrid1();
	}
}
/**
 * 记载未生产
 * @returns
 */
function loadNot(){
	
}

function loadForm1(){
	
}
function loadGrid1(){
	grid1 = $("#myGrid1").ligerGrid({
		
	});
}

function loadForm2(){
	
}
function loadGrid2(){
	
}