<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/style/style.css">
</head>

<head>
    <title>Start page</title>
</head>
<body>
<%--<div class="video-background">--%>
<%--    <div class="video-foreground">--%>
<%--        <video src="/videos/1.mp4" autoplay muted loop></video>--%>
<%--    </div>--%>
<%--</div>--%>
<jsp:include page="header.jsp" />
<div class="main">
    <h2>Привіт
        <c:if test="${sessionScope.user != null}">
            <c:out value=", ${sessionScope.user.name}"/>!
        </c:if>
    </h2>
</div>


</body>
</html>