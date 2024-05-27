<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Users">
    <div class="container text-center">
        <h1>Users</h1>
        <form id="searchForm" onsubmit="return false" method="get" action="${pageContext.request.contextPath}/SearchUsers">
            <label for="searchBar"></label>
            <input type="text" name="keyword" id="searchBar" class="form-control" placeholder="Search users..." value="${param.keyword}">
        </form>
        <div id="searchSuggestions"></div>
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Username</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>
                        <c:choose>
                            <c:when test="${sessionScope.user.username == user.username}">
                                <a href="${pageContext.request.contextPath}/Profile">${user.username}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/OtherProfile?username=${user.username}">${user.username}</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${user.email}</td>
                    <td>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/DeleteUser?username=${user.username}">Delete User</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:template>
<script>
    document.getElementById('searchBar').addEventListener('input', function() {
        var keyword = this.value.trim();
        if (keyword !== '') {
            fetch('${pageContext.request.contextPath}/SearchUsers?keyword=' + keyword)
                .then(response => response.json())
                .then(data => {
                    var suggestionsDiv = document.getElementById('searchSuggestions');
                    suggestionsDiv.innerHTML = ''; // Clear previous suggestions
                    data.forEach(username => {
                        var suggestionElement = document.createElement('div');
                        suggestionElement.textContent = username;
                        suggestionElement.addEventListener('click', function() {

                            window.location.href = '${pageContext.request.contextPath}/OtherProfile?username=' + username;
                        });
                        suggestionsDiv.appendChild(suggestionElement);
                    });
                })
                .catch(error => {
                    console.error('Error fetching search suggestions:', error);
                });
        } else {
            document.getElementById('searchSuggestions').innerHTML = '';
        }
    });
</script>
