<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    //allow access only if session exists
    long userId = (Long) session.getAttribute("userId");
    String sessionID = session.getId();

%>
<h3>Login successful. Your Session ID=<%=sessionID %>
</h3>
<br>
UserId=<%=userId%>
<br>
<a href="CheckoutPage.jsp">Checkout Page</a>
<a href="MyTest.jsp">MyTest</a>
<a href="CreateQuestions.jsp">New Questions</a>



<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>