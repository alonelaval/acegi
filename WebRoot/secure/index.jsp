<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	我现在很安全, 只有角色ROLE_SUPERVISOR可以访问!<br>
	<a href="<%=request.getContextPath() %>/authenticate/index.jsp">进入authenticate页面</a>
</body>
</html>