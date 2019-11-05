<%--
  Created by IntelliJ IDEA.
  User: eggpl
  Date: 03.11.2019
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:choose>
        <c:when test="${not empty teams}">
            <div style="display: flex; flex-direction: column">
                <c:forEach var="protocol" varStatus="loop" items="${protocols}">
                    <a href="${pageContext.request.contextPath}/player?idProtocol=${protocol.id}">${protocol.name}</a>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                Add Team!
            </div>
        </c:otherwise>
    </c:choose>
</head>
<body>
</body>
</html>
