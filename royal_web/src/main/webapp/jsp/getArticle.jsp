<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>黑马程序员论坛详情页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/search.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/detail.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/getArticle.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/hm-bbs.js"></script>
</head>
<body>


<!-- 头部 -->
<jsp:include page="common/header.jsp" />



<div class="hm-header"></div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">

        <!--帖子标题，点赞数，回复数，搜索-->
        <div class="hm-bbs-info">
            <div class="hm-bbs-icon l" style="width:130px;">
                <span><img src="images/bbs-icon.png" height="80"/></span>
            </div>
            <div class="hm-bbs-info-in l" style="margin-left:30px;">
                <div class="t clearfix">
                    <h2 class="l">${article.title}</h2>
                    <div class="hm-detail-fun l">
					     <span class="icon-like">
					         <a href="#"><i></i>3</a>
					     </span>
                        <span class="icon-talk">
						     <i></i>10
						</span>
                    </div>
                </div>
            </div>
            <div class="search-box l">
                <form action="javascript:;">
                    <input type="text" class="txt l" placeholder="请输入关键字">
                    <input type="button" value="搜索" class="btn l"/>
                </form>
            </div>
        </div>


        <!--导航，回首页，帖子标题，排序-->
        <div class="detail-page-box clearfix">
            <a href="${pageContext.request.contextPath}/index.jsp">
                <i class="hm-ico-home"></i>首页
            </a>
            <span>></span>
            <a href="#">${article.title}</a>
            <a class="new-to-old r" href="" style="font-size:12px;float: right;">
                <i></i>从新到旧查看
            </a>
        </div>


        <div class="detail-box">
            <ul class="detail-floors">

                <!--原帖楼-->
                <li class="floor clearfix">
                    <div class="floorer-info l">
                        <div class="floorer-photo"><img src="images/default.png"/></div>
                        <div class="floorer-name">${article.senderName}</div>
                    </div>
                    <div class="floor-con l">
                        <div class="floor-info clearfix">
                            <div class="floor-time l">发帖时间：${article.sendTime}</div>
                            <div class="r">${article.browseCount}</div>
                        </div>
                        <div class="floor-art-ans">
                            <div class="floor-art">
                                <p>${article.content}</p>
                            </div>
                            <div class="floor-ans"></div>
                        </div>
                        <span class="icon-comment"><a href="#comment"> <i></i> 评论</a></span>
                    </div>
                </li>


                <!-- 评论部分,一楼及以后 -->
                <c:forEach items="${commentList}" var="list" varStatus="lou">
                    <li class="floor clearfix">
                        <div class="floorer-info l">
                            <div class="floorer-photo"><img src="images/default.png"/></div>
                            <div class="floorer-name">${list.commentUserName}</div>
                        </div>
                        <div class="floor-con l">
                            <div class="floor-info clearfix">
                                <div class="floor-time l">回贴时间：${list.commentTime}</div>
                                <div class="r">${lou.index+1}楼</div>
                            </div>
                            <div class="floor-art-ans">
                                <div class="floor-art">
                                    <p>${list.commentContent}</p>
                                </div>
                                <div class="floor-ans">
                                    <ul>
                                        <c:forEach items="${list.replies}" var="reply">
                                            <!-- 回复部分,楼中楼 -->
                                            <li class="clearfix">
                                                <div class="floor-ans-pho l"><img src="images/default.png"/></div>
                                                <div class="floor-ans-con l">
                                                    <span class="name">${reply.replyUserName}</span>：${reply.replyContent}
                                                    <span class="ans-time">${reply.replyTime}</span>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <span class="icon-feedback">
                                <a href="javascript:;" onclick="showDialog(${lou.index+1},${list.commentId})"> <i></i> 回复</a>
                            </span>
                            </div>
                        </div>
                    </li>
                </c:forEach>
                

        <!--发表评论-->
        <div class="detail-to-comment">
            <div class="tit"><a name="comment">发表评论</a></div>
            <!-- 未登录时候显示 <div class="con">您没有登录论坛，请登录后再进行回复</div>-->

            <!-- 登录后显示评论输入框-->
            <form action="${pageContext.request.contextPath}/article/comment.do" method="post">
                <input type="hidden" value="${article.articleId}" name="articleId">
                <div class="con con-loged">
                    <div class="con-t">
                        <input type="hidden" name="commentUserName" value="${user.userName}">
                        <textarea id="content" name="commentContent" placeholder="请在此输入您要回复的信息"></textarea>
                    </div>
                    <div class="con-b">
                        <input type="submit" class="btn"/>
                        <span class="num">不能超过5000字</span>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>



<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>



<!-- 回复弹出框 -->
<form action="${pageContext.request.contextPath}/article/reply.do?" method="post">
    <input type="hidden" value="${article.articleId}" name="articleId">
    <input type="hidden" value="${user.userName}" name="replyUserName">
    <input type="hidden" id="commentId" name="commentId"/>
    <div class="pop-box ft-box">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">回复<span id="floorSpan"></span>楼</h4>
                <span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_b">
                    <textarea id="replyContent" name="replyContent" placeholder="回复内容限于40字以内"></textarea>
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input type="submit" class="btn" value="回复"/>

                </div>
            </div>
        </div>
    </div>
</form>



<div class="fixedBar" id="j_fixedBar">
    <a href="#comment" class="newTopic"><span></span>回复</a>
    <a href="#" class="goTop"><i></i><span>返回<br/>顶部</span></a>
</div>


</body>

<script type="text/javascript">
//弹出回复框
function showDialog(num,commentId) {
	var loginUser = "${user}";
	if(!loginUser){
		alert("请登录");
		return;
	}
	$("#commentId").val(commentId);
    $('.pop-box').css('display', 'block');
    $("#floorSpan").html(num);
}
</script>
</html>