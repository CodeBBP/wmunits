<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>视频上传</title>
		<style type="text/css">
		div.zTreeBackground {
			width: 760px;
			height: 400px;
			text-align: left;
			border:solid 1px #CCC; 
			line-height:21px; 
			background:#fff;
			margin-left: 10px;
		}
		</style>
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/uploadify/uploadify.css" />
		<script src="${ctx}/styles/uploadify/jquery.uploadify.js"></script>
		<script src="${ctx}/styles/uploadify/jquery.uploadify.min.js"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(function() {
            $("#file_upload").uploadify({
            	'height'         : 25,   
                'width'          : 56,
                'swf'            : '${ctx}/styles/uploadify/uploadify.swf',  
                'uploader'       : '${ctx}/bsgj/fileupload',
                'cancelImg'      : '${ctx}/styles/uploadify/uploadify-cancel.png',
                'folder'         : '${ctx}/styles/attached',
                'queueID'        : 'fileQueue', 
                'queueSizeLimit' : 5,  
                'fileDesc'    	 : 'avi文件或rmvb文件',      
           		'fileExt' 		 : '*.avi;*.rmvb', 
                'auto'           : false,  
                'multi'          : true,  
                'simUploadLimit' : 3,  
                'buttonText'     : 'BROWSE'
            });  
        });
	</script>
	</head>
	<body style="padding-top: 5px">
		<input type="file" class="input_240" name="file_upload" id="file_upload" />
		<a href="javascript:jQuery('#file_upload').uploadify('upload')">开始上传</a>
		<a href="javascript:jQuery('#file_upload').uplaodify('cancel','*')">取消所有上传</a>
		<div id="fileQueue"></div>
	</body>
</html>
