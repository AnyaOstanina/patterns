<%--
  Created by IntelliJ IDEA.
  User: eggpl
  Date: 03.11.2019
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Teams</title>
    <c:choose>
        <c:when test="${not empty teams}">
            <div style="display: flex; flex-direction: column">
                <c:forEach var="team" varStatus="loop" items="${teams}">
                    <a href="${pageContext.request.contextPath}/player?idTeam=${team.id}">${team.name}</a>
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
    <form action="${pageContext.request.contextPath}/team" id="teamForm" method="post">
        <input type="hidden" id="idTeam" name="idTeam" value="${team.id}">
        <input type="hidden" id="action" name="action" value="">
        <input type="text" name="teamName" value="">
        <a href="#" id="team"
           onclick="document.getElementById('action').value = 'addTeam';
               document.getElementById('teamForm').submit();">
            Add Team!
        </a>
    </form>
</head>
<body>

</body>
</html>
