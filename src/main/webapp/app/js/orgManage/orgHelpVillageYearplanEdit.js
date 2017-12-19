var v;
$(function ()
    {
        $.metadata.setType("attr", "validate");
         v = $("#"+routeName).validate({
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
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitOrgHelpVillageYearplan();
            }
        });
        $("#createBy").val(account);
     	$("#createByShow").val(userName);
     	var myDate = new Date();
        $("#createDate").val(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate());
        if(areaCode!="null")
    	{
    		$("#areaCode").val(areaCode);
    		initTreeCustomByArea("helpVillageWorkteamId", "helpVillageWorkteam", "radio",areaCode);
    	}
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        creatTreeDiv("areaCode");initTreeCustom("areaCode", "sysArea", "radio", "", "",treeSetting);
	    creatTreeDiv("helpVillageWorkteamId");
	    f_initGrid(row_id);
       if (row_id) {
    	   	initTreeCustom("helpVillageWorkteamId", "helpVillageWorkteam", "radio", "", "");
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
       }else if(areaCode!="null"){
    	   initByAreaCode();
       }
       checkflag();
    });

function initByAreaCode()
{
	$.ajax({
        url: basePath +"rest/orgHelpVillageYearplanManageAction/listByEntity?areaCode="+areaCode,
        type:"post",
        dataType:"json",
        async:true,
        success: function(json){
        	var obj = json.data;
          	if(obj.length>0){
          		row_id = obj[0].id;
          		var parm = "id="+row_id;
          		isInit = true;
    			pub_initForm(routeName,parm);
    			grid.set({url:basePath+"rest/orgHelpVillageYearplanManageAction/queryItem?id="+row_id});
    			grid.reload();
        	}else{
        		$("#areaCode").val(areaCode);
        		if(areaName!="null"){
         		   $("#areaCodeShow").val(areaName);
         	   	}
        	}
        },
        error: function(json) {
        	 top.$.ligerDialog.error("系统错误，请联系管理员！");
        }
    });
}

var treeSetting = {	
		check: { // check
			enable: false,
			chkboxType: {"Y":"", "N":""}
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: function(e, treeId, treeNode){
				
			},
			onClick: function(e, treeId, treeNode){
				if(treeNode.id.length!=12){
					top.$.ligerDialog.warn("只能选择村，请重新选择！");
					return;
				}else{
					initTreeCustomByArea("helpVillageWorkteamId","helpVillageWorkteam","radio",treeNode.id);
					$("#areaCode").val(treeNode.id);
					$("#areaCodeShow").val(treeNode.name);
					qname="";//清空上次点击时的父节点名字
					var zTree = $.fn.zTree.getZTreeObj(treeId),
					nodes = zTree.getSelectedNodes(),
					node = nodes[0];
					var name=nodes[0].name;
					checkAllParents(node,name);//递归取所有父节点的名字
					qname=qname.substring(0, qname.length-4);
					$("#title").val(qname+"年度实施计划");
				}
			}	
		}
	};
var qname="";
//递归取所有父节点的名字
function checkAllParents(treeNode,name){
	if (treeNode==null || treeNode.pId=="-1"){
		qname=name;
		return;
	}else{
		treeNode.checked=true;
		if(treeNode.getParentNode())
			name=treeNode.getParentNode().name+name;
		checkAllParents(treeNode.getParentNode(),name);
	}
}
function initTreeCustomByArea(colName,nodeTBName,treeType,queryValue){
	var url =  basePath +"rest/helpVillageWorkteamManageAction/listByEntity?areaCode="+queryValue;
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:true,
        success: function(json){
        	var obj = json.data;
          	if(obj.length>0){
          		$.fn.zTree.destroy($("#"+colName+"ContentTree"));
          		$.fn.zTree.init($("#"+colName+"ContentTree"), radioTreeSetting, json.data);
          		if(json.data.length>0){
          			$("#helpVillageWorkteamId").val(json.data[0].id);
          			$("#helpVillageWorkteamIdShow").val(json.data[0].name);
          		}
        	}else{
//        		top.$.ligerDialog.warn("该村没有驻村工作队！请先创建工作队！");
        	}
        },
        error: function(json) {
        	 top.$.ligerDialog.error("系统错误，请联系管理员！");
        }
    });
}
///**
// * 提交数据
// */
//function submitOrgHelpVillageYearplan() {
//	var parm = "";
//	if (row_id) {
//		//	加入查询参数，进行update参数
//		parm = "id=" + row_id;
//	} 
//	pub_save(routeName,parm,false);
//}

