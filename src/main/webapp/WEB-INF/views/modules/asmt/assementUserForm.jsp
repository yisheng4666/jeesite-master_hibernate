<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>被考评单位管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		
		$("#user\\.loginName").focus();
	    $("#inputForm").validate({
			rules: {
				'user.loginName': {remote: "${ctx}/asmt/assementUser/checkLoginName?oldLoginName=" + encodeURIComponent('${assementUser.user.loginName}')}
			},
			messages: {
				'user.loginName': {remote: "用户登录名已存在"},
				confirmNewPassword: {equalTo: "输入与上面相同的密码"}
			}
		}); 
	});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/asmt/assementUser/assementUserMain">被考评单位列表</a></li>
		<li class="active"><a href="${ctx}/asmt/assementUser/form?id=${assementUser.id}">被考评单位<shiro:hasPermission name="asmt:assementUser:edit">${not empty assementUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="asmt:assementUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="assementUser" action="${ctx}/asmt/assementUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="oldLoginName">系统登录名:</label>
			<div class="controls">
				<input id="oldLoginName" name="oldLoginName" type="hidden" value="${assementUser.user.loginName}">
				<form:input path="user.loginName" htmlEscape="false" maxlength="50" class="required userName"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="organizationNo">编制序列:</label>
			<div class="controls">
				<form:input path="organizationNo" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="userName">单位名称:</label>
			<div class="controls">
                <form:input path="userName" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="attribute">单位属性:</label>
			<div class="controls">
				<form:select path="attribute" >
					<form:options items="${fns:getDictList('user_attribute')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="appropriationType">财政拨款类型:</label>
			<div class="controls">
				<form:select path="appropriationType" >
					<form:options items="${fns:getDictList('user_appropriationType')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="newPassword">密码:</label>
			<div class="controls">
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty assementUser.user.id?'required':''}"/>
				<c:if test="${not empty assementUser.user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="confirmNewPassword">确认密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sort">排序:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="asmt:assementUser:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>