<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reports</title>
    <link rel="stylesheet" type="text/css" href="/style/style.css">
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="sidenav">
    <a href="reports"> Report</a>
    <br>
    <br>
    <a href="manageInventory">Manage inventory</a>
    <br>
    <br>
    <a href="createNewRecipe">Create Recipe</a>
</div>
<div class="main">
    <h2>Your Reports</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Material/Product</th>
            <th>Amount</th>
            <th>Date/Time</th>
            <th>User</th>
        </tr>
        <c:forEach var="transaction" items="${transactions}">
            <tr>
                <td>${transaction.id}</td>
                <td>${transaction.type}</td>
                <td>${transaction.material != null ? transaction.material.name : transaction.product.name}</td>
                <td>${transaction.amount}</td>
                <td>${transaction.dateTime}</td>
                <td>${transaction.username}</td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>