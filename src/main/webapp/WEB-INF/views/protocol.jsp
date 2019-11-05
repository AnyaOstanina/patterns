<%--
  Created by IntelliJ IDEA.
  User: eggpl
  Date: 05.11.2019
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:choose>
        <c:when test="${not empty protocol}">
            <p>ID:${protocol.id}</p>
            <p>Game: ${protocol.game}</p>
            <p>Date:${protocol.date}</p>

            <c:forEach var="event" varStatus="loop" items="${protocol.events}">
                <p>Event: ${event.name}</p>
                <p>Players:</p>
                <c:forEach var="player" varStatus="loop" items="${event.players}">
                    <p> ${player.name}</p>
                </c:forEach>
            </c:forEach>

            <h4>Goal statistic</h4>
            <p>Quantity of goals: ${protocolStatistic}</p>
            <p>Best player: ${protocolStatisticPlayer.name} - ${protocolStatisticPlayer.team} scored ${protocolStatisticPlayerGoal}</p>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
               Protocol not exist!
            </div>
        </c:otherwise>
    </c:choose>
</head>
<body>
</body>
</html>
