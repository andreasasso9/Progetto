<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="model.CarrelloDataSource"%>
<%@page import="DTO.Carrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String user=(String) session.getAttribute("user");
	CarrelloDataSource cds=new CarrelloDataSource();
	Collection<Carrello> ordini=cds.doRetrieveAll();
	List<Carrello> ordiniUser=ordini.stream().filter(c->c.getUsername().equals(user)).toList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ordini</title>
</head>
<body>
	<%
		for (Carrello c:ordiniUser){%>
			<%=c.getCodice() %>
		<%} %>
</body>
</html>