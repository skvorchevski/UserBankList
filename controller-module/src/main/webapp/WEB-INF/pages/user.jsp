<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<div>
    <form name="user_info" method="post" action="${pageContext.request.contextPath}/user">
        <label>
            <input name="id" type="text" placeholder="Enter id"/>
        </label>
        <input type="submit">
    </form>
    <p> User: <c:out value="user"/></p>
    <form name="account_info" method="get" action="${pageContext.request.contextPath}/user">
        <label>
            <input type="submit"/>
        </label>
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
        <c:forEach var="accounts" accounts="${accounts}">
        <td><b>${accounts.user.userId}</b></td>
        <td><b>${accounts.user.name}</b></td>
        <td><b>${accounts.user.sureName}</b></td>
        <td><b>${accounts.accountId}</b></td>
        <td><b>${accounts.account}</b></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<p> RichUser: <c:out value="rich_user"></c:out></p>
<p> SumAccount: <c:out value="sum"></c:out></p>
</body>
</html>
