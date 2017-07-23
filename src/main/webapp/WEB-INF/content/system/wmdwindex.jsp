<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文明单位管理系统</title>
<link href="${ctx }/styles/surface/css/accordion.css" rel="stylesheet"/>
<link href="${ctx }/styles/surface/css/blue.css" rel="stylesheet"/>
<script src="${ctx }/styles/surface/js/jquery-1.js"></script>
<script src="${ctx }/styles/surface/js/artDialog.js"></script>
<script src="${ctx }/styles/surface/js/iframeTools.js"></script>
<script src="${ctx }/styles/surface/js/FunctionJS.js"></script>
<script src="${ctx }/styles/surface/js/MainIndex.js"></script>
</head>
<body>
<form method="post" action="${ctx }/right" id="form1">
  <div style="display: none;" class="aspNetHidden">
    <input name="__VIEWSTATE" id="__VIEWSTATE" value="" type="hidden"/>
  </div>
  <div id="Container">
    <div id="Header">
      <div id="HeaderLogo"> <img src="/wmdw/styles/surface/images/product.png" style=""/> </div>
      <div id="Headermenu">
        <ul id="topnav">
          <li id="metnav_1" class="list"> <a href="javascript:;" id="nav_1" class="" onclick="Replace();"> <span class="c1"></span> 首页信息 </a> </li>
          <li id="metnav_1" class="list"> <a href="${ctx }/security/user/userupdate/${user.id}" target="tabs_iframe_Imain" id="nav_2" class="" onclick=""> <span class="c3"></span> 账户设置 </a> </li>
          <li id="metnav_5" class="list" onclick="window.parent.location.href='${ctx }/logout'"> <a href="javascript:;" id="nav_4"> <span class="c4"></span> 安全退出 </a> </li>
        </ul>
      </div>
    </div>
    <div id="Headerbotton">
      <div id="left_title"> <img src="/wmdw/styles/surface/images/clock_32.png" alt="" style="vertical-align: middle; padding-bottom: 3px;" height="20" width="20"/> <span id="datetime">2014年06月03日 11:41:23</span> </div>
      <div id="dww-menu" class="mod-tab">
        <div class="mod-hd" style="float: left">
          <ul class="tab-nav" id="tabs_container">
            <li id="tabs_Imain" class="selected" onclick="javascript:AddTabMenu('Imain','HomeIndex.html','首页主控台','4963_home.png','false')"><a><img src="/wmdw/styles/surface/images/4963_home.png" height="20" width="20"/>首页</a></li>
          </ul>
        </div>
        <div style="text-align: right; float: right"> <span class="south-separator"></span> 欢迎您：${user.dwmc } <span class="south-separator"></span></div>
        <div id="toolbar" style="float: right; width: 75px; padding-right: 5px; text-align: right;"> <img class="" src="/wmdw/styles/surface/images/arrow_refresh.png" title="刷新当前窗口" alt="" onclick="Loading(true);top.$('#' + Current_iframeID())[0].contentWindow.window.location.reload();return false;" style="padding-bottom: 3px; cursor: pointer; vertical-align: middle;" height="16" width="16"/> &nbsp; <img id="full_screen" src="/wmdw/styles/surface/images/arrow_out.gif" title="最大化" alt="" onclick="Maximize();" style="padding-bottom: 3px; cursor: pointer; vertical-align: middle;" height="16" width="16"/> &nbsp; </div>
      </div>
    </div>
    <div style="height: 820px;" id="MainContent">
      <div style="display: block;" class="navigation">
        <ul class="accordion">
          <li><a class="active"><img src="/wmdw/styles/surface/images/cog.png"/>文明创建</a>
            <div style="height: 657px; display: none; overflow: hidden;" class="sub-menu">
              <div class="" onclick="AddTabMenu('clgl', '/wmdw/dwcl/list', '材料管理', 'document_library.png', 'true');"><img src="/wmdw/styles/surface/images/document_library.png"/>材料管理</div>
              <div class="" onclick="AddTabMenu('dwzhcx', '/wmdw/dwcl', '综合查询', 'report_key.png', 'true');"><img src="/wmdw/styles/surface/images/report_key.png"/>综合查询</div>
              <div class="" onclick="AddTabMenu('dwtjbb', '/wmdw/dwcl/dwlm', '统计报表', '20130725112128475.png', 'true');"><img src="/wmdw/styles/surface/images/20130725112128475.png"/>统计报表</div>
            </div>
          </li>
          <li><a class=""><img src="/wmdw/styles/surface/images/424.png"/>三线三边</a>
            <div style="height: 657px; display: none; overflow: hidden;" class="sub-menu">
              <div class="" onclick="AddTabMenu('jksb', '/wmdw/cxhj/list', '境况上报', 'application_form.png', 'true');"><img src="/wmdw/styles/surface/images/application_form.png">境况上报</div>
              <div class="" onclick="AddTabMenu('hjzhcx', '/wmdw/cxhj', '综合查询', 'newspaper_go.png', 'true');"><img src="/wmdw/styles/surface/images/newspaper_go.png">综合查询</div>
              <%--<div onclick="AddTabMenu('b863d076-37bb-45aa-8318-37942026921e', '/CommonModule/Module/ModuleList.html', '模块管理', 'brick.png', 'true');"><img src="/wmdw/styles/surface/images/brick.png">模块管理</div>
              <div onclick="AddTabMenu('58e86c4c-8022-4d30-95d5-b3d0eedcc878', '/CommonModule/DataBase/DataBaseIndex.html', '数据资源库', 'database_gear.png', 'true');"><img src="/wmdw/styles/surface/images/database_gear.png">数据资源库</div>
              <div class="" onclick="AddTabMenu('5d896550-fde2-47fe-bb72-95cd5d3a2edb', '/CommonModule/DataBase/DataBaseManag.html', '数据库管理', 'database_lightning.png', 'true');"><img src="/wmdw/styles/surface/images/database_lightning.png">数据库管理</div>
              <div onclick="AddTabMenu('bd959bfa-797c-48ff-b72b-241c84cd03a8', '/CommonModule/InterfaceManage/InterfaceManageList.html', '接口管理', 'disconnect.png', 'true');"><img src="/wmdw/styles/surface/images/disconnect.png">接口管理</div>--%>
            </div>
          </li>
          <li><a class=""><img src="/wmdw/styles/surface/images/report_key.png"/>业务工作</a>
            <div style="height: 657px; display: none; overflow: hidden;" class="sub-menu">
              <div class="" onclick="AddTabMenu('sbdhr', '${ctx }/ywgz/list?sblx=1', '身边的好人', '4957_customers.png', 'true');"><img src="/wmdw/styles/surface/images/4957_customers.png">身边的好人</div>
              <div class="" onclick="AddTabMenu('xcsng', '${ctx }/ywgz/list?sblx=2', '乡村少年宫', 'button.png', 'true');"><img src="/wmdw/styles/surface/images/button.png">乡村少年宫</div>
              <div onclick="AddTabMenu('xlfhd', '${ctx }/ywgz/list?sblx=3', '学雷锋活动', '20131012030416621.png', 'true');"><img src="/wmdw/styles/surface/images/20131012030416621.png">学雷锋活动</div>
            </div>
          </li>          
        </ul>
      </div>
      <div id="ContentPannel">
        <iframe style="display: block;" id="tabs_iframe_Imain" name="tabs_iframe_Imain" src="${ctx }/right" frameborder="0" height="100%" width="100%"></iframe>
      </div>
    </div>
    <div id="botton_toolbar">
      <div style="width: 42%; text-align: left; float: left;"> &nbsp;</div>
      <div style="width: 16%; text-align: left; float: left;"> <a title="" href="javascript:void()">CopyRight © 安徽航天信息有限公司</a> </div>
    </div>
  </div>
  <!--载进度条start-->
  <div style="display: none;" id="loading" onclick="Loading(false);"> <img src="/wmdw/styles/surface/images/loading.gif" style="padding-bottom: 4px; vertical-align: middle;"/>&nbsp;正在处理，请稍待&nbsp; </div>
  <div id="Toploading"> </div>
  <!--载进度条end-->
