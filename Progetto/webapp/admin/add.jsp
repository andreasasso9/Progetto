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
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/add.css">
</head>
<body>
	<aside>
		<a href="<%=request.getContextPath() %>/admin/reserved.jsp">Torna alla pagina<br>riservata all'admin</a>
	</aside>
	<div class="container">
		<div class="form">
			<fieldset>
				<legend>Aggiungi una scarpa</legend>
				<form action="AddScarpaServlet" method="post">
					<input type="text" name="nome" placeholder="Nome"><br>
					<input type="number" name="prezzo" placeholder="Prezzo"><br>
					<input type="submit"> <input type="reset">
				</form>
			</fieldset>
		</div>
	</div>
	
	<div class="container">
		<div class="form">
			<fieldset>
			<legend>Aggiungi una foto</legend>
				<form action="UploadFotoServlet" method="post" enctype="multipart/form-data">
					<select name="id">
						<%if (scarpe != null && scarpe.size()>0){
							Iterator<?> it=scarpe.iterator();
							while (it.hasNext()){
								Scarpa s=(Scarpa) it.next();%>
								<option value="<%=s.getId() %>"><%=s.getNome() %></option>
							<%}
						}%>
					</select>
					<br>
					<input type="file" name="foto" value="Inserisci una foto"><br>
					<input type="submit" value="Upload"><input type="reset" value="Reset">
				</form>
			</fieldset>
		</div>
	</div>
	
	<div class="container">
		<div class="form">
			<fieldset>
			<legend>Cambia il prezzo di una scarpa</legend>
				<form action="CambiaPrezzoServlet" method="post">
					<select name="id">
						<%if (scarpe != null && scarpe.size()>0){
							Iterator<?> it=scarpe.iterator();
							while (it.hasNext()){
								Scarpa s=(Scarpa) it.next();%>
								<option value="<%=s.getId() %>"><%=s.getNome() %></option>
							<%}
						}%>
					</select>
					<input type="number" name="prezzo" min="1" placeholder="Prezzo">
					<input type="submit">
				</form>
			</fieldset>
		</div>
	</div>
</body>
</html>