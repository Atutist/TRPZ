<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Issue</title>
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
    <!-- Форма видачі продукції -->

    <form action="/worker/issueProduct" method="post" class="input-form">
        <h2>Видача продукції</h2>
        <div class="form-group">
            <label for="productId">ID продукту:</label>
            <input type="number" id="productId" name="productId" required>
        </div>
        <div class="form-group">
            <label for="productAmount">Кількість:</label>
            <input type="number" id="productAmount" name="amount" required>
        </div>
        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>
        <button type="submit">Видати продукцію</button>
    </form>

</div>
</body>
</html>