</form>
<div style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: none repeat scroll 0% 0% rgb(255, 255, 255);"></div>
</body>
<script>
     $(document).ready(function () {
         document.onselectstart = function () { return false; }
         Loading(true);
         iframeresize();
         
         AddTabMenu('Imain', 'HomeIndex.html', '首页主控台', '4963_home.png', 'false');
         GetAccordionMenu();
         resizeLayout();
         writeDateInfo();
         readyIndex();
     });
     function resizeLayout() {
         resizeU();
         $(window).resize(resizeU);
         function resizeU() {
             var accordion_head = $('.accordion > li > a'),
              accordion_body = $('.accordion li > .sub-menu');
             $(".sub-menu").css('height', $(".navigation").height() - 19 - accordion_head.length * accordion_head.height() - accordion_head.length + 'px');
             accordion_head.first().addClass('active').next().slideDown('normal');
             accordion_head.on('click', function (event) {
                 event.preventDefault();
                 if ($(this).attr('class') != 'active') {
                     accordion_body.slideUp('normal');
                     $(this).next().stop(true, true).slideToggle('normal');
                     accordion_head.removeClass('active');
                     $(this).addClass('active');
                 }
             });
         }
     }
     //手风琴导航菜单
     var AccordionMenuJson = "";
     function GetAccordionMenu() {
         var index = 0;
         var html = "";
         getAjax("Frame.ashx", "action=LoadFirstMenu", function (data) {
             AccordionMenuJson = eval("(" + data + ")");
             $.each(AccordionMenuJson, function (i) {
                 if (AccordionMenuJson[i].ParentId == '9f8ce93a-fc2d-4914-a59c-a6b49494108f') {
                     if (index == 0) {
                         html += "<li><a style=\"border-top: 0px solid #ccc;\"><img src=\"/Themes/Images/32/" + AccordionMenuJson[i].Img + "\">" + AccordionMenuJson[i].FullName + "</a>";
                     } else {
                         html += "<li><a><img src=\"/Themes/Images/32/" + AccordionMenuJson[i].Img + "\">" + AccordionMenuJson[i].FullName + "</a>";
                     }
                     html += GetSubmenu(AccordionMenuJson[i].MenuId);
                     html += "</li>";
                     index++;
                 }
             });
         })
         $(".accordion").append(html);
     }
     //子菜单
     function GetSubmenu(MenuId) {
         var html = "";
         html += "<div class=\"sub-menu\">";
         $.each(AccordionMenuJson, function (i) {
             if (AccordionMenuJson[i].ParentId == MenuId) {
                 html += "<div onclick=\"AddTabMenu('" + AccordionMenuJson[i].MenuId + "', '" + AccordionMenuJson[i].NavigateUrl + "', '" + AccordionMenuJson[i].FullName + "', '" + AccordionMenuJson[i].Img + "', 'true');\" ><img src=\"/Themes/Images/32/" + AccordionMenuJson[i].Img + "\">" + AccordionMenuJson[i].FullName + "</div>";
             }
         });
         html += "</div>";
         return html;
     }
     //控制面板
     function Controlpanel() {
         AddTabMenu('Controlpanel', '/CommonModule/Controlpanel/ControlpanelIndex.html', '控制面板', '5026_settings.png', 'true');
     }
</script>
</html>