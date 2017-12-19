// 如果有上传控件，声明如下两个页面全局变量
var uploadurl = "";
var uploadurlorigname = "";
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
				title:{
					required:true,
					rangelength:[5,10]	
				},
				addDate:true,
				own:{
					required:true,
					rangelength:[1,7]	
				}
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitBlog();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        //异步初始化树div元素
	    creatTreeDiv("city");
	    //初始化树的数据
	    initTree("city","sysArea","radio","","");
	    // checkbox
	    var ck_dataGrid = [
            { id: 'apple', name: '苹果'}, 
            { id: 'balala', name: '香蕉' },
            { id: 'pear', name: '梨' }
            ]; 
        $("#ck").ligerCheckBoxList({
            data: ck_dataGrid,
            textField: 'name' 
        });
        // radio
        var ra_dataGrid = [
           { id: 'man', name: '男'}, 
           { id: 'woman', name: '女' }
           ]; 
       $("#ra").ligerRadioList({
           data: ra_dataGrid,
           textField: 'name' 
       });
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
			//如果有上传控件，再添加如下
			pub_initUpload(routeName,parm);
       }
    });  

/**
 * 提交数据
 */
function submitBlog() {
	var parm = "";
	if (row_id) {
		//	加入查询参数，进行update参数
		parm = "id=" + row_id;
	} 
	pub_save(routeName,parm,true);
}

/**
 * 重写提交数据成功后的回调函数
	 */
function submitSuccess(json) {
	if (json.success) {
		alert("保存成功");
		if(json.id!=null)
			row_id = json.data.id;
	} else {
		alert("保存失败");
	}
}
/** 有上传控件，再添加此方法
 * 保存上传的附件名并放入input
 */
function addUploadData(path,origName){
	if( isNullObject(path) && isNullObject(origName) ){
		uploadurl += path + ",";
		uploadurlorigname += origName + ",";
	}
}