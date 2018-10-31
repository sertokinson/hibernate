<%@ page import="ru.sertok.jdbc.models.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 10.10.2018
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
<%List<User> users = (List) request.getAttribute("usersFromServer");%>
<table>
    <tr>
        <th>Name</th>
        <th>Birth Date</th>
    </tr>
    <%for (User user : users) {%>
    <tr>
        <td><%=user.getName()%></td>
        <td><%=user.getBirthDate()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
