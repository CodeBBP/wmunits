<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <title>六安文明管理系统</title>
</head>
<frameset rows="83,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="${ctx }/top" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame"/>
    <frameset id="myFrame" cols="172,7,*" frameborder="no" border="0" framespacing="0">
        <frame src="${ctx }/left" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame"/>
        <frame src="${ctx }/middle" name="midlleFrame" scrolling="no" noresize="noresize" id="midlleFrame"
               title="midlleFrame"/>
        <frame src="${ctx }/right" name="mainFrame" scrolling="auto" noresize="noresize" id="mainFrame"
               title="mainFrame"/>
    </frameset>
</frameset>
</html>

