<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<script type="text/javascript">
		jQuery.fn.rowspan = function(colIdx) { //封装的一个JQuery小插件
			return this.each(function(){
				var that;
				$('tr', this).each(function(row) {
	        		$('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
				        if (that!=null && $(this).html() == $(that).html()) {
				        	rowspan = $(that).attr("rowSpan");
				        	if (rowspan == undefined) {
								$(that).attr("rowSpan",1);
				        		rowspan = $(that).attr("rowSpan"); 
				        	}
				        	rowspan = Number(rowspan)+1;
				        	$(that).attr("rowSpan",rowspan);
				        	$(this).hide();
				        } else {
	        				that = this;
						}
					});
				});
	        });
		}
		$(function() {
			$("#treeTable").rowspan(0);  //传入的参数是对应的列数从0开始，哪一列有相同的内容就输入对应的列数值
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/asmt/assementUser/supervisionDirectoryReport">目录上报</a></li>
		<li><a href="${ctx}/asmt/assementUser/awardedMarkApplication">加分申请</a></li>
		<li><a href="${ctx}/sys/user/appraisalUnitScore">考评单位评分</a></li>
		<li><a href="${ctx}/asmt/assementUser/groupLeaderUnitScoring">组长单位评分</a></li>
		<li><a href="${ctx}/asmt/assement/monitoringObjectionResponse">异议响应</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="assementUser" action="${ctx}/asmt/assementUser/supervisionDirectoryReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>分组名称 ：</label><form:input path="office.name" htmlEscape="false" maxlength="50" class="input-small"/>
		<label>单位名称 ：</label><form:input path="userName" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th style="text-align:center;">分组名称</th>
			<th style="text-align:center;">单位名称</th>
			<th style="text-align:center;">当前状态</th>
			<th style="text-align:center;">完成状态</th>
		</tr>
		<c:forEach items="${page.list }" var="au">
			<tr>
				<td style="text-align:center;vertical-align:middle; background:white;">${au.office.name }</td>
				<td style="text-align:center;">${au.userName }</td>
				<td style="text-align:center;">${au.currentStatus }</td>
				<td style="text-align:center;">${au.completeStatus }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
