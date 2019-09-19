<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../live2d/css/live2d.css" />
    <script>
        var message_Path = '/live2d/';
        var talkAPI = "";
    </script>
    <script type="text/javascript" src="../live2d/js/live2d.js"></script>
    <script type="text/javascript" src="../live2d/js/message.js"></script>
    <title>用户登录</title>
</head>
<body>

<div class="hm-top-nav">
    <div class="hm-inner clearfix">
        <div class="hm-inner-l l"></div>
        <div class="hm-inner-r r">
            <div class="box">
                <c:if test="${not empty user.userName && !(user.userName eq null) }">

                    欢迎<c:if test="${user.role == 1}">
                    普通用户:
                </c:if>
                    <c:if test="${user.role == 2}">
                        高级用户:
                    </c:if>
                    <c:if test="${user.role == 3}">
                        超级管理员:
                    </c:if>
                    ${user.userName}&nbsp;


                    <a href='${pageContext.request.contextPath}/user/findUserInfo.do'>个人中心</a>
                    <a href='#' id='userExist'>注销</a>
                </c:if>
                <c:if test="${empty user.userName || (user.userName eq null) }">
                    <a href="javascript:;" id="login" class="to-login">游客登录</a>

                    <a href="${pageContext.request.contextPath}/user/findRegister.do" id="regist">新用户注册</a>
                </c:if>


                <div id="dialogBg"></div>
                <div id="dialog" class="animated">
                    <img class="dialogIco" width="50" height="40" src="${pageContext.request.contextPath}/images/ico.png"/>
                    <div class="dialogTop" style="height:25px;">
                        <a href="javascript:;" class="closeDialogBtn">关闭</a>
                    </div>
                    <form action="" id="LoginForm" method="post">
                        <ul class="editInfos">
                            <span id="check_in"></span>
                            <li>用户名：<input type="text" id="userName" name="userName" class="ipt"/></li>
                            <li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="userPass" name="userPass" class="ipt"/></li>
                            <li><input type="button" id="login_btn" value="登录" class="submitBtn"/></li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="landlord" style="left:5px;bottom:0px;">
    <div class="message" style="opacity:0"></div>
    <canvas id="live2d" width="500" height="560" class="live2d"></canvas>
    <div class="live_talk_input_body">
        <div class="live_talk_input_name_body">
            <input name="name" type="text" class="live_talk_name white_input" id="AIuserName" autocomplete="off" placeholder="你的名字" />
        </div>
        <div class="live_talk_input_text_body">
            <input name="talk" type="text" class="live_talk_talk white_input" id="AIuserText" autocomplete="off" placeholder="要和我聊什么呀？"/>
            <button type="button" class="live_talk_send_btn" id="talk_send">发送</button>
        </div>
    </div>
    <input name="live_talk" id="live_talk" value="1" type="hidden" />
    <div class="live_ico_box">
        <div class="live_ico_item type_info" id="showInfoBtn"></div>
        <div class="live_ico_item type_talk" id="showTalkBtn"></div>
        <div class="live_ico_item type_music" id="musicButton"></div>
        <div class="live_ico_item type_youdu" id="youduButton"></div>
        <div class="live_ico_item type_quit" id="hideButton"></div>
        <input name="live_statu_val" id="live_statu_val" value="0" type="hidden" />
        <audio src="" style="display:none;" id="live2d_bgm" data-bgm="0" preload="none"></audio>
        <input name="live2dBGM" value="http://localhost:8081/live2d/1.m4a" type="hidden">
        <input id="duType" value="douqilai,l2d_caihong" type="hidden">
    </div>
</div>
<div id="open_live2d">召唤蕾姆</div>

</body>
<script type="text/javascript">
    $(function () {
        //显示弹框
        $('.box #login').click(function () {
            if(${sessionScope.userName == null}){
                var className = $(this).attr('class');
                $('#dialogBg').fadeIn(300);
                $('#dialog').removeAttr('class').addClass('animated ' + className + '').fadeIn();
                $('#userName').focus();
                $("#j_fixedBar").hide()
            }
        });

        //关闭弹窗
        $('.closeDialogBtn').click(function () {
            $('#dialogBg').fadeOut(300, function () {
                $('#dialog').addClass('bounceOutUp').fadeOut();
                $("#j_fixedBar").show();
            });
        });

        //用户名非空校验
        function checkUserName(){
            var userName_log = $("#userName").val();
            var userName_Reg = /^\s*$/;
            var boolean = userName_Reg.test(userName_log);
            if(boolean){
                return false;
            }else{
                return true;
            }
        }

        //密码非空校验
        function checkPassWord(){
            var userPass_log = $("#userPass").val();
            var userPass_Reg = /^\s*$/;
            var boolean = userPass_Reg.test(userPass_log);
            if(boolean){
                return false;
            }else{
                return true;
            }
        }

        $("#login_btn").click(function () {
            if(checkUserName() && checkPassWord()){
                //可以往后台发送登录请求;
                $.ajax({
                    type:"POST",
                    url:"${pageContext.request.contextPath}/user/userLogin.do",
                    data:{"userName":$("#userName").val(),"userPass":$("#userPass").val()},
                    dataType:"JSON",
                    success:function(data){
                        if(data){
                            location.reload();
                        }else{
                            alert("用户名或密码错误");
                        }
                    }
                })
            }else{
                //用户名或密码输入非法
                alert("请输入用户名和密码");
            }
        })
        $("#userExist").click(function () {
            var flag = confirm("退出成功,是否返回首页");

            if (${user != null}) {
                if (flag) {
                    $.post("${pageContext.request.contextPath}/user/userExist.do")
                    location.href = "${pageContext.request.contextPath}/index.jsp"
                } else {
                    $.post("${pageContext.request.contextPath}/user/userExist.do", function () {
                        location.reload();
                    })
                }
            } else {
                alert("登录超时")
                <%--location.href = "${pageContext.request.contextPath}/index.jsp"--%>
            }
        })
        //调用方法校验用户名及密码
        $("#userName").blur(checkUserName);
        $("#userPass").blur(checkPassWord);

    });
</script>

</html>