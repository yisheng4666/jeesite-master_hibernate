<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考评管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/asmt/assement/">考评列表</a></li>
		<li class="active"><a href="${ctx}/asmt/assement/form?id=${assement.id}">考评<shiro:hasPermission name="asmt:assement:edit">${not empty assement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="asmt:assement:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="assement" action="${ctx}/asmt/assement/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="name">名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="year">年份:</label>
			<div class="controls">
				<%-- <form:input path="year"  htmlEscape="false" maxlength="200" class="required"/> --%>
				<form:select path="year" class="required">
					<form:option value="" label=""/>
					<form:options items="${yearList}" htmlEscape="false" class="required"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="description">考评描述:</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="1000" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="asmt:assement:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
