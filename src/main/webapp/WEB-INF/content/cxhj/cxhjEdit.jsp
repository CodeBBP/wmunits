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
			function uploadFiles() {
		 		iframewbox=$("#upload").wBox({
					   	requestType: "iframe",
					   	iframeWH:{width:700,height:400},
					   	title:"图片上传",
					   	show: true,
						target:"${ctx}/cxhj/upload"
					   });
			}

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
			
			function callbackProcess3(filename) {
				if(iframewbox) {
					var now = new Date();
			        var year = now.getFullYear();
			        var month = now.getMonth()+1;
			        var day = now.getDate();
			        var date1 = year;
			        if(month < 10)
			        	date1 += "0";
			        date1 += ""+month;
			        if(day < 10)
			        	date1 += "0";
			        date1 += ""+day;
					document.getElementById("sjzp_").src="${ctx}/uploads/${username}/" + date1 + "/" + filename;
					$("#sjzp").val(document.getElementById("sjzp_").src);
					iframewbox.close();
				}
			}
		</script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/cxhj/update" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="sjzp" id="sjzp" value="${cxhj.sjzp }"/>
			<input type="hidden" name="xpoint" id="xpoint" value="${cxhj.xpoint }"/>
			<input type="hidden" name="ypoint" id="ypoint" value="${cxhj.ypoint }"/>
			<input type="hidden" name="zzjgmc" id="zzjgmc" value="${dwcl.zzjgmc }"/>
			<input type="hidden" name="zzjgdm" id="zzjgdm" value="${dwcl.zzjgdm }"/>
			<input type="hidden" name="tsbz" id="tsbz" value="${tsbz }"/>
			<input type="hidden" name="ztbz" id="ztbz" value="${dwcl.ztbz }"/>
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
							value="${cxhj.title }" />
					</td>
					<td class="td_table_1">
						<span>创建时间：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="cjsj" name="cjsj" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
							value="${empty cxhj.cjsj ? cjsj : cxhj.cjsj}" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>所属类型：</span>
					</td>
					<td class="td_table_2">
						<!--<frame:select name="sslb" type="select" configName="hjlb" value="${cxhj.sslb}" cssClass="input_select"/>-->
						${khgz.khxm }
						&nbsp;<input type="hidden" name="khgz.id" id="khgzId" value="${khgz.id }"/>
					</td>
					<td class="td_table_1">
						<span>牵头单位：</span>
					</td>
					<td class="td_table_2">
			    		<input type="text" class="input_240" id="qtbm" name="qtbm"
							value="${empty cxhj.qtbm ? scdw.dwmc : cxhj.qtbm}" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>责任单位：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="zrdw" name="zrdw"
							value="${empty cxhj.zrdw ? scdw.dwmc : cxhj.zrdw}" />
					</td>
					<td class="td_table_1">
						<span>上传图片：</span>
					</td>
					<td class="td_table_2">
						&nbsp;<input type='button' class='button_70px' value='上传图片' id="upload" onclick="uploadFiles()"/>
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
				var sslb = $("#sslb").val();
				var content = $("#content").val();
				var xpoint = $("#xpoint").val();
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
				if(trim(sslb) == ''){
					alert("所属类别不能为空");
					$("#sslb").focus();
					return false;
				}
				if(trim(content) == ''){
					alert("提交内容不能为空");
					$("#content").focus();
					return false;
				}
				if(trim(xpoint) == ''){
					alert("地图坐标未选");
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