//function queryOrgHelpVillage(orgHelpVillageId){
//	$.ajax({
//		type : 'post',
//		url : basePath+"rest/orgHelpVillageManageAction/detail",
//		data: "id="+orgHelpVillageId,
//		dataType : 'json',
//		error : function() {
//		},
//		success : function(json) {
//			$("#orgCode").val(json.data.orgCode);
//			$("#countyAreaName").val(json.data.countyAreaName);
//			$("#areaName").val(json.data.areaName);
//			$("#orgCodeShow").val(json.data.orgName);
//		}
//	});
//	}

function f_initGrid(id) {
	var urlstr = basePath + "rest/orgHelpVillageYearplanManageAction/queryItem";
	if (id) {
		urlstr = basePath
				+ "rest/orgHelpVillageYearplanManageAction/queryItem?id="
				+ id;
	}
	grid = $("#yearplanContentList")
			.ligerGrid(
					{
						url : urlstr,
						columns : [
								{
									display : '计划id',
									name : 'id',
									width : 1,
									hide:true
								},
								{
									display : '项目名称',
									name : 'name',
									align : 'left',
									width : 200
								},{
									display : '操作',
									isAllowHide : false,
									width : 80,
									hide:flag,
									render : function(row) {
										if ('-1' == row.pcode) {
											return '';
										}
										var html = '<a href="#" title="添加一行" onclick="javascript:addRow('
												+ row.id
												+ ');return false;">增加</a>&nbsp;&nbsp;&nbsp;';
										html += '<a href="#" title="删除一行" onclick="javascript:deleteRow();return false;">删除</a>';
										return html;
									}
								}, {
									display : '年度',
									name : 'year',
									align : 'left',
									width : 50,
									editor:{
											type : 'int'
									}
								},{
									display : '月份',
									name : 'month',
									align : 'left',
									width : 50,
									editor:{
											type : 'int'
									}
								},{
									display : '单位',
									name : 'scaleUnit',
									width : 1,
									hide:true
								}, {
									display : '单位',
									name : 'scaleUnitName',
									width : 50,
								}, {
									display : '规模',
									name : 'projectScale',
									width : 50,
									type : 'float',
									editor : {
										type : 'float'
									}
								}, {
									display : '总投资(万元)',
									name : 'totalMoney',
									width : 90,
									
								},{
										display : '部门帮扶资金（万元）',
										name : 'orgMoney',
										width : 100,
										editor : {
											type : 'float'
										}
									}, {
										display : '财政扶贫资金（万元）',
										name : 'financeMoney',
										width : 100,
										editor : {
											type : 'float'
										}
									}, {
										display : '银行信贷资金（万元）',
										name : 'bankMoney',
										width : 100,
										editor : {
											type : 'float'
										}
									},{
									display : '业主投入（万元）',
										name : 'yzMoney',
										width : 100,
										editor : {
											type : 'float'
										}
									}, {
										display : '群众自筹（万元）',
										name : 'zcMoney',
										width : 100,
										editor : {
											type : 'float'
										}
									}, {
										display : '其他资金（万元）',
										name : 'otherMoney',
										width : 100,
										editor : {
											type : 'float'
										}
									}, {
										display : 'code',
										name : 'code',
										width : 1,
										hide:true
									}, {
										display : 'pcode',
										name : 'pcode',
										width : 1,
										hide:true
									}
						],
						usePager : false,
						rownumbers : true,
						alternatingRow : false,
						enabledEdit : true,
						onAfterEdit: f_onAfterEdit,
						tree : {
							// columnId: 'name',
							columnName : 'name',
							idField : 'code',
							parentIDField : 'pcode'/*,
										treeLine:true*/
						},
						width : '99%',
						height : window.screen.availHeight - 370

					});
}

