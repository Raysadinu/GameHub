<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 5/12/2024
  Time: 6:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template pageTitle="Game Profile">
    <div class="container text-center">
        <div>
            <h3>${game.gameName}</h3>
        </div>
        <p> Publisher: ${game.publisher} </p>
        <p> Developer: ${game.developer} </p>
        <p> Release Date: ${game.releaseDate} </p>
        <p> Description: ${game.description} </p>
        <div><a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditGame?gameId=${game.gameId}">Add Details</a></div>
    </div>
</t:template>
