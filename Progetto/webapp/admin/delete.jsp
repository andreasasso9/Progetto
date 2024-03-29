<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="DTO.Scarpa"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%Collection<?> scarpe=(Collection<?>) application.getAttribute("scarpe"); %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1" name="viewport" content="initial-scale=1, width=device-width">
<title>Elimina</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/login.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/select.css">
</head>
<body>
	<div id="container">
		<div id="form">
			<fieldset>
			<legend>Seleziona la scarpa da eliminare</legend>
				<form action="EliminaServlet" method="post">
					<select name="scarpa">
						<%Iterator<?> it=scarpe.iterator();
						while (it.hasNext()) {
							Scarpa s=(Scarpa) it.next();%>
							<option value="<%=s.getId() %>"><%=s.getNome() %></option>
						<%} %>
					</select>
					<input type="submit" value="Elimina" id="submit"><br>
				</form>
				<a class="home" href="<%=request.getContextPath() %>/admin/reserved.jsp">Torna alla pagina riservata all'admin</a>
			</fieldset>
		</div>
	</div>
</body>
</html>