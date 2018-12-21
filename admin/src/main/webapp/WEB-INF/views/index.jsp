<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyBuli Admin</title>
</head>
<body>
    ID <form:input path="j_username"/><br>
    PASSWORD <form:password path="j_password"/><br>
    <form:button type="submit" value="Login"/>
</body>
</html>
