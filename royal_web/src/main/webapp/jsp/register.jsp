<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>王者荣耀论坛注册页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="/js/jquery.validate.js"></script>
    <script src="/js/messages_zh.js"></script>
</head>
<body>


<!-- 头部 -->
<jsp:include page="common/header.jsp" />



<div class="hm-header">
    <div class="hm-inner clearfix">
        <div class="hm-header-t clearfix">
            <h1 class="logo l">
                <a href="javascript:;"><img src="images/logo.png" height="64" width="168" alt=""/></a>
            </h1>
            <div class="search-box l">
                <form action="javascript:;">
                    <input type="text" class="txt l" placeholder="请输入关键字">
                    <input type="button" value="搜索" class="btn l"/>
                </form>
            </div>
        </div>
        <div class="hm-header-b">
            <i class="hm-ico-home"></i>
            <a href="index.do">首页</a><span>></span>注册页面
        </div>
    </div>
</div>


<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="reg-box">
            <h2>用户注册<span>（红色型号代表必填）</span></h2>
            <div class="reg-info">
                <form action="${pageContext.request.contextPath}/user/register.do" method="post" id="registerForm">
                    <ul>
                        <li>
                            <div class="reg-l">
                                <span class="red">*</span> 用户名：
                            </div>
                            <div class="reg-c">
                                <input id="username" name="userName" class="txt" value=""/></br>
                                <span id="checkoutUsername"></span>
                            </div>
                            <span class="tips">用户名必须是由英文、数字、下划线组成</span>
                        </li>
                        <li>
                            <div class="reg-l">
                                <span class="red">*</span> 密&nbsp;&nbsp;&nbsp;码：
                            </div>
                            <div class="reg-c">
                                <input type="password" id="password" name="userPass" class="txt" value=""/>
                                <span id="checkoutPasswrod"></span>
                            </div>
                            <span class="tips">密码长度必须6~10位的英文或数字</span>
                        </li>
                        <li class="no-tips">
                            <div class="reg-l">&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;箱：</div>
                            <div class="reg-c">
                                <input type="email" id="email" name="email" class="txt" value=""/>
                                <br/>
                                <span id="checkoutEmail"></span>
                            </div>
                        </li>
                        <li>
                            <div class="reg-l"></div>
                            <div class="reg-c">
                                <input type="button" class="submit-btn" value="注册" id="zhuche" onclick="submitForm()"/><br/>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>




<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>

<script>
   $(function () {
       $("#username").blur(function () {
           var username = $("#username").val();
           //用户名必须是由英文、数字、下划线组成
           var reg = /^[0-9a-zA-Z-_]*$/;
         if(username){
          var reslut = reg.test(username);
          if (!reslut){
                $("#checkoutUsername").html("用户名格式不正确")
          }else {
              $.post("${pageContext.request.contextPath}/user/checkOutUsername.do",{"username":username},function (data) {
                  if (data.result){
                      $("#checkoutUsername").html("用户名可使用")
                  } else if (!data.result) {
                      $("#checkoutUsername").html("用户名已被使用")
                  }
              },"json")

          }
         }else {
             $("#checkoutUsername").html("");
         }
       })
       $("#password").blur(function () {
          // 密码长度必须6~10位的英文或数字
           var password = $("#password").val();
           var reg = /^[a-zA-Z0-9_]{3,10}$/;
           if(password){
           var reslut = reg.test(password);
           if (!reslut){
               $("#checkoutPasswrod").html("密码格式不正确")
           }else {
               $("#checkoutPasswrod").html("密码正确")

           }
           }else {
               $("#checkoutPasswrod").html("")
           }

       })
       $("#email").blur(function () {
           var reg = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
           var email = $("#email").val();
           var reslut = reg.test(email);
           if (!reslut){
               $("#checkoutEmail").html("邮箱格式不正确")

           }
       })
   })
       function submitForm() {
           alert("11")
           var email = $("#email").val();
           var reg = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
           var emailStatus = reg.test(email);
           var reg = /^[0-9a-zA-Z-_]*$/;
           var username = $("#username").val();
           var usernameStatus = reg.test(username);
           var password = $("#password").val();
           var reg = /^[a-zA-Z0-9_]{3,10}$/;
           var passwordStatus = reg.test(password);
           if (passwordStatus && usernameStatus && emailStatus) {
               $("#registerForm").submit();
           } else {
               alert("请填写正确信息再进行提交")
           }
           /* if(checkEmail() && checkPassword() && checkUsername()){
                $("#registerForm").submit;
            }else {
                alert("请填写正确信息再进行提交")
            }
        })*/
       }

  /* function checkUsername(){
       var reg = /^[0-9a-zA-Z-_]*$/;
       var username = $("#username").val();
       var usernameStatus =  reg.test(username);
       alert(usernameStatus)
       if(usernameStatus){
           return true;
       }else {
           return false;
       }
   }
   function checkPassword(){
       var password = $("#password").val();
       var reg = /^[a-zA-Z0-9_]{3,10}$/;
       var passwordStatus =  reg.test(password);
       if(passwordStatus){
           return true;
       }else {
           return false;
       }
   }
   function checkEmail(){
       var email = $("#email").val();
       var reg = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
       var emailStatus = reg.test(email);
       if(passwordStatus){
           return true;
       }else {
           return false;
       }
   }*/
</script>

</body>
</html>