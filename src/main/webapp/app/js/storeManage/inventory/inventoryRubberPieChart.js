var myChart;
var legends ;
var datas;
var title;
var option;
$(function() {
	myChart = echarts.init(document.getElementById('temp'));
//	createTest();
	$.ajax({
		url: basePath+"rest/storeInventoryManageAction/reqEchartRubberData",
		dataType: 'json',
		data: "materielType="+materielType,
		type: "post",
		delayLoad:true,
		success:function(data){
			debugger;
			if(data.success){
				var vo = data.data;
				loadVo(vo);
			}else{
				alert("请求饼状图数据失败");
			}
		}
	});
});
function loadVo (vo){
	createTitle(vo);
	createLegends(vo);
	createData(vo);
	createOption();
	myChart.setOption(option);
}
function createTest(){
	legends = [ '直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎' ];
	datas = [ {
		value : 335,
		name : '直接访问'
	}, {
		value : 310,
		name : '邮件营销'
	}, {
		value : 234,
		name : '联盟广告'
	}, {
		value : 135,
		name : '视频广告'
	}, {
		value : 1548,
		name : '搜索引擎'
	} ];
}


function createTitle(vo){
	title = vo.title;
}

function createLegends(vo){
	legends = vo.legends;
}
function createData(vo){
	datas = vo.datas;
}
function createOption(){
	debugger;
	option = {
			title : {
				text : title,
//				subtext : '纯属虚构',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : legends
//				,y:50
			},
			series : [ {
				name : '数据比例',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data :datas ,
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
//					,y:1
				}
			} ]
		};
}