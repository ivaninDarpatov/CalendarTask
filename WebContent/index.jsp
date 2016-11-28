<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>

<!-- CSS -->
<link rel="stylesheet" href="static/reset.css">
<link rel="stylesheet" href="static/style.css">
<link rel="stylesheet" href="static/home.css">

</head>
<body>
<div id="parent" style="background-color:yellow">
	<div id="empty_top"></div>
	<div id="menu" style="background-color:blue">
		<div id="user_page" class="menu_option" onclick="document.location.href = './user';" style="background-color:green"></div>
		<div id="admin_page" class="menu_option" onclick="document.location.href = './admin';" style="background-color:red"></div>
	</div>
</div>
</body>
</html>