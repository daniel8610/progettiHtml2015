<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>DataJungle Advanced Search</title>
</head>
<body>
<div id="container">
<div id="content">
<a href="index.jsp"><img src="img/logo.png" border="0" alt="DataJungle-logo" style="width:456px;height:100px"></a><br><br>
Advanced Search<br>
Find pages with...<br><br>
<font color="red">One or more inputs are missing</font>
<form method="get" action="AdvancedSearchServlet">
<div class="ui-widget">
                <label for="terms"></label>
                <input id="terms" type="text" name="and"  style="width:500px; height:30px"/> Words that you want to search
                <br><br><input id="terms" type="text" name="not"  style="width:500px; height:30px"/> Words that you don't want to be in the documents
                <br><br><br><div align="center"><input type="submit" value="Advanced Search" style="width:200px; height:30px" /></div>
</div>
</form>
<br><br>

<br><br>
</div>
</div>
<div id="footer" align="center">Copyright (c) 2015 Daniel Gianandrea, Eder Monaco</div>
</body>
</html>