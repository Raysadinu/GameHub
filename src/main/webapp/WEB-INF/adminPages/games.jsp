<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="g" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Games">
    <div class="container text-center">
        <h1>Games</h1>
        <div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AddGame">Add Game</a>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <g:forEach var="game" items="${games}" varStatus="loop">
                <tr>
                    <td>${game.gameId}</td>
                    <td><a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">${game.gameName}</a></td>
                    <td>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/DeleteGame?gameId=${game.gameId}">Delete Game</a>
                    </td>
                </tr>
            </g:forEach>
            </tbody>
        </table>
    </div>
</t:template>



