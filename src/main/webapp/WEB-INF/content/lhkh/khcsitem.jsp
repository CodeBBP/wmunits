<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>考核明细</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body style="PADDING-TOP: 5px">
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					考核明细
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td align=center width=40% class="td_list_1">
					考核项目
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					占分值
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<c:forEach items="${list}" var="khcs">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${khcs.khxm}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${khcs.score}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						<a href="${ctx}/dwcl/create/${khcs.id} " class="btnEdit" title="编辑">编辑</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	</body>
</html>
