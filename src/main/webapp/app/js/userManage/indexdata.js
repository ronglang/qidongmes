var indexdata = 
[
    { text: '用户管理',isexpand:true, children: [ 
		{url:"app/jsp/userManage/index.jsp",text:"用户管理"},
		{url:"demos/base/drag.htm",text:"人员管理"},
		{url:"demos/base/drag2.htm",text:"机构管理"},
		{url:"demos/base/dragresizable.htm",text:"用户权限"},
		{url:"demos/base/tip.htm",text:"数据字典"},
		{url:"demos/base/tip2.htm",text:"参数配置"}
	]
    },
    { text: '角色管理', isexpand: false, children: [
		{ url: "demos/filter/filter.htm", text: "角色管理" },
		{ url: "demos/filter/filterwin.htm", text: "角色权限" },
		{ url: "demos/filter/grid.htm", text: "用户角色" } 
	]
    }, 
	{ text: '权限管理',isexpand:false, children: [ 
		{ url: "demos/dialog/dialogAll.htm", text: "权限管理" },
        { url: "demos/dialog/dialogParent.htm", text: "资源管理" }
	]}
];


var indexdata2 =
[
    { isexpand: "true", text: "表格", children: [
        { isexpand: "true", text: "可排序", children: [
		    { url: "dotnetdemos/grid/sortable/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/sortable/server.aspx", text: "服务器" }
	    ]
        },
        { isexpand: "true", text: "可分页", children: [
		    { url: "dotnetdemos/grid/pager/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/pager/server.aspx", text: "服务器" }
	    ]
        },
        { isexpand: "true", text: "树表格", children: [
		    { url: "dotnetdemos/grid/treegrid/tree.aspx", text: "树表格" }, 
		    { url: "dotnetdemos/grid/treegrid/tree2.aspx", text: "树表格2" }
	    ]
        }
    ]
    }
];
