
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Panel</title>

<!-- CSS -->
<link rel="stylesheet" href="static/style.css">
<link rel="stylesheet" href="static/user.css">

<!-- JS -->
<script type="text/javascript" src="js/user.js"></script>

</head>
<body onload="initialize()"> 
<input id="reserved_hidden" type="hidden" value='${reservedDates}'/>
<input id="prices_hidden" type="hidden" value='${prices}'/>

<div id="quick_nav" class="row" style="background-color:red">
	<div id="navigation" style="background-color: orange">
		
	</div>
</div>

<div id="cal_area" class="row" style="background-color:black">
	<div id="cal_container" class="cal_row" style="background-color:green">
		<div class="cal_nav" onclick="previousMonth()" style="background-color:yellow"></div>

		<div id="calendar" style="background-color:green">
			<div id="month" class="cal_title" style="background-color:blue"></div>
			
			<div id="year" class="cal_title" style="background-color:purple"></div>
			
			<div id="days" style="background-color:orange"></div>
		</div>

		<div class="cal_nav" onclick="nextMonth()" style="background-color:yellow"></div>
	</div>
</div>

<div id="res_info" class="row" style="background-color:blue">
	<div id="prices" class="res_info" style="background-color: grey">
		<div id="prices_container" style="background-color:blue">
			<table>
				<tr>
					<td>
						weekday:
					</td>
					<td>
						<input id="weekday" type="text" value="1" disabled>
					</td>
				</tr>
				<tr>
					<td>
						weekend:
					</td>
					<td>
						<input id="weekend" type="text" value="2" disabled>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div id="reservation" class="res_info" style="background-color: purple">
		<div id="res_container" style="background-color:red">
			<table>
				<tr>
					<td>
						<label>start:</label>
					</td>
					<td>
						<input id="start" type="text" value="0" disabled>
					</td>
					<td rowspan="3">
						<button onclick = "reserveSelected()">RESERVE</button>
					</td>
				</tr>
				<tr>
					<td>
						<label>end:</label>
					</td>
					<td>
						<input id="end" type="text" value="0" disabled>
					</td>
				</tr>
				<tr>
					<td>
						<label>cost:</label>
					</td>
					<td>
						<input id="cost" type="text" value="0" disabled>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>

</body>
</html>