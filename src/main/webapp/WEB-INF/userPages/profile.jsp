<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Profile">

    <div class="container text-center" style="margin-top: 100px">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <c:if test="${not empty user.profilePicture}">
                    <img src="data:image/${user.profilePicture.imageFormat};base64,${user.profilePicture.base64ImageData}" alt="Profile Picture" width="200" height="200">
                </c:if>
                <c:if test="${empty user.profilePicture}">
                    <img src="img/default-picture.jpg" alt="Default Profile Picture" width="200" height="200">
                </c:if>
            </div>
        </div>
        <h1>${user.username}</h1>

        <div style="margin-top: 20px;">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/Following?username=${user.username}" style="margin-top: 10px;">Following</a>
        </div>
        <p style="margin-top: 20px;">Full name: ${user.firstName} ${user.lastName}</p>
        <p>Birthday: ${user.birthDate}</p>
        <p>Phone number: ${user.phoneNumber}</p>
        <p>Bio: ${user.bio}</p>

        <div>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/SettingsUser?username=${user.username}">Settings</a>
        </div>
        <h2>Add a Post</h2>
        <form id="postForm" action="${pageContext.request.contextPath}/Community/AddPost" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="4" cols="50"></textarea>
            </div>
            <div class="form-group">
                <label for="searchBar">Search Games:</label>
                <input type="text" class="form-control" id="searchBar" placeholder="Search games...">
                <ul id="gameSearchResults" class="list-group mt-2" style="list-style-type: none;"></ul>
            </div>
            <div class="form-group">
                <label for="postPicture">Upload Picture:</label>
                <input type="file" class="form-control-file" id="postPicture" name="postPicture">
            </div>
            <div class="form-group">
                <label for="selectedGames">Selected Game:</label>
                <ul id="selectedGames" class="list-group mt-2" style="list-style-type: none;"></ul>
            </div>
            <input type="hidden" id="selectedGameId" name="selectedGameId">
            <input type="submit" class="btn btn-primary" value="Add Post">
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        document.getElementById('searchBar').addEventListener('input', function() {
            var keyword = this.value.trim();
            if (keyword !== '') {
                fetch('${pageContext.request.contextPath}/SearchGames?keyword=' + keyword)
                    .then(response => response.json())
                    .then(data => {
                        var suggestionsDiv = document.getElementById('gameSearchResults');
                        suggestionsDiv.innerHTML = '';
                        data.forEach(game => {
                            var suggestionElement = document.createElement('div');
                            suggestionElement.textContent = game.gameName;
                            suggestionElement.dataset.gameId = game.gameId;

                            // Create a button to add the game
                            var addButton = document.createElement('a');
                            addButton.textContent = '+';
                            addButton.className = 'btn btn-primary';
                            addButton.onclick = function() {
                                addGame(game.gameId, game.gameName);
                            };

                            // Append the button to the suggestion element
                            suggestionElement.appendChild(addButton);

                            // Append the suggestion element to the search results div
                            suggestionsDiv.appendChild(suggestionElement);
                        });
                    })
                    .catch(error => {
                        console.error('Error fetching search suggestions:', error);
                    });
            } else {
                document.getElementById('gameSearchResults').innerHTML = '';
            }
        });

        function addGame(gameId, gameName) {
            var selectedGames = $("#selectedGames");

            selectedGames.append('<li id="game-' + gameId + '" class="list-group-item">' +
                gameName +
                '<button type="button" class="btn btn-danger btn-sm" onclick="removeGame(' + gameId + ')">x</button>' +
                '<input type="hidden" name="gameIds" value="' + gameId + '"></li>');
            $("#selectedGameId").val(gameId);

            $("#gameAdded").val("true");

            return false;
        }


        function removeGame(gameId) {
            $("#game-" + gameId).remove();
        }

        $(document).ready(function() {
            $("#addGameBtn").click(function() {
                $(this).prop('disabled', true);
            });
        });

    </script>
</t:template>
