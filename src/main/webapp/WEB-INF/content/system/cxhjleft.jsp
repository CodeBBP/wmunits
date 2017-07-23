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
					<a id='node_1' class='nodeLink' href='#' onclick="switchLeaf('1')">城乡环境管理</a>
					<div class='leaf' id='leaf_1' style='display: none;'>
						<a class='leafLink' href='/wmdw/cxhj' target='mainFrame' >实时境况管理</a>
						<a class='leafLink' href='/wmdw/cxhj/dbrw' target='mainFrame' >待治理任务</a>
						<a class='leafLink' href='/wmdw/cxhj/hjdtview' target='mainFrame' >环境地图总览</a>
						<a class='leafLink' href='/wmdw/cxhj/hjdj' target='mainFrame' >环境统计</a>
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