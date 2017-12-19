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
			onClick: function(e, treeId, treeNode) {
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
		}
};
//普通树的配置
var treeSetting = {	
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
			onClick: onTreeClick
		}
};

function onTreeClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId),
	nodes = zTree.getSelectedNodes(),
	v = "";
	h = "";
	var proUnit = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		h += nodes[i].id + ",";
		if(nodes[i].proUnit){
			proUnit += nodes[i].proUnit + ",";
		}
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (h.length > 0 ) h = h.substring(0, h.length-1);
	if (proUnit.length > 0 ) proUnit = proUnit.substring(0, proUnit.length-1);
	if(treeId.indexOf("ContentTree")>-1){
		var inputId = treeId.split("ContentTree")[0];
		var cityObj = $("#"+inputId);
		cityObj.attr("value", h);
		var cityObjSHOW = $("#"+inputId+"Show");
		cityObjSHOW.attr("value", v);
		setName(v);
		
		//级联单位
		if(proUnit){
			$('#scaleUnit').val(proUnit);
			var zTree = $.fn.zTree.getZTreeObj("scaleUnitContentTree");
			if(zTree){
				var node = zTree.getNodeByParam("id",proUnit);
				if(node){
					$('#scaleUnitShow').val(proUnit);
				}
			}
		}
		
		if(inputId == 'project'){
			var id = nodes[0].id + '';
			if(id != null && id != '205' && id.length == 3){
				top.$.ligerDialog.warn("不能选择大类。");
				$('#project').val('');
				$('#projectShow').val('');
				return false;
			}
		}
		
		//if(inputId == 'proType'){
		if(inputId == 'fiveBatch'){
			//var v = $('#proType').val();
			//其它
			if(h == '其他'){
				$('#span_oth1').show();
				$('#span_oth2').show();
				$('#span_xm1').hide();
				$('#span_xm2').hide();
				
				$('#project').val('');
				$('#projectShow').val('');
			}
			//五个一批 
			else{
				$('#span_oth1').hide();
				$('#span_oth2').hide();
				$('#span_xm1').show();
				$('#span_xm2').show();
				
				$('#othProject').val('');
				$('#project').val('');
				$('#projectShow').val('');
				
				var f = $('#fiveBatch').val();
				if(f){
					//var urlAndWhere = basePath +"rest/hProjectManageAction/nodeTree?fiveBatch="+f;
					var urlAndWhere = basePath +"rest/hProjectManageAction/nodeTree";
					creatTreeDiv("project");
					//initTree('project','','common','','',urlAndWhere,treeSetting);
					initTree_data('project','','common','','',urlAndWhere,treeSetting,"&fiveBatch="+f);
					
					//查询家庭成员五个一批
					var url = basePath + "rest/vBpoorProperManageAction/queryFamilyFiveBatchStep?pidNumber="+idNumber+"&dataYear="+dataYear;
					//+ "&fiveBatch="+f ;
	        		grid1.set({data:[]});
		        	var u = '&fiveBatch='+f;
		        	grid1.loadServerData(u);
		        	grid1.setParm('pidNumber',idNumber);
		        	grid1.setParm('dataYear',dataYear);
		        	grid1.setParm('fiveBatch',h);
				}
			}
		}
		if(inputId == 'project'){
			var f = $('#fiveBatch').val();
			var p = $('#project').val();
			if(f && p){
				//查询家庭成员五个一批
				var url = basePath + "rest/vBpoorProperManageAction/queryFamilyFiveBatchStep?pidNumber="+idNumber+"&dataYear="+dataYear;
        		grid1.set({data:[]});
	        	var u = '&fiveBatch='+f+'&project='+p;
	        	grid1.loadServerData(u);
	        	grid1.setParm('pidNumber',idNumber);
	        	grid1.setParm('dataYear',dataYear);
	        	grid1.setParm('fiveBatch',h);
	        	gird1.setParm('project',p);
			}
		}
		if(inputId == 'filingYear'){
			var f = $('#fiveBatch').val();
			var p = $('#project').val();
			var y = $('#filingYear').val();
			if(f && p){
				//查询家庭成员五个一批
				var url = basePath + "rest/vBpoorProperManageAction/queryFamilyFiveBatchStep?pidNumber="+idNumber+"&dataYear="+dataYear;
        		grid1.set({data:[]});
	        	var u = '&fiveBatch='+f+'&project='+p+'&filingYear='+y;
	        	grid1.loadServerData(u);
	        	grid1.setParm('pidNumber',idNumber);
	        	grid1.setParm('dataYear',dataYear);
	        	grid1.setParm('fiveBatch',h);
	        	gird1.setParm('project',p);
	        	grid1.setParm('filingYear',y);
			}
		}
	}
}

