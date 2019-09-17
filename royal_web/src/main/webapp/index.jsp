<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/9/17
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script src="js/jquery-1.7.2.min.js"></script>
<script>
    $(function () {
        location.href= "${pageContext.request.contextPath}/article/findArticleListByZoneId.do"
    })
</script>
</body>
</html>
