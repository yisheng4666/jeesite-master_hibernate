<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加分审核管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					confirmScore: {
					      required: true,
					      digits: true,
					      min: 0
					    }
				}
			});
			
			if ('${rule.confirmResult}' == '1'){
				$("#ishide1").show();
				$("#ishide2").show();
				}
			else{
			    $("#ishide1").hide();
				$("#ishide2").hide();
				}
			
 			$("input[name='confirmResult']").click(function(){ 
 				if ($(this).val() == '1'){
				$("#ishide1").show();
				$("#ishide2").show(); 				
 				}
 				else{
				$("#ishide1").show();
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
	
	<form:form id="inputForm" modelAttribute="awardedMark" action="${ctx}/asmt/awardedMark/confirmsave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="assementUserRule.assementUser.userName">申请单位:</label>
			<div class="controls">
				<form:input readonly="true" path="assementUserRule.assementUser.userName" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label" for="assementUserRule.rule.content">加分规则:</label>
			<div class="controls">
				<form:textarea readonly="true" path="assementUserRule.rule.content" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label" for="applyReason">申请原因:</label>
			<div class="controls">
				<form:textarea readonly="true" path="applyReason" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="applyScore">申请分值:</label>
			<div class="controls">
				<form:input readonly="true" path="applyScore" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>


	    <div class="control-group">
			<label class="control-label" for="groupSuggest">组长意见:</label>
			<div class="controls">
				<form:textarea readonly="true" path="groupSuggest" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label" for="groupScore">组长分值:</label>
			<div class="controls">
				<form:input readonly="true" path="groupScore" htmlEscape="false"  maxlength="50"/>
			</div>
		</div>	

	  
	    <div class="control-group">
			<label class="control-label" for="confirmRuggest">确认意见:</label>
			<div class="controls">
				<form:textarea path="confirmSuggest" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
			
		<div class="control-group">
			<label class="control-label" for="confirmRcore">确认分值:</label>
			<div class="controls">
				<form:input path="confirmScore" htmlEscape="false"  maxlength="50"/>
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
