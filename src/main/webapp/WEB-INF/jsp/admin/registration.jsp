<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create New User</title>
    <link rel="stylesheet" type="text/css" href="/style/style.css">
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="sidenav">
    <a href="operationWithUser"> Operation with User</a>
    <br>
    <br>
    <a href="registration">Create new User</a>
</div>
<div class="main">
    <h2>
        Creating new user
    </h2>
    <div class="authorization-container">
        <form action="/admin/register" method="post" class="input-form">
            <input type="text" name="username" id="username" value="${username}" placeholder=" username" required>
            <input type="password" name="password" id="password" placeholder=" password" required minlength="3">
            <input type="password" name="reppassword" id="reppassword" placeholder="repeat password" required minlength="3">
            <select id="role" name="role">
                <option value="ADMIN">admin</option>
                <option value="WORKER">worker</option>
                <option value="BOSS">manager</option>
            </select>
            <c:if test="${failMessage != null}">
                <div class="failMessage"><c:out value="${failMessage} "/></div>
            </c:if>
            <c:if test="${message != null}">
                <div class="message"><c:out value="${message} "/></div>
            </c:if>
            <button type="submit">Registration</button>

        </form>
        <div class="or-action"><a href="/admin/adminHome">←Home</a></div>

    </div>
</div>






</body>
</html>