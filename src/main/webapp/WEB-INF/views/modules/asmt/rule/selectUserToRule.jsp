<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${type eq '1' ? '关联评分用户' : '关联事件录入用户'}</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var ruserIds, ruserType;
	
		$(document).ready(function() {
			$("#ruserIds").change(function(){
				ruserIds = $(this).val();
			  });
			$("input[name='useType']").click(function(){ 
				ruserType = $(this).val();
			  });
		});
	</script>
</head>
<body>
	<form:form id="selectUserForm" modelAttribute="ruleUser" action="" method="post" class="form-horizontal">
	
		<div class="control-group">
		</div>
		<div class="control-group">
			<label class="control-label" for="userIds">用户:</label>
			<div class="controls">
				<form:select id="ruserIds" path="userIds" multiple="true">
					<form:options items="${userlist}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<c:if test="${type eq '1' }">
		<div class="control-group">
			<label class="control-label" for="useType">使用类型:</label>
			<div class="controls">
				<form:radiobuttons id="ruserType" path="useType" items="${usetype}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		</c:if>
	</form:form>
</body>
</html>
