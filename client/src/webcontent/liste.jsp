<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, pack.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
Collection<SysInfo> listeInfo = (Collection<SysInfo>)request.getAttribute("listepersonnes");
for (SysInfo s : listeInfo) {
	out.println(s.getHostname() + " " + s.getHostip()+ s.getCpuUsage() + "<br>");
}}
%>

</body>
</html>
