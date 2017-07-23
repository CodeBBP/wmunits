<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>材料上报</title>
		<c:if test="${khcl == '1'}"><title>考核材料</title></c:if>
		<c:if test="${empty khcl}"><title>材料上报</title></c:if>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="${ctx}/styles/js/kindeditor-4.1.10/themes/default/default.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/wbox/jquery1.4.2.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/kindeditor-4.1.10/kindeditor-min.js"></script>
		<script src="${ctx}/styles/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
		<script src="${ctx}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/wbox/wbox.js"></script>
		<script>
			function uploadFiles() {
		 		iframewbox=$("#upload").wBox({
					   	requestType: "iframe",
					   	iframeWH:{width:700,height:400},
					   	title:"视屏上传",
					   	show: true,
						target:"${ctx}/dwcl/upload"
					   });
			}
		</script>
	</head>

	<body>
		<form id="myForm" action="${ctx }/dwcl/update" method="post" >
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="zf" id="zf" value="${dwcl.zf }"/>
			<input type="hidden" name="tsbz" id="tsbz" value="${tsbz }"/>
			<input type="hidden" name="wjlx" id="wjlx" value="1"/>
			<input type="hidden" name="content" id="content" />
			<input type="hidden" name="cjsj" value="${cjsj }"/>
			<input type="hidden" name="sbdwid" value="${dwcl.sbdw.id == null? sbdw.id : dwcl.sbdw.id}"/>
			<input type="hidden" name="savecl" id="savecl" />
			<table width="100%" border="0" align="center" cellpadding="0"
					class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
				<tr>
					<td class="td_table_top" align="center">
						<c:if test="${khcl == '1'}">考核材料</c:if>
						<c:if test="${empty khcl}">材料上报</c:if>
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
						<frame:select name="sblx" type="radio" configName="sblx" value="${empty dwcl.sblx ? '1' : dwcl.sblx}"/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>上报单位：</span>
					</td>
					<td class="td_table_2" colspan="3">
						${dwcl.sbdw.dwmc == null? sbdw.dwmc : dwcl.sbdw.dwmc}
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>所属类别：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<select name="khcsId">
							<c:forEach var="khcs" items="${khcss}">
							<option value="${khcs.id}" <c:if test="${dwcl.khcsId == khcs.id }">selected</c:if>/>${khcs.khxm}</br>
							</c:forEach>
						</select>
						
					</td>
				</tr>
				<%--<tr>
					<td class="td_table_1">
						<span>添加附件：</span>
					</td>
					<td class="td_table_2">
						&nbsp;<input type='button' class='button_70px' value='视频上传' id="upload" onclick="uploadFiles()"/>
					</td>
				</tr>--%>
			</table>
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1" style="width:98%;text-align:center">
						<span>编辑内容：</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_2">
						<textarea name="content_" id="content_" style="width:100%;height:400px;visibility:hidden;">${dwcl.content }</textarea>
					</td>
				</tr>
			</table>
			
			<c:if test="${khcl == '1'}">
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1" style="width:80%;text-align:center">
						<span>审批意见：</span>
					</td>
					<td class="td_table_1" style="text-align:center">
						<span>审批状态：</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_2" style="height:50px" rowspan="2" >
						<textarea style="width:100%;height:100%" name="suggest" >${dwcl.suggest }</textarea>
					</td>
					<td class="td_table_2" rowspan="2">
						<frame:select name="spzt" type="radio" configName="spzt" value="${empty dwcl.spzt ? '1' : dwcl.spzt}" cssClass="input_select"/>
					</td>
				</tr>
			</table>
			</c:if>
			
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<c:if test="${khcl != '1'}">
						<input type="submit" class="button_70px" onclick="checkform()" value="提交">
						&nbsp;&nbsp;
						<input type="submit" class="button_70px" onclick="change()" value="保存">
						&nbsp;&nbsp;
						<input type="button" class="button_70px" name="reback" value="关闭"
							onclick="window.close()">
						</c:if>
						<c:if test="${khcl == '1'}">
						<input type="submit" class="button_70px" value="提交">
						&nbsp;&nbsp;
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
						</c:if>
					</td>
				</tr>
			</table>
			
			<script type="text/javascript">
			function checkform(){
				var title = $("#title").val();
				var content = editor.html();
				$("#content").val(content);
				if(title == ''){
					alert("标题不能为空");
					$("#title").focus();
					return false;
				}
				if(content == ''){
					alert("提交内容不能为空");
					$("#content_").focus();
					return false;
				}
				//window.close();
			}
			function change(){
				var content = editor.html();
				$("#content").val(content);
				$("#savecl").val("0");
				//window.close();
			}
			function subform(){
				$.ajax({
					type:'POST',
					url:'${ctx }/dwcl/update',
					//data:$("#myForm").serialize(),//序列化表单里所有的内容
					data : {"cjsj":"11111"},
					//dataType:'json', 
					async: false,
					error: function(){
						var failMsg = '对不起，保存失败！';
						alert(failMsg);
						return false;
					},
					success: function(data){
						if(data != ''){
							if(data == '1'){
								alert("提交成功");
								window.close();
								window.opener.location.reload();
							}else if(data == '2'){
								alert("保存成功");
								window.close();
								window.opener.location.reload();
							}else{
								alert('对不起，数据传输失败！');
								return false;
							}
						}else{
							alert('对不起，数据传输失败！');
							return false;
						}
					}
				});
			}
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content_"]', {
					allowFileManager : true,
					cssPath : '${ctx}/styles/js/kindeditor-4.1.10/plugins/code/prettify.css',
					uploadJson : '${ctx}/styles/js/kindeditor-4.1.10/jsp/upload_json.jsp',
					fileManagerJson : '${ctx}/styles/js/kindeditor-4.1.10/jsp/file_manager_json.jsp',
					items : [
					         'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
					         'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					         'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
					         'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
					         'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					         'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
					         'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
					         'anchor', 'link', 'unlink', '|', 'about'
					],
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