<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1" name="viewport" content="initial-scale=1, width=device-width">
<title>Pagamento</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/login.css">
<script src="<%=request.getContextPath() %>/scripts/validate.js"></script>
</head>
<body>
	<div id="container">
		<div id="form">
			<fieldset>
			<legend>Inserisci i dettagli della carta</legend>
				<form action="OrdinaServlet" method="get" id="regForm" onsubmit="return validate()">
					<input type="text" placeholder="Numero carta" id="numCarta" name="numCarta" autofocus
					onchange="validateFormElem(this, numCartaPattern, document.getElementById('errorNumCarta'), numCartaErrorMessage)"><span id="errorNumCarta"></span><br>
					<input type="text" placeholder="Scadenza" id="scadenza"
					onchange="validateFormElem(this, scadenzaPattern, document.getElementById('errorScadenza'), scadenzaErrorMessage)"><span id="errorScadenza"></span><br>
					<input type="text" placeholder="CVV" id="cvv"
					onchange="validateFormElem(this, cvvPattern, document.getElementById('errorCvv'), cvvErrorMessage)"><span id="errorCvv"></span><br>
					<input type="text" placeholder="Nome titolare carta" id="nome"
					onchange="validateFormElem(this, nameOrLastnamePattern, document.getElementById('errorName'), nameErrorMessage)"><span id="errorName"></span><br>
					<input type="text" placeholder="Cognome titolare carta" id="cognome"
					onchange="validateFormElem(this, nameOrLastnamePattern, document.getElementById('errorLastName'), nameErrorMessage)"><span id="errorLastName"></span><br>
					
					<input type="submit"><input type="reset">
				</form>
			</fieldset>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>