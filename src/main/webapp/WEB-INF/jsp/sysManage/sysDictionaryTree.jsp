<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysDictionary_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sysDictionaryForm</title>
		<%-- <link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" type="text/css" /> --%>
		<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/css/zTreeStyle/zTreeIcons.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />

		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/ztree/jquery-ztree-2.5.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
		<script type="text/javascript" src="<%=basePath%>app/js/sysDictionary/sysDictionary.js"></script>

		<script>
			var basePath  = '<%=basePath%>';
    	</script>
	</head>

	<body >
		<div>
			<ul id="tree" class="tree">
		</div>
		<div id="rMenu">
			<li>
				<!-- <ul id="r_addRoot"><li>增加根节点</li></ul> -->
				<ul id="r_addFolder"><li>增加文件夹</li></ul>
				<ul id="r_addNode"><li>增加节点</li></ul>
				<ul id="r_updateNode"><li>修改名称</li></ul>
				<ul id="r_deleteNode"><li>删除节点</li></ul>
			</li>
		</div>
	</body>

	<script type="text/javascript">
	var countryTree = (function(countryTree) {
	    var tree = {
	        zTree: '',
	        pNode: '',
	        setting: {
	            isSimpleData: true,
	            treeNodeKey: "id",
	            treeNodeParentKey: "pid",
	            showLine: true,
	            root: {
	                isRoot: true,
	                nodes: []
	            },
	            callback: {
	                rightClick: function(event, treeId, treeNode) {
	                    tree.pNode = treeNode;
	                    tree.showRightMenu({ //显示右键菜单
	                        x: event.clientX,
	                        y: event.clientY
	                    });
	                }
	            }
	        },
	        init: {
	            initEvent: {
	                initRMenu: function() {
	                    $("#rMenu").hover(function() { //设置进入右键菜单事件
	                        tree.bindClick($("#r_addFolder"), function() {
	                            tree.addFolder();
	                        });

	                        tree.bindClick($("#r_addNode"), function() {
	                            tree.addNode();
	                        });

	                        tree.bindClick($("#r_updateNode"), function() {
	                            tree.updateNode();
	                        });

	                        tree.bindClick($("#r_deleteNode"), function() {
	                            tree.deleteNode();
	                        });
	                    }, function() { //设置离开右键菜单事件
	                        tree.hideItem();
	                    });
	                }
	            }
	        },
	        loadTree: function() { //加载树
	            var nodes = [{
	                id: 1,
	                pid: 0,
	                name: "中国",
	                open: true
	            }, {"id" : "112", "pid" : "11", "name": "成都","isParent" : "true"},{
	                id: 11,
	                pid: 1,
	                name: "北京",
	                isParent: true
	            }, {
	                id: 111,
	                pid: 11,
	                name: "海淀"
	            }, {
	                id: 12,
	                pid: 1,
	                name: "河南"
	            }, {
	                id: 121,
	                pid: 12,
	                name: "郑州",
	                isParent: true
	            }];
	            tree.zTree = $("#tree").zTree(tree.setting, nodes);
	        },
	        showRightMenu: function(postionJson) {
	            $("#rMenu").css({ //设置右键菜单的位置
	                top: postionJson.y + "px",
	                left: postionJson.x + 2 + "px",
	                display: "block"
	            });
	            if(tree.pNode.id == 1) { //如果是根节点则禁用“删除”、“修改名称”选项
	                tree.showItem(["#r_addFolder", "#r_addNode"]);
	            } else if(tree.pNode.isParent) { //如果是文件夹节点，则显示所有菜单选项
	                tree.showItem(["#r_addFolder", "#r_addNode", "#r_updateNode", "#r_deleteNode"]);
	            } else { //此选项为节点，则禁用“增加节点”、“增加文件夹”选项
	                tree.showItem(["#r_deleteNode", "#r_updateNode"]);
	            }
	            tree.init.initEvent.initRMenu(); //加载菜单选项的事件
	        },
	        showItem: function(itemArray) { //显示某些域
	            for(var i = 0; i < itemArray.length; i++) {
	                $(itemArray[i]).show();
	            }
	        },
	        hideItem: function(itemArray) { //隐藏某些域
	            if(itemArray == undefined) { //如果为传入值，则禁用缺省的域
	                tree.hideItem(["#rMenu", "#r_addFolder", "#r_addNode", "#r_updateNode", "#r_deleteNode"]);
	            }
	            for(var i = 0; i < itemArray.length; i++) {
	                $(itemArray[i]).hide();
	            }
	        },
	        
	        addRoot:function(){	//添加根节点
	        	var rootName = window.prompt("请输入根节点的名称");
	        	if(rootName == "") {
	                alert("操作失败！文件夹的名称不能为空!");
	            } else {
	                if(rootName != null) {
	                    tree.zTree.addNodes(tree.pNode, [{ //添加节点
	                        id: 999,
	                        pId: 0,
	                        name: rootName,
	                        isParent: true
	                    }]);
	                    debugger;
	                    var pid = 0;
	                    var name = rootName;
	                    var isParent ="true";
	                    $.post('<%=basePath%>/rest/sysDictionaryManageAction/addSysDictionary',{pid:pid,name:name,isParent:isParent},function(data){
	                    	alert(data.msg);
	                    	window.reload;
	                    },"json");
	                    
	                }
	            }
	        	
	        },
	        
	        addFolder: function() { //添加文件夹节点
	            var folderName = window.prompt("请输入文件夹的名称");
	            if(folderName == "") {
	                alert("操作失败！文件夹的名称不能为空!");
	            } else {
	                if(folderName != null) {
	                    tree.zTree.addNodes(tree.pNode, [{ //添加节点
	                        id: tree.createNodeId(),
	                        pId: tree.pNode.id,
	                        name: folderName,
	                        isParent: true
	                    }]);
	                    debugger;
	                    var pid = tree.pNode.id;
	                    var name = folderName;
	                    var isParent ="true";
	                    $.post('<%=basePath%>/rest/sysDictionaryManageAction/addSysDictionary',{pid:pid,name:name,isParent:isParent},function(data){
	                    	alert(data.msg);
	                    },"json");
	                }
	            }
	        },
	        addNode: function() { //添加节点
	            var nodeName = window.prompt("请输入节点名称");
	            if(nodeName == "") {
	                alert("操作失败！节点名称不能为空!");
	            } else {
	                if(nodeName != null) {
	                    tree.zTree.addNodes(tree.pNode, [{ //添加节点
	                        id: tree.createNodeId(),
	                        pId: tree.pNode.id,
	                        name: nodeName,
	                        isParent: false
	                    }]);
	                    var pid = tree.pNode.id;
	                    var name = nodeName;
	                    var isParent ="false";
	                    $.post('<%=basePath%>/rest/sysDictionaryManageAction/addSysDictionary',{pid:pid,name:name,isParent:isParent},function(data){
	                    	alert(data.msg);
	                    },"json");
	                }
	            }
	        },
	        updateNode: function() { //更新节点-修改节点名称
	            var newName = window.prompt("输入新名称", tree.pNode.name);
	            if(newName != tree.pNode.name && newName != null && newName != undefined) {
	                tree.pNode.name = newName;
	                tree.zTree.updateNode(tree.pNode, true);
	                
	                var pid =tree.pNode.pid;
	                var id =tree.pNode.id;
	                var name=newName;
	                $.post('<%=basePath%>/rest/sysDictionaryManageAction/updateSysDictionary',{pid:pid,name:name,isParent:isParent},function(data){
                    	alert(data.msg);
                    },"json");
	            }
	        },
	        deleteNode: function() { //删除节点
	            if(tree.pNode.isParent) { //判断该节点是否是文件夹节点，并且检查是否有子节点
	                if(window.confirm("如果删除将连同子节点一起删掉。请确认！")) {
	                    var parentNode = tree.zTree.getNodeByParam("id", tree.pNode.pid); //获取父节点对象
	                    tree.zTree.removeNode(tree.pNode); //移除节点
	                    parentNode.isParent = true; //设置父节点为文件夹节点
	                    tree.zTree.refresh();
	                    
	                    var id = tree.pNode.id;
	                    var pid =tree.pNode.pid;
	                    $.post('<%=basePath%>/rest/sysDictionaryManageAction/deleteSysDictionary',{pid:pid,name:name,isParent:isParent},function(data){
	                    	alert(data.msg);
	                    },"json");
	                    
	                    
	                    
	                }
	            } else { //该节点为不是文件夹节点
	                if(window.confirm("确认要删除?")) {
	                    var parentNode = tree.zTree.getNodeByParam("id", tree.pNode.pid);
	                    tree.zTree.removeNode(tree.pNode); //移除节点
	                    parentNode.isParent = true; //设置父节点为文件夹节点
	                    tree.zTree.refresh();
	                    
	                    var id = tree.pNode.id;
	                    var pid =tree.pNode.pid;
	                    $.post('<%=basePath%>/rest/sysDictionaryManageAction/deleteSysDictionary',{pid:pid,name:name,isParent:isParent},function(data){
	                    	alert(data.msg);
	                    },"json");
	                }
	            }
	        },
	        createNodeId: function() { //动态生成节点id。生成策略：在父节点id后追加递增数字
	            var nodes = tree.zTree.getNodesByParam("pid", tree.pNode.id);
	            if(nodes.length == 0) { //生成id的算法
	                return tree.pNode.id + "1";
	            } else {
	                return nodes[nodes.length - 1].id + 1;
	            }
	        },
	        bindClick: function(obj, fn) { //绑定click事件
	            obj.unbind("click");
	            obj.bind("click", fn);
	        }
	    };
	    return { //此处为公开的数据
	        loadTree: function() {
	            tree.loadTree();
	        }
	    };
	})(countryTree);

	$().ready(function() {
	    countryTree.loadTree();
	});
  	</script>
  	<style type="text/css">
  	body {
	background-color: white;
	margin:0; padding:0;
	text-align: center;
	}

	div, ul , p, table, th, td {
	list-style:none; 
	margin:0; padding:0; 
	color:#333; font-size:13px; 
	font-family:"Segoe UI";
	}
	div#rMenu {
	background-color:#555555;
	text-align: left;
	padding:2px;
	width:100px;
	position:absolute;
	display:none;
	}
	div#rMenu ul {
	margin:1px 0;
	padding:0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color:#DFDFDF;
	display:none;
	}
	div#rMenu ul li {
	margin:0;
	padding:2px 0;
	}
  </style>
</html>

