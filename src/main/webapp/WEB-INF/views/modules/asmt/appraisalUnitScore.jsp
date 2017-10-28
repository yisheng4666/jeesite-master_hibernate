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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/asmt/assementUser/supervisionDirectoryReport">目录上报</a></li>
		<li><a href="${ctx}/asmt/assementUser/awardedMarkApplication">加分申请</a></li>
		<li class="active"><a href="${ctx}/sys/user/appraisalUnitScore">考评单位评分</a></li>
		<li><a href="${ctx}/asmt/assementUser/groupLeaderUnitScoring">组长单位评分</a></li>
		<li><a href="${ctx}/asmt/assement/">异议响应</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/appraisalUnitScore" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>评分单位 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th style="text-align:center;">评分单位</th>
			<th style="text-align:center;">完成状态</th>
		</tr>
		<c:forEach items="${page.list }" var="u">
			<tr>
				<td style="text-align:center;">${u.name }</td>
				<td style="text-align:center;">${u.completeStatus }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
