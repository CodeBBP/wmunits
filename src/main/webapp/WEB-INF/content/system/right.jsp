<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
<c:if test="${admin == 'y'}">
&nbsp;&nbsp;&nbsp;待办任务：${count } >><a href="${ctx}/dwcl/dbrw">更多</a>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				
				<td align=center width=20% class="td_list_1" nowrap>
					上报标题
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					上报时间
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					上报单位
				</td>
				<td align=center width=10% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<c:forEach items="${page.result}" var="dwcl">
				<tr>
					<td class="td_list_2" align=left nowrap>
						${dwcl.title}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwcl.cjsj}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwcl.sbdw.dwmc}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						<c:if test="${dwcl.wjlx == '1' }">
							<a href="${ctx}/dwcl/khcl/${dwcl.id }" class="btnEdit" title="审核">审核</a>
						</c:if>
						<c:if test="${dwcl.wjlx == '2' }">
							<a href="${ctx}/gjgl/khgj/${dwcl.id }" class="btnEdit" title="审核">审核</a>
						</c:if>
						<a href="${ctx}/dwcl/khclview/${dwcl.id }" class="btnView" title="查看">查看</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<table class="table_all" align="center" border="1" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<img alt="图表" src="${bchartUrl }" /><img alt="图表" src="${zchartUrl }" />
				</td>
			</tr>
		</table>
</c:if>
<c:if test="${empty admin}">
	</br>
	欢迎进入六安市精神文明建设管理信息平台
	</br>
	<img alt="图表" style="width:50%;height:200px" src="${ctx}/styles/images/11.png" />
	<%--<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align=center width=5% class="td_list_1" nowrap>
					<input type="checkbox" title="全选" id="selects" onclick="selectAll()">
					序号
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					材料标题
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
			</tr>
			<c:forEach items="${page.result}" var="dwcl" varStatus="s">
				<tr>
					<td class="td_list_2" align="left" nowrap>
						<input type="checkbox" name="dwclid" value="${dwcl.id }" />${s.count}
					</td>
					<td class="td_list_2" align=left nowrap>
						<a href="${ctx}/dwcl/view/${dwcl.id }">${dwcl.title}&nbsp;</a>
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwcl.sbdw.dwmc}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwcl.cjsj}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						<c:if test="${empty dwcl.spzt }">未审核&nbsp;</c:if>
						<c:if test="${dwcl.spzt == '0'}">新稿&nbsp;</c:if>
						<c:if test="${dwcl.spzt == '1' }">通过&nbsp;</c:if>
						<c:if test="${dwcl.spzt == '2' }">退回&nbsp;</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>--%>
</c:if>