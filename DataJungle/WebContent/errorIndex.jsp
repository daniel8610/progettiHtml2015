<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>DataJungle Search</title>
</head>
<body>
<div id="container">
<div id="content">
<div align='center'>
<img src="img/logo.png" alt="DataJungle-logo"><br><br><br>
<form method="get" action="HomeServlet">
<div class="buttonHolder">
<div class="ui-widget">
                <label for="terms"></label>
                <input id="terms" type="text" name="query"  style="width:500px; height:32px" />
                <input type="submit" id="search" name="submit" alt="search" value="" /><br><br>
        <font color="red">You forgot to type the query</font>  
                
</div>
</div>
</form>
<br>
<div class="body-text">
<a href="advanced.jsp">Advanced Search</a>
</div>
</div>
</div>
</div>
<div id="footer" align="center">Copyright (c) 2015 Daniel Gianandrea, Eder Monaco</div>
</body>
</html>
</html>