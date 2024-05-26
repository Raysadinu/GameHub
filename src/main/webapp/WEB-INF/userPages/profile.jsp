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

    </div>
</t:template>
