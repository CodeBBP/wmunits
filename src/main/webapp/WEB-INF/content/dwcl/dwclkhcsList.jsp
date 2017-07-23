<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>材料管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
		<script src="${ctx}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript">
			var succ = "${succ}";
			if(succ == '1'){
				alert("提交成功");
				window.close();
				window.opener.location.reload();
			}else if(succ == '2'){
				alert("保存成功");
				window.close();
				window.opener.location.reload();
			}
			function deletedwcl() {
				var checkbox = $("input[name='dwclid']:checked");
				if(checkbox.length == 0){
					alert("请选择稿件！");
					return false;
				}
				var dwclid = "";
				$("input[name='dwclid']:checked").each(function(){
					var item = $(this).val();
						dwclid += item+",";
				});
				dwclid = dwclid.substring(0,dwclid.length-1);
				window.location.href="${ctx}/dwcl/delete/"+dwclid;
			}
			var status = "0";
			function selectAll(){
				//var status = $("#selects").attr("checked");
				if(status == "0") {
					$("input[name='dwclid']").attr("checked",true);
					status = "1";
				} else {
					$("input[name='dwclid']").attr("checked",false);
					status = "0";
				}
			}
			function addNewDwcl(url){
				window.open(url);
			}
		</script>
	</head>

	<body style="PADDING-TOP: 5px">
	<form id="mainForm" action="${ctx}/dwcl" method="get">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="order" id="order" value="${page.order}"/>
		<input type="hidden" name="khcsid" id="khcsid" value="${khcsid}"/>
		<%--<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					材料管理
				</td>
			</tr>
		</table>
		--%><br/>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>材料标题：</span>
				</td>
				<td class="td_table_2">
					<input type="text" class="input_120" name="title" value="${title}"/>
					<input type='submit' class='button_70px' value='查询'/>
				</td>
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
			<tr>
				<td class="td_table_1">
					<span>审批状态：</span>
				</td>
				<td class="td_table_2" colspan="5">
					<frame:select name="spzt" type="radio" configName="spzt" value="${spzt}" cssClass="input_select"/>
				</td>
			</tr>
		</table>
		<table border="0" cellpadding="0" style="text-align:left;width:98%;align:center;"
			cellspacing="0">
			<tr align="left">
				<td align="left">
					<%--<input type='button' onclick="addNew('${ctx}/dwcl/create/${khcsid}')" class='button_70px' value='新建'/>--%>
					&nbsp;&nbsp;
					<input type='button' onclick="selectAll()" id="selects" class='button_70px' value='全选'/>&nbsp;
					<input type='button' onclick="deletedwcl()" class='button_70px' value='删除'/>&nbsp;
					<input type='button' onclick="addNewDwcl('${ctx}/dwcl/create/${khcsid}')" class='button_70px' value='新建'/>
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align=center width=5% class="td_list_1" nowrap>
					序号
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					材料标题
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					上报时间
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					状态
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					查看
				</td>				
			</tr>
			<c:forEach items="${page.result}" var="dwcl" varStatus="s">
				<tr>
					<td class="td_list_2" align="left" nowrap>
						<input type="checkbox" name="dwclid" value="${dwcl.id }" >${s.count}
					</td>
					<td class="td_list_2" align=left nowrap>
						<a href="javascript:;" onclick="window.open(${ctx}/dwcl/view/${dwcl.id })">${dwcl.title}&nbsp;</a>
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
					<td class="td_list_2" align=left nowrap>
					<c:if test="${dwcl.spzt != '1' }">
						<a href="javascript:;" onclick="addNewDwcl('${ctx}/dwcl/update/${dwcl.id }')" class="btnEdit" title="编辑">编辑</a>
					</c:if>
						<a href="javascript:;" onclick="addNewDwcl('${ctx}/dwcl/view/${dwcl.id }')" class="btnView" title="查看">查看</a>
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }" lookup="${lookup }"/>
		</table>
	</form>
	</body>
</html>
