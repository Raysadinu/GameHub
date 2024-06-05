<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 5/14/2024
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Admins">
    <style>
        .container {
            max-width: 800px;
            margin: 100px auto 50px auto;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h1 {
            margin-bottom: 20px;
            color: #333;
        }

        .btn {
            padding: 8px 16px;
            border-radius: 5px;
            text-decoration: none;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        
        .btn:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #dee2e6;
            text-align: center;
        }

        td {
            background-color: #fff;
            color: #333;
        }

        tbody tr:nth-child(even) {
            background-color: #f8f9fa;
        }
    </style>

    <div class="container text-center">
        <h1>Admins</h1>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/AddAdmin">Add Admin</a>
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Username</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="admin" items="${admins}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${admin.username}</td>
                    <td>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/DeleteAdmin?username=${admin.username}">Delete Admin</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:template>
