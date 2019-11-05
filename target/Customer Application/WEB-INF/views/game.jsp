<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Sign Up</title>
</head>
<body>
<h1>You choose ${protocol.game}</h1>


<h3>Please, enter date and events</h3>
<h3>Date</h3>
<form action="${pageContext.request.contextPath}/game" id="eventForm" method="post">
    <input type="hidden" id="idProtocol" name="idProtocol" value="${protocol.id}">
    <input type="hidden" id="idCustomer" name="idCustomer" value="${idCustomer}">
    <input type="hidden" id="action" name="action" value="">
    <label for="date">Date: </label>
    <c:choose>
        <c:when test="${protocol.date != null}">
            <p>${protocol.date}</p>
        </c:when>
        <c:otherwise>
            <input type="text" name="date" id="date" value="${protocol.date}">
        </c:otherwise>
    </c:choose>
    <h3>Events</h3>
<c:choose>
    <c:when test="${not empty protocol.events}">
        <c:forEach var="event" varStatus="loop" items="${protocol.events}">
            <div style="margin-bottom: 20px">
                <p style="margin-bottom: 5px">Event ${loop.count}</p>
                <div style="display: flex">
                    <p style="margin-right: 10px"> Event: ${event.name}</p>
                    <p>Time: ${event.time}</p>
                </div>
                <p>Players:</p>
                <div style="display: flex">
                    <br/>
                    <c:forEach var="player" varStatus="loop" items="${event.players}">
                        <div style="display: flex; margin-bottom: 10px;">
                            <p style="margin-bottom: 5px">Player ${loop.count}</p>
                            <p>${player.name}- ${player.team}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>

    </c:when>
    <c:otherwise>
        <br>
        <div class="alert alert-info">
            Add events!
        </div>
    </c:otherwise>
</c:choose>
    <div style="display: flex;flex-direction: column; align-items: flex-start">
        <label for="date">Name: </label>
        <input type="text" name="name" id="name" value="${name}">
        <label for="date">Time: </label>
        <br/>
        <input type="text" name="time" id="time" value="${time}">
        <label for="date">Player: </label>
        <c:choose>
            <c:when test="${not empty players}">
                <select multiple="multiple" size="2" name="players[]" id="players">
                    <c:forEach var="player" varStatus="loop" items="${players}">
                        <option value="${player.id}" name="${player.name}">${player.name}-${player.team}</option>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </div>
    <div style="display: flex; flex-direction: column" >
        <a href="#" id="event"
           onclick="document.getElementById('action').value = 'addEvent';
               document.getElementById('eventForm').submit();">
            Add Event!
        </a>

        <a href="#" id="protocol"
           onclick="document.getElementById('action').value = 'addProtocol';
               document.getElementById('eventForm').submit();">
            Add Protocol!
        </a>
    </div>
</form>
</body>
</html>