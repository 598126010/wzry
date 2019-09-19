<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帖信息管理页面</title>

</head>
<style type="text/css">
    html,body{
        overflow:auto;
        height:100%;
    }

    .line-limit-length {
        max-width: 220px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }


</style>
<script>

</script>
<body>
<div class="hrms_dept_container">
    <!-- 导航栏-->
    <%@ include file="../../jsp/commom/head.jsp"%>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div class="hrms_dept_body">
        <!-- 左侧栏 -->
        <%@ include file="../../jsp/commom/leftsidebar.jsp"%>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">
            <div class="panel panel-success">
                <!-- 路径导航 -->
                <div >
                    <ol class="breadcrumb">
                        <li><a href="#">用户管理</a></li>
                        <li class="active">板块审核</li>
                    </ol>
                </div>
                <hr>
                <!-- Table -->
                <div>
                    <div style="float: left">
                        <form method="post" id="articleSearchForm" action="${pageContext.request.contextPath}/zoneApply/findAll.do">
                            <table>
                                <tr>
                                    <th>
                                        <label for="title" class="control-label" >板块名:</label>
                                    </th>
                                    <th>
                                        <input type="text" id="title" class="form-control"
                                               name="zoneName" value="${zoneName}">
                                    </th>
                                    <th>
                                        <label for="article_sendername" class="control-label">用户名:</label>
                                    </th>
                                    <th>
                                        <input type="text" id="article_sendername" class="form-control"
                                               name="userName" value="${userName}">
                                    </th>
                                    <th colspan="2">
                                        <input type="submit" value="查询" class="form-control btn-primary">
                                    </th>
                                </tr>
                            </table>

                        </form>
                    </div>
                </div>
                <div style="clear:both"></div>
                <hr>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>新增板块名称</th>
                        <th>用户名</th>
                        <th>申请原因</th>
                        <th>处理状态</th>
                        <th>处理状态</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ZoneApply.list}" var="list">
                            <tr>
                                <td width="20%">${list.zoneName}</td>
                                <td width="20%">${list.userName}</td>
                                <td width="20%">${list.reason}</td>
                                <td width="20%">${list.statusStr}</td>
                                <td width="20%">
                                    <c:if test="${list.status==0}">
                                        <a href="${pageContext.request.contextPath}/zoneApply/kaiqi.do?applyZoneId=${list.applyZoneId}&status=1&zoneName=${list.zoneName}&page=${ZoneApply.pageNum}">板块开启</a>
                                    </c:if>
                                    <c:if test="${list.status==1}">
                                        <a href="${pageContext.request.contextPath}/zoneApply/kaiqi.do?applyZoneId=${list.applyZoneId}&status=0&zoneName=${list.zoneName}&page=${ZoneApply.pageNum}">板块关闭</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!--显示分页信息-->
                            <div class="row">
                            <!--文字信息-->
                            <div class="col-md-6">
                            当前第 ${ZoneApply.pageNum} 页.总共 ${ZoneApply.pages} 页.一共 ${ZoneApply.total} 条记录
                            </div>

                            <!--点击分页-->
                            <div class="col-md-6">
                            <nav aria-label="Page navigation">
                            <ul class="pagination">
                            <!--首页-->
                            <li><a href="${pageContext.request.contextPath}/zoneApply/findAll.do?page=1&size=${ZoneApply.pageSize}&zoneName=${zoneName}&userName=${userName}" onclick="searchArticle(1)">首页</a></li>
                            <!--上一页-->
                            <li>
                            <c:if test="${ZoneApply.hasPreviousPage}">
                                <a href="${pageContext.request.contextPath}/zoneApply/findAll.do?page=${ZoneApply.pageNum-1}&size=${ZoneApply.pageSize}&zoneName=${zoneName}&userName=${userName}" onclick="searchArticle('${ZoneApply.pageNum-1}')" aria-label="Previous">
                                    <span aria-hidden="true">«</span>
                                </a>
                            </c:if>
                            </li>

                            <c:forEach items="${ZoneApply.navigatepageNums}" var="pageNum">
                                <c:if test="${pageNum == ZoneApply.pageNum}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/zoneApply/findAll.do?page=${pageNum}&size=${ZoneApply.pageSize}&zoneName=${zoneName}&userName=${userName}">${pageNum}</a></li>
                                </c:if>
                                <c:if test="${pageNum != ZoneApply.pageNum}">
                                    <li><a href="${pageContext.request.contextPath}/zoneApply/findAll.do?page=${pageNum}&size=${list.pageSize}&zoneName=${zoneName}&userName=${userName}" onclick="searchArticle('${pageNum}')">${pageNum}</a></li>
                                </c:if>
                            </c:forEach>

                            <!--下一页-->
                            <li>
                            <c:if test="${ZoneApply.hasNextPage}">
                                <a href="${pageContext.request.contextPath}/zoneApply/findAll.do?page=${ZoneApply.pageNum+1}&size=${ZoneApply.pageSize}&zoneName=${zoneName}&userName=${userName}" onclick="searchArticle('${ZoneApply.pageNum+1}')"
                                   aria-label="Next">
                                    <span aria-hidden="true">»</span>
                                </a>
                            </c:if>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/zoneApply/findAll.do?page=${ZoneApply.pages}&size=${ZoneApply.pageSize}&zoneName=${zoneName}&userName=${userName}" onclick="searchArticle('${ZoneApply.pages}')">尾页</a></li>
                            </ul>
                            </nav>
                            </div>
                            </div>
                            </div><!-- /.dept_info -->
                            <!-- 尾部-->
                            <%@ include file="../../jsp/commom/foot.jsp"%>
                            </div><!-- /.hrms_dept_body -->

                            </div><!-- /.hrms_dept_container -->

 </body>
</html>


