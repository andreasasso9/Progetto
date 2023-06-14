<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Add</title>
</head>
<body>
	<form action="LoadFile" method="post" enctype="multipart/form-data"><br>
		<input type="text" name="nome" placeholder="Nome"><br>
		<input type="number" name="taglia" min="36" max="44" placeholder="Taglia"><br>
		<input type="number" name="prezzo" placeholder="Prezzo"><br>
		<label>Inserisci file</label>
		<input type="file" name="foto"><br>
		<input type="submit">
	</form>
</body>
</html>