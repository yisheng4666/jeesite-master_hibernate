<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加分管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>	
	<style type="text/css">
		.table td i{margin:0 2px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 4});
		})

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/asmt/awardedMark/">申请列表</a></li>
		<!--  
		<shiro:hasPermission name="asmt:awardedMark:edit"><li><a href="">申请添加</a></li></shiro:hasPermission>
		-->
	</ul>

	<tags:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>加分规则</th><shiro:hasPermission name="asmt:awardedMark:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page}" var="rule">
				<tr id="${rule.id}" pId="${rule.pid ne '1' ? rule.pid : '0'}">
					<td>${rule.content}</td>
				<shiro:hasPermission name="asmt:awardedMark:edit"><td>
    				<a href="${ctx}/asmt/awardedMark/form?ruleid=${rule.id}">${rule.opreat}</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>

</body>
</html>
