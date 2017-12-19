var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		createGrid();
		grid=$("#sysContactsManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/vwdataGridPage",
	        checkbox: true,
	        columns: [
	            { display: '序号', name: 'id',  width: 120 ,hide:true},
	            { display: '姓名', name: 'name',  width: 120 },
	            { display: '所属机构', name: 'orgIds',  width: 120 },
	            { display: '职位', name: 'positions',  width: 120 },
	            { display: '手机', name: 'mobileTel',  width: 120 , render: function (row,index,val){
                	return getcallTelHtml(val);
                	}
	            },
	            { display: '办公电话', name: 'officeTel',  width: 120 , render: function (row,index,val){
                    return getcallTelHtml(val);
                    }
                },
//	            { display: '家庭电话', name: 'homeTel',  width: 120, render: function (row,index,val){
//                    	return getcallTelHtml(val);
//                    }
//                },
		        {
                    display: '操作', isAllowHide: false,  width:120,
                    render: function (row){
                    	var html = '<a href="#" onclick="javascript:edit(' + row.id + ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
                    	html += '<a href="#" onclick="javascript:show(' + row.id + ');return false;">查看</a>';
                        return html;
                    }
                }
	        ], 
	        pageSize:10,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '100%',height:'400',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
		creatTreeDiv("orgId");
		initTree("orgId","sysOrg","radio","","");
        creatTreeDiv("position");
        initTree("position","sysDictionary","radio","PID","C32P");
		$("#add").click(function(){
			top.$.ligerDialog.open({ 
				url: basePath+"rest/sysContactsManageAction/toAddEditPage", 
				height: 600,
				width: 1000,
				modal:true, 
				title:'添加通讯录'
				});
		});
		
		$("#all").click(function(){
			var pa = $("#"+routeName+"ListForm").serialize();
			grid.loadServerData(decodeURIComponent(pa,true));
		});
    }
);

function getcallTelHtml(val){
	var html = '<a style="font-size: 14px;color: red;" title="点击呼叫" href="javascript:showCallTelPage(' + val + ');">'+val+'</a>';
    return html;
}

function edit(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/sysContactsManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 1000,
		modal:true,
		title:'编辑通讯录'
		});
}

function reloadData(){
	var pa = $("#"+routeName+"ListForm").serialize();
	grid.loadServerData(decodeURIComponent(pa,true));
}

//拼音查询
function sorts(jianma) {
	grid.loadServerData("pinyin=" + jianma);
}


function show(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/sysContactsManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1000,
		modal:true,
		title:'查看通讯录'
		});
} 

function createGrid(){
	groupgrid=$("#sysContactsGroupList").ligerGrid({
		url : basePath + "rest/sysContactGroupManageAction/dataGridPage",
        columns: [
            { display: '组名', name: 'name',  width: 120}
        ],
        pageSize:10,
        onSelectRow:function(rowdata, rowid, rowobj){
        	if(rowdata.id==1){
        		grid.set({url:basePath + "rest/sysContactsManageAction/vwdataGridPage"});
    			grid.reload();
        	}else{
        		grid.set({url:basePath + "rest/sysContactsManageAction/vwdataGridPage?contactGroupId="+rowdata.id});
    			grid.reload();
        	}
        	
        },
        width:120,
        height:400
    });
}	
/**
 * 显示打电话界面
 * @param {} tel
 */
function showCallTelPage(tel){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/sysContactsManageAction/toCallTelPage?tel="+tel, 
		height: 600,
		width: 1000,
		title:"电话呼叫",
		modal:true});
}