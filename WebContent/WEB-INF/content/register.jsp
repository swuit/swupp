<%--
网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
author  yeeku.H.lee kongyeeku@163.com
version  1.0
Copyright (C), 2001-2012, yeeku.H.Lee
This program is protected by copyright laws.
Program Name:
Date: 
--%>

<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="author" content="Yeeku.H.Lee(CrazyIt.org)" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>西大拍拍</title>
</head>
<body>
<table width="780" align="center" cellspacing="0"
	background="images/bodybg.jpg">
<tr>
<td >
<h3>欢迎注册</h3>
<div align="center">
<!-- 输出Action的错误提示 -->
<s:actionerror cssClass="error"/>
<s:form action="proRegister">
	<s:textfield name="username" label="用户名"/>
	<s:textfield name="password" label="密码"/>
	<s:textfield name="email" label="电子邮件"/>
	<s:textfield name="vercode" label="验证码"/>
	<s:submit value="注册"/>
	<s:reset value="重置"/>
</s:form>
验证码：<img name="d" src="auth.jpg">
</div>
</td>
</tr>
</table>
</body>
</html>