//总资金自动求和
function f_onAfterEdit(e)
{
	var orgMoney=0;
	var financeMoney=0;
	var bankMoney=0;
	var yzMoney=0;
	var zcMoney=0;
	var otherMoney=0;
	if(e.record.orgMoney!=""&&e.record.orgMoney!=null){
		orgMoney=parseFloat(e.record.orgMoney);
	}
	if(e.record.financeMoney!=""&&e.record.financeMoney!=null){
		financeMoney=parseFloat(e.record.financeMoney);
	}
	if(e.record.bankMoney!=""&&e.record.bankMoney!=null){
		bankMoney=parseFloat(e.record.bankMoney);
	}
	if(e.record.yzMoney!=""&&e.record.yzMoney!=null){
		yzMoney=parseFloat(e.record.yzMoney);
	}
	if(e.record.zcMoney!=""&&e.record.zcMoney!=null){
		zcMoney=parseFloat(e.record.zcMoney);
	}
	if(e.record.otherMoney!=""&&e.record.otherMoney!=null){
		otherMoney=parseFloat(e.record.otherMoney);
	}
	
	grid.updateCell('totalMoney', orgMoney +financeMoney
			+bankMoney+yzMoney+zcMoney+otherMoney, e.record); 
}

/**
 * 动态添加行
 */
function addRow(id){
		var selectRow = grid.getSelectedRow();
	if (!selectRow)
		return;
	var selectRowParnet = grid.getParent(selectRow);
	//var data = selectRow;
		var obj={};
		obj.id = null;
		obj.name=selectRow.name;
		obj.scaleUnit=selectRow.scaleUnit;
		obj.scaleUnitName=selectRow.scaleUnitName
		obj.code=selectRow.code;
		obj.pcode=selectRow.pcode;
		obj.projectScale=null;
		obj.totalMoney=null;
		obj.orgMoney=null;
		obj.financeMoney=null;
		obj.bankMoney=null;
		obj.yzMoney=null;
		obj.zcMoney=null;
		obj.otherMoney=null;
		obj.year=null;
		obj.month=null;
	grid.add(obj, selectRow, false, selectRowParnet);
}

var deleteIds=[];//存编辑时删除信息的id，保存是先将这些信息删除掉
function deleteRow() {
	var row = grid.getSelectedRow();
	if(row){
		grid.deleteRow(row);
		if(row.id)deleteIds.push(row.id);
	}
}

/**
 * 提交数据
 */