var gird,grid1;
$(function ()
    {
        $.metadata.setType("attr", "validate");
        var v = $("#"+routeName).validate({
            debug: true,
            errorPlacement: function (lable, element)
            {
                if (element.hasClass("l-textarea"))
                {
                    element.ligerTip({ content: lable.html(), target: element[0] }); 
                }
                else if (element.hasClass("l-text-field"))
                {
                    element.parent().ligerTip({ content: lable.html(), target: element[0] });
                }
                else
                {
                    lable.appendTo(element.parents("td:first").next("td"));
                }
            },
            rules: {
				ID_NUMBER:{required:true},MULLTI_YEAR:{required:true},DATA_YEAR:{required:true},FILING_YEAR:{required:true},
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitHPoorExecute();
            }
        });
        var v2 = $("#bPoorProperManageForm").validate({
            debug: true,
            errorPlacement: function (lable, element)
            {
                if (element.hasClass("l-textarea"))
                {
                    element.ligerTip({ content: lable.html(), target: element[0] }); 
                }
                else if (element.hasClass("l-text-field"))
                {
                    element.parent().ligerTip({ content: lable.html(), target: element[0] });
                }
                else
                {
                    lable.appendTo(element.parents("td:first").next("td"));
                }
            },
            rules: {
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#bPoorProperManageForm .l-text,.l-textarea").ligerHideTip();
                //submitHPoorExecute();
            }
        });
        
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        creatTreeDiv("scaleUnit");initTree("scaleUnit","sysCommdic","radio","clas","规模单位");
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
			
       }
       
       
       grid=$("#hPoorExecuteManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/queryExecuteList?idNumber="+$('#idNumber').val()+"&dataYear="+dataYear,
	        checkbox: false,
	        usePager:false,
	        InWindow : true,
	        shrinkToFit:true,
	        columns: [
	            { display: 'ID', name: 'id', hide:true,width:1},
//	            { display: 'project', name: 'project',  width: 100 },
	            { display: '数据年份', name: 'dataYear',   hide:false ,width:60},
	            { display: '管理', width:90,
	            	render:function(row)
	            		 {
	            			if(row.mulltiYear == '合计：')
	            				return '合计：';
	            			else{
	            				var html = "<a href='#' onclick='javascript:shangbaojindu(&quot;"+row.project+"&quot;,&quot;"+encodeURI(row.step)+"&quot;,&quot;"+row.scale+"&quot;,&quot;"+row.scaleUnit+"&quot;," + row.id + ",&quot;"+row.idNumber+"&quot;,&quot;"+row.fiveBatch+"&quot;,&quot;"+row.projectName+"&quot;);return false;'>上报进度</a>";
	            				return html;
	            			}
	                     }
	            },
	            { display: '贫困户主身份证', name: 'idNumber', width:140},
	            { display: '五个一批', name: 'fiveBatch',  width: 120 },
	            /*{ display: '建设年度', name: 'buildYear',  width: 100 },*/
	           /* { display: '建设截止年度', name: 'endYear',  width: 100 },*/
	            { display: '实施年度', name: 'filingYear',  width: 60 },
	            { display: '项目', name: 'projectName',  width: 120 /*,
	            	render: function(row,rownum,value){
	            		if(!value){
	            			return row.othProject;
	            		}
	            		else{
	            			return value;
	            		}
	            	}*/
	            },
	            { display: '规模', name: 'scale',  width: 60 },
	            { display: '单位', name: 'scaleUnit',  width:40},
	            { display: '实施计划（元）',columns:[
					{ display: '总投资计划', name: 'totlePlan',  width:80},
					{ display: '政府补助计划', name: 'govPlan',  width:80},
					{ display: '社会帮扶计划', name: 'societyPlan', width:80},
					{ display: '扶贫信贷计划', name: 'creditPlan', width:80},
					{ display: '农户自筹计划', name: 'farmerPlan', width:80},
					{ display: '收入计划', name: 'incomePlan',  width:80}               
	            ]},
	            { display: '是否完成', name: 'isComplete',  width: 70 },
	            { display: '进度完成（元）',columns:[
                    { display: '总投资', name: 'totleComplete',  width:80},
       	            { display: '政府补助', name: 'govComplete',   width:80},
       	            { display: '社会帮扶', name: 'societyComplete',   width:80},
       	            { display: '扶贫信贷', name: 'creditComplete',   width:80},
       	            { display: '农户自筹', name: 'farmerComplete',   width:80},
       	            { display: '收入', name: 'incomeComplete',  width:80}
	            ]},
	            { display: '帮扶措施', name: 'step',  width:150 },
	            { display: '年度', name: 'mulltiYear',  hide:true,width:1},
	            { display: '实施项目', name: 'project',  hide:true,width:1},
	            { display: '备注', name: 'remark',   hide:true ,width:1},
	            { display: '维护时间', name: 'createDate',  hide:true,width:1},
	            { display: '项目类型', name: 'proType',  width: 1,hide:true },
	            { display: '维护人', name: 'createBy',   hide:true,width:1}
	        ], 
	        //pageSize:10,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '100%',
	        height:'200px',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
	        onSelectRow:function(row, idx, rowDomElement){
	        	if(row.mulltiYear == '合计：'){ 
	        		$('#div_input').css('display','none');
	        		return false;
	        	}
	        	$('#div_input').css('display','block');
	        	
	        	$('#hPoorExecuteManage')[0].reset();
				$('#id').val('');
	        	
	        	//行选择，编辑
	        	var obj = new Object();
	        	obj.data = row;
	        	$('#project').val('');
				$('#projectShow').val('');
	        	initSuccess('hPoorExecuteManage',obj);
	        	
	        	var h = row.fiveBatch;
	        	var p = row.project;
	        	if(h == '其他'){
	        		$('#span_oth1').show();
					$('#span_oth2').show();
					$('#span_xm1').hide();
					$('#span_xm2').hide();
	        	}
	        	else{
					$('#span_oth1').hide();
					$('#span_oth2').hide();
					$('#span_xm1').show();
					$('#span_xm2').show();
					
					if(h){
						//var urlAndWhere = basePath +"rest/hProjectManageAction/nodeTree?fiveBatch="+h;
						var urlAndWhere = basePath +"rest/hProjectManageAction/nodeTree";
						creatTreeDiv("project");
						//initTree('project','','common','','',urlAndWhere,villageCodeTreeSetting);//treeSetting);
						initTree_data('project','','common','','',urlAndWhere,villageCodeTreeSetting,"&fiveBatch="+h);
						
						selectedProject = row.project;
						selectedProjectName = row.projectName;
						
						$('#project').val(selectedProject);
						$('#projectShow').val(selectedProjectName);
					}
				}
	        	
	        	if(h){
	        		var url = basePath + "rest/vBpoorProperManageAction/queryFamilyFiveBatchStep?pidNumber="+idNumber+"&dataYear="+dataYear ;
	        		//+"&fiveBatch="+h ;
	        		//不加建设年度：录入方案时，可以更新选择的五个一批表的计划年度。
	        		// + "&filingYear="+row.buildYear;
	        		grid1.set({data:[]});
		        	//grid1.set({url:url});
		        	//grid1.setParm('pidNumber',idNumber);
		        	//grid1.setParm('dataYear',dataYear);
		        	//grid1.setParm('fiveBatch',h);
	        		var filingYear = row.filingYear;
		        	var u = '&fiveBatch='+h+'&project='+p+'&filingYear='+filingYear;
		        	grid1.loadServerData(u);
		        	grid1.setParm('pidNumber',idNumber);
		        	grid1.setParm('dataYear',dataYear);
		        	grid1.setParm('fiveBatch',h);
		        	grid1.setParm('project',p);
		        	grid1.setParm('filingYear',filingYear);
				}
	        	
	        	checkedData = [];
	        	addChecked(row.id);
	        }
	    });
       
       grid1=$("#bPoorProperManageList").ligerGrid({
			url : basePath + "rest/vBpoorProperManageAction/queryFamilyFiveBatchStep?pidNumber="+idNumber+"&dataYear="+dataYear,
	        checkbox: true,
	        isMultiSelect:false,
	        rownumbersColWidth:60,
	        height:'200px',
	        //headerRowHeight:24,
	        InWindow : true,
	        shrinkToFit:true,
	        columns: [
	            { display: 'ID', hide:false,width:100,
	            	render : function(row,rownum,value){
	        		   if(row.project){
	            			return row.id;
	            		}
	            		else{
	            			return '';
	            		}
	        	   }
	            },
	            { display: '成员身份证', name: 'idNumber',width:140},
	            { display: '成员名称', name: 'fname',width:70},
	            { display: '户主身份证', name: 'pidNumber',width:140},
	            { display: '户主名称', name: 'name',width:70},
	           /* { display: '预脱贫时间', name: 'expectOffTm',  width: 100,
	            	render:function(row,idx,v){
	            		if(v == "0")
	            			return "-";
	            		else
	            			return v;
	            	}
	            },*/
	            { display: '五个一批', name: 'fiveBatch',  width: 120 ,
	            	render:function(row,rownum,value){
	            		if(!value){
	            			return row.fiveBatch;
	            		}
	            		else
	            			return value;
	            	}
	            },
	           { display: '项目', name: 'proName',  width: 100 },
	           { display: '项目', name: 'project',  width: 1,hide:true },
	           /* { display: '计划实施年度', name: 'planYear',  width: 140 },*/
	            { display: '实施年度',  width: 140,
	        	   render : function(row,rownum,value){
	        		   if(row.project){
	            			return row.implYear;
	            		}
	            		else{
	            			return '';
	            		}
	        	   }
	            },
	           /* { display: '是否已落实到人', width: 140,
	            	render:function(row,rownum,value){
	            		if(row.planYear){
	            			return "是";
	            		}
	            		else{
	            			return "否";
	            		}
	            	}
	            },*/
	            { display: '分年实施是否落实', width: 140,
	            	render:function(row,rownum,value){
	            		if(row.implYear && row.project){
	            			return "是";
	            		}
	            		else{
	            			return "否";
	            		}
	            	}
	            },
	            { display: '数据年度', name: 'dataYear',  hide:true,width:1},
	            { display: '维护时间', name: 'createDate',  hide:true,width:1},
	            { display: '维护人', name: 'createBy', hide:true,width:1}
	        ], 
	        pageSize:100,
	        usePager:false,
	        //isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '100%',
	        //height:'200',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
				$("#pageloading").hide();
				if(json && json.responseText){
					var o = eval('['+json.responseText+']');
					if(o && o.length > 0){
						var msg = o[0].msg;
						if(msg)
							top.$.ligerDialog.warn(o[0].msg);
					}
				}
	        },
	        onSelectRow:function(row, idx, rowDomElement){
	        }
	    });
       
       $("#109003002_2_btn_add").click(function(){
			//$('#hPoorExecuteManage')[0].reset();
    	    $('#div_input').css('display','block');
			$('#id').val('');
			//新增按钮时，保留只读项目
			$('#scale').val('');
			$('#totlePlan').val('');
			$('#govPlan').val('');
			$('#societyPlan').val('');
			$('#totleComplete').val('');
			$('#govComplete').val('');
			$('#societyComplete').val('');
			$('#creditPlan').val('');
			$('#farmerPlan').val('');
			$('#creditComplete').val('');
			$('#farmerComplete').val('');
			$('#incomePlan').val('');
			$('#incomeComplete').val('');
			
			$('#filingYear').val($('#dataYear').val());
			$('#filingYearShow').val($('#dataYear').val());
		});
       
       $('#createDate').ligerDateEditor({format:'yyyy-MM-dd hh:mm:ss'});
        creatTreeDiv("mulltiYear");
		initTree("mulltiYear","sysCommdic","common","clas","党员帮户年度");
		creatTreeDiv("filingYear");
		initTree("filingYear","sysCommdic","common","clas","年度",treeSetting);
		//creatTreeDiv("proType");
		//initTree("proType","sysCommdic","common","clas","帮扶方案项目类型",'',treeSetting);
		creatTreeDiv("fiveBatch");
		initTree("fiveBatch","sysCommdic","common","clas","五个一批2",'',treeSetting);
		creatTreeDiv("project");
		initTree("project","hProject","common","code","2",'',treeSetting);
		creatTreeDiv("scaleUnit");
		initTree("scaleUnit","sysCommdic","common","clas","规模单位");
		
		$('#filingYear').val($('#dataYear').val());
		$('#filingYearShow').val($('#dataYear').val());
		
		$('#span_oth1').hide();
		$('#span_oth2').hide();
		
		$('#govPlan').change(function(){
			inChange();
		});
		$('#govComplete').change(function(){
			inChange();
		});
		$('#societyPlan').change(function(){
			inChange();
		});
		$('#societyComplete').change(function(){
			inChange();
		});
		$('#creditPlan').change(function(){
			inChange();
		});
		$('#creditComplete').change(function(){
			inChange();
		});
		$('#farmerPlan').change(function(){
			inChange();
		});
		$('#farmerComplete').change(function(){
			inChange();
		});
		
		$('#109003002_2_btn_save').click(function(){
			$('#hPoorExecuteManage').submit();
		});
    });  


