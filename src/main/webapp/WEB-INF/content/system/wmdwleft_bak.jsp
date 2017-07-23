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
				<div class='node'>
					<a id='node_1031' class='nodeLink' href='#' onclick="switchLeaf('1031')">量化考核</a>
					<div class='leaf' id='leaf_1031' style='display: none;'>
						<a class='leafLink' href='/wmdw/dwcl/dbrw' target='mainFrame' >待办任务</a>
						<c:if test="${admin == 'y'}">
						<a class='leafLink' href='/wmdw/khcs/list' target='mainFrame' >考核参数设定</a>
						</c:if>
					</div>
				</div>
				<div class='node'>
					<a id='node_1' class='nodeLink' href='#' onclick="switchLeaf('1')">单位材料管理</a>
					<div class='leaf' id='leaf_1' style='display: none;'>
						<a class='leafLink' href='/wmdw/dwcl/dwtj' target='mainFrame' >单位材料统计</a>
						<a class='leafLink' href='/wmdw/dwcl/create' target='mainFrame' >材料编辑上报</a>
						<a class='leafLink' href='/wmdw/dwcl' target='mainFrame' >材料查询管理</a>
					</div>
				</div>
				<div class='node'>
					<a id='node_291' class='nodeLink' href='#' onclick="switchLeaf('291')">网络宣传</a>
					<div class='leaf' id='leaf_291' style='display: none;'>
						<a class='leafLink' href='/wmdw/gjgl/dwtj' target='mainFrame' >宣传稿件统计</a>
						<a class='leafLink' href='/wmdw/gjgl' target='mainFrame' >稿件查询管理</a>
						<a class='leafLink' href='/wmdw/gjgl/create' target='mainFrame' >稿件编辑上报</a>
					</div>
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