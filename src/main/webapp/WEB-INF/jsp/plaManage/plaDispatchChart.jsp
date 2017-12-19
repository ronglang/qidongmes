<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String date = request.getParameter("date");
	String seqName = request.getParameter("seqName");
%>

<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
	<link rel="shortcut icon" href="<%=basePath%>core/img/logos.png">
	
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"src="<%=basePath%>core/js/echarts-all-3.js"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
</head>
<body style="height: 100%; margin: 0">
	<div class="top"
		style="width: 80%;height:360px;margin:0 auto; margin-top:0;border: 1px solid #EBEBEB;">
		<div style="text-align: center;position: absolute;left: 160px;z-index: 999;top: 9px;">
				<form id="form2" action="">
				</form>
		</div>
		<div id="container"
			style="height: 100%;width: 96%;text-align: center;margin: 0 auto"></div>
	</div>
	<div class="top"
		style="width: 80%;height:400px;margin:0 auto; margin-top:0;border: 1px solid #EBEBEB;border-top-color: red">
		<div class="two-a"
			style="width:100%;height: 100%;margin:0 auto; margin-top:0;margin-left:1px;margin-right:1px;">
			<div class="l-loading" style="display:block" id="pageloading"></div>
 				<div class="l-clear"></div>
    			<div id="maingrid"></div>
		</div>
	</div>
	<script type="text/javascript">
       var cdate = "<%=date%>";
       var cseqName = "<%=seqName%>";
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;
		var dataAxis;
		var data,form;
		var yMax = 24;
		var dataShadow = [];
	$(function(){
		$.ajax({
			url :'<%=basePath%>rest/plaDispatchManageAction/getPlanDisPatchCanBar?date='
								+ cdate + "&seqName=" + cseqName,
						type : 'POST',
						dataType : 'JSON',
						success : function(vo) {
							var bean = new Object();
							bean.mounth = cdate;
							bean.seqName = cseqName;
							forms(cdate,cseqName);
							setDatas(bean);
							item(vo);
							grid();
						}

					});
		});

		function item(vo){
			dataAxis = vo.barX; //获取X轴数据
			data = vo.barY; //获取Y轴数据
			getChart();
		}
	
		function getBar() {

			option = {
				title : {
					left : 'center',
					text : '机台负荷柱状图',
					subtext : 'SMTC'
				},
				tooltip : {
					trigger : 'axis',
				},
				grid : {
					top : '25%',
					containLabel : true
				},
				xAxis : {
					name:'机台名称',
					data : dataAxis,
					type : 'category',
					boundaryGap : true,
				},
				yAxis : {
					name:'工作时间(h)',
					axisLabel : {
						textStyle : {
							color : '#999'
						}
					}
				},
				toolbox : {
					show : true,
					right:'5%',
					feature : {
						saveAsImage : {
							show : true
						}
					}
				},
				dataZoom : [ {
					type : 'inside'
				} ],
				series : [ { // For shadow
					type : 'bar',
					barGap : '-100%',
					barCategoryGap : '40%',
					data : dataShadow,
					animation : false,
				}, {
					type : 'bar',
					itemStyle : {
						normal : {
							label:{show:true},
							color : function(params) {
								var index_color = params.value;
								if (index_color >= 24) {
									return '#EE9A49';
								} else if(index_color == -1){
									return '#8A2BE2';
								}
								else {
									return '#6495ED';
								}

							}
						}
					},
					data : data
				} ]
			};
			return option;
		}

		// Enable data zoom when user click bar.

		function getChart() {
			myChart.on('click', function(params) {
				//alert(params.name);
				grids.set({url:"<%=basePath %>rest/plaProductOrderSeqHourseManageAction/getPlaProductOrderSeqHourse?date="+cdate+"&seqName="+cseqName+"&macCode="+encodeURIComponent(encodeURIComponent(params.name))});
			});
			;
			if (getBar() && typeof getBar() === "object") {
				myChart.setOption(getBar(), true);
			}

		}
		
		
		function forms() {
			//创建表单结构 
			form = $("#form2").ligerForm({
				inputWidth: 150,
				labelWidth: 65,
				space: 20,
				fields: [
					{ display: "查看月份", name: "mounth",newline: false,type: "date",editor:{format:"yyyy-MM-dd",
						onchangedate: function (value)
		                 {
							cdate = $("[name=mounth]").val();
							cseqName = $("[name=seqName]").val();
							debugger;
		                    $.post("<%=basePath%>rest/plaDispatchManageAction/getPlanDisPatchCanBar",{
		                    	date:cdate,
		                    	seqName:cseqName
		                    },function(vo,textStatus){
		                            item(vo);
		                           grids.set({data:[]});
		                     },"json");
	             	 }}},
					{ display: "工序编码", name: "seqName",newline: false,type: "select",editor: {url:'<%=basePath %>rest/craSeqManageAction/getSeqCode',valueField:'text',
						onChangeValue: function (value)
		                 {
							cdate = $("[name=mounth]").val();
							cseqName = $("[name=seqName]").val();
							debugger;
			                    $.post("<%=basePath%>rest/plaDispatchManageAction/getPlanDisPatchCanBar",{
			                    	date:cdate,
			                    	seqName:cseqName
			                    },function(vo,textStatus){
			                           item(vo);
			                           grids.set({data:[]});
			                     },"json");
	             	 }}}
				],
				validate: true
			});

		}
		
		function setDatas(bean){
			form.setData(bean);
		}
		
		var grids;
		function grid(){
			//window['g'] =
           grids= $("#maingrid").ligerGrid({
                height: '100%',
                title:'生产令详情工序、机台、工时拆分表',
                url: '<%=basePath %>rest/plaProductOrderSeqHourseManageAction/getPlaProductOrderSeqHourse?date='+cdate+'&seqName='+cseqName,
                checkbox: true,
                columns: [
                { display: '机台名称', name: 'maccode',width:'9%'  },
                { display: '工序名称', name: 'seqname',width:'9%' },
                { display: '工作日', name: 'workday',width:'7%'},
                { display: '轴名称', name: 'axisname',width:'14%' },
                { display: '产品规格型号', name: 'proggxh',width:'13%' },
                { display: '产品工艺编码', name: 'procraftcode',width:'12%' },
                { display: '每轴线长度', name: 'partlen',width:'6%' },
                { display: '长度单位', name: 'lenunit',width:'5%'},
                { display: '开工日期', name: 'startdate' ,width:'9%' },
                { display: '完工日期', name: 'enddate',width:'8%' },
                { display: '工序用时', name: 'seqhours' ,width:'6%'},
                { hide:'id',name:'id',width:1}
                ],
                toolbar: {
                    items: [
                    { text: '计划调度', click: updateRows, icon: 'modify' },
                    { line: true }
                    ]
                },
                rownumbers: true,
                usePager:false,	
            });
             

            $("#pageloading").hide();
		}
		
		function updateRows(){
			//TODO 修改数据
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	var dataArray = [];
        	if(li.length<1 ){
        		$.ligerDialog.warn('请选择需要调度的行');
        		return;
        	}
        	for(var i = 0; i<li.length;i++){
        		var beans = li[i];
		        var bean = new Object();
        		bean.id = beans.id;
        		dataArray.push(bean);
        	}
        		var ids = JSON.stringify(dataArray);
        		var width = document.body.clientWidth*0.8;
    			var heigth = document.body.clientHeight*.9;
        		$.ligerDialog.open({

    		        height: heigth,

    		        width: width,

    		        title: '添加BOM材料消耗',

    		        url: "<%=basePath %>rest/plaProductOrderSeqHourseManageAction/toFormPage?date="+cdate+"&seqName="+cseqName,

    		        showMax: false,

    		        showMin: false,

    		        isResize: true,

    		        slide: false,

    		        name:'repairAgain',

    		        buttons: [{ text: '保存', onclick: function (item, dialog) {
	    		        var hours = dialog.frame.$("[name=hours]").val();
			        	var macName = dialog.frame.$("[name=macName]").val();
			        	var workDay = dialog.frame.$("[name=workDay]").val();
    		        	debugger;
    		        	if(hours=="" || macName==""||workDay=="" ){
    		        		$.ligerDialog.warn('请将数据填写完整！');
    		        		return ;
    		        	}
    		        	$.ajax({
    		        		url:"<%=basePath %>rest/plaProductOrderSeqHourseManageAction/savePlaProductOrderSeqHourse?ids="+ids+"&hours="+hours+"&macCode="
    		        				+encodeURIComponent(encodeURIComponent(macName))+"&workDay="+workDay+"&nowDate="+cdate,
    		        		type:'POST',
    		        		dataType:'JSON',
    		        		success:function(map){
    		        			if(map.success){
	    		        			dialog.close();
	    		        			$.ligerDialog.success('保存成功');
	    		        			grid();
	    		        			location.reload();
    		        			}else{
    		        				$.ligerDialog.error(map.msg);
    		        			}
    		        		},
    		        		error:function(map){
    		        			$.ligerDialog.error(map.msg);
    		        			dialog.close();
    		        		}
    		        	});
    		        	grid();
    		        }

    		        },
    		            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }]

    		    });
			
		}
		
		
		
	</script>
</body>
</html>

