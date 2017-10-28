<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>事件录入管理</title>
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
		<li><a href="${ctx}/asmt/entryIncident/">事件录入列表</a></li>
		<li class="active"><a href="${ctx}/asmt/entryIncident/form?id=${entryIncident.id}">事件录入<shiro:hasPermission name="asmt:entryIncident:edit">${not empty entryIncident.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="asmt:entryIncident:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="entryIncident" action="${ctx}/asmt/entryIncident/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="incidentNo">事件编码:</label>
			<div class="controls">
				<form:input path="incidentNo" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="unitCatalog">相关单位:</label>
			<div class="controls">
				<form:select path="unitCatalog">
					<form:options items="${unitcataloglist}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rule">相关规则:</label>
			<div class="controls">
                <tags:treeselect id="rule" name="rule.id" value="${entryIncident.rule.id}" labelName="rule.content" labelValue="${entryIncident.rule.content}"
					title="规则" url="/asmt/rule/treeData"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="level">事件等级:</label>
			<div class="controls">
				<form:select path="level">
					<form:option value="" label=""/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="content">内容:</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="3" maxlength="1000" class="required input-xlarge" cols="30"/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="asmt:entryIncident:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
