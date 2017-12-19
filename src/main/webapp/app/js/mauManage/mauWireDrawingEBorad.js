 $(function(){
	 var dataGrid = [
         { id: 1, text: '李三'},
         { id: 2, text: '李四'},
         { id: 3, text: '赵武'},
         { id: 4, text: '陈留'}
	 ]; 
	 $("#txtGrid").ligerComboBox({
		width : 300,
        data: dataGrid,
        textField: 'text',
        isShowCheckBox: true, 
        isMultiSelect: true,
        selectBoxWidth: 200
    });
 })
 
var gfunc = function(){
	var repairForm = $("#repairForm");
	var g = $.ligerDialog.open({
		height : 300,
		width : 500,
		title : '通知维修',
		showMax : true,
		showToggle : true,
		showMin : true,
		isResize : true,
		slide : false,
		target : repairForm,
		buttons : [{
			text : '确认',
			onclick : function(i, d) {
				d.hide();
				window.reload(true);
			}
		}, {
			text : '取消',
			onclick : function(i, d) {
				d.hide();
			}
		}],
	});
}