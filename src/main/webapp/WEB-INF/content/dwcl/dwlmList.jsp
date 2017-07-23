<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>单位栏目</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/wbox/jquery1.4.2.js" type="text/javascript"></script>
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
		<script src="${ctx}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript">
			function change(obj){
				var targetValue = obj.value;
				var targetText = $(obj).find('option:selected').text();
				if(targetValue != ""){
					$.ajax({
						type:'POST',
						url:'${ctx}/security/user/orguser/'+targetValue,
						async: false,
						error: function(){
							var failMsg = '对不起，数据通信失败！';
							alert(failMsg);
							return false;
						},
						success: function(data){
							if(data != null){
								var htmlcontent = "<select name='sbdw'><option value=''>------请选择------</option>";
								for(var i=0; i<data.length; i++){
									htmlcontent += "<option value='"+data[0].id+"'>"+data[i].dwmc+"</option>";
								}
								htmlcontent +="</select>"; 
								document.getElementById("sbdw").outerHTML = htmlcontent;
							}else{
								alert('对不起，数据处理失败！');
								return false;
							}
						}
					});
				}
			}
			function dwexport(){
				$("#export").val("1");
				$("#mainForm").submit();
			}
			function query(){
				$("#export").val("");
				$("#mainForm").submit();
			}
		</script>
	</head>

	<body style="PADDING-TOP: 5px">
	<form id="mainForm" action="${ctx}/dwcl/dwlm" method="get">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="order" id="order" value="${page.order}"/>
		<input type="hidden" name="export" id="export" />
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					单位栏目
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<c:if test="${enable != '0'}">
				<td class="td_table_1" style="width: 15%" >
					<span>县区文明办：</span>
				</td>
				<td class="td_table_2" style="width: 20%" >
					<select id="qxwmb" name="qxwmb" style="width: 97%" class="input_select" onchange="change(this);"/>
			    		<option value="">------请选择------</option>
				    	<c:forEach var="item" items="${orgs}" varStatus="s">
				    		<option value="${item.id }" <c:if test="${item.id == qxwmb}">selected</c:if>>${item.zzjgmc}</option>
				    	</c:forEach>
			   		</select>
				</td>
				<td class="td_table_1" style="width: 15%" >
					<span>文明单位：</span>
				</td>
				<td class="td_table_2" style="width: 20%" >
					<select id="sbdw" name="sbdw" style="width: 97%" class="input_select" />
			    		<option value="">------请选择------</option>
			    		<c:forEach var="item" items="${sbdws}">
				    		<option value="${item.id }" <c:if test="${sbdw == item.id}">selected</c:if>>${item.dwmc}</option>
				    	</c:forEach>
			   		</select>
				</td>
				</c:if>
				<td class="td_table_1" style="width: 10%" >
					<span>年份：</span>
				</td>
				<td class="td_table_2" style="width:10%">
					<input type="text" class="input_120" id="ndsj" name="ndsj" value="${ndsj}" onFocus="WdatePicker({dateFmt:'yyyy'})" />
				</td>
				<td class="td_table_1" style="width: 10%" >
					<span>月份：</span>
				</td>
				<td class="td_table_2" style="width:10%">
					<input type="text" class="input_120" id="ndsj" name="ydsj" value="${ydsj}" onFocus="WdatePicker({dateFmt:'MM'})" />
				</td>
			</tr>
		</table>
		<table style="text-align:left;width:98%;align:center;" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="left">
					&nbsp;&nbsp;&nbsp;<input type='button' onclick="query()" class='button_70px' value='查询'/>
					<input type='button' onclick="dwexport()" class='button_70px' value='导出'/>
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align=center width=5% class="td_list_1" nowrap>
					序号
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					栏目
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					上报数
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					通过数
				</td>
				<td align=center width=15% class="td_list_1" nowrap>
					积分
				</td>				
			</tr>
			<c:forEach items="${page.result}" var="dwtj" varStatus="s">
				<tr>
					<td class="td_list_2" align="left" nowrap>
						${s.count}
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwtj.sbdw}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwtj.sbzs}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwtj.ygds}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwtj.zf}&nbsp;
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }" lookup="${lookup }"/>
		</table>
	</form>
	</body>
</html>
