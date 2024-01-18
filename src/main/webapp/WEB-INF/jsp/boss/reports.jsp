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
    <a href="reports">Report</a>
    <br><br>
    <a href="manageInventory">Manage Inventory</a>
    <br><br>
    <a href="createNewRecipe">Create Recipe</a>
</div>
<div class="main">
    <h2>Your Reports</h2>

    <!-- Кнопки для вибору звіту -->
    <form action="/boss/reports" method="get">
        <input type="hidden" name="reportType" value="transaction">
        <button type="submit">Transaction Report</button>
    </form>
    <form action="/boss/reports" method="get">
        <input type="hidden" name="reportType" value="material">
        <button type="submit">Material Report</button>
    </form>
    <form action="/boss/reports" method="get">
        <input type="hidden" name="reportType" value="product">
        <button type="submit">Product Report</button>
    </form>

    <c:if test="${empty reportType}">
        ${reportType = 'transaction'} <!-- Встановлення значення за замовчуванням -->
    </c:if>
    <!-- Таблиця для відображення звіту -->
    <c:choose>
        <c:when test="${reportType == 'transaction'}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Material/Product</th>
                    <th>Amount</th>
                    <th>Date/Time</th>
                    <th>User</th>
                </tr>
                <c:forEach var="transaction" items="${report}">
                    <tr>
                        <td>${transaction.id}</td>
                        <td>${transaction.type}</td>
                        <td>${transaction.material != null ? transaction.material.name : transaction.product.name}</td>                        <td>${transaction.amount}</td>
                        <td>${transaction.dateTime}</td>
                        <td>${transaction.username}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:when test="${reportType == 'material'}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Amount</th>
                    <th>Minimum Stock</th>
                </tr>
                <c:forEach var="material" items="${report}">
                    <tr>
                        <td>${material.id}</td>
                        <td>${material.name}</td>
                        <td>${material.amount}</td>
                        <td>${material.minimumStock}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:when test="${reportType == 'product'}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Amount</th>
                </tr>
                <c:forEach var="product" items="${report}">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.amount}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</div>
</body>
</html>
