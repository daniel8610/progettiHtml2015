<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.lucene.document.Document" %>
<%@ page import="java.util.List" %>
<%	
	List<Document> risultati = (List<Document>)request.getSession().getAttribute("risultati");
	int totale = risultati.size();
	List<String> snippets = (List<String>)request.getSession().getAttribute("snippets");
	String suggestion = (String)request.getSession().getAttribute("suggestions");
	int paginaCorrente = (Integer)request.getAttribute("page");
	double time = (Double)request.getSession().getAttribute("time");
	int max = totale/10;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>DataJungle Search Results</title>
</head>
<body>
<div id="container">
<div id="content">
<a href="index.jsp"><img src="img/logo.png" border="0" alt="DataJungle-logo" style="width:456px;height:100px"></a><br><br>
<%
	if (suggestion!=null){
		out.println("<div class=\"body-text\">");
		out.println("Did you mean: ");
		out.println("<a href=\"http://localhost:8080/DataJungle/HomeServlet?query="+ suggestion + "\">" + suggestion+"</a> ?");
		out.println("</div>");
	}
	
	if (risultati!=null && risultati.size()!=0){
		out.println("<div class=\"body-text\">");
		out.println("A total of "+ totale + " results found in: " + time + " seconds");
		out.println("</div>");
		if(max==0) {
			for(int i=0;i<totale;i++){
				out.println("<div class=\"result-separator\">");
				out.println("<a href=\""+risultati.get(i).get("url")+"\">"+risultati.get(i).get("title")+"</a><br><br>");
				out.println("<div class=\"date\"><a href=\"" + risultati.get(i).get("url")+"\">" + risultati.get(i).get("url") + "</a></div><br><br>");
				if(snippets.get(i)!=null){
					out.println(snippets.get(i) + "<br><br>");
				}
				out.println("<div class=\"date\">" + risultati.get(i).get("data") + "</div>");
				out.println("</div>");
			}
		}
		if(paginaCorrente<max && max!=0) {
			for(int i=((paginaCorrente-1)*10);i<paginaCorrente*10;i++){
				out.println("<div class=\"result-separator\">");
				out.println("<a href=\""+risultati.get(i).get("url")+"\">"+risultati.get(i).get("title")+"</a><br><br>");
				out.println("<div class=\"date\"><a href=\"" + risultati.get(i).get("url")+"\">" + risultati.get(i).get("url") + "</a></div><br>");
				if(snippets.get(i)!=null){
					out.println(snippets.get(i) + "<br><br>");
				}
				out.println("<div class=\"date\">" + risultati.get(i).get("data") + "</div>");
				out.println("</div>");
			}
		}
		if (paginaCorrente>=max && max!=0) {
			for(int i=((paginaCorrente-1)*10);i<totale;i++){
				out.println("<div class=\"result-separator\">");
				out.println("<a href=\""+risultati.get(i).get("url")+"\">"+risultati.get(i).get("title")+"</a><br><br>");
				out.println("<div class=\"date\"><a href=\"" + risultati.get(i).get("url")+"\">" + risultati.get(i).get("url") + "</a></div><br>");
				if(snippets.get(i)!=null){
					out.println(snippets.get(i) + "<br><br>");
				}
				//out.println("<div class=\"date\">" + risultati.get(i).get("data") + "</div>");
				out.println("</div>");
			}
		}
	} else{ 
		out.println("<div class=\"result-separator\">");
		out.println("No results found<br>");
		out.println("</div>");
	}

%>
<div class="body-text" align="center">
<%if ((paginaCorrente-3)>0) {%>
<a href=<%="http://localhost:8080/DataJungle/PaginationServlet?page="+(paginaCorrente-3) %>>Previous</a><% } %>
<%if ((paginaCorrente-2)>0) {%>
<a href=<%="http://localhost:8080/DataJungle/PaginationServlet?page="+(paginaCorrente-2) %>><%=paginaCorrente-2 %></a>
<% } %>
<%if ((paginaCorrente-1)>0) {%>
<a href=<%="http://localhost:8080/DataJungle/PaginationServlet?page="+(paginaCorrente-1) %>><%=paginaCorrente-1 %></a>
<% } %>
<a><%=paginaCorrente %></a>
<%if ((paginaCorrente+1)<max) {%>
<a href=<%="http://localhost:8080/DataJungle/PaginationServlet?page="+(paginaCorrente+1) %>><%=paginaCorrente+1 %></a>
<% } %>
<%if ((paginaCorrente+2)<max) {%>
<a href=<%="http://localhost:8080/DataJungle/PaginationServlet?page="+(paginaCorrente+2) %>><%=paginaCorrente+2 %></a>
<% } %>
<%if ((paginaCorrente+3)<max) {%>
<a href=<%="http://localhost:8080/DataJungle/PaginationServlet?page="+(paginaCorrente+3) %>>Next</a>
<% } %>
</div>
</div>
</div>
<div id="footer" align="center">Copyright (c) 2015 Daniel Gianandrea, Eder Monaco</div>
</body>
</html>