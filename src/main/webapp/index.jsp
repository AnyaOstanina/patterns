<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Sign Up</title>
</head>
<body>
<h1>Customer Sign Up</h1>
    <form action="${pageContext.request.contextPath}/processcustomer" method="post">
        <label for="login">Login : </label>
        <input type="text" name="login" id="login" value="${login}">
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" value="${password}">
        <input type="submit" >
    </form>


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
</body>
</html>