/**
 * 提交数据
 */
function submitHPoorExecute() {
	var r = grid.getSelectedRow();
	if(r && r.mulltiYear == '合计：'){
		alert("不能操作合计行");
		return false;
	}
	
	var parm = "";
	/*if (row_id) {
		//	加入查询参数，进行update参数
		parm = "id=" + row_id;
	} */
	
	var proType = $('#proType').val();
	var othProject = $('#othProject').val();
	if(proType == '其它'){
		if(!othProject){
			alert("请录入其它项目的内容");
			return false;
		}
	}
	
	var rows = grid1.getSelecteds();
	var str = "";
	if(rows.length > 0){
		for(var i = 0 ; i < rows.length ;i ++){
			/*if(!rows[i].planYear){
				str = str + rows[i].idNumber+'@'+rows[i].fiveBatch+',';
			}*/
			
			//允许修改五个一批的计划年度 
			/*if(rows[i].idNumber && rows[i].fiveBatch){
				var id = rows[i].id;
				if(!id || id=="null") id='';
				str = str + rows[i].id+',';
			}*/
			
			if(rows[i].idNumber && rows[i].fiveBatch){
				var id = rows[i].id;
				if(!id || id=="null") id='';
				str = str + id+'@'+rows[i].idNumber+'@'+rows[i].fiveBatch+',';
			}
		}
		if(str.length == 0){
			/*if(!confirm("选择的五个一批已存在数据，确认不录入其它家庭成员?")){
				return ;
			}*/
			if(!confirm("没选择五个一批，确认不录入?")){
				return ;
			}
		}
	}
	else{
		if(!confirm("没选择五个一批，确认不录入?")){
			return ;
		}
	}
	
	$('#idNumbers').val(str);
	
	/*var filingYear = $('#filingYear').val();
	var startYear = $('#startYear').val();
	var endYear = $('#endYear').val();
	if(filingYear > endYear || filingYear < startYear){
		alert('实施年度('+filingYear+')超出方案开始-截止年度('+startYear+'-'+endYear+')范围');
		return false;
	}*/
	pub_save2(routeName,parm,false);
}

