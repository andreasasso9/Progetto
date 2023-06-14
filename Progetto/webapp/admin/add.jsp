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
		<input type="number" min="36" max="44" name="taglia" placeholder="Taglia"><br>
		<input type="number" name="costo" placeholder="Prezzo"><br>
		<label>Inserisci file</label>
		<input type="file" name="foto">
	</form>
</body>
</html>