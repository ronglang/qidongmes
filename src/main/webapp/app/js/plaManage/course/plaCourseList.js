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

//debugger;
var tbdArray = new Array();
var grid;
var ligerDialogOpen;
$(function() {
	// pub_initList(routeName);
	grid = $("#plaCourseManageList")
			.ligerGrid({
						url : basePath + "rest/" + routeName+ "Action/getCourseInfoList",
						checkbox : true,
						columns : [
								{display : '序号',name : 'id',width : 1,hide:true},
								{display : '工作单类型',name : 'ws_type',width : 90},
								{display : '工作单编码',name : 'ws_code',width : 120},
								{display : '产品工艺编码',name : 'pro_craft_code',width : 150},
								{display : '产品名称',name : 'pro_name',width : 180},
								{display : '型号规格',name : 'pro_ggxh',width : 150},
								{display : '颜色',name : 'pro_color',width : 60},
								{display : '交货日期',name : 'demand_date',width : 100},
								{display : '段长',name : 'product_part_len',width : 80},
								{display : '轴数',name : 'amount',width : 60},
								{display : '总长度',name : 'total_amount',width : 90},
								{display : '开单日期',name : 'bill_date',width : 120},
								{display : '扎装段长',name : 'head_zzdc',width : 120},
								{display : '扎装段数',name : 'head_zzds',width : 120},
								{display : '是否完成',name : 'is_finish',width : 90},
								{display : '计划是否已分解',name : 'decompose_flag',width : 110,frozen:true,
									render:function(row,idx,val){
										if(!val)
											return '否';
										else
											return val;
									}
								},
								{display : '计划是否已生成',name : 'plan_flag',width : 110,frozen:true},
								{display : '批次号',name : 'bat_code',width : 100},
								{display : '工单进度',name : 'wsSchedule',width : 100},
								{display : '操作',isAllowHide : false,width : 150,frozen:true,
									render : function(row) {
										var html = ''; //<a href="#" onclick="javascript:edit('+ row.id+ ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
										//html += '<a href="void(0);" onClick=\"javascript:genGD('+ row.id +',\''+ row.plan_flag + '\');return false;\">分解</a>&nbsp;&nbsp;&nbsp;';
										//html += '<a href="void(0);" onClick=\"javascript:genGD_detail('+ row.id +',\''+ row.pro_craft_code + '\',\''+row.pro_ggxh+'\',\''+row.pro_color+'\');return false;\">分解明细</a>&nbsp;&nbsp;&nbsp;';
										html += '<a href="void(0);" onClick=\"javascript:genGD_2('+ row.id +',\''+ row.plan_flag + '\');return false;\">生成计划</a>';
										return html;
									}
								}
								],
						pageSize : 10,
						isChecked : f_isChecked,
						onCheckRow : f_onCheckRow,
						onCheckAllRow : f_onCheckAllRow,
						width : '99%',
						height : '97%',
						usePager:true,
						onSuccess : function(json, grid) {
							$("#pageloading").hide();
						},
						onError : function(json, grid) {
							$("#pageloading").hide();
						}/*,
						toolbar :{
							items : [
					          {text:"编辑",click:editUpdate,icon:"edit"},
					          { line: true } //,
					         // {text:"删除",click:del,icon:"delete"}
							]
						}*/
					});
	
	creatTreeDiv("decomposeFlag");
	initTree("decomposeFlag","sysCommdic","common","clas","是否选项");
	creatTreeDiv("planFlag");
	initTree("planFlag","sysCommdic","common","clas","是否选项");
	creatTreeDiv("wsType");
	initTree("wsType","sysCommdic","common","clas","工作单类型");
	creatTreeDiv("priorityWay");
	initTree("priorityWay","sysCommdic","common","clas","开单方式");
	
	$("#add").click(function() {
		top.$.ligerDialog.open({
			url : basePath + "rest/plaCourseManageAction/toAddEditPage",
			height : 600,
			width : 1080,
			modal : true,
			title : "添加"
		/*
		 * buttons: [ { text: '确定', onclick: function (item, dialog) {
		 * alert(item.text); } },
		 * 
		 * {text: '取消', onclick: function (item, dialog) { dialog.close(); } } ]
		 */});
	});
});
function editUpdate(){
	var data = grid.getSelecteds() ;
	if(data.length == 1){
		edit(data[0].id);
	}else if(data.length==0){
		$.ligerDialog.warn("请选择要修改的记录");
	}else if(data.length>1){
		$.ligerDialog.warn("编辑数据时，只能选择一条");
	}
	
}
/*function edit(id) {
	//ligerDialogOpen = top.$.ligerDialog.open({
	ligerDialogOpen = $.ligerDialog.open({
		url : basePath + "rest/plaCourseManageAction/toAddEditPage?id=" + id,
		height : 600,
		width : 1080,
		modal : true,
		title : "编辑",
		buttons :[
	         { 
	        	 text: '保存', onclick: function(i,d){save(i,d);}
	         },
	         {
	        	 text:"取消",onclick:function(){
	        		 ligerDialogOpen.close();
	        	 }
	         }
		]
	});
}*/
	
	function save(i,d){
		var bean = d.frame.form.getData() ;
		var data = bean;
		$.ajax({
			url: basePath+"rest/plaCourseManageAction/saveOrUpdate",
			dataType: 'json',
			data: data,
			type: "post",
			success:function(data){
				if(data.success){
					$.ligerDialog.success("保存成功", "提示内容", function(){});
					ligerDialogOpen.close();
					grid.reload() ;
				}else{
					$.ligerDialog.success("保存失败", "提示内容", function(){});
				}
			}
		
		});
	}
	
	/*function reloadData() {
		var pa = $("#" + routeName + "ListForm").serialize();
		grid.loadServerData(decodeURIComponent(pa, true));
	}*/
	
	function show(id) {
		top.$.ligerDialog.open({
			url : basePath + "rest/plaCourseManageAction/toDetailPage?id=" + id,
			height : 600,
			width : 1080,
			modal : true,
			title : "详细信息"
		/*
		 * buttons: [ {text: '取消', onclick: function (item, dialog) {
		 * dialog.close(); } } ]
		 */});
	}
		
	function genGD(id,status){
		if(status == '是'){
			$.ligerDialog.warn("工单已生成计划，不能再执行分解");
			return;
		}
		/*var ids = f_getChecked();
		if (ids.length == 0) {
			$.ligerDialog.warn("请选择要分解的表单！");
			return;
		}*/
		
		/*var priorityWay = $('input[name="priorityWay_"]:checked').val();
		if(!priorityWay){
			$.ligerDialog.warn("请选择计划生成方式");
			return ;
		}*/
		
		//var deleteCount=ids.split(",").length;
			var url = basePath + "rest/" + routeName + "Action/genPlanByGd?ids=" + id;
			$('#btn_kd').attr('disabled','true');
			
			$.ajax({
				url : url,
				type : "post",
				dataType : "json",
				async : true,
				success : function(json) {
					$('#btn_kd').removeAttr('disabled');
					ajaxSuccess(json);
					grid.reload();
				},
				error : function(json) {
					$('#btn_kd').removeAttr('disabled');
					ajaxError(json);
				}
			});
	}
	
	function genGD_2(id,status){
		if(status == '是'){
			var yes = window.confirm("计划已生成过，确认重新生成么?");
			if(!yes)
				return;
		}
		/*var ids = f_getChecked();
		if (ids.length == 0) {
			$.ligerDialog.warn("请选择要生成计划的的表单！");
			return;
		}*/
		
		/*var priorityWay = $('input[name="priorityWay_"]:checked').val();
		if(!priorityWay){
			$.ligerDialog.warn("请选择计划生成方式");
			return ;
		}*/
		
		//var deleteCount=ids.split(",").length;
		//var yes = window.confirm("确定要生成选定的 "+deleteCount+" 条表单计划吗?");
		var yes = window.confirm("确定要生成计划吗?");
		if (yes) {
			var url = basePath + "rest/" + routeName + "Action/genPlanByGd_step2?ids=" + id;
			$('#btn_kd_2').attr('disabled','true');
			
			$.ajax({
				url : url,
				type : "post",
				dataType : "json",
				async : true,
				success : function(json) {
					$('#btn_kd_2').removeAttr('disabled');
					ajaxSuccess(json);
					grid.reload();
				},
				error : function(json) {
					$('#btn_kd_2').removeAttr('disabled');
					ajaxError(json);
				}
			});
		}
	}

	function genGD_detail(id,pro_craft_code,proGgxh,proColor){
		/*var ids = f_getChecked();
		if (ids.length == 0) {
			$.ligerDialog.warn("请选择要分解的工单！");
			return;
		}
		
		if(ids.indexOf(',') > -1){
			$.ligerDialog.warn("只能选择一条记录");
		}*/
		if(!proGgxh || !proColor){
			$.ligerDialog.warn("数据错误。产品规格型号或颜色不能为空");
			return false; 
		}
		//var url = basePath + "rest/" + routeName+ "Action/gotoGenDetail";
		
		/*var priorityWay = $('input[name="priorityWay_"]:checked').val();
		if(!priorityWay){
			$.ligerDialog.warn("请选择计划生成方式");
			return ;
		}*/
		
		//var deleteCount=ids.split(",").length;
		//pro_craft_code
		//alert(pro_craft_code);
		
		var url = basePath + "rest/" + routeName + "Action/gotoGenDetail?ids=" + id+"&proCraftCode="+pro_craft_code+"&proGgxh="+proGgxh+"&proColor="+proColor;
		var opener = window.open(url);
		if(opener && opener.data){
			grid.reload();
		}
	}
