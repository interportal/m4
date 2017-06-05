<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div>
    <ul>
        <li>Menu1</li>
        <li>Menu2</li>
        <li>Menu3</li>
        <li>Menu4</li>
    </ul>

    <form:form action="/logout" method="post">
        <input type="submit" value="Logout">
    </form:form>

    <form action="/logout" method="post">
        <input type="submit" value="Logout">
        <sec:csrfInput/>
    </form>
</div>