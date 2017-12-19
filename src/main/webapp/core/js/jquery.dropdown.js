var maxHeight = 400;

//二级菜单鼠标滑动效果
$(function(){
    $(".nav").hover(function() {
    	$(".nav_dd").css("display","none");
    	$("#dd_" + this.id).css("display","block")
    	$("#" + this.id).css("background-color","white")
    	$("#dd_" + this.id).css("background-color","#60ABDB");
    	$(".span_" + this.id).css("color","#60ABDB")
    	var text = $("#" + this.id).text();
    	
    	//$("#two").html(text);
    },function(){
    	$(".nav").css("background-color","#60ABDB");
    	$(".span_" + this.id).css("color","white")
    });
    
    $(".nav_dd").hover(function() {
    	var pId= this.id.substring(3,6)
    	$("#nav" + pId).css("display","block")
    },function(){
    	$("#dd_" + this.id).css("display","none");
    	$(".nav").css("background-color","#60ABDB");
    });
    
    $(".navs").hover(function() {
    },function(){
    	$(".navs").css("display","none");
    	$(".nav_dd").css("display","none");
    });
    
    $(".child").hover(function() {
    	$(this).css("background-color","white");
    	$(this).css("color","#60ABDB")
    },function(){
    	$(this).css("background-color","#60ABDB");
    	$(this).css("color","white")
    });
    
    
});



//二级菜单点击效果
function hiddenNavTwo(obj,id){
/*	
	$(".nav_dd").css("display","none")
	$("#dd_" + id).css("display","block")
	$(".nav").css("background-color","#f1f1f1")
	$("#" + id).css("background-color","#e5e5e5")
	$("#dd_" + id).css("background-color","#e5e5e5")
	$("#two").html(obj + "&nbsp;&nbsp;&gt;");*/
	//$("#dd_" + obj.id).css("display","none")
}

