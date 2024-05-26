<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Edit Game">
    <div class="container">
        <div class="row justify-content-center align-items-center">
            <div class="col-md-6 text-center">
                <h3>Edit ${game.gameName}</h3>
                <form method="POST" action="${pageContext.request.contextPath}/AddGameScreenshots" enctype="multipart/form-data">

                    <!-- Screenshot Upload -->
                    <div class="form-group">
                        <label for="screenshots">Screenshots</label>
                        <input type="file" class="form-control" id="screenshots" name="screenshots" accept="image/*" multiple>
                    </div>

                    <input type="hidden" name="gameId" value="${game.gameId}">
                    <button type="submit">Save</button>
                </form>
            </div>
        </div>
    </div>

</t:template>