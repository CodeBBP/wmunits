<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<title>文明单位管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="/wmdw/styles/css/style.css" type="text/css" rel="stylesheet" />
<script src="/wmdw/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
</head>

<body bgcolor="#ECF5F1" >
	<div class="">
		<div class="brsDetail">
		<div class='node'>
			<a id='node_90' class='nodeLink' href='#' onclick="switchLeaf('90')">系统管理</a>
			<div class='leaf' id='leaf_90' style='display: none;'>
				<a class='leafLink' href='/wmdw/security/orgtree/list' target='mainFrame' >用户管理</a>
				<a class='leafLink' href='/wmdw/security/org' target='mainFrame' >部门管理</a>
				<a class='leafLink' href='/wmdw/security/role' target='mainFrame' >角色管理</a>
				<a class='leafLink' href='/wmdw/security/authority' target='mainFrame' >权限管理</a>
				<a class='leafLink' href='/wmdw/security/resource' target='mainFrame' >资源管理</a>
				<a class='leafLink' href='/wmdw/security/menu' target='mainFrame' >菜单管理</a>
			</div>
		</div>
		<div class='node'>
			<a id='node_1310' class='nodeLink' href='#' onclick="switchLeaf('1310')">配置管理</a>
			<div class='leaf' id='leaf_1310' style='display: none;'>
			<a class='leafLink' href='/wmdw/config/dictionary' target='mainFrame' >数据字典</a>
			</div>
		</div>
		
		<div class='node'><a id='node_287' class='nodeLink' href='#' onclick="switchLeaf('287')">协同办公</a>
			<div class='leaf' id='leaf_287' style='display: none;'>
				<a class='leafLink' href='/wmdw/tzgg' target='mainFrame' >通知公告</a>
				<a class='leafLink' href='/wmdw/tzgg/dwgg' target='mainFrame' >我的公告</a>
				<a class='leafLink' href='/wmdw/security/czrz' target='mainFrame' >工作记录</a>
			</div>
		</div>
		</div>
	</div>
	<script type="text/javascript">
	function switchLeaf(id) {
		var l = $("#leaf_"+id);
		var n = $("#node_"+id);
		var display = l.css('display');
		if(display && display == 'none') {
			l.css('display', 'block');
			n.css('background-image', 'url(/wmdw/styles/images/open.jpg)');
		}
		if(display && display == 'block') {
			l.css('display', 'none');
			n.css('background-image', 'url(/wmdw/styles/images/close.jpg)');
		}
	}
	</script>
</body>
</html>