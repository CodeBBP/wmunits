<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
		<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
}

#l-map {
	height: 100%;
	width: 100%;
	float: left;
	border-right: 2px solid #bcbcbc;
}

#r-result {
	height: 100%;
	width: 0%;
	float: left;
}
</style>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=24E9478179c8068db731b15c1d7b06c2">
</script>
		<script src="${ctx}/styles/js/jquery-1.8.3.js" type="text/javascript">
</script>
		<title>六安市城乡环境治理</title>
	</head>
	<body onload="getBoundary('${xpoint}','${ypoint}');">
		<div id="l-map"></div>
	</body>
</html>
<script type="text/javascript">
// 百度地图API功能
var map = new BMap.Map("l-map", {
	minZoom : 6,
	maxZoom : 15
});
map.centerAndZoom("安徽省", 6);
map.enableScrollWheelZoom();
map.addEventListener("click", function(e) {
	//document.getElementById("r-result").innerHTML = e.point.lng + ", " + e.point.lat;
	window.parent.callbackPoint(e.point.lng,e.point.lat);
	getBoundary(e.point.lng, e.point.lat);
});

function getBoundary(xdian, ydian) {
	var bdary = new BMap.Boundary();
	bdary.get("六安", function(rs) { //获取行政区域
			map.clearOverlays(); //清除地图覆盖物       
			var count = rs.boundaries.length; //行政区域的点有多少个
			for ( var i = 0; i < count; i++) {
				var ply = new BMap.Polygon(rs.boundaries[i], {
					strokeWeight : 2,
					strokeColor : "#ff0000"
				}); //建立多边形覆盖物
				map.addOverlay(ply); //添加覆盖物
				map.setViewport(ply.getPath()); //调整视野         
			}
			var point = new BMap.Point(xdian, ydian);
			var marker = new BMap.Marker(point);
			map.addOverlay(marker);
		});
}

</script>
