<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>敏感词汇管理</title>

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
    <script type="text/javascript">
        function addWord()
        {
            var addword=prompt("请输入需要添加的敏感词");
            if (addword!=null)
            {
                document.form1.addword.value=addword;
                document.form1.submit();
            }
            // window.open('url' ,'_blank','width=400,height=500 menebar=no,toolbar=no');
        }
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
                        <li class="active">敏感词汇管理</li>
                    </ol>
                </div>
                <hr>
                <!-- Table -->
                <div>
                    <div style="float: left">
                        <form action="/article/addWords.do" method="post" name="form1" id="articleSearchForm">
                            <table>
                                <tr>
                                    <th colspan="2">
                                        <input type="hidden" name="addword">
                                        <input type="button" onclick="addWord()" value="新增敏感词汇" class="form-control btn-primary">
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
                        <th>序号</th>
                        <th>敏感词</th>
                        <th>是否启用</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${wordsMsgs.list}" var="word">
                        <tr>
                            <td width="15%">${word.wordId}</td>
                            <td width="30%" class="line-limit-length">${word.word}</td>
                            <td width="5%" class="line-limit-length">
                                <c:if test="${word.status==0}">
                                    <a>已停用</a>
                                </c:if>
                                <c:if test="${word.status==1}">
                                    <a>使用中</a>
                                </c:if>
                            </td>

                            <td width="15%">
                                <c:if test="${word.status==0}">
                                    <a href="${pageContext.request.contextPath}/article/wordStatus.do?id=${word.wordId}&pn=${wordsMsgs.pageNum}&page=${wordsMsgs.pageNum}&size=${wordsMsgs.pageSize}&status=1" role="button" class="btn btn-danger" >启用</a>
                                </c:if>
                                <c:if test="${word.status==1}">
                                    <a href="${pageContext.request.contextPath}/article/wordStatus.do?id=${word.wordId}&pn=${wordsMsgs.pageNum}&page=${wordsMsgs.pageNum}&size=${wordsMsgs.pageSize}&status=0" role="button" class="btn btn-info" >停用</a>
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
                    当前第 ${wordsMsgs.pageNum} 页.总共 ${wordsMsgs.pages} 页.一共 ${wordsMsgs.total} 条记录
                </div>

                <!--点击分页-->
                <div class="col-md-6">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <!--首页-->
                            <li><a href="${pageContext.request.contextPath}/article/SensitiveWordsPage.do?page=1&size=${wordsMsgs.pageSize}" onclick="searchArticle(1)">首页</a></li>
                            <!--上一页-->
                            <li>
                                <c:if test="${wordsMsgs.hasPreviousPage}">
                                    <a href="${pageContext.request.contextPath}/article/SensitiveWordsPage.do?page=${wordsMsgs.pageNum-1}&size=${wordsMsgs.pageSize}" onclick="searchArticle('${wordsMsgs.pageNum-1}')" aria-label="Previous">
                                        <span aria-hidden="true">«</span>
                                    </a>
                                </c:if>
                            </li>

                            <c:forEach items="${wordsMsgs.navigatepageNums}" var="pageNum">
                                <c:if test="${pageNum == wordsMsgs.pageNum}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/article/SensitiveWordsPage.do?page=${pageNum}&size=${wordsMsgs.pageSize}">${pageNum}</a></li>
                                </c:if>
                                <c:if test="${pageNum != wordsMsgs.pageNum}">
                                    <li><a href="${pageContext.request.contextPath}/article/SensitiveWordsPage.do?page=${pageNum}&size=${wordsMsgs.pageSize}" onclick="searchArticle('${pageNum}')">${pageNum}</a></li>
                                </c:if>
                            </c:forEach>

                            <!--下一页-->
                            <li>
                                <c:if test="${wordsMsgs.hasNextPage}">
                                    <a href="${pageContext.request.contextPath}/article/SensitiveWordsPage.do?page=${wordsMsgs.pageNum+1}&size=${wordsMsgs.pageSize}" onclick="searchArticle('${wordsMsgs.pageNum+1}')"
                                       aria-label="Next">
                                        <span aria-hidden="true">»</span>
                                    </a>
                                </c:if>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/article/SensitiveWordsPage.do?page=${wordsMsgs.pages}&size=${wordsMsgs.pageSize}" onclick="searchArticle('${wordsMsgs.pages}')">尾页</a></li>
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
