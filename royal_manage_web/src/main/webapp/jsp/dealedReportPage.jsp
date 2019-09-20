<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>相关帖子</title>

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
                        <li><a href="#">用户帖管理</a></li>
                        <li class="active">相关帖子</li>
                    </ol>
                </div>
                <hr>
                <!-- Table -->
                <div>
                    <div style="float: left">
                        <form action="/article/reportPage.do" method="get" id="articleSearchForm">
                            <table>
                                <tr>
                                    <th colspan="2">
                                        <input type="submit" value="返回" class="form-control btn-primary">
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
                        <th>帖子ID</th>
                        <th>举报内容</th>
                        <th>举报人</th>
                        <th>举报时间</th>
                        <th>处理状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${reportMsgs.list}" var="report">
                        <tr>
                            <td width="15%">${report.articleId}</td>
                            <td width="15%" class="line-limit-length">${report.reportContent}</td>
                            <td width="5%" class="line-limit-length">${report.reportUserName}</td>
                            <td width="5%">${report.reportTime}</td>
                            <td width="5%">${report.reportStatus}</td>

                            <td width="15%">
                                    <%--<c:if test="${article.isTop==0}">--%>
                                <%--<a href="${pageContext.request.contextPath}/article/changeReport.do?id=${report.articleId}&page=${reportMsgs.pageNum}&status=2" role="button" class="btn btn-danger" >屏蔽</a>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${article.isTop==1}">--%>
                                <a href="${pageContext.request.contextPath}/article/changeReport.do?id=${report.articleId}&page=${reportMsgs.pageNum}&status=0" role="button" class="btn btn-info" >一键洗白</a>
                                    <%--</c:if>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div><!-- /.panel panel-success -->
            <!--显示分页信息-->
            <div class="row">
                <!--文字信息-->
                <div class="col-md-6">
                    当前第 ${reportMsgs.pageNum} 页.总共 ${reportMsgs.pages} 页.一共 ${reportMsgs.total} 条记录
                </div>

                <!--点击分页-->
                <div class="col-md-6">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <!--首页-->
                            <li><a href="${pageContext.request.contextPath}/article/reportPage.do?page=1&size=${reportMsgs.pageSize}" onclick="searchArticle(1)">首页</a></li>
                            <!--上一页-->
                            <li>
                                <c:if test="${reportMsgs.hasPreviousPage}">
                                    <a href="${pageContext.request.contextPath}/article/reportPage.do?page=${reportMsgs.pageNum-1}&size=${reportMsgs.pageSize}" onclick="searchArticle('${reportMsgs.pageNum-1}')" aria-label="Previous">
                                        <span aria-hidden="true">«</span>
                                    </a>
                                </c:if>
                            </li>

                            <c:forEach items="${reportMsgs.navigatepageNums}" var="pageNum">
                                <c:if test="${pageNum == reportMsgs.pageNum}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/article/reportPage.do?page=${pageNum}&size=${reportMsgs.pageSize}">${pageNum}</a></li>
                                </c:if>
                                <c:if test="${pageNum != reportMsgs.pageNum}">
                                    <li><a href="${pageContext.request.contextPath}/article/reportPage.do?page=${pageNum}&size=${reportMsgs.pageSize}" onclick="searchArticle('${pageNum}')">${pageNum}</a></li>
                                </c:if>
                            </c:forEach>

                            <!--下一页-->
                            <li>
                                <c:if test="${reportMsgs.hasNextPage}">
                                    <a href="${pageContext.request.contextPath}/article/reportPage.do?page=${reportMsgs.pageNum+1}&size=${reportMsgs.pageSize}" onclick="searchArticle('${reportMsgs.pageNum+1}')"
                                       aria-label="Next">
                                        <span aria-hidden="true">»</span>
                                    </a>
                                </c:if>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/article/reportPage.do?page=${reportMsgs.pages}&size=${reportMsgs.pageSize}" onclick="searchArticle('${reportMsgs.pages}')">尾页</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div><!-- /.dept_info -->
        <!-- 尾部-->
        <%@ include file="../../jsp/commom/foot.jsp"%>
    </div><!-- /.hrms_dept_body -->

</div><!-- /.hrms_dept_container -->

<%--<%@ include file="ArticleAdd.jsp"%>--%>
<%@ include file="ArticleUpdate.jsp"%>
</body>
</html>