function submitOrgHelpVillageYearplan(){
	var parm =  "";
	if (row_id) {
		//	加入查询参数，进行update参数
		parm = "&id=" + row_id;
	} 
	
	//pub_save(routeName,parm,false);
//	 var title = $("#title").val();
//	 if(!title){
//	 	top.$.ligerDialog.warn('请填写标题！');
//	 	return;
//	 }
	 
//	 var helpVillageWorkteamId = $("#helpVillageWorkteamId").val();
//	 if(!helpVillageWorkteamId){
//	 	top.$.ligerDialog.warn('请选择驻村工作队！');
//	 	return;
//	 }
	 var isSubmitSuc = false;//标志是否提交成功
	 var formdata = $("#orgHelpVillageYearplanManage").serialize();
	 var datas = [];
  		 datas = grid.getData();
  	 var sucdata = [];
  	 for(var i = 0;i<datas.length;i++){
  	 	var obj = {};
  	 	if(datas[i].year==undefined||datas[i].year==""){
  	 		continue;
  	 	}else{
  	 		if(datas[i].id)obj.id=datas[i].id;
  	 		if(datas[i].name)obj.name=datas[i].name;
  	 		if(datas[i].year)obj.year=datas[i].year;
  	 		if(datas[i].month)obj.month=datas[i].month;
  	 		if(datas[i].scaleUnitName)obj.scaleUnit=datas[i].scaleUnitName;
  	 		if(datas[i].code)obj.code=datas[i].code;
  	 		if(datas[i].pcode)obj.pcode=datas[i].pcode;
  	 		if(datas[i].projectScale)obj.projectScale=datas[i].projectScale;
  	 		if(datas[i].totalMoney)obj.totalMoney=datas[i].totalMoney;
  	 		if(datas[i].orgMoney)obj.orgMoney=datas[i].orgMoney;
  	 		if(datas[i].financeMoney)obj.financeMoney=datas[i].financeMoney;
  	 		if(datas[i].bankMoney)obj.bankMoney=datas[i].bankMoney;
  	 		if(datas[i].yzMoney)obj.yzMoney=datas[i].yzMoney;
  	 		if(datas[i].zcMoney)obj.zcMoney=datas[i].zcMoney;
  	 		if(datas[i].otherMoney)obj.otherMoney=datas[i].otherMoney;
  	 		sucdata.push(obj);
  	 	}
  	 }
  	 if(sucdata.length==0){
  		this.$.ligerDialog.warn('请至少填写一条记录(年月必填)！');
  		isSubmitSuc = false;
  		return isSubmitSuc;
  	 }
  	var pa = JsonArrayToStringCfz(sucdata);
  	var s = "jsons="+pa+"&"+formdata+parm+"&deleteIds="+deleteIds.join("_");
  	 s = decodeURIComponent(s,true);
	var urlstr = basePath + "rest/orgHelpVillageYearplanManageAction/saveEntityByJson";
		$.ajax({
        url: urlstr,
        type:"post",
        dataType:"json",
        async:false,
        data:s,
        success: function(json){
        	submitSuccess(json);
        	isSubmitSuc = true;
        },
        error: function(json) {
            ajaxError(json);
            isSubmitSuc = false;
        }
    });
	return isSubmitSuc;
}

function submitSuccess(json) {
	if (json.success) {
		alert("年度实施计划规划保存成功");
		if(json.data.id!=null){
			row_id = json.data.id;
			var parm = "id="+row_id;s
			isInit=true;
			pub_initForm(routeName,parm);
		}
	} else {
		alert("保存失败");
	}
}

//flag用于 贫困村附加信息  隐藏输入框、提交按钮和列表中的操作
function checkflag(){
	if(flag){
		document.getElementById("s1").style.display="none";
		document.getElementById("s2").style.display="none";
	}
}

//给《贫困村附加信息》页面提供的编辑方法，通过此方法来初始化页面
function setdata(areaCode){
	var urlstr = basePath + "rest/orgHelpVillageYearplanManageAction/listByEntity";
	$.ajax({
		url:urlstr,
		dataType:"json",
		type:"post",
		data:"areaCode="+areaCode,
		 success: function(json){
			 if(json.data.length>0){
				 var id=json.data[0].id;
				 f_initGrid(id);
				 var parm = "id="+id;
				 pub_initForm(routeName,parm);
			 }else{
				 $("#areaCode").val(areaCode);
				 getWorkteamId(areaCode);
			 }
	        },
	        error: function(json) {
	            ajaxError(json);
	        }
	})
}

//给《贫困村附加信息》页面提供的新增方法
function setAreaCode(areaCode){
	$("#areaCode").val(areaCode);
	getWorkteamId(areaCode);
}

function getWorkteamId(areaCode){
	var urlstr = basePath + "rest/helpVillageWorkteamManageAction/listByEntity";
	$.ajax({
		url:urlstr,
		dataType:"json",
		type:"post",
		data:"areaCode="+areaCode,
		 success: function(json){
			 if(json.data.length>0){
				 var id=json.data[0].id;
				 $("#helpVillageWorkteamId").val(id);
			 }else{
			 }
	        },
	        error: function(json) {
	            ajaxError(json);
	        }
	})
}