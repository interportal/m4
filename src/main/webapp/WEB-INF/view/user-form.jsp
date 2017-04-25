<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
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

    <div>
        <h3>Display user information:</h3>
        <div>Name: ${user.name}</div>
        <div>Surname: ${user.surname}</div>
        <div>Email: ${user.email}</div>
        <div>Age: ${user.age}</div>
    </div>

    <h3>Edit User information:</h3>


    <spring:form action="${pageContext.request.contextPath}/users/save" method="post" modelAttribute="user">
        <spring:input path="id" type="hidden"/>
        <%--Examples--%>
        <div class="form-group">
            <label for="name">Name</label>
            <spring:input path="name" type="text" class="form-control" />
            <spring:errors path="name" cssClass="bg-danger" />
        </div>
        <div class="form-group">
            <label for="surname">Surname</label>
            <spring:input path="surname" type="text" class="form-control" />
            <spring:errors path="surname" cssClass="bg-danger" />
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <spring:input path="email" type="email" class="form-control" />
            <spring:errors path="email" cssClass="bg-danger" />
        </div>
        <div class="form-group">
            <label for="age">Age</label>
            <spring:input path="age" type="number" class="form-control" />
            <spring:errors path="age" cssClass="bg-danger" />
        </div>
        <input type="submit" class="btn btn-default" value="Submit">
    </spring:form>
</div>

<%-- Load JS libraries --%>
<script src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
</body>
</html>
