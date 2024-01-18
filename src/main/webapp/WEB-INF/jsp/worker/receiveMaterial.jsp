<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Receive</title>
    <link rel="stylesheet" type="text/css" href="/style/style.css">
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="sidenav">
    <a href="production"> Production</a>
    <br>
    <br>
    <a href="issueProduct">Issue of Product</a>
    <br>
    <br>
    <a href="receiveMaterial">Receiving</a>
</div>
<div class="main">

    <!-- Форма прийняття матеріалів -->

    <form action="/worker/receiveMaterial" method="post" class="input-form">
        <h2>Прийняття матеріалів</h2>
        <div class="form-group">
            <label for="materialId">ID матеріалу:</label>
            <input type="number" id="materialId" name="materialId" required>
        </div>
        <div class="form-group">
            <label for="materialAmount">Кількість:</label>
            <input type="number" id="materialAmount" name="amount" required>
        </div>
        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>
        <button type="submit">Прийняти матеріал</button>
    </form>

</div>
</body>
</html>
