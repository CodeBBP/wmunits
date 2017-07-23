<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>审核稿件</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="${ctx}/styles/js/kindeditor-4.1.10/themes/default/default.css" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script charset="utf-8" src="${ctx}/styles/js/kindeditor-4.1.10/kindeditor-min.js"></script>
		<script charset="utf-8" src="${ctx}/styles/js/kindeditor-4.1.10/lang/zh_CN.js"></script>	
	</head>

	<body>
		<form id="inputForm" action="${ctx }/gjgl/khgjsave" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="zzjgmc" id="zzjgmc" value="${bsgj.zzjgmc }"/>
			<input type="hidden" name="zzjgdm" id="zzjgdm" value="${bsgj.zzjgdm }"/>
			<input type="hidden" name="tsbz" id="tsbz" value="${tsbz }"/>
			<input type="hidden" name="wjlx" id="wjlx" value="2"/>
			<table width="100%" border="0" align="center" cellpadding="0"
					class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
				<tr>
					<td class="td_table_top" align="center">
						审核稿件
					</td>
				</tr>
			</table>
			<table class="table_all" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>标题：</span>
					</td>
					<td class="td_table_2" >
						<input type="text" class="input_240" id="title" name="title"
							value="${bsgj.title }" />
					</td>
					<td class="td_table_1">
						<span>创建时间：</span>
					</td>
					<td class="td_table_2" >
						<input type="text" class="input_240" id="createdate" name="createdate"
							value="${bsgj.createdate }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>提交单位：</span>
					</td>
					<td class="td_table_2" >
						<input type="text" class="input_240" id="sbdw" name="sbdw"
							value="${bsgj.sbdw }" />
					</td>
					<td class="td_table_1">
						<span>稿件类别：</span>
					</td>
					<td class="td_table_2" >
						<input type="text" class="input_240" id="gjlb" name="gjlb"
							value="${bsgj.gjlb }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>作者：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="author" name="author"
							value="${bsgj.author }" />
					</td>
				</tr>
			</table>
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1" style="text-align:center">
						<span>编辑内容：</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_2">
						<textarea name="content" id="content" style="width:100%;height:300px;">${bsgj.content }</textarea>
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
						<textarea style="width:100%;height:100%" name="suggest" >${bsgj.suggest }</textarea>
					</td>
					<td class="td_table_2">
						<frame:select name="gjzt" type="select" configName="gjzt" value="${bsgj.gjzt}" cssClass="input_select"/>
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
				var gjzt = $("input[name='gjzt']").val();
				if(trim(gjzt) == ''){
					alert("审批状态不能为空");
					$("#gjzt").focus();
					return false;
				}
				return true;
			}
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					allowFileManager : true,
					readonlyMode : true,
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