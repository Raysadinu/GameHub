<%@page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template pageTitle="Add Game">
    <div class="container">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-6 text-center">
                <h3>Add Game</h3>
                <form method="POST" action="${pageContext.request.contextPath}/AddGame">
                    <div class="form-group">
                        <label for="game_name">Game Name:</label>
                        <input type="text" class="form-control" id="game_name" name="game_name" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</t:template>