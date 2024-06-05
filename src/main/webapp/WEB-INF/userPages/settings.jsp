<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Profile">
    <style>
        .container {
            max-width: 800px;
            margin: 100px auto;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .btn {
            padding: 10px 20px;
            margin: 5px;
            border-radius: 5px;
            font-size: 16px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border: 1px solid #007bff;
            transition: background-color 0.3s, border-color 0.3s, color 0.3s;
        }

        .btn:hover {
            background-color: #0056b3;
            border-color: #0056b3;
            color: #fff;
        }
    </style>

    <div class="container text-center">
        <div>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditProfile?username=${user.username}">Edit your profile</a>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/Payment?username=${user.username}&page=cards">Cards</a>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/PurchaseHistory?username=${user.username}">Purchase History</a>
        </div>
    </div>
</t:template>
