<%@page import="DTO.Scarpa"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection" %>
<%
	Collection<?> scarpe=(Collection<?>) getServletContext().getAttribute("scarpe");
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Add</title>
</head>
<body>
	<fieldset>
		<legend>Aggiungi una scarpa</legend>
		<form action="AddScarpaServlet" method="post"><br>
			<input type="text" name="nome" placeholder="Nome"><br>
			<input type="number" name="prezzo" placeholder="Prezzo"><br>
			<input type="submit"> <input type="reset">
		</form>
	</fieldset>
	
	<fieldset>
	<legend>Aggiungi una foto</legend>
		<form action="UploadFotoServlet" method="post" enctype="multipart/form-data">
			<select name="id">
			<%
				if (scarpe != null && scarpe.size()>0){
					Iterator<?> it=scarpe.iterator();
					while (it.hasNext()){
						Scarpa s=(Scarpa) it.next();
			%>
						<option value="<%=s.getId() %>"><%=s.getNome() %></option>
			<%
					}
				}
			%>
			</select>
			<br>
			<input type="file" name="foto" value="Inserisci una foto">
			<input type="submit" value="Upload"><input type="reset" value="Reset">
		</form>
	</fieldset>
	<a href="<%=request.getContextPath() %>/common/index.jsp">Trona alla home</a>
</body>
</html>