<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
    if (screen && screen.width > 480) {
        document.write('<script type="text/javascript" color="230, 179, 255" opacity="1" zIndex="-2" count="99" src="${pageContext.request.contextPath}/js/canvas-nest.js"><\/script>');
    }
</script>


<div class="hm-footer" style="padding-top:10px;">
    <div class="hm-inner">
        <div class="hm-footer-cpr">
            <p>Copyright@2019 ITCAST. All Rights Reserved</p>
            <p>传智播客 版权所有</p>
        </div>
    </div>
</div>
</body>
</html>