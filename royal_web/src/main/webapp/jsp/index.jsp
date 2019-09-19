<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>王者荣耀论坛</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index-new.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/hm-bbs.js"></script>
    <style>
        ul.pagination {
            display: inline-block;
            padding: 5px 20%;
            margin: 0;
            /*align-content: center;*/
        }

        ul.pagination li {display: inline;}

        ul.pagination li a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
            transition: background-color .3s;
        }

        ul.pagination li a.active {
            background-color: #4CAF50;
            color: white;
        }

        ul.pagination li a:hover:not(.active) {background-color: #ddd;}
    </style>
</head>
<body>

<!-- 头部 -->
<jsp:include page="common/header.jsp"/>


<!-- 主体部分 -->
<div class="hm-header"></div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="hm-banner"></div>




        <!--头部，帖子统计，搜索-->
        <div class="hm-bbs-info">
            <div class="hm-bbs-icon l" style="width:130px;">
                <span><img src="/images/bbs-icon.png" height="80"/></span>
            </div>
            <div class="hm-bbs-info-in l" style="margin-left:30px;">
                <div class="t clearfix"><h2 class="l">王者荣耀</h2></div>
                <p>
                    <span id="todayCount"></span>
                    <span id="totalCount"></span>
                </p>
            </div>
            <div class="search-box l">
                <form action="${pageContext.request.contextPath}/article/findByKeyWord.do" method="get">
                    <input type="text" name="keyWord" class="txt l" placeholder="请输入关键字">
                    <input type="submit" value="搜索" class="btn l"/>
                </form>
            </div>
        </div>




        <!-- 导航 -->
        <ul class="hm-bbs-nav border-lrb clearfix">
            <c:forEach items="${zoneList}" var="zone">
                <c:if test="${zone.zoneId == zoneId}">
                    <li class="current">
                        <a href="${pageContext.request.contextPath}/article/findArticleListByZoneId.do?zoneId=${zone.zoneId}"><em></em>${zone.zoneName}</a>
                    </li>
                </c:if>
                <c:if test="${zone.zoneId != zoneId}">
                    <li >
                        <a href="${pageContext.request.contextPath}/article/findArticleListByZoneId.do?zoneId=${zone.zoneId}"><em></em>${zone.zoneName}</a>
                    </li>
                </c:if>

            </c:forEach>
        </ul>




        <!-- 主体部分 -->
        <div class="hm-bbs-main border-lrb clearfix">
            <!-- 左侧列表 -->
            <div class="list-view l">
                <ul>
                    <c:forEach items="${pageInfo.list}" var="list">
                        <c:if test="${list.isTop == 1}">
                            <li class="clearfix ding">
                                <div class="hm-index-title">
                                    <i class="set-to-top">顶</i> <a href="${pageContext.request.contextPath}getArticle.do?articleId=${list.articleId}">${list.title}</a>
                                </div>
                                <div class="hm-index-con">${list.content}</div>
                                <div class="hm-index-info l">
                                    <span class="article-username">${list.senderName}</span>
                                    <span class="post-time">${list.sendTime}</span>
                                </div>
                                <div class="hm-index-fun r">
                                    <span class="icon-like"><i></i>${list.upvoteCount}</span>
                                    <span class="icon-talk"><i></i><c:if test="${list.replyCount == null}">0</c:if>
                                    <c:if test="${list.replyCount != null}">${list.replyCount}</c:if></span>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${list.isTop == 0}">
                        <li class="clearfix">
                        <div class="hm-index-title">
                        <i class="set-to-top"></i><a href="${pageContext.request.contextPath}getArticle.do?articleId=${list.articleId}">${list.title}</a>
                        </div>
                        <div class="hm-index-con">${list.content}</div>
                        <div class="hm-index-info l">
                        <span class="article-username">${list.senderName}</span><span class="post-time">${list.sendTime}</span>
                        </div>
                        <div class="hm-index-fun r">
                        <span class="icon-like"><i></i>${list.upvoteCount}</span>
                        <span class="icon-talk"><i></i><c:if test="${list.replyCount == null}">0</c:if>
                        <c:if test="${list.replyCount != null}">${list.replyCount}</c:if>
                        </span>
                        </div>
                        </c:if>
                    </c:forEach>
                        <%--<c:forEach items="${pageInfo.list}" var="list">--%>
                       <%--<c:if test="${list.isTop == 0}">--%>
                        <%--<li class="clearfix">--%>
                            <%--<div class="hm-index-title">--%>
                                <%--<i class="set-to-top"></i><a href="${pageContext.request.contextPath}getArticle.do?articleId=${list.articleId}">${list.title}</a>--%>
                            <%--</div>--%>
                            <%--<div class="hm-index-con">${list.content}</div>--%>
                            <%--<div class="hm-index-info l">--%>
                                <%--<span class="article-username">${list.senderName}</span><span class="post-time">${list.sendTime}</span>--%>
                            <%--</div>--%>
                            <%--<div class="hm-index-fun r">--%>
                                <%--<span class="icon-like"><i></i>${list.upvoteCount}</span>--%>
                                <%--<span class="icon-talk"><i></i><c:if test="${list.replyCount == null}">0</c:if>--%>
                                    <%--<c:if test="${list.replyCount != null}">${list.replyCount}</c:if>--%>
                                <%--</span>--%>
                            <%--</div>--%>
                            <%--</c:if>--%>
                    <%--</c:forEach>--%>

                    </li>
                </ul>
            </div>




            <!-- 右侧侧边栏,在线用户 -->
            <div class="aside l">
                <div class="aside-box">
                    <h3 class="t">
                        <a href="javascript:;">在线用户(${onlineUser.size()})</a>
                    </h3>
                    <ul class="b clearfix">
                        <c:forEach items="${onlineUser}" var="onlineUser">
                            <li>
                                <div><img src="/${onlineUser.picUrl}" height="55"/> </div>
                                <p>${onlineUser.userName}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <ul class="pagination" >
                <li><a href=href="${pageContext.request.contextPath}/article/findArticleListByZoneId.do?pageSize=${pageInfo.size}&pageNum=1">«</a></li>
                <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                    <c:if test="${pageInfo.pageNum == i}">
                        <li><a  class="active"  href="${pageContext.request.contextPath}/article/findArticleListByZoneId.do?pageSize=${pageInfo.size}&pageNum=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${pageInfo.pageNum != i}">
                        <li><a href="${pageContext.request.contextPath}/article/findArticleListByZoneId.do?pageSize=${pageInfo.size}&pageNum=${i}">${i}</a></li>
                    </c:if>

                </c:forEach>
                <li><a href=href="${pageContext.request.contextPath}/article/findArticleListByZoneId.do?pageSize=${pageInfo.size}&pageNum=${pageInfo.pageNum}">»</a></li>


            </ul>
        </div>
    </div>
</div>



<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>



<!-- 右边发帖，回顶部 -->
<div class="fixedBar" id="j_fixedBar">
    <a id="newTopicBtn" href="javascript:;" class="newTopic"><span></span>发帖</a>
    <a href="#" class="goTop"><i></i><span>返回<br/>顶部</span></a>
</div>

<!-- 发帖弹出框 -->
<form action="${pageContext.request.contextPath}/article/createNewArticle.do?" method="post" id="reportArticle">
    <input type="hidden" value="${user.userName}" name="senderName">
    <input type="hidden" value="${zoneId}" name="zoneId">
    <div class="pop-box ft-box">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">主题帖</h4><span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_t">
                    <input type="text" id="title" name="title" placeholder="帖子标题"/>
                </div>
                <div class="win_bd_b">
                    <textarea id="content" name="content" placeholder="正文"></textarea>
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input type="button" class="btn" value="发表" id="Article"/>
                </div>
            </div>
        </div>
    </div>
</form>
<script>
    $(function () {
        $("#newTopicBtn").click(function () {
            if(${user==null}){
                alert("请先登录用户")
                return
            }if(${user.talkStatus == 1}){
                alert("您已经被禁言无法发帖,请于管理员联系")
                return;
            }
            $('.ft-box').fadeIn(120);
        })
        $("#Article").click(function () {
            var title =$("#title").val();
            var content = $("#content").val();
            if(title == ""){
                alert("请输入标题")
                return
            }
            if(content == ""){
                alert("请输入内容")
                return
            }
            $("#reportArticle").submit();
        })

        $.post("/article/getTotalCount.do",{},function(result){
            $("#totalCount").html("全部帖子<strong>"+result+"</strong>")
        });
        $.post("/article/getTodayCount.do",{},function(result){
            $("#todayCount").html("今日帖子<strong>"+result+"</strong>")
        });
    })
</script>
</body>
</html>