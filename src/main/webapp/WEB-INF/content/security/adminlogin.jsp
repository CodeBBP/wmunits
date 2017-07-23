<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <%@ include file="/common/meta.jsp"%>
	<title>六安文明办</title>
    <link href="${ctx}/styles/css/login1.css" rel="stylesheet" type="text/css" />
    <script language="javascript" type="text/javascript" src="${ctx}/styles/js/jquery-1.8.3.js"></script>
    <script language="javascript" type="text/javascript" src="${ctx}/styles/js/lhbz.js"></script>
    <script language="javascript" type="text/javascript">
        $(document).ready(function() {
            $(document).keydown(function(event){
                if(event.keyCode==13)
                {
                    submitForm();
                }
            });
            $(".lo_list_text span").click(function(){
                $(this).hide();
                $(this).next('input').focus();
            })
            if($("#username").val()!="")
            {
                $("#username").prev("span").hide();
            }
            if($("#password").val()!="")
            {
                $("#password").prev("span").hide();
            }
        });
        function submitForm(){
            if ($.trim($("#username").val()) == "" || $.trim($("#username").val()) == "账户" ) {
                $("#ListMsg").html("请输入账户");
                $("#username").focus();
                return false;
            }
            if ($.trim($("#password").val()) == "" || $.trim($("#password").val()) == "不少于六位密码") {
                $("#ListMsg").html("请输入密码");
                $("#password").focus();
                return false;
            }
            if ($.trim($("#txtCheckCode").val()) == "") {
                $("#ListMsg").html("请输入验证码");
                $("#txtCheckCode").focus();
                return false;
            }
            $('#loginForm').submit();
        }
    </script>
    </head>
    <body>
    <form id="loginForm" action="${ctx }/adminlogin" method="post">
      <div class="logo wap"><a href="#" title="六安市文明单位管理系统" target="_self"><img src="${ctx}/styles/images/sublogo.png" width="448" height="65" alt="六安市文明单位管理系统"></a></div>
      <div class="lo_ct">
        <div class="lo_bg wap">
          <ul class="lo_list">
            <li class="lo_list_text"><h2>用户登录&nbsp;&nbsp;&nbsp;&nbsp;<font color="red" size="2px" id='ListMsg'>${error}</font></h2></li>
            <li class="lo_list_text"><span>账户</span>
              <input type="text" class="us_name" name="username" id="username" value="${username}" onfocus="$(this).prev('span').hide()" onBlur="if(!value){$(this).prev('span').show()}" />
            </li>
            <li class="lo_list_text"><span>密码</span>
              <input type="password" class="us_pwd" name="password" id="password" value="" onfocus="$(this).prev('span').hide()" onBlur="if(!value){$(this).prev('span').show()}"  />
            </li>
            <li class="lo_list_text"><span>验证码</span>
              <input type="text" class="us_spot" name="vcode" id="txtCheckCode" value="" onfocus="$(this).prev('span').hide()" onBlur="if(!value){$(this).prev('span').show()}"  />
              <a href="JavaScript:reloadVCode('vcodeImg', '${ctx }');" title="看不清楚？点击换一张" style="width: 101px; height:35px;" >
              <img id="vcodeImg" name="vcodeImg" alt="" align="right" width="101" height="35" src="${ctx}/validate"/></a>
            </li>
            <li> <a class="lo_bnt lt" href="JavaScript:void(0);" onclick="submitForm();" title="登录">登录</a></li>
            <li class="lo_copt">&nbsp; </li>
          </ul>
        </div>
      </div>
      <p class="lo_bt">版权所有：安徽航天信息有限公司 </p>
    </form>
</body>
</html>