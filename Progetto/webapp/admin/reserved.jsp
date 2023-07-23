<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1" name="viewport" content="initial-scale=1, width=device-width">
<title>Admin</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/admin.css">
</head>
<body>
	<div>
		<ul>
			<li><a href="<%=request.getContextPath() %>/admin/add.jsp">Inserisci o modifica una scarpa</a></li>
			<li><a href="<%=request.getContextPath() %>/admin/delete.jsp">Elimina un prodotto</a></li>
			<li><a href="<%=request.getContextPath() %>/admin/ordiniUtenti.jsp">Visualizza gli ordni di tutti gli utenti</a></li>
			<li><a href="<%=request.getContextPath() %>/common/index.jsp">Trona alla home</a></li>
		</ul>
	</div>
</body>
</html>