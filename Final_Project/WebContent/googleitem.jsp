<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div style="text-align: center;">
	<img src="https://i.imgur.com/PFxwwJV.png" width="500" height="300">
	<br>
	<br>
	<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body>
	<%
		String[][] orderList = (String[][]) request.getAttribute("query");
		String[][] orderList1 = (String[][]) request.getAttribute("relative");
		String keywordlist = request.getParameter("keyword");
		for (int i = 0; i < orderList.length; i++) {
	%>
	<a href='<%=orderList[i][1]%>'><%=orderList[i][0]%></a>
	<br>
	<h style="font-size:10px ;"><%=orderList[i][1]%></h>
	<br>
	<br>
	<%
		}
	%>
	<div>
		<h1>
			<font color="#0000a0" size=4><%=keywordlist%></font><font size=4>的相關搜尋</font>
		</h1>
	</div>
	<%
		for (int i = 0; i < orderList1.length; i++) {
	%>
	<a href='<%=orderList1[i][1]%>'><%=orderList1[i][0]%></a>
	<br>
	<h style="font-size:10px ;"><%=orderList1[i][1]%></h>
	<br>
	<br>
	<%
		}
	%>
</body>
	</html>