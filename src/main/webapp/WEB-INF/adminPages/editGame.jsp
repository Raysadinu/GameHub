<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Edit Game">
    <div class="container">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-6 text-center">
                <h3>Edit ${game.gameName}</h3>
                <form method="POST" action="${pageContext.request.contextPath}/EditGame">
                    <div class="form-group">
                        <label for="trailer">Trailer</label>
                        <input type="text" class="form-control" id="trailer" name="trailer" value="${trailer}">
                    </div>
                    <div class="form-group">
                        <label for="price">Price</label>
                        <input type="number" class="form-control" id="price" name="price" placeholder="" value="${price.price}">
                    </div>
                    <div class="form-group">
                        <label for="discount">Discount (%)</label>
                        <input type="number" class="form-control" id="discount" name="discount" placeholder="" value="${price.discount}">
                    </div>
                    <div class="form-group">
                        <label for="storage">Storage</label>
                        <input type="text" class="form-control" id="storage" name="storage" placeholder="" value="${game.storage}">
                    </div>
                    <div class="form-group">
                        <label for="publisher">Publisher</label>
                        <input type="text" class="form-control" id="publisher" name="publisher" placeholder="" value="${game.publisher}">
                    </div>
                    <div class="form-group">
                        <label for="developer">Developer</label>
                        <input type="text" class="form-control" id="developer" name="developer" placeholder="" value="${game.developer}">
                    </div>
                    <div class="form-group">
                        <label for="releaseDate">Release Date</label>
                        <input type="date" class="form-control" id="releaseDate" name="releaseDate" placeholder="" value="${game.releaseDate}">
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" name="description" placeholder="" rows="5">${game.description}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="searchCategory">Search Categories:</label>
                        <input type="text" class="form-control" id="searchCategory" placeholder="Type to search categories...">
                        <ul id="categoryResults" class="list-group mt-2" style="list-style-type: none;"></ul>

                        <h3>Selected Categories:</h3>
                        <ul id="selectedCategories" class="list-group" style="list-style-type: none;">
                            <c:forEach var="category" items="${gameCategories}">
                                <li id="category-${category.categoryId}" class="list-group-item d-flex justify-content-between align-items-center">
                                        ${category.categoryName}
                                    <button type="button" class="btn btn-danger btn-sm" onclick="removeCategory(${category.categoryId}, ${game.gameId})">x</button>
                                    <input type="hidden" name="categoryIds" value="${category.categoryId}">
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <input type="hidden" name="gameId" value="${game.gameId}">
                    <input type="submit" class="btn btn-primary" value="Submit Changes">
                </form>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#searchCategory").on("input", function () {
                var keyword = $(this).val();
                if (keyword.length >= 1) {
                    $.ajax({
                        url: "SearchCategory",
                        method: "GET",
                        data: { keyword: keyword },
                        success: function (data) {
                            var resultList = $("#categoryResults");
                            resultList.empty();
                            $.each(data, function (index, category) {
                                resultList.append('<li class="list-group-item d-flex justify-content-between align-items-center">' +
                                    category.categoryName +
                                    ' <button type="button" class="btn btn-primary btn-sm" onclick="addCategory(' + category.categoryId + ', \'' + category.categoryName + '\')">+</button></li>');
                            });
                        }
                    });
                }
            });
        });

        function addCategory(categoryId, categoryName) {
            var selectedCategories = $("#selectedCategories");
            if (!$("#category-" + categoryId).length) {
                selectedCategories.append('<li id="category-' + categoryId + '" class="list-group-item d-flex justify-content-between align-items-center">' +
                    categoryName +
                    ' <button type="button" class="btn btn-danger btn-sm" onclick="removeCategory(' + categoryId + ')">x</button>' +
                    '<input type="hidden" name="categoryIds" value="' + categoryId + '"></li>');
            }
        }

        function removeCategory(categoryId, gameId) {
            $.ajax({
                url: "DeleteCategory",
                method: "POST",
                data: { categoryId: categoryId, gameId: gameId },
                success: function () {
                    $("#category-" + categoryId).remove();
                }
            });
        }
    </script>
</t:template>
