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

<!-- JS -->
<script type="text/javascript" src="js/home.js"></script>

</head>
<body>
<div id="parent">
	<div id="empty_top"></div>
	<div id="menu">
		<div id="user_page" class="menu_option" onclick="document.location.href = './user';"
				onmouseover="highlight('user_page')"
				onmouseout="deHighlight('user_page')">
			<h1 class="title">USER</h1>		
		</div>
		<div id="admin_page" class="menu_option" onclick="document.location.href = './admin';"
				onmouseover="highlight('admin_page')"
				onmouseout="deHighlight('admin_page')">
			<h1 class="title">ADMIN</h1>		
		</div>
	</div>
</div>
</body>
</html>