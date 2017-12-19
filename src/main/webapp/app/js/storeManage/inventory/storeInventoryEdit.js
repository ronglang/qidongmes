$(function(){
	loadForm();
});
function loadForm(){
	
	$.ajax({
		url: basePath+"rest/storeInventoryManageAction/getMaterielType",
		dataType: 'json',
		data: "",
		type: "post",
		delayLoad:true,
		success:function(data){
			var arr = data.data;
			var datas = [];
			for(var i = 0;i < arr.length;i++){
				var obj = new Object();
				obj.id = arr[i].value;
				obj.text = arr[i].value;
				datas.push(obj);
			}
		}
	});
	
	
	$("#myForm").ligerForm({
		inputWidth: 150, 
		labelWidth: 60, 
		space: 50,
		fields: [
		    { display: "盘点时间", name: "inv_time",   newline :false,icon:'search'
		    	,type:"date"
		    },
		    { display: "盘点材料", id:"inv_materiel",name: "inv_materiel",
		    	newline: false, type: "select", comboboxName: "inv_materiel" ,
		    	options:{data: datas}
		    }
		],
	});
}