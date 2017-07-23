<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <meta name="title" content="六安市精神文明建设管理信息平台"/>
    <meta name="keywords" content="六安市精神文明建设管理信息平台"/>
    <meta name="description" content="六安市精神文明建设管理信息平台"/>
    <title>六安文明办</title>
    <link href="${ctx}/styles/css/assets/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/styles/css/assets/app-1.4.3.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/styles/css/assets/common-1.4.3.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/styles/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${ctx}/styles/js/lhbz.js"></script>
    <script src="${ctx}/styles/js/common-1.4.3.js"></script>
    <script>require("common/init")</script>
    <script type="text/javascript" src="${ctx}/styles/js/jquery.DB_tabMotionBanner.min.js"></script>

    <script type="text/javascript">
        var count = 3;
        var jint;
        $(function () {
            $(document).keydown(function (event) {
                if (event.keyCode == 13) {
                    submitForm();
                }
            });
        });
        function submitForm() {
            if ($.trim($("#username").val()) == "") {
                $("#ListMsg").html("请输入账号");
                $("#username").focus();
                return false;
            }
            if ($.trim($("#password").val()) == "") {
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
        /*
         function sendmessage(){
            var mob = $("#mobile").val();
            document.getElementById('hqyzm').onclick = "";
        	$.ajax({
    			type:'POST',
    			url:'${ctx}/sendmsg',
    			data:'mobile='+mob,
    			async: false,
    			error: function(){
    				var failMsg = '数据通信错误！请稍后再试';
    				alert(failMsg);
    				return false;
    			},
    			success: function(data){
        			var recode = data.retCode;
    				if(recode != "0"){
    					alert("短信验证码已发送！"+recode);
    				}else{
    					alert('对不起，数据处理错误，短信发送失败！');
    					return false;
    				}
    			}
    		});
        	$("#waitMessage").html(count + "秒后获取");
        	jint = window.setInterval("timeout()",1000);
        }
         function timeout(){
			if(count > 0){
				count--;
				$("#waitMessage").html(count + "秒后再获取");
			}else{
				count = 3;
				jint = window.clearInterval(jint);
				$("#waitMessage").html("");
				//document.getElementById('hqyzm').onclick = "sendmessage();";
				$("#hqyzm").attr("onclick","sendmessage();");
			}
        }*/
    </script>
</head>
<body>
<div id="divBody">
    <div class="index-header js-comp" data-comp-path="official/bestpay/index-header">
        <div class="topbar">
            <div class="ui-topbarmain">
    <span class="ui-topleftlink">
		您好，欢迎来到六安市精神文明建设管理信息平台！
    </span>
                <div class="ui-toprightinfo">
                    <a title="首页" href="/index">首页</a>
                    <font>|</font>
                    <div class="ui-helpselect">
                        帮助中心 <i></i>
                        <div class="ui-topbar-selectdiv">
                            <ul>
                                <li>
                                    <a href="/api/help/index" target="_blank">常见问题</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="divheaderbg">
            <div class="divheader">
                <a href="#" class="ui-headerlogo"></a>
            </div>
        </div>
    </div>

    <div class="index-main">
        <div class="index-main-AdRotation js-comp" data-comp-path="official/bestpay/index-main/AdRotation">

            <div class="divheaderShadow"></div>
            <div id="full-screen-slider">
                <ul id="slides">

                    <li style="background:url(${ctx}/styles/images/img_login/bg_01.png) no-repeat center top">

                    </li>

                    <li style="background:url(${ctx}/styles/images/img_login/bg_02.jpg) no-repeat center top">

                    </li>

                    <li style="background:url(${ctx}/styles/images/img_login/bg_03.jpg) no-repeat center top">

                    </li>

                </ul>
            </div>
        </div>
        <div class="index-main-indexLogin js-comp" data-comp-path="official/bestpay/index-main/indexLogin">
            <form id="loginForm" action="${ctx }/login" method="post">
                <div class="index-loginbg">
                    <div class="index-login">
                        <div id="loginDIvBg" class="ui-logindivbg"
                             style="background-image:url(${ctx}/styles/assets/bestpay/inside-main/loading.gif)">
                            <input id="userType" type="hidden" value="">
                            <div id="loginTitle" class="ui-logindiv-title" style="display:none">
                                <ul>
                                    <li class="logindiv-sel title-left" name="Account">
                                        <span>用户登录</span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red" size="2px"
                                                                                       id='ListMsg'>${error}</font>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div id="loginDiv" class="ui-logindiv" style="display:none">
                            <div class="ui-logindiv-main mob-login" id="Account">
                                <ul id="Teltrue">
                                    <form id="telLoginForm" name="telLoginForm" method="post">
                                        <li class="ui-login-txtli">
                                            <span>用户名:</span>
                                        </li>
                                        <li>
                                            <input name="username" id="username" type="text" maxlength="11"
                                                   class="ui-txtinput01" style="width:220px;" value=""/>
                                        </li>
                                        <li class="ui-login-txtli">
                                            <span>密码:</span>
                                        </li>
                                        <li>
                                            <input name="password" id="password" type="password"
                                                   class="ui-txtinput01" style="width:220px;" value=""/>
                                        </li>
                                        <li class="ui-login-txtli">
                                            <span>验证码:</span>
                                        </li>
                                        <li>
                                            <input type="text" style="width:130px" name="vcode" id="txtCheckCode"
                                                   value="" onfocus="$(this).prev('span').hide()"
                                                   onBlur="if(!value){$(this).prev('span').show()}"/>
                                            <a href="JavaScript:reloadVCode('vcodeImg', '${ctx }');" title="看不清楚？点击换一张"
                                               style="width: 101px; height:35px;">
                                                <img id="vcodeImg" name="vcodeImg" alt="" align="right" width="101"
                                                     height="35" src="${ctx}/validate"/></a>
                                        </li>
                                        <li>
                                            <a class="ui-button ui-button-morange ui-loginbutton"
                                               id="telLoginButtn" href="javascript:void(0);" onclick="submitForm()">登&nbsp;&nbsp;录</a>
                                        </li>
                                    </form>
                                </ul>
                                <ul id="Telfalse" style="display:none">
                                    <form id="telFalseForm" name="telFalseForm" method="post">
                                        <li class="ui-login-txtli">
                                            <span>手机号码:</span>
                                        </li>
                                        <li>
                                            <input name="productNo" id="otherPhone" type="text" maxlength="11"
                                                   class="ui-txtinput01" style="width:220px;" value="请输入手机号(非电信)"/>
                                        </li>
                                        <li class="ui-login-txtli">
                                            <span>密码:</span>
                                            <a href="/forget/forpwd" class="ui-forget-a">忘记密码</a>
                                        </li>
                                        <li>
                                            <div class="js-security-input"></div>
                                        </li>
                                        <li id="othercode">
                                            <input name="vcode" id="othercodeVcode" type="text" maxlength="4"
                                                   class="ui-txtinput01" style=" width:60px;" value="验证码"/>
							<span name="codeflagSpan" id="othercodeFlagSpan" class="ui-Securityimage"
                                  style="display:none">
								<img id="othercodeFlag" src="/assets/bestpay/er.png">
								<input id="othercodeCheckFlag" name="codeCheckFlag" type="hidden" value="false">
							</span>
							<span name="codeImgSpan" class="ui-Verification">
								<img name="codeImg" id="othercodeImg" title="点击更换"
                                     src="" style="display:none">
							</span>
							<span class="ui-Verificationtxt">
								<a name="flushCode">点击刷新</a>
							</span>
                                        </li>
                                        <li>
                                            <a class="ui-button ui-button-morange ui-loginbutton"
                                               id="otherLoginButtn" href="javascript:void(0);">登&nbsp;&nbsp;录</a>
                                        </li>
                                    </form>
                                </ul>
                            </div>
                            <div class="ui-logindiv-main" id="card" style="display:none">

                                <ul>
                                    <form id="cardForm" name="cardForm" method="post">
                                        <li class="ui-login-txtli">
                                            <span>卡号:</span>
                                        </li>
                                        <li>
                                            <input name="productNo" id="cardNo" type="text" class="ui-txtinput01"
                                                   style="width:220px;" maxlength="30" value="请输入卡号"/>
                                        </li>
                                        <li class="ui-login-txtli">
                                            <span>密码:</span>
                                        </li>
                                        <li>
                                            <div class="js-security-input1" id="security1"></div>
                                        </li>
                                        <li id="cardcode">
                                            <input name="vcode" id="cardcodeVcode" type="text" class="ui-txtinput01"
                                                   style=" width:90px;" value="验证码" maxlength="4"/>
							<span name="codeflagSpan" id="cardcodeFlagSpan" class="ui-Securityimage"
                                  style="display:none">
								<img id="cardcodeFlag" src="/assets/bestpay/er.png">
								<input id="cardcodeCheckFlag" name="codeCheckFlag"
                                       type="hidden" value="false">
							</span>
							<span name="codeImgSpan" class="ui-Verification">
								<img name="codeImg" id="cardcodeImg" title="点击更换"
                                     src="" style="display:none">
							</span>
							<span class="ui-Verificationtxt">
								<a name="flushCode">点击刷新</a>
							</span>
                                        </li>
                                        <li>
                                            <a class="ui-button ui-button-morange ui-loginbutton"
                                               id="cardLoginButtn" href="javascript:void(0);">登&nbsp;&nbsp;录</a>
                                        </li>
                                    </form>
                                </ul>
                                <div class="ui-logindiv-bottom">
                                    <a href="/appcenter/mall/list" class="ui-button ui-button-mwhite">购买翼充卡</a>
                                    <a href="/wingcard/home" class="ui-txt-a">卡片欣赏</a>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="index-main-AppNews js-comp" data-comp-path="official/bestpay/index-main/index-AppNews">

            <div class="indexmain-AppNews">
                <div class="indexmain-App">

                    <div class="ui-Appmain">
                        <span class="ui-appmaintitleimg"></span>
		<span class="ui-Apptitlelink">
			<a class="ui-txt-a" href="">更多>></a>
		</span>
                        <ui>
                            <li>
                                <a href="#">
                                    <span class="ui-appicon53 ui-mobicon53"></span>
                                    <span class="index-Appicontxt">单位材料上报</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="ui-appicon53 ui-newnumbericon53"></span>
                                    <span class="index-Appicontxt">单位材料统计</span>
                                </a>
                            </li>
                            <li>
                                <a href="${ctx}/wszt.html">
                                    <span class="ui-appicon53 ui-Watericon53"></span>
                                    <span class="index-Appicontxt">网上展馆</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="ui-appicon53 ui-Trafficicon53"></span>
                                    <span class="index-Appicontxt">稿件编辑上报</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="ui-appicon53 ui-insupericon53"></span>
                                    <span class="index-Appicontxt">宣传稿件统计</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="ui-appicon53 ui-Gasicon53"></span>
                                    <span class="index-Appicontxt">稿件查询管理</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="ui-appicon53 ui-TVicon53"></span>
                                    <span class="index-Appicontxt">城乡环境管理</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="ui-appicon53 ui-telephoneicon53"></span>
                                    <span class="index-Appicontxt">待治理任务</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="ui-appicon53 ui-11888cardicon53"></span>
                                    <span class="index-Appicontxt">环境地图总览</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="ui-appicon53 ui-3gicon53"></span>
                                    <span class="index-Appicontxt">环境统计</span>
                                </a>
                            </li>
                        </ui>
                    </div>
                </div>
                <div class="indexmain-new">
                    <div class="news-title">
                        <ul>
                            <li class="ui-titel-sel" name="LatestNews">
                                <a class="ui-indexspanimg LatestNews" href="" target="_blank"></a>
                            </li>
                            <li name="News">
                                <a class="ui-indexspanimg News" href="" target="_blank"></a>
                            </li>
                        </ul>
                    </div>
                    <div class="news-main" id="LatestNews">
                        <ul>

                            <li>
                                <span></span>
                                <a target="_blank" href="#">抓好三项建设，构成文明创建工作的保障力</a>
                            </li>

                            <li>
                                <span></span>
                                <a target="_blank" href="#">加强六项工作，形成文明创建工作推动力</a>
                            </li>

                            <li>
                                <span></span>
                                <a target="_blank" href="#">开展三项活动"，形成文明创建工作促进力</a>
                            </li>

                            <li>
                                <span></span>
                                <a target="_blank" href="#">深化教育，培养良好道德风尚</a>
                            </li>

                            <li>
                                <span></span>
                                <a target="_blank" href="#">强化管理，树立文明机关形象</a>
                            </li>

                        </ul>
                        <a class="ui-txt-a" title="更多" href="#" target="_blank">更多>></a>
                    </div>
                    <div class="news-main" id="News" style="display:none">
                        <ul>

                            <li>
                                <span></span>
                                <a target="_blank" href="#">注重学习，营造学习进取氛围</a>
                            </li>

                            <li>
                                <span></span>
                                <a target="_blank" href="#">开拓创新，创造一流工作业绩</a>
                            </li>

                            <li>
                                <span></span>
                                <a target="_blank" href="#">加强组织领导，创建工作扎实有效</a>
                            </li>

                            <li>
                                <span></span>
                                <a target="_blank" href="#">落实科学发展观，走进中小学搞调研</a>
                            </li>

                            <li>
                                <span></span>
                                <a target="_blank" href="#">促进各中小学均衡、快速发展</a>
                            </li>

                        </ul>
                        <a class="ui-txt-a" title="更多" href="#" target="_blank">更多>></a>
                    </div>
                    <div class="news-main" id="ConvenienceServices" style="display:none">
                        <ul>
                            <li>
                                <span></span>
                                <font>【生活缴费服务】</font><a href="">缴水费 </a><a href="#">缴电费</a>
                            </li>
                            <li>
                                <span></span>
                                <font>【电信产品服务】</font><a href="">手机充值</a>
                            </li>
                            <li>
                                <span></span>
                                <font>【虚拟物品服务】</font><a href="">电信充值卡</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <script type="text/javascript">
        $('.DB_tab25').DB_tabMotionBanner({
            key: 'b28551',
            autoRollingTime: 10000,
            bgSpeed: 500,
            motion: {
                DB_1_1: {left: -50, opacity: 0, speed: 1000, delay: 500},

                DB_2_1: {top: 50, opacity: 0, speed: 1000, delay: 500},

                DB_3_1: {left: -50, opacity: 0, speed: 1000, delay: 500},

                end: null
            }
        });
        //$(function(){
        //	for( i=1; i<5; i++){
        //		$("#tab"+i).bind('mouseover', function() {
        //			for (n = 1; n < 5; n++) {
        //                if (i == n) {
        //                	$("#tab"+n).addClass("current");
        //                	$("#con"+n).addClass("ul.current");
        //                } else {
        //                	$("#tab"+n).addClass("current");
        //                	$("#con"+n).removeClass("ul.current");
        //                }
        //            }
        //		  });
        //	}
        //});
        function switchTab(ProTag) {
            for (i = 1; i < 5; i++) {
                if (i == ProTag) {
                    $("#tab" + i).removeClass("current");
                    $("#tab" + i).addClass("current");
                    $("#con" + i).removeClass("current");
                    $("#con" + i).addClass("current");
                } else {
                    $("#tab" + i).removeClass("current");
                    $("#con" + i).removeClass("current");
                }
            }
        }
        function CNNIC_change(eleId) {
            var str = document.getElementById(eleId).href;
            var str1 = str.substring(0, (str.length - 6));
            str1 += CNNIC_RndNum(6);
            document.getElementById(eleId).href = str1;
        }
        function CNNIC_RndNum(k) {
            var rnd = "";
            for (var i = 0; i < k; i++)
                rnd += Math.floor(Math.random() * 10);
            return rnd;
        }
    </script>
    <div id="footer">
        <div class="footerbg">
            <div class="footer-main">
                <div class="footer-link">
                    <div class="elselink">
                        <div class="elsebotlink">
                            <div class="txt01">版权所有 &copy; 安徽航天信息有限公司</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>