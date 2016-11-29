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
<script type="text/javascript" src="js/home.js"></script>

</head>
<body onload="initialize()">
	<input id="res_hidden" type="hidden" value='${reservations}' />
	<input id="prices_hidden" type="hidden" value='${prices}' />
	
	<div id="parent">
		<div id="menu" onclick="document.location.href = './';"
							onmouseover="highlight('menu')"
							onmouseout="deHighlight('menu')">BACK</div>
		<div id="left_panel" class="inner_col">
			<div id="res_info">
				<table id="res_table">
					<tr>
						<th colspan="3">Reservations</th>
					</tr>

					<tr>
						<th id="start" class="res_start, res_meta">start date</th>
						<th class="res_end, res_meta">end date</th>
						<th id="cost" class="res_cost, res_meta">cost</th>
					</tr>
				</table>
			</div>

			<div id="total_cost">
				<table id="total_table">
					<tr>
						<td id="total_meta">total</td>
						<td><input id="total" class="text_input" type="text" disabled></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="right_panel" class="inner_col">
			<div id="prices_update" class="stats_container">
				<table>
					<tr>
						<th colspan="2">prices</th>
					</tr>

					<tr>
						<th class="stats_meta">weekday</th>
						<th class="stats_meta">weekend</th>
					</tr>

					<tr>
						<td><input id="weekday" class="text_input" type="text"></td>
						<td><input id="weekend" class="text_input" type="text"></td>
					</tr>

					<tr>
						<td id="update" onclick="updatePrices()"
										onmouseover="highlight('update')"
										onmouseout="deHighlight('update')"
										colspan="2">
							UPDATE
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>


</body>
</html>