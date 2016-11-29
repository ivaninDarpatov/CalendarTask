var month = 0;
var year = 0;
var sDate = 0;
var eDate = 0;
var selectedDates = new Array();
var reservedDates = new Array();

function initialize() {
	var prices = document.getElementById("prices_hidden").value;
	prices = JSON.parse(prices);
	document.getElementById("weekday").value = prices[0];
	document.getElementById("weekend").value = prices[1];

	var today = new Date();
	month = today.getMonth();
	year = today.getFullYear();

	getReserved();

	printCalendar();
}

function getReserved() {
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.getResponseHeader("status") == "ok") {
				getReservedDates(this.getResponseHeader("reserved"));
				reserveDates();
			} else {
				console.log(this.getResponseHeader("status"));
			}
		}
	};
	xhttp.open("POST", "./user", true);
	xhttp.setRequestHeader("currentMonth", month + 1);
	xhttp.setRequestHeader("currentYear", year);
	xhttp.send();
}

function getReservedDates(dates) {
	reservedDates = JSON.parse(dates);
}

function printCalendar() {
	var calendar2D = new Array(6);
	for (var i = 0; i < calendar2D.length; i++) {
		calendar2D[i] = new Array(7);
	}

	var firstDay = new Date(year, month % 12, 1);
	var lastDayYear = (month == 11) ? year + 1 : year;
	var lastDay = new Date(lastDayYear, (month + 1) % 12, 0);

	var monthName;
	switch (month) {
	case 0:
		monthName = "January";
		break;
	case 1:
		monthName = "February";
		break;
	case 2:
		monthName = "March";
		break;
	case 3:
		monthName = "April";
		break;
	case 4:
		monthName = "May";
		break;
	case 5:
		monthName = "June";
		break;
	case 6:
		monthName = "July";
		break;
	case 7:
		monthName = "August";
		break;
	case 8:
		monthName = "September";
		break;
	case 9:
		monthName = "October";
		break;
	case 10:
		monthName = "November";
		break;
	case 11:
		monthName = "December";
		break;
	}

	document.getElementById("month").innerHTML = monthName;
	document.getElementById("year").innerHTML = year;

	var calContainer = document.getElementById("days");
	var table = document.createElement("table");
	table.setAttribute("id", "days_table");

	var date = 1;
	var print = false;

	for (var i = 0; i < calendar2D.length; i++) {
		var row = document.createElement("tr");

		for (var j = 0; j < calendar2D[i].length; j++) {
			var cell = document.createElement("td");

			if (i == 0 && firstDay.getDay() % 7 == (j + 1) % 7) {
				print = true;
			}

			if (print && date <= lastDay.getDate()) {
				var dateString = dateToString(year, month, date);
				cell.style.background = "#8BD3F5";

				if (j % 7 == 5 || j % 7 == 6) {
					cell.style.background = "#6DAECC";
				}

				cell.id = dateString;
				cell.innerHTML = date++;
				cell.realDate = new Date(cell.id);
				cell.onmouseover = function() {
					if (sDate == 0) {
						highlight(this.id);
					} else {
						this.markDates();
						selectDates();
						reserveDates();
					}
				}
				cell.onmouseout = function() {
					if (sDate == 0) {
						deHighlight(this.id);
						reserveDates();
					}
				}
				cell.onclick = function() {
					if (isReserved(this.id)) {
						return;
					}

					if (sDate == 0) {
						sDate = this.id;
						this.style.color = "white";
					} else {
						if (sDate == this.id) {
							if (eDate == this.id) {
								sDate = 0;
								eDate = 0;
								this.markDates();
							} else {
								eDate = this.id;
								this.markDates();
								selectDates();
							}
						} else {
							if (eDate == this.id) {
								eDate = 0;
								printCalendar();
							} else {
								if (containsReserved(sDate, this.id)
										|| containsReserved(this.id, sDate)) {
									return;
								}

								eDate = this.id;
								this.markDates();
								selectDates();
								reserveDates();
							}
						}
						printCalendar();
					}
					calculateCost();
				}
				cell.markDates = function() {

					if (sDate == eDate || sDate != 0 && eDate != 0) {
						var unmarkedDates = getDates(firstDay, lastDay);

						for (var count = 0; count < unmarkedDates.length; count++) {
							var cellID = dateToString(unmarkedDates[count]
									.getFullYear(), unmarkedDates[count]
									.getMonth(), unmarkedDates[count].getDate());

							document.getElementById(cellID).style.color = "black";
						}
					}

					if (sDate != 0 && eDate == 0) {
						var markedDates = new Array();
						var unmarkedDates = new Array();

						var startDate = new Date(sDate);
						var thisDate = new Date(this.id);

						if (isAfter(startDate, lastDay)) {
							// mark all from this day to last day
							if (thisDate.getDate() == firstDay.getDate()) {
								markedDates = getDates(firstDay, lastDay);
							} else {
								var toDate = new Date(thisDate.getFullYear(),
										thisDate.getMonth(),
										thisDate.getDate() - 1);
								markedDates = getDates(thisDate, lastDay);
								unmarkedDates = getDates(firstDay, toDate);
							}
						}

						if (isAfter(firstDay, startDate)) {
							// mark all from first day to this day
							if (thisDate.getDate() == lastDay.getDate()) {
								markedDates = getDates(firstDay, lastDay);
							} else {
								var fromDate = new Date(thisDate.getFullYear(),
										thisDate.getMonth(),
										thisDate.getDate() + 1);
								markedDates = getDates(firstDay, thisDate);
								unmarkedDates = getDates(fromDate, lastDay);
							}
						}

						if (!isAfter(startDate, lastDay)
								&& !isAfter(firstDay, startDate)) {
							// mark all from this day to start date
							if (isAfter(startDate, thisDate)) {
								var toDate = new Date(thisDate.getFullYear(),
										thisDate.getMonth(),
										thisDate.getDate() - 1);
								var fromDate = new Date(
										startDate.getFullYear(), startDate
												.getMonth(), startDate
												.getDate() + 1);

								markedDates = getDates(thisDate, startDate);

								if (isAfter(thisDate, firstDay)) {
									unmarkedDates = getDates(firstDay, toDate);
								}

								if (isAfter(lastDay, startDate)) {
									unmarkedDates = unmarkedDates
											.concat(getDates(fromDate, lastDay));
								}
							} else {
								var toDate = new Date(startDate.getFullYear(),
										startDate.getMonth(), startDate
												.getDate() - 1);
								var fromDate = new Date(thisDate.getFullYear(),
										thisDate.getMonth(),
										thisDate.getDate() + 1);

								markedDates = getDates(startDate, thisDate);

								if (isAfter(startDate, firstDay)) {
									unmarkedDates = getDates(firstDay, toDate);
								}

								if (isAfter(lastDay, thisDate)) {
									unmarkedDates = unmarkedDates
											.concat(getDates(fromDate, lastDay));
								}
							}
						}

						for (var count = 0; count < markedDates.length; count++) {
							var cellID = dateToString(markedDates[count]
									.getFullYear(), markedDates[count]
									.getMonth(), markedDates[count].getDate());
							document.getElementById(cellID).style.color = "white";
						}

						for (var count = 0; count < unmarkedDates.length; count++) {
							var cellID = dateToString(unmarkedDates[count]
									.getFullYear(), unmarkedDates[count]
									.getMonth(), unmarkedDates[count].getDate());
							document.getElementById(cellID).style.color = "black";
						}
					}
					reserveDates();
				}
			}

			row.appendChild(cell);
		}

		table.appendChild(row);
	}

	calContainer.innerHTML = "";
	calContainer.appendChild(table);
	selectDates();
	reserveDates();
}