/**
 * 重写提交数据成功后的回调函数
	 */
function submitSuccess(json) {
	if (json.success) {
		alert("保存成功");
		/*if(json.data.id!=null)
			row_id = json.data.id;*/
		
		grid.reload();
		$('#hPoorExecuteManage')[0].reset();
		$('#id').val('');
		
		$('#filingYear').val($('#dataYear').val());
		$('#filingYearShow').val($('#dataYear').val());
		
		grid1.reload();
	} else {
		var msg = json.msg;
		if(msg){
			top.$.ligerDialog.error(msg);
		}
		else if(json.responseText){
			var result = jQuery.parseJSON(json.responseText);
			if(result)
				top.$.ligerDialog.error(result.msg);
			else
				top.$.ligerDialog.error(json.responseText);
		}
		else{
			alert("保存失败");
		}
	}
}

function pub_save2(routeName,parm,isUpload){
	var url = "";
	if(isUpload)  url = basePath + "rest/"+ routeName + "Action/saveFormAndAttach";
	else url = basePath + "rest/"+ routeName + "Action/save2";
	if(parm){
		// update
		url = url +"?"+parm;
	}
	
	
	// 根据routeName 获取传参
	var data =$("#"+routeName).serializeArray();
	// 获取上传文件原名称
	var uploadifyDiVs = $("div:[swfuploadify='true']");
	if(uploadifyDiVs.length>0){
		$.each(uploadifyDiVs,function(i,field){
			var name = this.id;
			var origName = this.innerText;		// 目前只支持div中只有一个文件操作
			var obj = {'name':name};
			obj['value'] = origName;
			data.push(obj);
		});
	}
	if(isUpload){
		if(uploadurl.length > 0 && uploadurlorigname.length > 0){
			uploadurl = uploadurl.substring(0, uploadurl.length-1);
			uploadurlorigname = uploadurlorigname.substring(0, uploadurlorigname.length-1);
		}
		var url_obj = {'name':'uploadurl'};
		url_obj['value'] = uploadurl;
		data.push(url_obj);
		var origname_obj = {'name':'uploadurlorigname'};
		origname_obj['value'] = uploadurlorigname;
		data.push(origname_obj);
	}
	
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:true,
        data:$.param(data),
        success: function(json){
        	submitSuccess(json);
        },  
        error: function(json) {
        	var msg = json.msg;
    		if(msg){
    			top.$.ligerDialog.error(msg);
    		}
    		else if(json.responseText){
    			var result = jQuery.parseJSON(json.responseText);
    			if(result)
    				top.$.ligerDialog.error(result.msg);
    			else
    				top.$.ligerDialog.error(json.responseText);
    		}
    		else{
    			alert("保存失败");
    		}
            //ajaxError(json);
        },
        failure : function(result){
        	alert('保存失败');
        }
    });
}



