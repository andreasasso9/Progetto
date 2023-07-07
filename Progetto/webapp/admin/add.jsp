<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Add</title>
</head>
<body>
	<fieldset name="Aggiungi una scarpa">
		<form action="AddScarpaServlet" method="post" enctype="multipart/form-data"><br>
			<input type="text" name="nome" placeholder="Nome"><br>
			<input type="number" name="taglia" min="36" max="50" placeholder="Taglia"><br>
			<input type="number" name="prezzo" placeholder="Prezzo"><br>
			<label>Inserisci file</label>
			<input type="file" name="foto"><br>
			<input type="submit"> <input type="reset">
		</form>
	</fieldset>
	
	<fieldset name="Aggiungi la foto">
		<select>
			<%
				
			%>
		</select>
	</fieldset>
</body>
</html>