<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="g" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Games">
    <style>
        .container {
            max-width: 800px;
            margin: 100px auto 50px auto;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h1 {
            margin-bottom: 20px;
            color: #333;
        }

        .btn {
            padding: 8px 16px;
            border-radius: 5px;
            text-decoration: none;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-danger {
            background-color: #dc3545;
            border: none;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #dee2e6;
            text-align: center;
        }

        td {
            background-color: #fff;
            color: #333;
        }

        tbody tr:nth-child(even) {
            background-color: #f8f9fa;
        }
    </style>

    <div class="container text-center">
        <h1>Games</h1>
        <div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AddGame">Add Game</a>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th style="background-color: #007bff; color: #fff; font-weight: bold;">#</th>
                <th style="background-color: #007bff; color: #fff; font-weight: bold;">Name</th>
                <th style="background-color: #007bff; color: #fff; font-weight: bold;">Actions</th>
            </tr>
            </thead>
            <tbody>
            <g:forEach var="game" items="${games}" varStatus="loop">
                <tr>
                    <td>${game.gameId}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">
                                ${game.gameName}
                        </a>
                    </td>

                    <td>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/DeleteGame?gameId=${game.gameId}">Delete Game</a>
                    </td>
                </tr>
            </g:forEach>
            </tbody>
        </table>
    </div>
</t:template>
