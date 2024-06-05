<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Profile">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/emojionearea/dist/emojionearea.min.css">
    <script src="https://cdn.jsdelivr.net/npm/emojionearea/dist/emojionearea.min.js"></script>

    <div class="container text-center">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="profile-picture-container">
                    <c:if test="${not empty user.profilePicture}">
                        <img src="data:image/${user.profilePicture.imageFormat};base64,${user.profilePicture.base64ImageData}" alt="Profile Picture" class="profile-picture">
                    </c:if>
                    <c:if test="${empty user.profilePicture}">
                        <img src="default-picture.jpg" alt="Default Profile Picture" class="profile-picture">
                    </c:if>
                </div>
            </div>
        </div>
        <div class="col-md-12 mt-3">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/Following?username=${user.username}"><i class="bi bi-people-fill"></i> Following</a>
            <a href="${pageContext.request.contextPath}/SettingsUser?username=${user.username}" class="btn btn-secondary ml-2"><i class="bi bi-gear"></i> Settings</a>
        </div>
        <h1 class="mt-4 username">${user.username}<span class="nickname">${user.nickname}</span></h1>

        <c:if test="${not empty user.bio}">
            <p class="bio" style="white-space: pre-wrap;">${user.bio}</p>
        </c:if>




        <form id="postForm" action="${pageContext.request.contextPath}/Community/AddPost" method="post" enctype="multipart/form-data">
            <h3 class="mt-5">Add a Post</h3>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="4" cols="50"></textarea>
            </div>
            <div class="form-group">
                <label for="searchBarPost">Add your favorite game or the game you post about:</label>
                <input type="text" name="keyword" id="searchBarPost" class="form-control" placeholder="Search games..." value="${param.keyword}">
            </div>
            <div class="col-md-12">
                <div class="center-content">
                    <div id="gameSearchResultsPost" class="search-results"></div>
                </div>
            </div>

            <div class="form-group">
                <label for="postPicture">Upload Picture:</label>
                <input type="file" class="form-control" id="postPicture" name="postPicture" accept="image/*">
            </div>

            <div class="form-group">
                <label for="selectedGames">Selected Game:</label>
                <ul id="selectedGames" class="list-group mt-2"></ul>
            </div>
            <input type="hidden" id="selectedGameId" name="selectedGameId">
            <input type="submit" class="post" value="Add Post">
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        document.getElementById('searchBarPost').addEventListener('input', function() {
            var keyword = this.value.trim();
            if (keyword !== '') {
                fetch('${pageContext.request.contextPath}/SearchGames?keyword=' + keyword)
                    .then(response => response.json())
                    .then(data => {
                        var suggestionsDiv = document.getElementById('gameSearchResultsPost');
                        suggestionsDiv.innerHTML = '';
                        data.forEach(game => {
                            // Create a div element for each game suggestion
                            var suggestionElement = document.createElement('div');
                            suggestionElement.textContent = game.gameName;
                            suggestionElement.dataset.gameId = game.gameId;
                            suggestionElement.classList.add('list-group-item', 'd-flex', 'justify-content-between', 'align-items-center');

                            // Create a plus button
                            var addButton = document.createElement('a');
                            addButton.textContent = '+';
                            addButton.classList.add('btn', 'btn-primary', 'btn-sm', 'ml-2');
                            addButton.addEventListener('click', function() {
                                addGame(game.gameId, game.gameName);
                            });

                            // Append the plus button to the suggestion element
                            suggestionElement.appendChild(addButton);

                            // Append the suggestion element to the search results
                            suggestionsDiv.appendChild(suggestionElement);
                        });
                    })
                    .catch(error => {
                        console.error('Error fetching search suggestions:', error);
                    });
            } else {
                document.getElementById('gameSearchResultsPost').innerHTML = '';
            }
        });

        function validateForm() {
            // Check if any game is selected
            if ($("#selectedGames li").length === 0) {
                alert("Please select a game before submitting.");
                return false; // Prevent form submission
            }
            return true; // Allow form submission if a game is selected
        }
        $("#postForm").submit(function() {
            return validateForm(); // Call the validation function
        });
        function addGame(gameId, gameName) {
            var selectedGames = $("#selectedGames");

                selectedGames.append('<li id="game-' + gameId + '" class="list-group-item d-flex justify-content-between align-items-center">' +
                    gameName +
                    '<button type="button" class="btn btn-danger btn-sm" onclick="removeGame(' + gameId + ')">x</button>' +
                    '<input type="hidden" name="gameIds" value="' + gameId + '"></li>');
                $("#selectedGameId").val(gameId);

                return false;
        }

        function removeGame(gameId) {
                $("#game-" + gameId).remove();
        }

        $("#addGameBtn").click(function() {
                $(this).prop('disabled', true);
        });

    </script>
</t:template>
