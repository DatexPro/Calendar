<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a style="color: #761c19" onclick="document.forms['logoutForm'].submit()">Logout</a>
            | <a style="color: #761c19" href="${contextPath}/calendar">Show calendar </a>
            <c:if test="${pageContext.request.isUserInRole('ROLE_admin')}">
               | <a style="color: #761c19" href="${contextPath}/admin">Go to Admin Panel</a>
            </c:if>
        </h2>
        <div class="container">
            <form action="${contextPath}/welcome" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <label for="firstNumber"> First number</label>
                <input class="form-control"  type="text" id="firstNumber" name="firstNumber"/>
                <label for="secondNumber"> First number</label>
                <input class="form-control"  type="text" id="secondNumber" name="secondNumber"/>
                <label><h3>Result: ${result}</h3></label>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Calculate</button>
            </form>
        </div>
    </c:if>

</div>
</body>
</html>