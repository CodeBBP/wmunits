<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<title>Left</title>
<%@ include file="/common/meta.jsp"%>
<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/styles/css/dtree.css" type="text/css">
<script type="text/javascript" src="${ctx}/styles/js/dtree.js"></script>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.sv3 dl, .sv3 dt, .sv3 dd {
	padding: 0;
	margin: 0;
}
.sv3 {
	width: 240px;/*	border: 1px solid #BFC7D9;*/
}
.sv3 dl {
	width: 240px;
	height: 300px;
	background: #e5f4fd;
	overflow: hidden;
}
.sv3 dt {
	/*padding: 5px 10px;*/
	padding: 5px 10px 5px 42px;
	height: 13px;
	font-size: 13px;
	color: #FFF;
	/*	color: #000;
	background: #E5ECF9;
	border-top: 1px solid #fff;
	border-bottom: 1px solid #BFC7D9;*/
	background: url(${ctx}/styles/images/img_main/main_43.gif);
	text-align: left;
}
.sv3 dl.on dt {
	/*background: #3366CC;*/
	background: url(${ctx}/styles/images/img_main/main_43.gif);
	color: #FFF;
	/*	font-weight: bold;*/
	text-align: left;
}
.sv3 dd {
	/*padding: 10px;*/
	padding: 10px 8px 10px 9px;
	color: #1f5078;
	font-size: 12px;
	line-height: 2em;
	text-align: left;
}
.sv3 dd a:link, .sv3 dd a:visited, .sv3 dd a:hover, .sv3 dd a:active {
	color: #1f5078;
	text-align: left;
}

.sv3 dl.on dd ul {
	height: 286px;
	overflow: hidden;
	border: 0px solid #021255
}

.sv3 ul {
	margin: 0px;
	padding: 0px;
	list-style: none;
}
.sv3 .on a {
	color: White
}
.sv3 a {
	color: Black;
	text-decoration: none
}
.sv3 dd a:hover {
	color: #1088ba;
	text-decoration: underline
}
.sv3 ul li {
	width: 98%;
}
</style>
</head>


<body>
<table width="171" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0"  style="table-layout:fixed;">
        <tr>
          <td style="width:3px; background:#0a5c8e;">&nbsp;</td>
          <td><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
              <tr>
                <td height="5" style="line-height:5px; background:#0a5c8e;">&nbsp;</td>
              </tr>
              <tr>
                <td height="23" background="${ctx}/styles/images/img_main/main_29.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="30%">&nbsp;</td>
                      <td width="52%"><font style="height:1;font-size:9pt; color:#FFF;filter:glow(color=#1070a3,strength=1)"></font></td>
                      <td width="18%">&nbsp;</td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td bgcolor="#e5f4fd"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td valign="top"><div align="center">
                      <frame:menu/>
<script type="text/javascript">
	function SlideView(e) {
		for (var r = document.getElementById(e).getElementsByTagName('dl'), c = clearInterval, i = -1, p = r[0], j; j = r[++i]; ) {
			j.style.height = (i ? 24 : 317) + 'px';
			j.onclick = function () { clearTimeout(j); var i = this; j = setTimeout(function () { if (p != i) _(p, 379, 24)(p = i, 24, 317) }, 200) };
		}
		function _(el, f, t) {
			c(el.$1); el.className = f > t ? '' : 'on';
			return el.$1 = setInterval(function () { el.style.height = (f += Math[f > t ? 'floor' : 'ceil']((t - f) * .3)) + 'px'; if (f == t) c(el.$1) }, 10), _
		}
	};
	new SlideView("idSlideView3");
</script> 
                        </div></td>
                    </tr>
                    <tr>
                      <td height="50"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td height="25"></td>
                          </tr>
                          <tr>
                            <td height="25"></td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td height="23" background="${ctx}/styles/images/img_main/main_45.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="18%">&nbsp;</td>
                      <td width="64%"><div align="center"><font style="height:1;font-size:9pt; color:#bfdbeb;filter:glow(color=#1070a3,strength=1)">版本2014 V1.0 </font></div></td>
                      <td width="18%">&nbsp;</td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td height="9" style="line-height:9px; background:#0a5c8e;">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>