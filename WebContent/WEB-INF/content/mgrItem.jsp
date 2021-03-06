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
	<title>管理拍卖物品</title>
</head>
<body>
<table width="780" align="center" cellspacing="0"
	background="images/bodybg.jpg">
<tr>
<td>
<br />
<table width="80%" align="center" cellpadding="0"
	cellspacing="1" style="border:1px solid black">
<tr>
	<td colspan="4" ><div class="mytitle">您当前的拍卖物品：</div></td> 
</tr>
<tr  height="30">
	<td><b>物品名</b></td>
	<td><b>物品种类</b></td>
	<td><b>赢取价格</b></td>
	<td><b>物品备注</b></td>
</tr>
<s:iterator id="item" value="items" status="st">
	<tr height="24" <s:if test="#st.odd">
	style="background-color:#dddddd"</s:if>
	<s:else>style="background-color:#eeeeee"</s:else>>
		<td><s:property value="name"/></td>
		<td><s:property value="kind"/></td>
		<td><s:property value="maxPrice"/></td>
		<td><s:property value="remark"/></td>
	</tr>
</s:iterator>
</table>
</td>
</tr>
<tr>
<td>
<br />
<h3>添加新物品</h3>
<div align="center">
<s:actionerror/> 
<s:form action="proAddItem">
<s:textfield name="item.itemName" label="物品名"/>
<s:textfield name="item.itemDesc" label="物品描述"/>
<s:textfield name="item.itemRemark" label="物品备注"/>
<s:textfield name="item.initPrice" label="起拍价格"/>
<s:select name="avail" list="#{'1':'一天','2':'二天','3':'三天','4':'四天',
	'5':'五天','6':'一个星期','7':'一个月','8':'一年'}"
	label="有效时间"/>
<s:select list="kinds" label="物品种类" name="kindId"
	listKey="id"
	listValue="kindName"/>
<s:textfield name="vercode" label="验证码"/>
<s:submit value="添加"/>
</s:form>
验证码：<img name="d" src="auth.jpg">
</div>
</td>
</tr>
</table>
</body>
</html>