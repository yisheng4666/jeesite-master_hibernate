<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流程管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/workflow/processList/">流程列表</a></li>
		<shiro:hasPermission name="sys:workflow:edit"><li class="active"><a href="${ctx}/sys/workflow/form">流程添加</a></li></shiro:hasPermission>
	</ul>

	<tags:message content="${message}"/>
	
	<fieldset id="deployFieldset">
		<legend>部署流程资源</legend>
		<form action="${ctx }/sys/workflow/deploy" method="post" enctype="multipart/form-data" style="margin-top:1em;">
			<input type="file" name="file" />
			<input type="submit" value="Submit" class="btn" />
		</form>
		<hr class="soften" />
	</fieldset>
</body>
</html>
