<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.contact.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users</title>
</head>
<body>
<%
		List<RegBean> list = (List<RegBean>)request.getAttribute("listUsers");
		for(RegBean bean : list)
		{
			out.println("Name : "+bean.getName()+" Email : "+bean.getEmail()+" Pass : "+bean.getPass()+" <br/>");
			
		}
	
%>
	<jstl:forEach var="x" items="${listUsers}">	
			Name:${x.uname} Email:${x.email} Pass:${x.pass} <br/>
	</jstl:forEach>
</body>
</html>