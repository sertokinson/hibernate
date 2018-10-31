<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 12.10.2018
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=Cp1251" %>
<html>
<head>
    <title>Home</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="home">
    <form method="post">
        <button class="button" id="registration" value="registration" name="registration">Зарегестрироваться</button>
        <button class="button" id="signIn" value="signIn" name="signIn">Войти</button>
    </form>
</div>
</body>
</html>
