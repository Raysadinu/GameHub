<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Community">
    <div class="container text-center" style="margin-top: 100px">
        <h2>Add a Post</h2>
        <form action="${pageContext.request.contextPath}/Community/AddPost" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="4" cols="50"></textarea>
            </div>
            <div class="form-group">
                <label for="postPicture">Upload Picture:</label>
                <input type="file" class="form-control-file" id="postPicture" name="postPicture">
            </div>
            <div class="form-group">
                <label for="videoLink">Video Link:</label>
                <input type="text" class="form-control" id="videoLink" name="videoLink">
            </div>
            <input type="submit" class="btn btn-primary" value="Submit">
        </form>
        <h3>Existing Posts:</h3>
        <div>
            <c:forEach var="post" items="${postList}">
                <div class="post-container">
                    <p>Username: ${post.user.username}</p>
                    <p>Date:
                        <fmt:formatDate value="${post.postingDate}" pattern="yyyy-MM-dd HH:mm" />
                    </p>
                    <p>${post.description}</p>
                    <div id="postSlideshow${post.postId}" class="slideshow">
                        <c:forEach var="media" items="${post.mediaPosts}">
                            <c:if test="${not empty media}">
                                <iframe width="700" height="415" src="${media.link}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                            </c:if>
                        </c:forEach>
                        <c:forEach var="picture" items="${post.postPictures}">
                            <c:if test="${not empty picture}">
                                <img src="data:image/${picture.imageFormat};base64,${picture.base64ImageData}" alt="Post Picture" width="700" height="415" />
                            </c:if>
                        </c:forEach>
                    </div>

                    <div class="button-group">
                        <button type="button" class="btn btn-primary">LIKE</button>
                        <button type="button" class="btn btn-primary">FUN</button>
                        <button type="button" class="btn btn-primary">HELPFUL</button>
                        <button type="button" class="btn btn-primary">DISLIKE</button>
                        <button type="button" class="btn btn-primary">COMMENT</button>
                        <form action="${pageContext.request.contextPath}/DeletePost" method="post">
                            <input type="hidden" name="postId" value="${post.postId}">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</t:template>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css">

<script>
    $(document).ready(function() {
        var posts = ${postList};

        posts.forEach(function(post) {
            var slideshowContent = '';

            post.mediaPosts.forEach(function(media) {
                if (media != null) {
                    slideshowContent += '<div class="slide-content"><iframe width="50%" height="415" src="' + media.link + '" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>';
                }
            });

            post.postPictures.forEach(function(picture) {
                if (picture != null) {
                    slideshowContent += '<img src="data:image/' + picture.imageFormat + ';base64,' + picture.base64ImageData + '" alt="Post Picture" width="50%" height="415" /></div>';
                }
            });

            $('#postSlideshow' + post.postId).append('<div class="slide">' + slideshowContent + '</div>');
        });

        $('.slideshow').slick({
            autoplay: true,
            autoplaySpeed: 5000,
            arrows: true,
            dots: true
        });
    });

</script>