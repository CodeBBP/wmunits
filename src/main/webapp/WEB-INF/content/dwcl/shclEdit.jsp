<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>审核稿件</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" href="${ctx}/styles/js/richtext/jquery.wysiwyg.css" type="text/css" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/js/richtext/jquery.wysiwyg.js"></script>	
	</head>

	<body>
		<form id="inputForm" action="${ctx }/bsgj/update" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="shzt" id="shzt" value="N"/>
			<table width="100%" border="0" align="center" cellpadding="0"
					class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
				<tr>
					<td class="td_table_top" align="center">
						审核稿件
					</td>
				</tr>
			</table>
			<table class="table_all" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>标题：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="title" name="title"
							value="${bsgj.title }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>创建时间：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="createdate" name="createdate"
							value="${bsgj.createdate }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>提交单位：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="author" name="author"
							value="${bsgj.author }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>所属类型：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<select id="category" name="category" class="input_240"/>
			    			<option value="">------请选择------</option>
			    			<c:forEach var="item" items="${khcs}" varStatus="s">
			    				<option value="${item.para }" <c:if test="${item.para == bsgj.category}">selected</c:if> >${item.para }</option>
			    			</c:forEach>
			    		</select>
					</td>
				</tr>
			</table>
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1" style="text-align:center">
						<span>编辑内容：</span>
					</td>
				</tr>
				<tr>
					<td class="td_table_2">
						<textarea name="content" id="content" style="width:100%;height:300px;">${bsgj.content }</textarea>
					</td>
				</tr>
			</table>
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1" style="text-align:center">
						<input name="shzt" type="radio" value="${bsgj.shzt == '0' ? '1' : user.sex }" class="input_radio"/>
					</td>
				</tr>
			</table>
			
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<input type="submit" class="button_70px" name="submit" onclick="check()" value="提交">
						&nbsp;&nbsp;
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
					</td>
				</tr>
			</table>
			
			<script type="text/javascript">
			function check(){
				var title = $("#title").val();
				var createdate = $("#createdate").val();
				var author = $("#author").val();
				var content = $("#content").val();
				if(trim(title) == ''){
					alert("标题不能为空");
					$("#title").focus();
					return false;
				}
				if(trim(createdate) == ''){
					alert("创建时间不能为空");
					$("#createdate").focus();
					return false;
				}	
				if(trim(author) == ''){
					alert("提交单位不能为空");
					$("#author").focus();
					return false;
				}
				if(trim(content) == ''){
					alert("提交内容不能为空");
					$("#content").focus();
					return false;
				}
				return true;
			}
			(function($)
					{
					  $('#content').wysiwyg({
					    controls: {
					      strikeThrough : { visible : true },
					      underline     : { visible : true },
					      
					      separator00 : { visible : true },
					      
					      justifyLeft   : { visible : true },
					      justifyCenter : { visible : true },
					      justifyRight  : { visible : true },
					      justifyFull   : { visible : true },
					      
					      separator01 : { visible : true },
					      
					      indent  : { visible : true },
					      outdent : { visible : true },
					      
					      separator02 : { visible : true },
					      
					      subscript   : { visible : true },
					      superscript : { visible : true },
					      
					      separator03 : { visible : true },
					      
					      undo : { visible : true },
					      redo : { visible : true },
					      
					      separator04 : { visible : true },
					      
					      insertOrderedList    : { visible : true },
					      insertUnorderedList  : { visible : true },
					      insertHorizontalRule : { visible : true },
					      
					      h4mozilla : { visible : true && $.browser.mozilla, className : 'h4', command : 'heading', arguments : ['h4'], tags : ['h4'], tooltip : "Header 4" },
					      h5mozilla : { visible : true && $.browser.mozilla, className : 'h5', command : 'heading', arguments : ['h5'], tags : ['h5'], tooltip : "Header 5" },
					      h6mozilla : { visible : true && $.browser.mozilla, className : 'h6', command : 'heading', arguments : ['h6'], tags : ['h6'], tooltip : "Header 6" },
					      
					      h4 : { visible : true && !( $.browser.mozilla ), className : 'h4', command : 'formatBlock', arguments : ['<H4>'], tags : ['h4'], tooltip : "Header 4" },
					      h5 : { visible : true && !( $.browser.mozilla ), className : 'h5', command : 'formatBlock', arguments : ['<H5>'], tags : ['h5'], tooltip : "Header 5" },
					      h6 : { visible : true && !( $.browser.mozilla ), className : 'h6', command : 'formatBlock', arguments : ['<H6>'], tags : ['h6'], tooltip : "Header 6" },
					      
					      separator07 : { visible : true },
					      
					      cut   : { visible : true },
					      copy  : { visible : true },
					      paste : { visible : true }
					    }
					  });
					})(jQuery);
			</script>
		</form>
	</body>
</html>