window.onclick = function() {
	selectDates();
}

function containsReserved(from, to) {
	from = new Date(from);
	to = new Date(to);
	var datesRange = getDates(from, to);

	for (var j = 0; j < datesRange.length; j++) {
		if (isReserved(datesRange[j])) {
			return true;
		}
	}

	return false;
}

function isReserved(dateStr) {
	var date = new Date(dateStr);

	for (var i = 0; i < reservedDates.length; i++) {
		if (reservedDates[i].year == date.getFullYear()
				&& reservedDates[i].month - 1 == date.getMonth()
				&& reservedDates[i].day == date.getDate()) {
			return true;
		}
	}

	return false;
}

function reserveDates() {
	if (reservedDates == null) {
		return;
	}
	
	for (var i = 0; i < reservedDates.length; i++) {
		var cellID = dateToString(year, month, reservedDates[i].day);
		var el = document.getElementById(cellID);
		var jsDay = new Date(cellID);

		if (jsDay.getDay() == 6 || jsDay.getDay() == 0) {
			el.style.background = "#515151";
		} else {
			el.style.background = "#8E8E8E";
		}
		
		el.style.cursor = "not-allowed";
	}
}

function selectDates() {
	if (sDate != 0 && eDate != 0) {
		var startDate = new Date(sDate);
		var endDate = new Date(eDate);

		if (isAfter(startDate, endDate)) {
			var temp = startDate;
			startDate = endDate;
			endDate = temp;
		}

		selectedDates = getDates(startDate, endDate);

		for (var i = 0; i < selectedDates.length; i++) {
			if (selectedDates[i].getMonth() == month
					&& selectedDates[i].getFullYear() == year) {
				var cellID = dateToString(year, month, selectedDates[i]
						.getDate());
				if (!isReserved(cellID)) {
					var el = document.getElementById(cellID);
					var jsDay = new Date(cellID);

					if (jsDay.getDay() == 6 || jsDay.getDay() == 0) {
						el.style.background = "#134827";
					} else {
						el.style.background = "#2F8C53";
					}
					el.style.color = "black";
				}
			}
		}
	}
}