/**
 * 删除
 */
function pub_del_execute(routeName) {
	var ids = f_getChecked();
	if (ids.length == 0) {
		top.$.ligerDialog.warn("请选择包含实施数据的记录！");
		return;
	}
	var deleteCount=ids.split(",").length;
	// top.$.ligerDialog.confirm('确定要删除选定的数据吗？', function(yes) {
		var yes = window.confirm("确定要删除选定的 "+deleteCount+" 条数据吗?");
		if (yes) {
			var list_ck = $("input:[name='list_ck']");

			var url = basePath + "rest/" + routeName
					+ "Action/delete_execute?ids=" + ids;
			// 提交
			$.ajax({
						url : url,
						type : "post",
						dataType : "json",
						async : true,
						success : function(json) {
							delSuccess(json);
							checkedData = [];//删除成功后清空选中数组
							
							grid.reload();
							$('#hPoorExecuteManage')[0].reset();
							row_id = null;
							$('#id').val('');
						},
						error : function(json) {
							var msg = json.msg;
				    		if(msg){
				    			top.$.ligerDialog.error(msg);
				    		}
				    		else if(json.responseText){
				    			var result = jQuery.parseJSON(json.responseText);
				    			if(result)
				    				top.$.ligerDialog.error(result.msg);
				    			else
				    				top.$.ligerDialog.error(json.responseText);
				    		}
				    		else{
				    			alert("操作失败");
				    			return false;
				    		}
						}
					});
			}
  }

