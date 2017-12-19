<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>电科智联---工业管理</title>
  <link rel="stylesheet" href="<%=basePath %>core/css/amazeui.css" />
  <link rel="stylesheet" href="<%=basePath %>core/css/common.min.css" />
  <link rel="stylesheet" href="<%=basePath %>core/css/index.min.css" />
</head>
<body>
  <div class="layout">
    <!--===========layout-header================-->
    <div class="layout-header am-hide-sm-only">
      <!--topbar start-->
      <div class="topbar">
        <div class="container">
          <div class="am-g">
            <div class="am-u-md-3">
              <div class="topbar-left">
                <i class="am-icon-globe"></i>
                <div class="am-dropdown" data-am-dropdown>
						
                </div>
              </div>
            </div>
            <div class="am-u-md-9">
              <div class="topbar-right am-text-right am-fr">
                <a href="#">登录</a>
                <a href="#">申请账号</a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--topbar end-->
      <div class="header-box" data-am-sticky>
        <!--nav start-->
        <div class="nav-contain">
          <div class="nav-inner">
            <ul class="am-nav am-nav-pills am-nav-justify">
              <li class=""><a href="./home_1.html">首页</a></li>
              <li><a href="#">高管入口</a></li>
              <li>
                <a href="#">财务部</a>
                <!-- sub-menu start-->
                <ul class="sub-menu">
                  <li class="menu-item"><a href="#">1</a></li>
                  <li class="menu-item"><a href="#">2</a></li>
                  <li class="menu-item"><a href="#">3</a></li>
                </ul>
                <!-- sub-menu end-->
              </li>
              <li><a href="#">计划部</a>
              	<ul class="sub-menu">
              		<li class="menu-item"><a href="#">周计划</a></li>
              	</ul>
              	
              </li>
              <li><a href="#">生产部</a>
              	<ul class="sub-menu">
              		<li class="menu-item"><a href="#">计划完成</a></li>
              	</ul>
              </li>
              <li>
                <a href="html/news.html">仓库</a>
                <!-- sub-menu start-->
                <ul class="sub-menu">
                  <li class="menu-item"><a href="#">原料库存</a></li>
                  <li class="menu-item"><a href="#">成品库存</a></li>
                  <li class="menu-item"><a href="#">仓库详情信息</a></li>
                </ul>
                <!-- sub-menu end-->
              </li>
              <li><a href="html/about.html">海关物流</a>
              	<ul class="sub-menu">
              		<li class="menu-item"><a href="#">1</a></li>
              		<li class="menu-item"><a href="#">2</a></li>
              		<li class="menu-item"><a href="#">3</a></li>
              	</ul>
              </li>
              <li><a href="#">质检部</a></li>
              <li><a href="#">采购部</a></li>
              <li><a href="#">销售部</a></li>
              <li><a href="#">工艺组</a></li>
              <li><a href="#">工程部</a></li>
            </ul>
          </div>
        </div>
        <!--nav end-->
      </div>
    </div>
    <div style="text-align:center;height: 620px;border-top: 3px solid #CAE1FF;border-bottom: 3px solid #CAE1FF;">
    	
    	显示内容
    	
    </div>
    <div id="floor"style="text-align:center;height: 100%;margin-top:2px;">
    	Copyright&nbsp; © 2017-2022 www.cniots.com&nbsp;&nbsp;&nbsp;&nbsp; 成都电科智联科技有限公司
    </div>
  <script src="assets/js/jquery-2.1.0.js" charset="utf-8"></script>
  <script src="assets/js/amazeui.js" charset="utf-8"></script>
  <script src="assets/js/common.js" charset="utf-8"></script>
</body>
</html>
