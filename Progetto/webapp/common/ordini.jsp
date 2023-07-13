<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="model.CarrelloDataSource"%>
<%@page import="DTO.Carrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String user=(String) session.getAttribute("user");
	if (user==null)
		response.sendRedirect(request.getContextPath()+"/common/index.jsp");
	CarrelloDataSource cds=new CarrelloDataSource();
	Collection<Carrello> ordini=cds.getOrdini(user);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ordini</title>
</head>
<body>
	<%
	for (Carrello c:ordini){%>
		<fieldset>
		<legend>Ordine</legend>
			<%=c.getScarpe() %>
		</fieldset>
	<%}%>
</body>
</html>