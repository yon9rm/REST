<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.multi.contactsapp.biz.domain.*"%>
<%@ page import="java.util.*"%>
<%
	ContactList contactList = (ContactList) request.getAttribute("data");
	List<Contact> contacts = contactList.getContacts();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	<%for (Contact c : contacts) {%>
		<div>
		<%= c.getNo() %> : <%= c.getName() %> 
		<%= c.getTel() %>, <%= c.getAddress() %> 
		</div>
	<%}%>
	</div>
</body>
</html>