
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
				
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitOrgYearplanRecord();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        f_initGrid(row_id,recordYear);
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
       }
    });  


function f_initGrid(id,recordYear) {
	if(recordYear!="null"){
		var time=recordYear.split("-");
		$("#year").val(time[0]);
		$("#year_txt").val(time[0]);
		$("#month").val(time[1]);
		$("#month_txt").val(time[1]);
		urlstr = basePath
		+ "rest/orgYearplanRecordManageAction/queryContent?yearplanId="
		+ id+"&recordYear="+recordYear;
	}else{
		urlstr = basePath
		+ "rest/orgYearplanRecordManageAction/queryContent?yearplanId="
		+ id;
	}
	
	grid = $("#yearplanRecordList")
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
									width : 220
								}
								, {
									display : '单位',
									name : 'scaleUnit',
									width : 100,
								}, {
									display : '计划规模',
									name : 'planProjectScale',
									width : 100,
								}, {
									display : '计划总投资',
									name : 'planTotal',
									width : 100,
								}, {
									display : '已完成规模',
									name : 'finishProjectScale',
									width : 100,
								}, {
									display : '已完成总投资',
									name : 'finishTotal',
									width : 100,
								}, {
									display : '规模',
									name : 'projectScale',
									width : 100,
									type : 'float',
									editor : {
										type : 'float'
									}
								}, {
									display : '总投资(万元)',
									name : 'totalMoney',
									width : 100,
//									editor : {
//										type : 'float'
//									}
								},
								{
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
									},{
										display : 'yearplanContentId',
										name : 'yearplanContentId',
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
							columnName : 'name',
							idField : 'code',
							parentIDField : 'pcode'
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
 * 提交数据
 */
function submitOrgYearplanRecord(){
	var year=$("#year").val();
	var month=$("#month").val();
	 var datas = [];
  		 datas = grid.getData();
  	 var sucdata = [];
  	 for(var i = 0;i<datas.length;i++){
  	 	var obj = {};
  	 		if(datas[i].id)obj.id=datas[i].id;
  	 		if(datas[i].name)obj.name=datas[i].name;
  	 		if(datas[i].scaleUnit)obj.scaleUnit=datas[i].scaleUnit;
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
  	 		if(datas[i].yearplanContentId)obj.yearplanContentId=datas[i].yearplanContentId;
  	 		obj.recordYear=year+"-"+month;
  	 		sucdata.push(obj);
  	 }
  	 
  	var pa = JsonArrayToStringCfz(sucdata);
  	var s = "jsons="+pa;
  	 s = decodeURIComponent(s,true);
	var urlstr = basePath + "rest/orgYearplanRecordManageAction/saveEntityByJson";
		$.ajax({
        url: urlstr,
        type:"post",
        dataType:"json",
        async:true,
        data:s,
        success: function(json){
        	submitSuccess(json);
        },
        error: function(json) {
            ajaxError(json);
        }
    });
}