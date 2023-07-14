<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
	<h1>Pagina riservata all'admin</h1>
	<a href="<%=request.getContextPath() %>/admin/add.jsp">Inserisci un prodotto</a><br>
	<a href="<%=request.getContextPath() %>/admin/delete.jsp">Elimina un prodotto</a><br>
	<a href="<%=request.getContextPath() %>/admin/ordiniUtenti.jsp">Visualizza gli ordni di tutti gli utenti</a>
</body>
</html>