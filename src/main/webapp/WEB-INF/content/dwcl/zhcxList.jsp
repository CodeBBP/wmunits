<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>综合查询</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/wbox/wbox/wbox.css" />
		<script src="${ctx}/styles/wbox/jquery1.4.2.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/styles/wbox/wbox.js"></script> 
		<script src="${ctx}/styles/js/table.js" type="text/javascript"></script>
		<script src="${ctx}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript">
			var iframewbox;
			function openLm() {
				iframewbox=$("#selectLmBtn").wBox({
					   	requestType: "iframe",
					   	iframeWH:{width:760,height:400},
					   	show: true,
					   	title:"选择栏目",
						target:"${ctx}/khcs/khcstree"
					   });
			}
			
			function callbackProcess2(id, name) {
				if(iframewbox) {
					document.getElementById("khcsid").value=id;
					document.getElementById("khcsmc").value=name;
					iframewbox.close();
				}
			}
			function deletedwcl() {
				var checkbox = $("input[name='dwclid'][checked]");
				if(checkbox.length == 0){
					alert("请选择稿件！");
					return false;
				}
				var dwclid = "";
				$("input[name='dwclid'][checked]").each(function(){
					var item = $(this).val();
						dwclid += item+",";
				});
				dwclid = dwclid.substring(0,dwclid.length-1);
				window.location.href="${ctx}/dwcl/delete/"+dwclid;
			}

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
								var htmlcontent = "<select id='sbdw' name='sbdw'><option value=''>------请选择------</option>";
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
	<form id="mainForm" action="${ctx}/dwcl/zhcx" method="get">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
		<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}"/>
		<input type="hidden" name="order" id="order" value="${page.order}"/>
		<input type="hidden" name="export" id="export" />
		<table width="100%" border="0" align="center" cellpadding="0"
				class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
			<tr>
				<td class="td_table_top" align="center">
					综合查询
				</td>
			</tr>
		</table>
		<table class="table_all" align="center" border="0" cellpadding="0"
			cellspacing="0" style="margin-top: 0px">
			<tr>
				<td class="td_table_1">
					<span>材料标题：</span>
				</td>
				<td class="td_table_2" style="width:15%">
					<input type="text" class="input_120" name="title" value="${title}"/>
				</td>
				<td class="td_table_1">
					<span>上报日期：</span>
				</td>
				<td class="td_table_2" style="width:15%">
					<input type="text" class="input_120" id="cjsjq" name="cjsjq" value="${cjsjq}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'cjsjz\')}'})" />
				</td>
				
				<td class="td_table_1">
					<span>结束日期：</span>
				</td>
				<td class="td_table_2" style="width:15%">
					<input type="text" class="input_120" id="cjsjz" name="cjsjz" value="${cjsjz}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'cjsjq\')}'})" />
				</td>
				<td class="td_table_1">
					<span>审批状态：</span>
				</td>
				<td class="td_table_2">
					<frame:select name="spzt" type="radio" configName="spzt" value="${spzt}" cssClass="input_select"/>
				</td>
			</tr>
			<tr>
				<td class="td_table_1">
					<span>县区文明办：</span>
				</td>
				<td class="td_table_2">
					<c:choose>
					<c:when test="${org.id == 221 }">
					<select id="qxwmb" name="qxwmb" style="width: 90%" class="input_select" onchange="change(this);"/>
			    		<option value="">------请选择------</option>
				    	<c:forEach var="item" items="${orgs}" varStatus="s">
				    		<option value="${item.id }" <c:if test="${item.id == qxwmb}">selected</c:if>>${item.zzjgmc}</option>
				    	</c:forEach>
			   		</select>
					</c:when>
					<c:otherwise>
					${org.zzjgmc }
					</c:otherwise>
					</c:choose>
				</td>
				<td class="td_table_1">
					<span>文明单位：</span>
				</td>
				<td class="td_table_2">
					<select id="sbdw" name="sbdw" style="width: 90%" class="input_select" />
			    		<option value="">------请选择------</option>
			    		<c:forEach var="item" items="${sbdws}">
				    		<option value="${item.id }" <c:if test="${sbdw == item.id}">selected</c:if>>${item.dwmc}</option>
				    	</c:forEach>
			   		</select>
				</td>
				<td class="td_table_1">
					<span>栏目：</span>
				</td>
				<td class="td_table_2" colspan="3">
					<input type="hidden" id="khcsid" name="khcsid" value="${khcsid }">
					<input type="text" class="input_120" name="khcsmc" id="khcsmc" value="${khcsmc}"/>
					<input type='button' class='button_70px' value='选择栏目' id="selectLmBtn" onclick="openLm()"/>
				</td>
			</tr>
		</table>
		<table align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="left">
					<%--<input type='button' onclick="addNew('${ctx}/dwcl/create/${khcsid}')" class='button_70px' value='新建'/>--%>
					<input type='button' onclick="query()" class='button_70px' value='查询'/>
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
					材料标题
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					上报单位
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					上报时间
				</td>
				<td align=center width=20% class="td_list_1" nowrap>
					审批状态
				</td>
				<td align=center width=15% class="td_list_1" nowrap>
					操作
				</td>				
			</tr>
			<c:forEach items="${page.result}" var="dwcl" varStatus="s">
				<tr>
					<td class="td_list_2" align="left" nowrap>
						${s.count}
					</td>
					<td class="td_list_2" align=left nowrap>
						<a href="javascript:;" onclick="window.open('${ctx}/dwcl/view/${dwcl.id }')">${dwcl.title}&nbsp;</a>
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwcl.sbdw.dwmc}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						${dwcl.cjsj}&nbsp;
					</td>
					<td class="td_list_2" align=left nowrap>
						<c:if test="${empty dwcl.spzt }">未审核&nbsp;</c:if>
						<c:if test="${dwcl.spzt == '0'}">新稿&nbsp;</c:if>
						<c:if test="${dwcl.spzt == '1' }">通过&nbsp;</c:if>
						<c:if test="${dwcl.spzt == '2' }">退回&nbsp;</c:if>
					</td>
					<td class="td_list_2" align=left nowrap>
					<c:if test="${dwcl.spzt != '1' }">
						<a href="javascript:;" onclick="window.open('${ctx}/dwcl/update/${dwcl.id }')" class="btnEdit" title="编辑">编辑</a>
					</c:if>
						<a href="javascript:;" onclick="window.open('${ctx}/dwcl/view/${dwcl.id }')" class="btnView" title="查看">查看</a>
					</td>
				</tr>
			</c:forEach>
			<frame:page curPage="${page.pageNo}" totalPages="${page.totalPages }" totalRecords="${page.totalCount }" lookup="${lookup }"/>
		</table>
	</form>
	</body>
</html>
