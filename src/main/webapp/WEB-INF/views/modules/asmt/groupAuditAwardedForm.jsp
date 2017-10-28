<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加分审核管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
			
			if ('${awardedMark.group_result}' == '1'){
				$("#ishide1").show();
				$("#ishide2").show();
				}
			else if('${awardedMark.group_result}' == '0'){
				$("#ishide1").show();
				$("#ishide2").hide();
				} else{
			    $("#ishide1").hide();
				$("#ishide2").hide();
				}
			
 			$("input[name='group_result']").click(function(){ 
 				if ($(this).val() == '1'){
				$("#ishide1").show();
				$("#ishide2").show(); 				
 				}
 				else{
				$("#ishide1").show();
				$("#ishide2").hide(); 
 				}
 					
			  }); 			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/asmt/awardedMark/groupAuditlist">审核列表</a></li>
		<li class="active"><a href="">审核</a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="awardedMark" action="${ctx}/asmt/awardedMark/auditsave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="assementUser.userName">申请单位:</label>
			<div class="controls">
				<form:input readonly="true" path="assementUser.userName" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label" for="rule.content">加分规则:</label>
			<div class="controls">
				<form:textarea readonly="true" path="rule.content" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label" for="apply_reason">申请原因:</label>
			<div class="controls">
				<form:textarea readonly="true" path="apply_reason" htmlEscape="false" rows="4" maxlength="200" class="required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="apply_score">申请分值:</label>
			<div class="controls">
				<form:input readonly="true" path="apply_score" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="group_result">审核结果:</label>
			<div class="controls">
				<form:radiobuttons path="group_result" items="${fns:getDictList('group_result')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
	    <div id="ishide1">	
	    		<div class="control-group">
			<label class="control-label" for="group_suggest">审核意见:</label>
			<div class="controls">
				<form:textarea path="group_suggest" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		</div>
		<div id="ishide2">	
		<div class="control-group">
			<label class="control-label" for="group_score">审核分值:</label>
			<div class="controls">
				<form:input path="group_score" htmlEscape="false"  maxlength="50"/>
			</div>
		</div>	
	    </div>
		<div class="form-actions">
			<shiro:hasPermission name="asmt:awardedMark:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
