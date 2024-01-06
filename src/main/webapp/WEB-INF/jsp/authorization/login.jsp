<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/style/style.css">
    <title>Login</title>
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="authorization-container">
    <form action="/login" method="post" class="input-form">
        <input type="text" name="username" value="${username}" placeholder="Username" required>
        <input type="password" name="password" placeholder=" Password" required>
        <button type="submit">Ввійти</button>
        <c:if test="${failMessage != null}">
            <div class="failMessage"><c:out value="${failMessage} "/></div>
        </c:if>
    </form>

</div>
</body>
</html>
