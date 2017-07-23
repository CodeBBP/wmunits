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
		<form id="inputForm" action="${ctx }/xmcl/khclsave" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="shld" id="shld" value="${xmcl.shld }"/>
			<input type="hidden" name="zf" id="zf" value="${xmcl.zf }"/>
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
						<input type="text" class="input_240" id="title" name="title"
							value="${xmcl.title }" readonly/>
					</td>
					<td class="td_table_1">
						<span>创建时间：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="createdate" name="createdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							value="${xmcl.createdate }" readonly/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>提交学校：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="sbxs" name="sbxs"
							value="${xmcl.sbxs }" readonly/>
					</td>
					<td class="td_table_1">
						<span>所属类型：</span>
					</td>
					<td class="td_table_2" colspan="3">
			    		<input type="text" class="input_240" id="category" name="category" value="${xmcl.category }" readonly/>
					</td>
				</tr>
			</table>
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1" style="width:80%;text-align:center">
						<span>编辑内容：</span>
					</td>
					<td class="td_table_1" style="text-align:center">
						<span>评估标准：</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_2">
						<textarea name="content" id="content" style="width:100%;height:400px;visibility:hidden;" readonly>${xmcl.content }</textarea>
					</td>
					<td class="td_table_2">
						<textarea name="pgbz" id="pgbz" style="width:100%;height:400px;" readonly>${xmcl.pgbz }</textarea>
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
						<span>得分：(总分：${xmcl.zf }分)</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_2" rowspan="2" >
						<textarea style="width:100%;height:100%" name="suggest" >${xmcl.suggest }</textarea>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" name="score" id="score">
					</td>
				</tr>
				<tr align="left">
					<td class="td_table_2" style="width:80%;text-align:center">
						<input type="radio" class="input_radio" name="shzt" onclick="change2()" value="1">通过
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" class="input_radio" name="shzt" onclick="change()" value="0">未通过
					</td>
				</tr>
			</table>
			
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
			function change(){
				$("#zf").val("");
				$("#zf").attr("readonly","true");
			}
			function change2(){
				$("#zf").removeAttr("readonly");
			}
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