<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>部门查看</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/wbox/jquery1.4.2.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/wbox/wbox.js"></script> 
	</head>

	<body>
		<form id="inputForm" action="${ctx }/security/org/update" method="post">
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					组织查看
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>组织机构名称：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="zzjgmc" name="zzjgmc"
							value="${org.zzjgmc }" readonly/>
					</td>
				</tr>
				<tr>
				<td class="td_table_1">
						<span>组织机构代码：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="zzjgdm" name="zzjgdm"
							value="${org.zzjgdm }" readonly/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>上级部门：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" id="parentOrgName" readonly="readonly" name="parentOrgName" class="input_240" value="${org.parentOrg.zzjgmc }" readonly>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>部门描述：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_520" id="description" name="description"
							value="${org.description }" readonly>
					</td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
