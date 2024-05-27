<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Community">
    <div class="container text-center" style="margin-top: 100px">
         <div>
            <p>${post.user.username}</p>
            <p><fmt:formatDate value="${post.postingDate}" pattern="yyyy-MM-dd HH:mm" /></p>
            <p>${post.description}</p>
            <c:forEach var="picture" items="${post.postPictures}">
                <c:if test="${not empty picture}">
                    <img src="data:image/${picture.imageFormat};base64,${picture.base64ImageData}" alt="Post Picture" width="700" height="415" />
                </c:if>
            </c:forEach>
            <div class="reaction-buttons">
                <c:set var="userReactedLike" value="false"/>
                <c:set var="userReactedFun" value="false"/>
                <c:set var="userReactedHelpful" value="false"/>
                <c:set var="userReactedDislike" value="false"/>
                <c:forEach var="reaction" items="${post.reactions}">
                    <c:if test="${reaction.user.username eq sessionScope.user.username}">
                        <c:choose>
                            <c:when test="${reaction.reactionType eq 'LIKE'}">
                                <c:set var="userReactedLike" value="true"/>
                            </c:when>
                            <c:when test="${reaction.reactionType eq 'FUN'}">
                                <c:set var="userReactedFun" value="true"/>
                            </c:when>
                            <c:when test="${reaction.reactionType eq 'HELPFUL'}">
                                <c:set var="userReactedHelpful" value="true"/>
                            </c:when>
                            <c:when test="${reaction.reactionType eq 'DISLIKE'}">
                                <c:set var="userReactedDislike" value="true"/>
                            </c:when>
                        </c:choose>
                    </c:if>
                </c:forEach>
                <c:choose>
                    <c:when test="${userReactedLike}">
                        <button class="btn-link" disabled>&#128077;</button>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=like" class="btn-link">&#128077;</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${userReactedFun}">
                        <button class="btn-link" disabled>&#128514;</button>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=fun" class="btn-link">&#128514;</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${userReactedHelpful}">
                        <button class="btn-link" disabled style="color: green;">Helpful</button>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=helpful" class="btn-link" style="color: green;">Helpful</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${userReactedDislike}">
                        <button class="btn-link" disabled>&#128078;</button>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=dislike" class="btn-link">&#128078;</a>
                    </c:otherwise>
                </c:choose>
                <c:if test="${post.user.username eq user.username}">
                    <form action="${pageContext.request.contextPath}/DeletePost" method="post">
                        <input type="hidden" name="postId" value="${post.postId}">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</t:template>
