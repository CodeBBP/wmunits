<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>考核参数结构树</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="考核参数管理">
		<meta http-equiv="description" content="考核参数管理">
	</head>
	<frameset id="orgUserFrame" cols="270,*" frameborder="no" border="0" framespacing="0">
		<frame src="${ctx}/khcs/dwcltreelist" name="orgFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="${ctx}/khcs/khcsitem/578" name="khcsFrame" scrolling="auto" noresize="noresize" id="khcsFrame" title="khcsFrame" />
	</frameset>
</html>
