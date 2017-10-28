<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/asmt/assementUser/assementUserMain">被考评单位列表</a></li>
		<shiro:hasPermission name="asmt:assementUser:edit"><li><a href="${ctx}/asmt/assementUser/form">被考评单位添加</a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<div style="width: 50%; height:auto; float:left; display:inline">
		<%-- <%@ include file="/WEB-INF/views/modules/asmt/assementUserLeft.jsp"%> --%>
		<iframe id="LeftFrame" name="LeftFrame" src="${ctx}/asmt/assementUser/list" style="overflow:visible;"
						scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
	</div>
	<div style="width: 1%; height:auto; float:left; display:inline">
	&nbsp;<!-- 此div用于隔开两个div  -->
	</div>
	<div style="width: 49%; height:auto; float:left; display:inline">
		<iframe id="rightFrame" name="rightFrame" src="${ctx}/asmt/assementUser/userList" style="overflow:visible;"
					scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
	</div>
</body>
</html>