function dateToString(_year, _month, _date) {
	var monthStr = (_month < 9) ? "0" + (_month + 1) : (_month + 1);
	var dateStr = (_date <= 9) ? "0" + _date : _date;
	return _year + "-" + monthStr + "-" + dateStr;
}

function nextMonth() {
	if (month == 11) {
		month = 0;
		year++;
	} else {
		month++;
	}
	
	reservedDates = null;
	
	getReserved();

	printCalendar();
}

function previousMonth() {
	if (month == 0) {
		month = 11;
		year--;
	} else {
		month--;
	}
	
	reservedDates = null;

	getReserved();

	printCalendar();
}

function isAfter(startDate, endDate) {
	if (startDate.getFullYear() != endDate.getFullYear()) {
		return startDate.getFullYear() > endDate.getFullYear();
	}

	if (startDate.getMonth() != endDate.getMonth()) {
		return startDate.getMonth() > endDate.getMonth();
	}

	return startDate.getDate() > endDate.getDate();
}

function getDates(startDate, endDate) {
	var dates = new Array();
	var currentDate = startDate;
	while (currentDate <= endDate) {
		dates.push(new Date(currentDate))
		currentDate = new Date(currentDate.getFullYear(), currentDate
				.getMonth(), currentDate.getDate() + 1);
	}
	return dates;
}

function calculateCost() {
	var startHolder = document.getElementById("start");
	var endHolder = document.getElementById("end");
	var costHolder = document.getElementById("cost");

	if (isAfter(new Date(sDate), new Date(eDate)) && eDate != 0) {
		endHolder.value = sDate;
		startHolder.value = eDate;
	} else {
		startHolder.value = sDate;
		endHolder.value = eDate;
	}

	if (sDate == 0) {
		costHolder.value = 0;
	} else {
		var eChanged = false;

		if (eDate == 0) {
			eDate = sDate;
			eChanged = true;
		}

		var startDate = new Date(sDate);
		var endDate = new Date(eDate);

		var weekdayPrice = document.getElementById("weekday").value;
		var weekendPrice = document.getElementById("weekend").value;
		var resultCost = 0;

		if (isAfter(startDate, endDate)) {
			var temp = new Date(startDate);
			startDate = new Date(endDate);
			endDate = new Date(temp);
		}

		var dates = getDates(startDate, endDate);

		for (var i = 0; i < dates.length; i++) {
			if (dates[i].getDay() % 7 == 0 || dates[i].getDay() % 7 == 6) {
				resultCost += JSON.parse(weekendPrice);
			} else {
				resultCost += JSON.parse(weekdayPrice);
			}
		}

		costHolder.value = JSON.stringify(resultCost);
		if (eChanged) {
			eDate = 0;
		}
	}
}

function reserveSelected() {
	var from = document.getElementById("start").value;
	var to = document.getElementById("end").value;
	var cost = document.getElementById("cost").value;

	var newRes = new Array();
	newRes.push(from);
	newRes.push(to);
	newRes.push(cost);

	if (from == 0 || to == 0) {
		return;
	}

	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.getResponseHeader("status") == "ok") {
				sDate = 0;
				eDate = 0;
				selectedDates = new Array();
				getReservedDates(this.getResponseHeader("reserved"));
				reserveDates();
				printCalendar();

				document.getElementById("start").value = 0;
				document.getElementById("end").value = 0;
				document.getElementById("cost").value = 0;
			} else {
				console.log(this.getResponseHeader("status"));
			}
		}
	};
	xhttp.open("POST", "./user", true);
	xhttp.setRequestHeader("toReserve", JSON.stringify(newRes));
	xhttp.setRequestHeader("currentMonth", month + 1);
	xhttp.setRequestHeader("currentYear", year);
	xhttp.send();
}