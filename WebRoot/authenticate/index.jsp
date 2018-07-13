<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	我现在很安全, 只有ROLE_USER,ROLE_SUPERVISOR可以访问!<br>
	<a href="<%=request.getContextPath() %>/secure.action">进入安全领域...</a><br/>
	
	<a href="<%=request.getContextPath() %>/add.action">add</a><br/>
	<a href="<%=request.getContextPath() %>/get.action">get</a><br/>
	<a href="<%=request.getContextPath() %>/remove.action">remove</a><br/>
	<a href="<%=request.getContextPath() %>/update.action">update</a><br/>
	<a href="<%=request.getContextPath() %>/getAll.action">getAll</a><br/>
	<br/>
	<a href="<%=request.getContextPath() %>/j_acegi_logout">Logout</a>
</body>
</html>