<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考评管理</title>
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
		<li class="active"><a href="${ctx}/asmt/assement/">考评列表</a></li>
		<shiro:hasPermission name="asmt:assement:edit"><li><a href="${ctx}/asmt/assement/form">考评添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="assement" action="${ctx}/asmt/assement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>名称</th><th>年份</th><th>考评描述</th><shiro:hasPermission name="asmt:assement:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="assement">
			<tr>
				<td><a href="${ctx}/asmt/assement/form?id=${assement.id}">${assement.name}</a></td>
				<td>${assement.year}</td>
				<td>${assement.description}</td>
				<shiro:hasPermission name="asmt:assement:edit"><td>
					<c:if test="${assement.status==0 }">
						<a href="${ctx}/asmt/assement/start?id=${assement.id}">开启</a>
					</c:if>
					<c:if test="${assement.status==1 }">
						<a href="${ctx}/asmt/assement/trace?id=${assement.id}" class="fancybox" data-fancybox-type="iframe">追踪</a>
					</c:if>
    				<a href="${ctx}/asmt/assement/form?id=${assement.id}">修改</a>
					<a href="${ctx}/asmt/assement/delete?id=${assement.id}" onclick="return confirmx('确认要删除该考评吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
