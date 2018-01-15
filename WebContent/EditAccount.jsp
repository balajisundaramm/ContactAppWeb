<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Account</title>
</head>
<body>
<h3>Edit your Account details</h3><br/>
<form action="EditAccountInt.jsp" method="post">
Name:<input type="text" name="name"><br/>
Email*:<input type="text" name="email" value="${sessionScope.user}" readonly="readonly"><br/>
Password:<input type="password" name="pass"><br/>
Retype Password:<input type="password" name="rpass"><br/>
<input type="submit" value="complete"/><br/>
</form>
<b>${errorMsg}</b>
</body>
</html>