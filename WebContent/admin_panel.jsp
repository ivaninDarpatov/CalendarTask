<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel</title>

<!-- CSS -->
<link rel="stylesheet" href="static/style.css">
<link rel="stylesheet" href="static/admin.css">

<!-- JS -->
<script type="text/javascript" src="js/admin.js"></script>

</head>
<body onload="initialize()">
	<input id="res_hidden" type="hidden" value='${reservations}' />
	<input id="prices_hidden" type="hidden" value='${prices}' />
	
	<div id="parent" style="background-color: yellow">
		<div id="menu" onclick="document.location.href = './';">BACK</div>
		<div id="left_panel" class="inner_col" style="background-color: red">
			<div id="res_info" style="background-color: orange">
				<table id="res_table">
					<tr>
						<th colspan="3">Reservations</th>
					</tr>

					<tr>
						<th class="res_cell">start date</th>
						<th class="res_cell">end date</th>
						<th class="res_cost">cost</th>
					</tr>
				</table>
			</div>

			<div id="total_cost" style="background-color: pink">
				<table id="total_table">
					<tr>
						<td>total:</td>
						<td><input id="total" class="text_input" type="text" disabled></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="right_panel" class="inner_col" style="background-color: blue">
			<div id="prices_update" style="background-color: purple">
				<table>
					<tr>
						<th colspan="2">prices</th>
					</tr>

					<tr>
						<th>weekday</th>
						<th>weekend</th>
					</tr>

					<tr>
						<td><input id="weekday" class="text_input" type="text"></td>
						<td><input id="weekend" class="text_input" type="text"></td>
					</tr>

					<tr>
						<td colspan="2">
							<button onclick="updatePrices()">UPDATE</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>


</body>
</html>