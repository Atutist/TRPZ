<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create Recipe</title>
    <link rel="stylesheet" type="text/css" href="/style/style.css">
    <script>
        // Цей скрипт зберігає всі доступні матеріали і дозволяє додавати їх у форму
        let materials = [
            <c:forEach items="${materials}" var="material" varStatus="status">
            {
                id: '<c:out value="${material.id}"/>',
                name: '<c:out value="${material.name}"/>'
            }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];

        function addMaterial() {
            let container = document.getElementById('materials');

            let materialWrapper = document.createElement('div');

            let materialSelect = document.createElement('select');
            materialSelect.name = 'materialIds'; // Важливо, щоб це ім'я відповідало імені параметра в контролері

            materials.forEach(function(material) {
                let option = document.createElement('option');
                option.value = material.id;
                option.text = material.name;
                materialSelect.appendChild(option);
            });

            let amountInput = document.createElement('input');
            amountInput.type = 'number';
            amountInput.name = 'materialAmounts'; // Важливо, щоб це ім'я відповідало імені параметра в контролері
            amountInput.min = '0';
            amountInput.step = '0.01';
            amountInput.required = true;

            materialWrapper.appendChild(materialSelect);
            materialWrapper.appendChild(amountInput);
            container.appendChild(materialWrapper);
        }

        document.addEventListener('DOMContentLoaded', function() {
            addMaterial();
        });

    </script>
</head>
<body>
<!-- Включення загального хедеру -->
<jsp:include page="../header.jsp" />
<div class="sidenav">
    <a href="reports">Report</a>
    <br><br>
    <a href="manageInventory">Manage Inventory</a>
    <br><br>
    <a href="createNewRecipe">Create Recipe</a>
</div>
<div class="main">
    <form action="/boss/createNewRecipe" method="post" class="input-form">
        <h2>Створення нового рецепту</h2>
        <label for="name">Назва рецепту:</label>
        <input type="text" id="name" name="name" required>
        <div id="materials">
            <label>Матеріали:</label>
            <!-- Тут будуть динамічно додаватися матеріали та кількість -->
        </div>
        <button type="button" onclick="addMaterial()">Додати матеріал</button>
        <button type="submit">Створити рецепт</button>
        <!-- Відображення повідомлень про успіх або помилку -->
        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>
        <c:if test="${failMessage != null}">
            <div class="failMessage"><c:out value="${failMessage}"/></div>
        </c:if>
    </form>
</div>
</body>
</html>
