<%@tag description="base page template" pageEncoding="UTF-8"%>
<%@attribute name="pageTitle"%>

<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <link rel="icon" href="resources/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/games.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/home.css">
</head>
<body>
<jsp:include page="/WEB-INF/components/menus/menu.jsp"/>
<main class="container-fluid mt-5">
    <jsp:doBody/>
    <script src="${pageContext.request.contextPath}/scripts/validateForm.js"></script>
</main>

</body>
<footer>
    <jsp:include page="/WEB-INF/components/footer.jsp" />
</footer>
</html>