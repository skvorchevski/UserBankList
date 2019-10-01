<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<div>
    <form name="user_info" method="post" action="${pageContext.request.contextPath}/user">
        <fieldset>
            <label> ID: </label>
            <input name="id" type="text" placeholder="Enter id"/>
            <input type="submit">
        </fieldset>
    </form>
    <p> User: <c:out value="${user}"/></p>

    <form name="account_info" method="get" action="${pageContext.request.contextPath}/user">
        <fieldset>
            <label>Show all Accounts</label>
            <button type="submit">accounts</button>
        </fieldset>
    </form>
</div>
<div>
    <table class="table" cellspacing="2" cellpadding="5" width="600" border="2">
        <thead>
        <tr>
            <th scope="col"><i>ID</i></th>
            <th scope="col"><i>Name</i></th>
            <th scope="col"><i>SureName</i></th>
            <th scope="col"><i>AccountID</i></th>
            <th scope="col"><i>Account</i></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td><b>${account.userDTO.userId}</b></td>
                <td><b>${account.userDTO.name}</b></td>
                <td><b>${account.userDTO.sureName}</b></td>
                <td><b>${account.accountId}</b></td>
                <td><b>${account.account}</b></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<p><b>RichUser: <c:out value="${rich_user}"/></b></p>
<p><b>SumAccount: <c:out value="${sum}"/></b></p>
</body>
</html>
