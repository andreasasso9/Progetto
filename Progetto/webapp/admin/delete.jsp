<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="DTO.Scarpa"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%Collection<?> scarpe=(Collection<?>) application.getAttribute("scarpe"); %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Elimina</title>
</head>
<body>
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
			<input type="submit" value="Elimina">
		</form>
	</fieldset>
	<a href="<%=request.getContextPath() %>/common/index.jsp">Trona alla home</a>
</body>
</html>