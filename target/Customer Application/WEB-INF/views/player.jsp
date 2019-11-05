<%--
  Created by IntelliJ IDEA.
  User: eggpl
  Date: 03.11.2019
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Players</title>
    <h1>Players</h1>
    <c:choose>
        <c:when test="${not empty players}">
            <div style="display: flex; flex-direction: column">
            <c:forEach var="player" varStatus="loop" items="${players}">
                <p>${player.name}</p>
            </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                Add Plyer!
            </div>
        </c:otherwise>
    </c:choose>
    <form action="${pageContext.request.contextPath}/player" id="playerForm" method="post">
        <input type="hidden" id="action" name="action" value="">
        <input type="hidden" id="teamId" name="teamId" value="${idTeam}">
        <input type="text" name="playerName" value="">
        <a href="#" id="player"
           onclick="document.getElementById('action').value = 'addPlayer';
               document.getElementById('playerForm').submit();">
            Add Player!
        </a>
    </form>
</head>
<body>

</body>
</html>