function pub_del3(routeName) {
	var rows = grid1.getCheckedRows();
	var ids = "";
	var str = "";
	if(rows.length > 0){
		for(var i = 0 ; i < rows.length ;i ++){
			if(rows[i].implYear && rows[i].project){
				str = str + rows[i].idNumber+'@'+rows[i].fiveBatch+'@'+rows[i].implYear+'@'+rows[i].pidNumber+'@'+rows[i].dataYear+'@'+rows[i].project+'@'+rows[i].id+',';
			}
			else{
				var ss = rows[i].idNumber+'';
				ids +=  ss + ',';
			}
			
		}
		if(ids.length > 0){
			ss = ids.substring(0,ids.length - 1);
			alert("选择的行（"+ss+"）未实施。无法删除");
			return;
		}
	}
	else{
		alert("请选择记录");
		return;
	}
	//if(ids.length > 0) ids = ids.substring(0,ids.length-1);
	
	//var ids = f_getChecked();
	if (str.length == 0) {
		//alert("请选择要删除的数据");
		top.$.ligerDialog.warn("请选择要删除的数据！");
		return;
	}
	
	str = str.substring(0,str.length - 1);
	var deleteCount=str.split(",").length;
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
		
		var url = basePath + "rest/hPoorExecuteManageAction/delete2?1=1";
		// 提交
		$.ajax({
			url : url,
			data:"fbstr="+str,
			type : "post",
			dataType : "json",
			async : true,
			success : function(json) {
				delSuccess(json);
				
				grid1.reload();
			},
			error : function(json) {
				var msg = json.msg;
				if(msg){
					top.$.ligerDialog.error(msg);
				}
				else if(json.responseText){
					var result = jQuery.parseJSON(json.responseText);
					if(result)
						top.$.ligerDialog.error(result.msg);
					else
						top.$.ligerDialog.error(json.responseText);
				}
				else{
					alert("操作失败");
					return false;
				}
			}
		});
	}
}

