<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>考核参数编辑</title>
		<%@ include file="/common/meta.jsp"%>
		<style type="text/css">
		div.zTreeBackground {
			width: 300px;
			height: 420px;
			text-align: left;
			border:solid 1px #CCC; 
			line-height:21px; 
			background:#fff;
			margin-left: 10px;
		}
		</style>
		<link rel="stylesheet" href="${ctx}/styles/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/zTree/js/jquery.ztree.core-3.5.js"></script>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
		<script type="text/javascript">
		var setting = {
			data : {
				simpleData : {
					enable : true
				}
			}
		};
		$(function(){
			var url = '${ctx}/khcs/treedata';
			$.ajax({
				type: 'POST',
				url: url,
				async: false,
				error: function(){
					var failMsg = "对不起，后台数据处理异常，删除失败！";
					alert(failMsg);
					return false;
				},
				success: function(data){
					initZtree(data);
				}
			});
		});
		function initZtree(data) {
			var zNodes = data;
			$.fn.zTree.init($('#khcsTree'), setting, zNodes);
		}
	</script>
	</head>
	<body style="padding-top: 5px">
		<%--<div class="zTreeBackground">
		--%><div style="width:320px;height:430px;overflow:scroll">
			<ul id="khcsTree" class="ztree"></ul>
		</div>
	</body>
</html>