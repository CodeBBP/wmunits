<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>报送材料</title>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="${ctx}/styles/js/kindeditor-4.1.10/themes/default/default.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/kindeditor-4.1.10/kindeditor-min.js"></script>
		<script src="${ctx}/styles/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
		<script src="${ctx}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/wbox/wbox.js"></script>
		<script>
			var iframewbox;
			function openGrade() {
		 		iframewbox=$("#selectGrade").wBox({
					   	requestType: "iframe",
					   	iframeWH:{width:800,height:400},
					   	title:"选择评分标准",
					   	show: true,
						target:"${ctx}/xmcl/pfbztree"
					   });
			}

			function uploadFiles() {
		 		iframewbox=$("#upload").wBox({
					   	requestType: "iframe",
					   	iframeWH:{width:700,height:400},
					   	title:"视屏上传",
					   	show: true,
						target:"${ctx}/xmcl/upload"
					   });
			}
			
			function callbackProcess2(pgbz,sslx,bzfz) {
				if(iframewbox) {
					document.getElementById("category").value=sslx;
					document.getElementById("pgbz").value=pgbz;
					document.getElementById("zf").value=bzfz;
					iframewbox.close();
				}
			}
		</script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/xmcl/update" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="shzt" id="shzt" value="${xmcl.shzt }"/>
			<input type="hidden" name="zf" id="zf" value="${xmcl.zf }"/>
			<table width="100%" border="0" align="center" cellpadding="0"
					class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
				<tr>
					<td class="td_table_top" align="center">
						报送材料
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
							value="${xmcl.title }" />
					</td>
					<td class="td_table_1">
						<span>创建时间：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="createdate" name="createdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							value="${xmcl.createdate }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>申报学校：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="sbxs" name="sbxs"
							value="${xmcl.sbxs }" />
					</td>
					<td class="td_table_1">
						<span>所属类型：</span>
					</td>
					<td class="td_table_2">
			    		<input type="text" class="input_240" id="category" name="category" value="${xmcl.category }" readonly/>
						<input type='button' class='button_70px' value='选择标准' id="selectGrade" onclick="openGrade()"/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>审核领导：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="shld" name="shld"
							value="${xmcl.shld }" />
					</td>
					<td class="td_table_1">
						<span>添加附件：</span>
					</td>
					<td class="td_table_2">
						&nbsp;<input type='button' class='button_70px' name="fjzl" value='视频上传' id="upload" onclick="uploadFiles()"/>
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
						<textarea name="content" id="content" style="width:100%;height:400px;visibility:hidden;">${xmcl.content }</textarea>
					</td>
					<td class="td_table_2">
						<textarea name="pgbz" id="pgbz" style="width:100%;height:400px;" readonly>${xmcl.pgbz }</textarea>
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
				var createdate = $("#createdate").val();
				var author = $("#author").val();
				var content = $("#content").val();
				if(trim(title) == ''){
					alert("标题不能为空");
					$("#title").focus();
					return false;
				}
				if(trim(createdate) == ''){
					alert("创建时间不能为空");
					$("#createdate").focus();
					return false;
				}	
				if(trim(author) == ''){
					alert("提交学校不能为空");
					$("#author").focus();
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