<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template pageTitle="Edit User Form">

    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditUser">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="email">Username ${user.username}</label>
                <input type="text" class="form-control" id="email" name="email" placeholder="" value="${user.email}" required>
                <div class="invalid-feedback">
                    Email field cannot be empty!
                </div>
            </div>
        </div>
        <input type="hidden" name="password" value="${user.password}">
        <input type="submit" value="submit">
    </form>

</t:template>
