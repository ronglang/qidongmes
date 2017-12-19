$(function(){
	 $.ajax({
			  url: basePath+"rest/sellSalesOrderManageAction/getVoList",
			  contentType: "application/json",
			  dataType: 'json',
//			  data: data,
			  type: "post",
			  success:function(data){
				  if(data.success){
					 loadView1(data);
				  }
			  }
			});
	
	
	
	
	
});
function loadView1(data){
	var canvas = document.getElementById("canvas");  
    var ctx = canvas.getContext("2d");  
	var dataList = data.data;
	for(var i =0;i < dataList.length;i++){
		var bean = dataList[i];
		if(bean.type=="矩形"){
			rectangle(ctx, bean.start_x, bean.start_y, bean.width, bean.height, bean.seqName)
		}else if(bean.type=="横线"){
			line_x(ctx, bean.start_x, bean.start_y, bean.line_length);
		}else if(bean.type=="纵线"){
			line_y(ctx, bean.start_x, bean.start_y, bean.line_length)
		}
	}
	ctx.stroke();//绘制边框  
}


function loadView(data){
	var canvas = document.getElementById("canvas");  
    var ctx = canvas.getContext("2d");  
	var dataList = data.data;
	debugger;
	for(var i = 0;i < dataList.length;i++){
		var bean = dataList[i];
//		rectangle(ctx, bean.rectangle_start_x, bean.rectangle_start_y, 100, 50, bean.routeCode);
		var seqNameList = bean.seqNameList;
		var coordinateList = bean.coordinateList;
		var x = bean.rectangle_start_x;
		for(var j = 0;j < seqNameList.length;j++){
			var seqName = seqNameList[j];
			var cond = coordinateList[j];
//			alert(cond);
//			alert(cond[0]);
//			alert(seqName+";x:"+cond[0]);
			rectangle(ctx,cond[0],  bean.rectangle_start_y, 100, 50, seqName);
			
			if(j!=seqNameList.length-1){
				line_x(ctx, cond[0]+100, bean.rectangle_start_y+50/2);
			}else{//当前的最后一个元素
				
				if(bean.level!=1){//不是一级，都要在结尾画横线，
					var count = seqNameList.length;//矩形个数
					line_x(ctx, cond[0]+100, bean.rectangle_start_y+50/2);
				}
				if(bean.is_y){
					alert(seqName);
					if(bean.is_y){
						line_x(ctx, cond[0]+100+100, bean.rectangle_start_y+50/2);
						line_y(ctx,cond[0]+100+100, bean.rectangle_start_y+50/2);
					}
				}
			}
			
//			x = x+100;
		}
	}
	
	ctx.stroke();//绘制边框  
}

function rectangle(ctx,x,y,x1,y1,str){
	ctx.rect(x,y,x1,y1);
	ctx.fillText(str, x, y+y1/2);
}
	
function line_x(ctx,x,y,length){
	ctx.moveTo(x, y);
    ctx.lineTo(x+length, y);
}
function line_y(ctx,x,y,length){
	ctx.moveTo(x, y);
    ctx.lineTo(x, y+length);
}