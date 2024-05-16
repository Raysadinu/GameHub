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
    <h1>Library, ${username}</h1>

    <c:if test="${empty games}">
        <p>You don't have any games in your library.</p>
    </c:if>

    <c:if test="${not empty games}">
        <l:forEach var="game" items="${games}">
            <div class="row">
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-body">
                            <p class="card-title">${game.gameName}</p>
                        </div>
                    </div>
                </div>
            </div>
        </l:forEach>
    </c:if>
</t:template>
