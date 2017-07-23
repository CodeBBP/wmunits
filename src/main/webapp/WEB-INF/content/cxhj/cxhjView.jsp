<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>城乡环境</title>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="${ctx}/styles/js/kindeditor-4.1.10/themes/default/default.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/wbox/jquery1.4.2.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/kindeditor-4.1.10/kindeditor-min.js"></script>
		<script src="${ctx}/styles/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
		<script src="${ctx}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/wbox/wbox.js"></script>
		<script>
			var iframewbox;
			function mapmark(){
				var xd = $("#xpoint").val();
				var yd = $("#ypoint").val()*1000000;
				var mapurl = "";
				if(xd == ""){
					mapurl = "${ctx}/cxhj/mapmark";
				}else{
					mapurl = "${ctx}/cxhj/mapmark/"+xd+"/"+yd;
				}
				iframewbox=$("#map").wBox({
				   	requestType : "iframe",
				   	iframeWH : {width:800,height:600},
				   	title : "地图标记",
				   	show : true,
					target : mapurl
				   });
			}

			function callbackPoint(xdian, ydian) {
				var xdd = xdian;
				var ydd = ydian;
				$("#xpoint").val(xdd);
				$("#ypoint").val(ydd);
			}
		</script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/cxhj/update" method="post" enctype="multipart/form-data">
			<table width="100%" border="0" align="center" cellpadding="0"
					class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
				<tr>
					<td class="td_table_top" align="center">
						城乡环境
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
							value="${cxhj.title }" readonly/>
					</td>
					<td class="td_table_1">
						<span>创建时间：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="cjsj" name="cjsj" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							value="${cxhj.cjsj }" readonly/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>所属类型：</span>
					</td>
					<td class="td_table_2">
						${cxhj.khgz.khxm }
					</td>
					<td class="td_table_1">
						<span>牵头单位：</span>
					</td>
					<td class="td_table_2">
			    		<input type="text" class="input_240" id="qtbm" name="qtbm"
							value="${cxhj.qtbm }" readonly/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>责任单位：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="zrdw" name="zrdw"
							value="${cxhj.zrdw }" readonly/>
					</td>
					<td class="td_table_1">
						<span>上传图片：</span>
					</td>
					<td class="td_table_2">
						&nbsp;
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
						<span>实景照片：</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_2">
						<textarea name="content" id="content" style="width:100%;height:400px;visibility:hidden;">${cxhj.content }</textarea>
					</td>
					<td class="td_table_2" style="text-align:center">
						<img id="sjzp_" name="sjzp_" alt="" width="130" height="100" src="${cxhj.sjzp }"></img><br/><br/><br/>
						<input type='button' class='button_70px' value='地图标记' id="map" onclick="mapmark()"/>
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
			
			<script type="text/javascript">
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