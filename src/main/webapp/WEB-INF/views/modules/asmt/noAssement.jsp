<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
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
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/asmt/assementUser/assementUserMain">被考评单位列表</a></li>
		<shiro:hasPermission name="asmt:assementUser:edit"><li><a href="${ctx}/asmt/assementUser/form">被考评单位添加</a></li></shiro:hasPermission>
	</ul> --%>
	<tags:message content="${message}"/>
	<div style="margin-top: 70px;margin-left: 100px">
		<h1>当前考评未开启</h1>
	</div>
</body>
</html>
