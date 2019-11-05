<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Data</title>
</head>
<body>
    <h1>Protocols!</h1>
    <c:choose>
        <c:when test="${not empty protocols}">
            <div style="display: flex; flex-direction: column">
                <c:forEach var="protocol" varStatus="loop" items="${protocols}">
                    <a href="${pageContext.request.contextPath}/protocol?idProtocol=${protocol.id}">${protocol.date}</a>
                </c:forEach>
            </div>

        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                No protocols
            </div>
        </c:otherwise>
    </c:choose>
    <br/>
    <br/>

    <h1>Choose game!</h1>
    <c:choose>
        <c:when test="${not empty gameList}">
            <c:forEach var="game" items="${gameList}">
                <form action="${pageContext.request.contextPath}/game" method="post">
                    <input type="hidden" name="idCustomer" value=${idCustomer} >
                    <input type="hidden" name="gameName" id="gameName" value="${game.name}">
                    <Input type="submit" value ="${game.name}">
                </form>
            </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                No people found matching your search criteria
            </div>
        </c:otherwise>
    </c:choose>

    <h1>Create Team!</h1>
    <form action ="${pageContext.request.contextPath}/team" method="post">
        <input type="hidden" name="idCustomer" value=${idCustomer} >
        <button type="submit" class="btn btn-primary  btn-md">New team</button>
    </form>
</body>
</html>