<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 5/16/2024
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="l" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template pageTitle="Cart">
    <style>
        /* Add your custom styles here */
        .game-container {
            margin-top: 50px;
        }

        .game-card {
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 20px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .game-picture {
            width: 100%;
            height: auto;
        }

        .game-title {
            font-size: 18px;
            font-weight: bold;
            margin-top: 10px;
            margin-bottom: 0;
            text-align: center;
        }

        .install-btn {
            display: block;
            margin: 10px auto;
            width: 80%;
        }
        .text-center{
            margin-top: 100px;
        }
    </style>

    <div class="text-center">
        <h1>Library, ${username}</h1>

        <c:if test="${empty games}">
            <p>You don't have any games in your library.</p>
        </c:if>
    </div>

    <c:if test="${not empty games}">
        <div class="row game-container">
            <c:forEach var="game" items="${games}">
                <div class="col-sm-6 col-md-4 col-lg-3 col-xl-2">
                    <div class="game-card">
                        <div class="card-body">
                            <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">
                                <img src="${pageContext.request.contextPath}/GamePhotos?gameId=${game.gameId}" alt="Game Profile Picture" class="game-picture">
                            </a>
                            <p class="game-title">${game.gameName}</p>
                            <button type="button" class="btn btn-primary install-btn" disabled>Install</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</t:template>
