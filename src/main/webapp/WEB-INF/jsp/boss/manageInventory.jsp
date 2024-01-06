<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Manage</title>
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
    <h2>Manage your Inventory</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Current Stock</th>
            <th>Minimum Stock</th>
            <th>Action</th>
        </tr>
        <c:forEach var="material" items="${materials}">
            <tr>
                <td>${material.id}</td>
                <td>${material.name}</td>
                <td>${material.amount}</td>
                <form action="/boss/updateMinimumStock" method="post">
                    <td><input type="number" name="minimumStock" value="${material.minimumStock}"/></td>
                    <td>
                        <input type="hidden" name="materialId" value="${material.id}"/>
                        <button type="submit">Update</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>