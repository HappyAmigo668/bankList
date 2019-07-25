<%@ page import="com.potapov.bankList.entity.User" %>
<%@ page import="com.potapov.bankList.service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BankList</title>
</head>
<body>

    <%
        UserService userService = new UserService();
        User richestUser = userService.getRichestUser();
    %>

    <div>
        <h1>Richest user: </h1>
        <span><%=richestUser.toString()%></span>
    </div>

</body>
</html>
