<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>考核材料</title>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="${ctx}/styles/js/kindeditor-4.1.10/themes/default/default.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script charset="utf-8" src="${ctx}/styles/js/kindeditor-4.1.10/kindeditor-min.js"></script>
		<script charset="utf-8" src="${ctx}/styles/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
		<script src="${ctx}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/wbox/wbox.js"></script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/dwcl/khclsave" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="zf" id="zf" value="${dwcl.zf }"/>
			<input type="hidden" name="createdate" id="createdate" value="${dwcl.createdate }"/>
			<input type="hidden" name="wjlx" id="wjlx" value="1"/>
			<table width="100%" border="0" align="center" cellpadding="0"
					class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
				<tr>
					<td class="td_table_top" align="center">
						考核材料
					</td>
				</tr>
			</table>
			<table class="table_all" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>标题：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_520" id="title" name="title"
							value="${dwcl.title }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>上报类型：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<frame:select name="sblx" type="radio" configName="sblx" value="${empty entity.sblx ? '1' : entity.sblx}"/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>上报单位：</span>
					</td>
					<td class="td_table_2" colspan="3">
						${dwcl.sbdw.dwmc}
						<input type="hidden" name="operatorid" value="${dwcl.operator.id == null? operator.id : dwcl.operator.id}"/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>所属类别：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<c:forEach var="khcs" items="${khcss}">
							<input type="radio" name="khcsId" class="${khcs.score}" value="${khcs.id}" <c:if test="${dwcl.khcsId == khcs.id }">checked</c:if>/>${khcs.khxm}</br>
						</c:forEach>
					</td>
				</tr>
			</table>
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1" style="width:80%;text-align:center">
						<span>编辑内容：</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_2">
						<textarea name="content" id="content" style="width:100%;height:400px;visibility:hidden;" readonly>${dwcl.content }</textarea>
					</td>
				</tr>
			</table>
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1" style="width:80%;text-align:center">
						<span>审批建议：</span>
					</td>
					<td class="td_table_1" style="text-align:center">
						<span>审批状态：</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_2" style="height:50px" rowspan="2" >
						<textarea style="width:100%;height:100%" name="suggest" >${dwcl.suggest }</textarea>
					</td>
					<td class="td_table_2">
						<frame:select name="spzt" type="select" configName="spzt" value="${dwcl.spzt}" cssClass="input_select"/>
					</td>
				</tr>
				<%--<tr align="left">
					<td class="td_table_2" style="width:80%;text-align:center">
						<input type="radio" class="input_radio" name="spzt" onclick="change2()" value="1">通过
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" class="input_radio" name="spzt" onclick="change()" value="0">未通过
					</td>
				</tr>
			--%></table>
			
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td>
						<input type="submit" class="button_70px" name="submit" value="提交">
						&nbsp;&nbsp;
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
					</td>
				</tr>
			</table>
			
			<script type="text/javascript">
			//function change(){
			//	$("#zf").val("");
			//	$("#zf").attr("readonly","true");
			//}
			//function change2(){
			//	$("#zf").removeAttr("readonly");
			//}
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					allowFileManager : true,
					cssPath : '${ctx}/styles/js/kindeditor-4.1.10/plugins/code/prettify.css',
					uploadJson : '${ctx}/styles/js/kindeditor-4.1.10/jsp/upload_json.jsp',
					fileManagerJson : '${ctx}/styles/js/kindeditor-4.1.10/jsp/file_manager_json.jsp',
					afterCreate : function() {
						var self = this;
						K.ctrl(document, 13, function() {
							self.sync();
							document.forms['example'].submit();
						});
						K.ctrl(self.edit.doc, 13, function() {
							self.sync();
							document.forms['example'].submit();
						});
					}
				});
				prettyPrint();
			});
			</script>
		</form>
	</body>
</html>