<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>考核参数</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/wbox/jquery1.4.2.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/wbox/wbox.js"></script>
		<style type="text/css">
		.td_list_2_my {
			text-align:center;
			height:26px;
			border-left:1px solid #9AC3E1;
			border-bottom:1px solid #9AC3E1;
			padding:2px 4px 0;
		}
		</style>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/khgz/update" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					考核参数
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>考核项目：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="khxm" name="khxm"
							value="${khgz.khxm}" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>上级项目：</span>
					</td>
					<td class="td_table_2">
						<input type="hidden" id="parentKhgzId" name="parentKhgzId" value="${pid == null? khgz.pkhgz.id : pid}">
						<input type="text" id="parentKhgzName" readonly="readonly" name="parentKhgzName" class="input_240" value="${pkhgz == null? khgz.pkhgz.khxm : pkhgz}">
					</td>
					<td class="td_table_1">
						<span>占分值：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="score" name="score"
							value="${khgz.score}" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>详细内容：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<textarea style="width:100%;height:150px;" id="xxnr" name="xxnr">${khgz.xxnr}</textarea>
					</td>
				</tr>
			</table>
			
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<input type="submit" class="button_70px" onclick="check()" name="submit" value="提交">
						&nbsp;&nbsp;
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script type="text/javascript">
	function check(){
		var khxm = $("#khxm").val();
		var xxnr = $("#xxnr").val();
		var score = $("#score").val();

		if(trim(khxm) == ''){
			alert("考核项目不能为空");
			$("#title").focus();
			return false;
		}
		if(trim(xxnr) == ''){
			alert("详细信息不能为空");
			$("#title").focus();
			return false;
		}
		if(trim(score) == ''){
			alert("占分值不能为空");
			$("#score").focus();
			return false;
		}
		return true;
	}
	</script>
</html>
