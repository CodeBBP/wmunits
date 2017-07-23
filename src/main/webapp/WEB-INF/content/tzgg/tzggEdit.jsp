<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>通知公告</title>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="${ctx}/styles/js/kindeditor-4.1.10/themes/default/default.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/wbox/jquery1.4.2.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/kindeditor-4.1.10/kindeditor-min.js"></script>
		<script src="${ctx}/styles/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
		<script src="${ctx}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/wbox/wbox.js"></script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/tzgg/update" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="fbdwid" value="${tzgg.fbdw.id != null ? tzgg.fbdw.id : fbdw.id }"/>
			<table width="100%" border="0" align="center" cellpadding="0"
					class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
				<tr>
					<td class="td_table_top" align="center">
						通知公告
					</td>
				</tr>
			</table>
			<table class="table_all" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>标题：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="title" name="title"
							value="${tzgg.title }" />
					</td>
					<td class="td_table_1">
						<span>发布单位：</span>
					</td>
					<td class="td_table_2" colspan="3">
			    		<input type="text" class="input_240" name="fbdwmc"
							value="${tzgg.fbdw.dwmc != null ? tzgg.fbdw.dwmc : fbdw.dwmc}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>创建时间：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="cjsj" name="cjsj" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							value="${tzgg.cjsj }" />
					</td>
					<td class="td_table_1">
						<span>有效日期：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="yxrq" name="yxrq" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							value="${tzgg.yxrq }" />
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
						<textarea name="content" id="content" style="width:100%;height:400px;visibility:hidden;">${tzgg.content }</textarea>
					</td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<input type="submit" class="button_70px" name="submit" onclick="check()" value="提交">
						&nbsp;&nbsp;
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
					</td>
				</tr>
			</table>
			
			<script type="text/javascript">
			function check(){
				var title = $("#title").val();
				var cjsj = $("#cjsj").val();
				var content = $("#content").val();
				if(trim(title) == ''){
					alert("标题不能为空");
					$("#title").focus();
					return false;
				}
				if(trim(cjsj) == ''){
					alert("创建时间不能为空");
					$("#cjsj").focus();
					return false;
				}	
				if(trim(content) == ''){
					alert("提交内容不能为空");
					$("#content").focus();
					return false;
				}
				return true;
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