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
                        <form method="post" id="articleSearchForm" action="${pageContext.request.contextPath}/user/findByPage.do">
                            <table>
                                <tr>
                                    <th>
                                        <label for="title" class="control-label">用户名:</label>
                                    </th>
                                    <th>
                                        <input type="text" id="title" class="form-control"
                                               name="userName" value="${userName}">
                                    </th>
                                    <th>
                                        <label for="article_sendername" class="control-label">用户组:</label>
                                    </th>
                                    <th>
                                        <input type="text" id="article_sendername" class="form-control"
                                               name="role" value="${role}">
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
                        <th>用户名</th>
                        <th>用户组</th>
                        <th>邮箱</th>
                        <th>是否禁言</th>
                        <th>最近登录时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userInfo.list}" var="list">
                            <tr>
                                <td width="15%">${list.userName}</td>
                                <td width="15%">${list.roleStr}</td>
                                <td width="15%">${list.email}</td>
                                <td width="5%">${list.talkStatusStr}</td>
                                <td width="30%">${list.lastLoginTime}</td>
                                <td width="10%">
                                    <a href="${pageContext.request.contextPath}/user/manageAllUser.do?id=${list.userId}&page=${userInfo.pageNum}">升级</a>
                                </td>
                                <td width="10%">
                                    <c:if test="${list.talkStatus==0}">
                                        <a href="${pageContext.request.contextPath}/user/manageAllUser.do?userId=${list.userId}&talkStatus=1&page=${userInfo.pageNum}">禁言</a>
                                    </c:if>
                                    <c:if test="${list.talkStatus==1}">
                                        <a href="${pageContext.request.contextPath}/user/manageAllUser.do?userId=${list.userId}&talkStatus=0&page=${userInfo.pageNum}">恢复</a>
                                    </c:if>
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
                    当前第 ${userInfo.pageNum} 页.总共 ${userInfo.pages} 页.一共 ${userInfo.total} 条记录
                </div>
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <!--首页-->
                        <li><a href="${pageContext.request.contextPath}/user/manageAllUser.do?page=1&size=${userInfo.pageSize}" onclick="searchArticle(1)">首页</a></li>
                        <!--上一页-->
                        <li>
                            <c:if test="${userInfo.hasPreviousPage}">
                                <a href="${pageContext.request.contextPath}/user/manageAllUser.do?page=${userInfo.pageNum-1}&size=${userInfo.pageSize}" onclick="searchArticle('${userInfo.pageNum-1}')" aria-label="Previous">
                                    <span aria-hidden="true">«</span>
                                </a>
                            </c:if>
                        </li>

                        <c:forEach begin="1" end="${userInfo.pages}" var="pageNum">
                            <c:if test="${pageNum == userInfo.pageNum}">
                                <li class="active"><a href="${pageContext.request.contextPath}/user/manageAllUser.do?page=${pageNum}">${pageNum}</a></li>
                            </c:if>
                            <c:if test="${pageNum != userInfo.pageNum}">
                                <li><a href="${pageContext.request.contextPath}/user/manageAllUser.do?page=${pageNum}" onclick="searchArticle('${pageNum}')">${pageNum}</a></li>
                            </c:if>
                        </c:forEach>

                        <!--下一页-->
                        <li>
                            <c:if test="${userInfo.hasNextPage}">
                                <a href="${pageContext.request.contextPath}/user/manageAllUser.do?page=${userInfo.pageNum+1}&size=${userInfo.pageSize}" onclick="searchArticle('${userInfo.pageNum+1}')"
                                   aria-label="Next">
                                    <span aria-hidden="true">»</span>
                                </a>
                            </c:if>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/user/manageAllUser.do?page=${userInfo.pages}&size=${userInfo.pageSize}" onclick="searchArticle('${userInfo.pages}')">尾页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div><!-- /.dept_info -->
    <!-- 尾部-->
    <%@ include file="../../jsp/commom/foot.jsp"%>
</div><!-- /.hrms_dept_body -->

</div><!-- /.hrms_dept_container -->


