
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
<script type="text/javascript" src="js/home.js"></script>

</head>
<body onload="initialize()">
	<input id="reserved_hidden" type="hidden" value='${reservedDates}' />
	<input id="prices_hidden" type="hidden" value='${prices}' />

	<div id="parent">
		<div id="head" class="row">
			<div id="menu" onclick="document.location.href = './';"
							onmouseover="highlight('menu')"
							onmouseout="deHighlight('menu')">BACK</div>
		</div>
		<div id="cal_area" class="row">
			<div id="cal_container" class="cal_row"
				style="background-color: green">
				<div id="back" class="cal_nav"
						onclick="previousMonth()"
						onmouseover="this.style.background='#D7D7D7'"
						onmouseout="this.style.background=''">
					<div class="empty_top"></div>
					<div id="left_arrow" class="nav_img">
						<img class="arrow" src="static/img/left_arrow.png">
					</div>
				</div>

				<div id="calendar">
					<div id="month" class="cal_title"></div>

					<div id="year" class="cal_title"></div>

					<div id="days"></div>
				</div>

				<div id="forward" class="cal_nav"
						onclick="nextMonth()"
						onmouseover="this.style.background='#D7D7D7'"
						onmouseout="this.style.background=''">
					<div id="right_arrow" class="empty_top"></div>
					<div class="nav_img">
						<img class="arrow" src="static/img/right_arrow.png">
					</div>
				</div>
			</div>
		</div>

		<div id="res_info" class="row">
			<div id="prices" class="res_info">
				<div id="prices_container" class="stats_container">
					<table>
						<tr>
							<th align="center" colspan="2">prices</th>
						</tr>
						<tr>
							<td class="stats_meta"><label>weekday</label></td>
							<td><input id="weekday" class="res_stats" type="text" value="1" disabled>
							</td>
						</tr>
						<tr>
							<td class="stats_meta"><label>weekend</label></td>
							<td><input id="weekend" class="res_stats" type="text" value="2" disabled>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<div id="reservation" class="res_info">
				<div id="res_container" class="stats_container">
					<table>
						<tr>
							<th align="center" colspan="3">your reservation</th>
						</tr>
						<tr>
							<td class="stats_meta"><label>start</label></td>
							<td><input id="start" class="res_stats" type="text" value="0" disabled>
							</td>
							<td id="reserve" onclick="reserveSelected()"
											onmouseover="highlight('reserve')"
											onmouseout="deHighlight('reserve')"
											rowspan="3">
								RESERVE
							</td>
						</tr>
						<tr>
							<td class="stats_meta"><label>end</label></td>
							<td><input id="end" class="res_stats" type="text" value="0" disabled>
							</td>
						</tr>
						<tr>
							<td class="stats_meta"><label>cost</label></td>
							<td><input id="cost" class="res_stats" type="text" value="0" disabled>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>