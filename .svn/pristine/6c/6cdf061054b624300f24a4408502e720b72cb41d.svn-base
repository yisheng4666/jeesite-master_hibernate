<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>事件录入管理</title>
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
		<li class="active"><a href="${ctx}/asmt/entryIncident/">事件录入列表</a></li>
		<shiro:hasPermission name="asmt:entryIncident:edit"><li><a href="${ctx}/asmt/entryIncident/form">事件录入添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="entryIncident" action="${ctx}/asmt/entryIncident/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>事件内容 ：</label><form:input path="content" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>事件编码</th><th>相关单位</th><th>事件等级</th><th>内容</th><shiro:hasPermission name="asmt:entryIncident:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="entryIncident">
			<tr>
				<td><a href="${ctx}/asmt/entryIncident/form?id=${entryIncident.id}">${entryIncident.incidentNo}</a></td>
				<td>${entryIncident.unitCatalog.name}</td>
				<td>${fns:getDictLabel(entryIncident.level, 'rule_incidentLevel', '无')}</td>
				<td>${entryIncident.content}</td>
				<shiro:hasPermission name="asmt:entryIncident:edit"><td>
    				<a href="${ctx}/asmt/entryIncident/form?id=${entryIncident.id}">修改</a>
					<a href="${ctx}/asmt/entryIncident/delete?id=${entryIncident.id}" onclick="return confirmx('确认要删除该事件吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
