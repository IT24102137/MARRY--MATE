<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<%
  int[] x = {1, 2, 3, 4, 5};
  for (int i : x) {
    out.println(i);
  }
%>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>