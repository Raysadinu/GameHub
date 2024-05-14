<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Profile">

    <div class="container text-center">

        <h1>Profile, ${user.username}</h1>
        <div>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/Following?username=${user.username}">Following</a>
        </div>
        <p>Full name: ${user.firstName} ${user.lastName}</p>
        <p>Birthday: ${user.birthDate}</p>
        <p>Phone number: ${user.phoneNumber}</p>
        <p>Bio: ${user.bio}</p>

        <div>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/SettingsUser?username=${user.username}">Settings</a>
        </div>

    </div>
</t:template>
