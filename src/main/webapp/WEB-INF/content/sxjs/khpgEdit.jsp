<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>考核评估</title>
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
		<form id="inputForm" action="${ctx }/khpg/update" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					考核评估
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>评估项目：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="para" name="para"
							value="${khpg.para}" />
					</td>
					<td class="td_table_1">
						<span>占分值：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="score" name="score"
							value="${khpg.score}" />
					</td>
				</tr>
			</table>
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1">
						<span>添加选项：</span>
					</td>
					<td class="td_table_2" colspan="6">
						<input type="button" class="button_70px" value="添加选项" onclick="addItem()">
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>选项列表：</span>
					</td>
					<td class="td_table_2" colspan="6">
						<table class="table_all" align="center" border="0" cellpadding="0" cellspacing="0" id="itemTable" style="margin: 0">
							<tr>
								<td align=center width="5%" class="td_list_1" nowrap>
									序号
								</td>
								<td align=center width="55%" class="td_list_1" nowrap>
									评估内容
								</td>
								<td align=center width="10%" class="td_list_1" nowrap>
									考核方式
								</td>
								<td align=center width="15%" class="td_list_1" nowrap>
									审核要点
								</td>
								<td align=center width="10%" class="td_list_1" nowrap>
									标准分值
								</td>
								<td align=center width="5%" class="td_list_1" nowrap>
									操作
								</td>
							</tr>
							<c:forEach var="item" items="${pgmxs}" varStatus="s">
								<tr>
									<td class="td_list_2" width="5%" style="text-align:center">
										<input type="text" value="${s.count }" name='orderbys' size="2">
									</td>
									<td class="td_list_2" width="55%">
										<textarea name="pgnrs" style="width:100%;height:100px;">${item.pgnr }</textarea>
									</td>
									<td class="td_list_2" width="10%">
										<input type='text' name="khfss" class="input_50" value="${item.khfs }">
									</td>
									<td class="td_list_2" width="15%">
										<input type="text" class="input_120" name="shyds" value="${item.shyd}" />
									</td>
									<td class="td_list_2" width="10%">
										<input type="text" class="input_50" name="bzfzs" value="${item.bzfz}" />
									</td>
									<td class="td_list_2" width="5%">
										<a href='javascript:void(0)' onclick='delRow(${s.count})' class='btnDel' title='删除'>删除</a>
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
		var para = $("#para").val();
		var score = $("#score").val();

		if(trim(para) == ''){
			alert("参数名称不能为空");
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

	var order = ${index + 1};
	function addItem() {
		var table = document.getElementById("itemTable");
		var row = table.insertRow(-1);
		var cell = row.insertCell(-1);
		if(order) {
			cell.innerHTML = "<input type='text' value='" + order + "' name='orderbys' size='2'>";
		} else {
			cell.innerHTML = "<input type='text' value='" + 1 + "' name='orderbys' size='2'>";
		}
		
		cell.className = "td_list_2_my";
		
		cell = row.insertCell(-1);
		cell.setAttribute("width","55%");
		cell.innerHTML = "<textarea name='pgnrs' style='width:100%;height:100px;'></textarea>";
		cell.className = "td_list_2";
		
		cell = row.insertCell(-1);
		cell.setAttribute("width","10%");
		cell.innerHTML = "<input type='text' name='khfss' class='input_50'></textarea>";
		cell.className = "td_list_2";

		cell = row.insertCell(-1);
		cell.setAttribute("width","15%");
		cell.innerHTML = "<input type='text' class='input_120' name='shyds' />";
		cell.className = "td_list_2";
		
		cell = row.insertCell(-1);
		cell.innerHTML = "<input type='text' class='input_50' name='bzfzs' />";
		cell.className = "td_list_2";
		
		cell = row.insertCell(-1);
		cell.innerHTML = "<a href='javascript:void(0)' onclick='delRow(" + order + ")' class='btnDel' title='删除'>删除</a>";
		cell.className = "td_list_2";
		order = order + 1;
	}
	
	function delRow(rowId) {
		var table = document.getElementById("itemTable");
		table.deleteRow(rowId);
		order = order - 1;
		var rns = table.rows.length;
		if(rns >= 3) {
			var cns = table.rows[0].cells.length;
			var idx;
			for(idx = 1; idx < rns; idx++) {
				table.rows[idx].cells[0].innerHTML="<input type='text' value='" + idx + "' name='orderbys' size='2'>";
				table.rows[idx].cells[cns - 1].innerHTML="<a href='javascript:void(0)' onclick='delRow(" + idx + ")' class='btnDel' title='删除'>删除</a>";
			}
		}
	}
	
	function validateInput(){
			var table = document.getElementById("itemTable");
			var rowLen = table.rows.length;
			if(rowLen == 0) {
				alert("请添加选项");
				return false;
			}
			var warning = "";
			$("input[name='itemNames']").each(function(){
				var item = $(this).val();
				if(item == '') {
					warning = "选项列表 不能为空";
				}
			});
			if(warning != '') {
				alert(warning);
				return false;
			}
	}
	</script>
</html>
