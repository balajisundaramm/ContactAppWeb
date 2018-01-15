<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.contact.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ListContact</title>
</head>
<body>
<%
		List<ContactBean> list = (List<ContactBean>)request.getAttribute("listContacts");
		for(ContactBean bean : list)
		{
			out.println("Name : "+bean.getName()+" Emails : "+bean.getEmail()+
					" Gender : "+bean.getGender()+" Date of birth : "+bean.getDob()+
					"Phone numbers : "+bean.getPhno()+" Tags : "+bean.getTag()+" <br/>");
			
		}
	
%>
</body>
</html>