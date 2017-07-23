<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>帐号管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/wbox/jquery1.4.2.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/wbox/wbox.js"></script>
		<script>
		var iframewbox;
		function openOrg() {
 		iframewbox=$("#selectOrgBtn").wBox({
			   	requestType: "iframe",
			   	iframeWH:{width:800,height:400},
			   	title:"选择上级部门",
			   	show: true,
				target:"${ctx}/security/org?lookup=1"
			   });
		}
		
		function callbackProcess(id, name) {
			if(iframewbox) {
				document.getElementById("parentOrgId").value=id;
				document.getElementById("parentOrgName").value=name;
				iframewbox.close();
			}
		}
		</script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/security/user/setting" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					账号设置
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>单位名称*：</span>
					</td>
					<td class="td_table_2">
						&nbsp;${user.dwmc }
					</td>
					<td class="td_table_1">
						<span>所属县区*：</span>
					</td>
					<td class="td_table_2">
						&nbsp;${user.org.zzjgmc }
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>手机号码：</span>
					</td>
					<td class="td_table_2">
						${user.sjhm }
					</td>
					<td class="td_table_1">
						<span>用户名*：</span>
					</td>
					<td class="td_table_2">
						${user.username }
					</td>
				</tr>				
				<tr>
					<td class="td_table_1">
						<span>单位类型*：</span>
					</td>
					<td class="td_table_2">
						<frame:select name="dwlx" type="select" configName="dwlx" value="${user.dwlx}" cssClass="input_select" displayType="1"/>
					</td>
					<td class="td_table_1">
						<span>单位性质*：</span>
					</td>
					<td class="td_table_2">
						<frame:select name="dwxz" type="select" configName="dwxz" value="${user.dwxz}" cssClass="input_select" displayType="1"/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>单位等级*：</span>
					</td>
					<td class="td_table_2">
						<frame:select name="dwdj" type="select" configName="dwdj" value="${user.dwdj}" cssClass="input_select" displayType="1"/>
					</td>
					<td class="td_table_1">
						<span>单位地址*：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="dwdz" name="dwdz"
							value="${user.dwdz }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>分管领导：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="fgld" name="fgld"
							value="${user.fgld }" />
					</td>
					<td class="td_table_1">
						<span>分管领导号码：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="fgldhm" name="fgldhm"
							value="${user.fgldhm }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>单位联系人：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="lxr" name="lxr"
							value="${user.lxr }" />
					</td>
					<td class="td_table_1">
						<span>性别：</span>
					</td>
					<td class="td_table_2">
						<frame:select name="sex" type="radio" configName="sex" value="${user.sex == null ? '1' : user.sex }" cssClass="input_radio"/>
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>邮箱：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="email" name="email"
							value="${user.email }" />
					</td>
					<td class="td_table_1">
						<span>联系电话：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="lxdh" name="lxdh"
							value="${user.lxdh }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>QQ号码：</span>
					</td>
					<td class="td_table_2">
						<input type="text" class="input_240" id="qqhm" name="qqhm"
							value="${user.qqhm }" />
					</td>
					<td class="td_table_1">
						<span>密码*：</span>
					</td>
					<td class="td_table_2">
						<input type="password" class="input_240" id="password" name="password"
							value="${user.password }" />
					</td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<input type="submit" class="button_70px" name="submit" value="提交" onclick="return check()">
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
		var password = $("#password").val();
		if(password == ""){
			alert("密码不能为空");
			return false;
		}
		return true;
	}
	</script>
</html>
