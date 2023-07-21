function ordinaPerNome() {
	let fieldsets = $("fieldset");

	fieldsets.sort(function(a, b) {
		let nomeA = $(a).find("legend").text()
		let nomeB = $(b).find("legend").text()

		return nomeA.localeCompare(nomeB);
	})

	fieldsets.detach()

	$("div.scarpa").append(fieldsets)
}

function ordinaPerPrezzo() {
	let fieldsets = $("fieldset");

	fieldsets.sort(function(a, b) {
		let prezzoA = parseFloat($(a).find("p").text().substring(1))
		let prezzoB = parseFloat($(b).find("p").text().substring(1))

		return prezzoB - prezzoA
	})

	fieldsets.detach()

	$("div.scarpa").append(fieldsets)
}

function ordinaPerData(inizio, fine) {
	let fieldsets = $("fieldset")
	let inData=[]
	let dataInizio=new Date(inizio)
	let dataFine=new Date(fine)
	console.log(dataInizio)
	console.log(dataFine)

	fieldsets.each(function () {
		let data = $(this).find("p#data").text();
		let currentData=new Date(data)
		if (currentData>=dataInizio && currentData<=dataFine)
			$(this).show()
		else
			$(this).hide()
	})
}
