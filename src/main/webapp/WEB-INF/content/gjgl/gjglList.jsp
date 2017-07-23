<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>稿件管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body style="PADDING-TOP: 5px">
	<form id="mainForm" action="${ctx}/gjgl/list" method="get">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="order" id="order" value="${page.order}"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					稿件管理
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>稿件标题：</span>
				</td>
				<td class="td_table_2">
					<input type="text" class="input_240" name="filter_LIKES_title" value="${param['filter_LIKES_title']}"/>
				</td>
			</tr>
		</table>
		<table align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="left">
					<input type='submit' class='button_70px' value='查询'/>
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align=center width=20% class="td_list_1" nowrap>
					稿件标题
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					上报单位
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					上报时间
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					审批状态
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<c:forEach items="${page.result}" var="dwcl">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${dwcl.title}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwcl.sbdw}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwcl.createdate}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>&nbsp;
						<c:if test="${dwcl.gjzt == 'N' }">待上报</c:if>
						<c:if test="${dwcl.gjzt == '0'}">未通过</c:if>
						<c:if test="${dwcl.gjzt == '1' }">稿件初审</c:if>
						<c:if test="${dwcl.gjzt == '2' }">已审核</c:if>
						<c:if test="${dwcl.gjzt == '3' }">已发布</c:if>
					</td>
					<td class="td_list_2" align=left nowrap>
						<a href="${ctx}/gjgl/delete/${dwcl.id }" class="btnDel" title="删除" onclick="return confirmDel();">删除</a>
						<a href="${ctx}/gjgl/update/${dwcl.id }" class="btnEdit" title="编辑">编辑</a>
						<a href="${ctx}/gjgl/view/${dwcl.id }" class="btnView" title="查看">查看</a>
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }" lookup="${lookup }"/>
		</table>
	</form>
	</body>
</html>
