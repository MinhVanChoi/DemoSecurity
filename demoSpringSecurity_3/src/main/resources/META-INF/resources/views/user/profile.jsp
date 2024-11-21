<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h1>Welcome, ${username}!</h1>
<form action="${pageContext.request.contextPath}/profile">
    <p><strong>Email:</strong>&nbsp ${email}</p> 
    <p><strong>Full Name:</strong>&nbsp ${fullname}</p>
    <p><strong>Phone:</strong>&nbsp ${phone}</p>
	<p><strong>Password:</strong>&nbsp ${password}</p>
	
    <a href="${pageContext.request.contextPath}/multiPartServlet" class="UpdateProfile">UpdateProfile</a>
    <br>
    <img src="${pageContext.request.contextPath}/uploads/${image}" alt="Profile Image" style="max-width: 200px; max-height: 200px;" />
    <br>
    <br>
    <br>
    
</form>


</body>
</html>