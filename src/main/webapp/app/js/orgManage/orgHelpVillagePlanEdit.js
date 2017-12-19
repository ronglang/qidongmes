var uploadurl = "";var uploadurlorigname = "";
var yearLength;
var startYear;
$(function ()
    {
	if(row_id!=null&& row_id!=""){
		findYsarTable(row_id);
	}
	
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
				
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitOrgHelpVillagePlan();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            //alert(v.element($("#txtName")));
        });
//        creatTreeDiv("helpVillageWorkteamId");
	   // initTreeCustom("orgCode", "orgHelpVillage", "radio", "", "",treeSetting);
//	     initTree("helpVillageWorkteamId", "helpVillageWorkteam", "radio", "", "");
       creatTreeDiv("areaCode");
       initTreeCustom("areaCode", "sysArea", "normal", "", "",treeSetting);
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
			pub_initUpload(routeName,parm);
			$("#yearBtn").css("display","none");
       }else if(villageCode){
    	   initByVillageCode();
       }
    });  

function initSuccessCallback(json){
	$("#startYear").val(json.data.startYear);
	$("#startYear_txt").val(json.data.startYear);
	$("#endYear").val(json.data.endYear);
	$("#endYear_txt").val(json.data.endYear);
}
/**
 * 提交数据
 */
function submitOrgHelpVillagePlan() {
	var parm = "";
	if (row_id) {
		//	加入查询参数，进行update参数
		parm = "id=" + row_id;
	} 
	pub_save();
}

//根据村代码来初始化编辑页面
function initByVillageCode()
{
	$.ajax({
        url: basePath + "rest/orgHelpVillagePlanManageAction/listByEntity",
        type:"post",
        dataType:"json",
        async:true,
        data:"areaCode="+villageCode,
        success: function(json){
        	if(json.data.length>0){
        		row_id = json.data[0].id;
        		var parm = "id="+row_id;
        		isInit=true;
    			pub_initForm(routeName,parm);
        	}else{
        		if(areaName!="null"){
        			$("#areaCodeShow").val(areaName);
        		}
        		$("#areaCode").val(villageCode);
        		
        	}
        },  
        error: function(json) {
            ajaxError(json);
        }
    });
}

/**
 * 显示table
 */
function showTable(){
	//获取时间文本框的值
	startYear = $("#startYear").val();
	var endYear = $("#endYear").val();
	if(endYear<startYear){
		top.$.ligerDialog.warn("结束年 不能早于 开始年！");
		return;
	}
	//alert(endYear+"    "+startYear);
	var out = "<tr><td>年份</td><td>减贫计划(人)</td><td>农民人均纯收入</td><td>集体经济收入</td></tr>";
	if(startYear !="" && endYear !="" && endYear>=startYear){
		yearLength= (endYear-startYear)*1+1;
		for(var i = 0;i<yearLength;i++){
			out += "<tr><td>"+((startYear*1)+i)+"</td><td><input name='iput_"+i+"_0' id='iput_"+i+"_0'  type='hidden'><input name='iput_"+i+"_1' id='iput_"+i+"_1' type='text'></td><td><input  name='iput_"+i+"_2' id='iput_"+i+"_2' type='text'></td><td><input  name='iput_"+i+"_3' id='iput_"+i+"_3' type='text'></td></tr>";
		}
	}
	$("#guiHuaTable").html(out);
};

function resetPage(){
	document.getElementById("orgHelpVillagePlanManage").reset();
	$("#guiHuaTable").empty();
	$("#yearBtn").css("display","inline");
}

function pub_save(){
	var url = basePath + "rest/"+"orgHelpVillagePlanManageAction/newSaveFormAndAttach";
	var arr = [];
	var obj = new Object();
	var data =$("#orgHelpVillagePlanManage").serializeArray();
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
	for(var i = 0;i<yearLength;i++){
		obj.year = (startYear*1)+i;
		obj.offPoorNum = $("#iput"+i+"1").val();
		//alert('$("#iput"+i+"1").val()='+$("#iput"+i+"1").val());
		obj.peopleIncome = $("#iput"+i+"2").val();
		obj.groupIncome = $("#iput"+i+"3").val();
		arr.push(obj);
	}
	data.push(obj);
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        //async:true,
        data:data,
        success: function(json){
        	submitSuccess(json);
        },
        error: function(json) {
            ajaxError(json);
        }
    });
}

function addUploadData(path, origName) {
	if (isNullObject(path) && isNullObject(origName)) {
		uploadurl += path + ",";
		uploadurlorigname += origName + ",";
	}
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
				$("#title").val(treeNode.name+"总体规划");
				
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
			}	
		}
	};


function queryOrgHelpVillage(orgHelpVillageId){
	$.ajax({
		type : 'post',
		url : basePath+"rest/orgHelpVillageManageAction/detail",
		data: "id="+orgHelpVillageId,
		dataType : 'json',
		error : function() {
		},
		success : function(json) {
			$("#orgCode").val(json.data.orgCode);
			$("#countyAreaName").val(json.data.countyAreaName);
			$("#areaName").val(json.data.areaName);
			$("#orgCodeShow").val(json.data.orgName);
		}
	});
}

function findYsarTable(oid){
	$.ajax({
		type : 'post',
		url : basePath+"rest/orgHelpVillageYeartargetManageAction/listByEntity",
		data: "villagePlanId="+oid,
		dataType : 'json',
		error : function() {
		},
		success : function(json) {
			//alert(json.data.year);
			var out = "<tr><td>年份</td><td>减贫计划(人)</td><td>农民人均纯收入(元)</td><td>集体经济收入(元)</td></tr>";
			datas = json.data;
			for(var i = 0;i<datas.length;i++){
				var offPoorNum=datas[i].offPoorNum;
				var peopleIncome=datas[i].peopleIncome;
				var groupIncome=datas[i].groupIncome;
				if(peopleIncome==null)peopleIncome="";
				if(offPoorNum==null)offPoorNum="";
				if(groupIncome==null)groupIncome="";
				out += "<tr><td>"+(datas[i].year)+"</td><td><input name='iput_"+i+"_0' id='iput_"+i+"_0'  value='"+datas[i].id+"' type='hidden'><input name='iput_"+i+"_1' id='iput_"+i+"_1'  value='"+offPoorNum+"' type='text'></td><td><input name='iput_"+i+"_2' id='iput_"+i+"_2' value='"+peopleIncome+"' type='text'></td><td><input name='iput_"+i+"_3' id='iput_"+i+"_3' value='"+groupIncome+"' type='text'></td></tr>";
			}
			$("#guiHuaTable").html(out);
		}
	});
}
