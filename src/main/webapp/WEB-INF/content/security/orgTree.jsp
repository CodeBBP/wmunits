<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>税务机构人员管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="税务机构人员管理">
		<meta http-equiv="description" content="税务机构人员管理">
	</head>
	<frameset id="orgUserFrame" cols="320,*" frameborder="no" border="0" framespacing="0">
		<frame src="${ctx}/security/orgtree/orglist" name="orgFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="${ctx}/security/orgtree/userlist/${orgid}" name="userFrame" scrolling="auto" noresize="noresize" id="mainFrame" title="mainFrame" />
	</frameset>
</html>