/**
 * 默认的提交成功函数 各jsp页面可以自己重写
 */
function delSuccess(json) {
	if(typeof grid != 'undefined'){
		//grid.set({data:[]});
   		//grid.loadServerData();
   		grid.reload();
	}
	if (json.success) {
		top.$.ligerDialog.success("实施数据清除成功！");
	} else {
		var msg = "删除失败！";
		if(json.msg){
			msg = json.msg;
		}
		top.$.ligerDialog.error(msg);
	}
}

function inChange(){
	var totlePlan = 0 ;
	var totleComplete = 0;
	
	var govplan = $('#govPlan').val();
	var govComplete = $('#govComplete').val();
	var societyPlan = $('#societyPlan').val();
	var societyComplete = $('#societyComplete').val();
	var creditPlan = $('#creditPlan').val();
	var creditComplete = $('#creditComplete').val();
	var farmerPlan = $('#farmerPlan').val();
	var farmerComplete = $('#farmerComplete').val();
	if(!govplan || isNaN(govplan)){
		govplan = 0;
	}
	if(!govComplete || isNaN(govComplete)){
		govComplete = 0;
	}
	if(!societyPlan || isNaN(societyPlan)){
		societyPlan = 0;
	}
	if(!societyComplete || isNaN(societyComplete)){
		societyComplete = 0;
	}
	if(!creditPlan || isNaN(creditPlan)){
		creditPlan = 0;
	}
	if(!creditComplete || isNaN(creditComplete)){
		creditComplete = 0;
	}
	if(!farmerPlan || isNaN(farmerPlan)){
		farmerPlan = 0;
	}
	if(!farmerComplete || isNaN(farmerComplete)){
		farmerComplete = 0;
	}
	var m1 = (Number(govplan) + Number(societyPlan) + Number(creditPlan) + Number(farmerPlan)).toFixed(2);
	var m2 = (Number(govComplete) + Number(societyComplete) + Number(creditComplete) + Number(farmerComplete)).toFixed(2);
	
	$('#totlePlan').val(m1);
	$('#totleComplete').val(m2);
}


function shangbaojindu(project,step,scale,scaleUnit,id,pidNumber,fiveBatchName,projectName){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/hPoorExecuteProManageAction/toAddEditPage?pid="+id+"&idNumber="+pidNumber+"&fiveBatch="+encodeURI(fiveBatchName)+"&projectName="+encodeURI(projectName)+"&scale="+encodeURI(scale)+"&scaleUnit="+encodeURI(scaleUnit)+"&step="+step+"&project="+encodeURI(project)+"&dataYear="+dataYear, 
		height: 600,
		width: 1080,
		modal:true,
		title:"上报进度"
		});
}