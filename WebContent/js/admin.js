function initialize() {
	var reservations = JSON
			.parse(document.getElementById("res_hidden").value);
	loadPrices();

	var totalCost = 0;

	for (var i = 0; i < reservations.length; i++) {
		var startDate = parseDate(reservations[i].startingDate);
		var endDate = parseDate(reservations[i].endDate);
		var cost = reservations[i].cost;

		var row = document.createElement("tr");

		var startCol = document.createElement("td");
		var endCol = document.createElement("td");
		var costCol = document.createElement("td");

		startCol.setAttribute("class", "res_cell");
		endCol.setAttribute("class", "res_cell");
		costCol.setAttribute("class", "res_cost");
		
		startCol.innerHTML = startDate;
		endCol.innerHTML = endDate;
		costCol.innerHTML = cost;

		row.appendChild(startCol);
		row.appendChild(endCol);
		row.appendChild(costCol);

		document.getElementById("res_table").appendChild(row);

		totalCost += cost;
	}

	document.getElementById("total").value = totalCost;
}

function loadPrices() {
	var prices = JSON.parse(document.getElementById("prices_hidden").value);

	document.getElementById("weekday").value = prices[0];
	document.getElementById("weekend").value = prices[1];
}

function parseDate(date) {
	var dateStr = date.year + "-";

	if (date.month <= 9) {
		dateStr += "0";
	}
	dateStr += date.month + "-";

	if (date.day <= 9) {
		dateStr += "0";
	}
	dateStr += date.day;

	return dateStr;
}

function updatePrices() {
	var weekday = document.getElementById("weekday").value;
	var weekend = document.getElementById("weekend").value;
	
	var newPrices = new Array();
	newPrices.push(weekday);
	newPrices.push(weekend);

	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.getResponseHeader("status") == "ok") {
				document.getElementById("prices_hidden").value = this.getResponseHeader("prices");
				
				loadPrices();
			} else {
				console.log(this.getResponseHeader("status"));
			}
		}
	};
	xhttp.open("POST", "./admin", true);
	xhttp.setRequestHeader("newPrices", JSON.stringify(newPrices));
	xhttp.send();
}