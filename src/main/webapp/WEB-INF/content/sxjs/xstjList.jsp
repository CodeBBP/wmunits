<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>单位材料统计</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body style="PADDING-TOP: 5px">
	<form id="mainForm" action="${ctx}/bsgj/list" method="get">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="order" id="order" value="${page.order}"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					单位材料统计
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>单位名称：</span>
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
					创建单位
				</td>
				<td align=center width=20% class="td_list_1" >
					上报总数
				</td>
				<td align=center width=20% class="td_list_1" >
					考核通过数
				</td>
				<td align=center width=20% class="td_list_1" >
					未通过数
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					未考核数
				</td>
			</tr>
			<c:forEach items="${dwtjs}" var="dwtj">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${dwtj.dwmc}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwtj.sbzs}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwtj.khtgs}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwtj.khwtgs}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwtj.wkhs}&nbsp;
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }" lookup="${lookup }"/>
		</table>
	</form>
	</body>
</html>
