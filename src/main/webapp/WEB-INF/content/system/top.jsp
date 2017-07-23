<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
<title>Top</title>
<%@ include file="/common/meta.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="${ctx }/styles/surface/css/accordion.css" rel="stylesheet">
<link href="${ctx }/styles/surface/css/blue.css" rel="stylesheet" type="text/css">
<script src="${ctx }/styles/surface/js/jquery-1.js"></script>
<script src="${ctx }/styles/surface/js/artDialog.js" type="text/javascript"></script>
<script src="${ctx }/styles/surface/js/iframeTools.js" type="text/javascript"></script>
<script src="${ctx }/styles/surface/js/FunctionJS.js"></script>
<script src="${ctx }/styles/surface/js/MainIndex.js"></script>
</head>
<body bgcolor="">
	<div id="Container">
    <div id="Header">
      <div id="HeaderLogo"> <img src="images/product.png" style=""> </div>
      <div id="Headermenu">
        <ul id="topnav">
          <li id="metnav_1" class="list"> <a href="javascript:;" id="nav_1" class="" onclick="Replace();"> <span class="c1"></span> 首页信息 </a> </li>
          <li id="metnav_2" class="list"> <a href="${ctx }/security/user/userupdate/${uid}" target="mainFrame" > <span class="c2"></span> 账户修改</a></li>
          <li id="metnav_5" class="list" onclick="window.parent.location.href='${ctx }/logout'"> <a href="javascript:;" id="nav_4"> <span class="c4"></span> 安全退出 </a> </li>
        </ul>
      </div>
    </div>
    </div>
</body>
</html>