<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>城乡环境</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
	</head>

	<body style="PADDING-TOP: 5px">
	<form id="mainForm" action="${ctx}/cxhj" method="get">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="order" id="order" value="${page.order}"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					城乡环境
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>标题：</span>
				</td>
				<td class="td_table_2">
					<input type="text" class="input_120" name="title" value="${title}"/>
					<input type='submit' class='button_70px' value='查询'/>
				</td>
				<td class="td_table_1">
					<span>审批状态：</span>
				</td>
				<td class="td_table_2" colspan="5">
					<frame:select name="spzt" type="radio" configName="spzt" value="${spzt}" cssClass="input_select"/>
				</td>
			</tr>
			<tr>
				<td class="td_table_1">
					<span>上报日期：</span>
				</td>
				<td class="td_table_2">
					<input type="text" class="input_120" id="cjsjq" name="cjsjq" value="${cjsjq}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'cjsjz\')}'})" />
				</td>
				
				<td class="td_table_1">
					<span>结束日期：</span>
				</td>
				<td class="td_table_2">
					<input type="text" class="input_120" id="cjsjz" name="cjsjz" value="${cjsjz}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'cjsjq\')}'})" />
				</td>
			</tr>
		</table>
		<table align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="left">
					<input type='submit' class='button_70px' value='查询'/>&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align=center width=20% class="td_list_1" nowrap>
					环境标题
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					所属类别
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					创建时间
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					责任单位
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<c:forEach items="${page.result}" var="cxhj">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${cxhj.title}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
					<!-- 
						<c:if test="${cxhj.sslb == '1' }">整治生产生活垃圾</c:if>
						<c:if test="${cxhj.sslb == '2'}">整治废旧物资回收站点</c:if>
						<c:if test="${cxhj.sslb == '3' }">整治河道水体污染</c:if>
						<c:if test="${cxhj.sslb == '4' }">整治户外广告牌标识牌</c:if>
						<c:if test="${cxhj.sslb == '5' }">整治建筑物</c:if>
						<c:if test="${cxhj.sslb == '6' }">整治矿山</c:if>
						<c:if test="${cxhj.sslb == '7' }">提升绿化环境</c:if>
						<c:if test="${cxhj.sslb == '8' }">保护水源地环境</c:if>&nbsp;
						 -->
						 ${cxhj.khgz == null ? '' : cxhj.khgz.khxm }
					</td>
					<td class="td_list_2" align=left nowrap>
						${cxhj.cjsj}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${cxhj.zrdw}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						<a href="${ctx}/cxhj/delete/${cxhj.id }" class="btnDel" title="删除" onclick="return confirmDel();">删除</a>
						<a href="${ctx}/cxhj/update/${cxhj.id }" class="btnEdit" title="编辑">编辑</a>
						<a href="${ctx}/cxhj/view/${cxhj.id }" class="btnView" title="查看">查看</a>
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }" lookup="${lookup }"/>
		</table>
	</form>
	</body>
</html>
