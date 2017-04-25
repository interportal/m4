<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Private Page</title>
    <%-- Bootstrap css --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>

    <h4>Hello, ${authUser.name}</h4>

    <h3>Numbers</h3>
    <div>
        <%-- Print numbers from 0 to 100 in step of 5 --%>
        <c:forEach var="i" begin="0" end="100" step="5">
            <span>${i}</span>
        </c:forEach>
    </div>

    <h3>Users List:</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <td>Name</td>
            <td>Surname</td>
            <td>Age</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.age < 18}">
                            <span class="text-danger">${user.age}</span>
                        </c:when>
                        <c:when test="${user.age >= 18}">
                            ${user.age}
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${minorsCount > 4}">
        <h3 class="bg-danger">Prea multi minori!!!</h3>
    </c:if>

</div>

<%-- Load JS libraries --%>
<script src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
</body>
</html>
