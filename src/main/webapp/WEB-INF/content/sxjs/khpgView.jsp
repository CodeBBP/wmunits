<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>考核参数</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
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
		<form id="inputForm" action="${ctx }/khcs/update" method="post">
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
						<span>参数名称：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="para" name="para"
							value="${khcs.para}" readonly/>
					</td>
					<td class="td_table_1">
						<span>占分值：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="score" name="score"
							value="${khcs.score}" readonly/>
					</td>
				</tr>
			</table>
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1">
						<span>选项列表：</span>
					</td>
					<td class="td_table_2" colspan="5">
						<table class="table_all" align="center" border="0" cellpadding="0" cellspacing="0" id="itemTable" style="margin: 0">
							<tr>
								<td align=center width="5%" class="td_list_1" nowrap>
									序号
								</td>
								<td align=center width="25%" class="td_list_1" nowrap>
									考核内容
								</td>
								<td align=center width="55%" class="td_list_1" nowrap>
									计分标准
								</td>
								<td align=center width="5%" class="td_list_1" nowrap>
									标准分值
								</td>
							</tr>
							<c:forEach var="item" items="${khmxs}" varStatus="s">
								<tr>
									<td class="td_list_2" style="text-align:center">
										<input type="text" value="${s.count }" name='orderbys' size="2">
									</td>
									<td class="td_list_2">
										<textarea name="khnrs" style="width:100%;height:100px;" readonly>${item.khnr }</textarea>
									</td>
									<td class="td_list_2">
										<textarea name="jfbzs" style="width:100%;height:100px;" readonly>${item.jfbz }</textarea>
									</td>
									<td class="td_list_2">
										<input type="text" class="input_240" name="bzfzs" value="${item.bzfz}" readonly/>
									</td>
								</tr>
								<c:set var="index" value="${s.count }"/>
							</c:forEach>
						</table>
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