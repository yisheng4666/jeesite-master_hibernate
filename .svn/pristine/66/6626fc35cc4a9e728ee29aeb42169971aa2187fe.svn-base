<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加分管理</title>
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
		<li><a href="${ctx}/asmt/awardedMark/">申请列表</a></li>
		<li class="active"><a href="">加分<shiro:hasPermission name="asmt:awardedMark:edit">${not empty awardedMark.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="asmt:awardedMark:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<tags:message content="${message}"/>	
	<form:form id="inForm" modelAttribute=" " action="" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label" for="name">规则依据:</label>
			<div class="controls">
						<table id="treeTable" class="table table-striped table-bordered table-condensed">
							<thead><tr><th>规则内容</th><shiro:hasPermission name="asmt:awardedMark:edit"><th>分值上线</th></shiro:hasPermission></tr></thead>
							<tbody>
							<c:forEach items="${page.list}" var="rule">
									<tr id="${rule.id}" pId="${rule.parent.id ne '1' ? rule.parent.id : '0'}">
										<td>${rule.content}</td>
									<td>
					    				${rule.scoreLimit}
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>				
			</div>
		</div>   
	</form:form>	
	<form:form id="inputForm" enctype="multipart/form-data" modelAttribute="awardedMark" action="${ctx}/asmt/awardedMark/save" method="post" class="form-horizontal">
		<form:hidden path="rule.id"/>

		<div class="control-group">
			<label class="control-label" for="apply_reason">申请原因:</label>
			<div class="controls">
				<form:textarea path="apply_reason" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="apply_score">申请分值:</label>
			<div class="controls">
				<form:input path="apply_score" htmlEscape="false"  maxlength="50"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label" for="prove_url">证明材料:</label>
			<div class="controls">
				<input  type="file" name="file"  class="required"/>
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
