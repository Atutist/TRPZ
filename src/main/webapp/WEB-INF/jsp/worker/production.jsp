<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Production</title>
    <link rel="stylesheet" type="text/css" href="/style/style.css">
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="sidenav">
    <a href="production"> Production</a>
    <br><br>
    <a href="issueProduct">Issue of Product</a>
    <br><br>
    <a href="receiveMaterial">Receiving</a>
</div>
<div class="main">
    <h2>Production</h2>
    <form action="/worker/useRecipe" method="post" class="input-form">
        <div>
            <label for="productId">Виберіть продукт:</label>
            <select id="productId" name="productId" required>
                <c:forEach items="${products}" var="product">
                    <option value="${product.id}">${product.name}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label for="amount">Кількість:</label>
            <input type="number" id="amount" name="amount" required>
        </div>
        <div>
            <button type="submit">Створити продукт</button>
        </div>
        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>
    </form>
</div>
</body>
</html>