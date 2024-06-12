<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template pageTitle="Friends">
    <style>
        .search-suggestion {
            cursor: pointer;
            color: #333;
            transition: color 0.3s;
        }
        .search-suggestion:hover {
            color: #580ee3;
            font-weight: bold;
        }
        h1 {
            text-align: center;
            margin-top: 100px;
        }
    </style>

    <h1>Friends</h1>
    <div class="container text-center">
        <form id="searchForm" onsubmit="return false" method="get" action="${pageContext.request.contextPath}/SearchUsers">
            <label for="searchBar"></label>
            <input type="text" name="keyword" id="searchBar" class="form-control" placeholder="Search users..." value="${param.keyword}">
        </form>
        <div style="margin-bottom:25px;">
            <div class="search-suggestion" id="searchSuggestions"></div>
        </div>

        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Friend</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="following" items="${followings}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/OtherProfile?username=${following.followed.username}">
                                ${following.followed.username}
                        </a>
                    </td>
                    <td>${following.dateCreated}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/UnfollowUser" method="post">
                            <input type="hidden" name="followed" value="${following.followed.username}" />
                            <input type="submit" value="Unfollow" class="btn btn-danger"/>
                            <input type="hidden" name="fromPage" value="following" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

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
                            suggestionElement.classList.add('search-suggestion'); // Add CSS class
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
</t:template>
