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
                        <label for="price">Price</label>
                        <input type="number" class="form-control" id="price" name="price" placeholder="" value="${price.price}">
                    </div>
                    <div class="form-group">
                        <label for="discount">Discount</label>
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

                    <input type="hidden" name="gameId" value="${game.gameId}">
                    <input type="hidden" name="gameName" value="${game.gameName}">
                    <input type="submit" class="btn btn-primary" value="Submit Changes">
                </form>
            </div>
        </div>
    </div>
</t:template>
