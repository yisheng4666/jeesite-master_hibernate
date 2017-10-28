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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/asmt/assementUser/groupingMain">快速分组</a></li>
	</ul>
	<tags:message content="${message}"/>
	<div style="width: 25%; height:auto; float:left; display:inline">
		<%-- <%@ include file="/WEB-INF/views/modules/asmt/assementUserLeft.jsp"%> --%>
		<iframe id="leftFrame" name="leftFrame" src="${ctx}/asmt/assementUser/groupingList?officeId=${officeId}" style="overflow:visible;"
						scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
	</div>
	<div style="width: 1%; height:auto; float:left; display:inline">
	&nbsp;<!-- 此div用于隔开两个div  -->
	</div>
	<div style="width: 48%; height:auto; float:left; display:inline">
		<iframe id="centerFrame" name="centerFrame" src="${ctx}/asmt/assementUser/groupMembersList" style="overflow:visible;"
					scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
	</div>
	<div style="width: 1%; height:auto; float:left; display:inline">
	&nbsp;<!-- 此div用于隔开两个div  -->
	</div>
	<div style="width: 25%; height:auto; float:left; display:inline">
		<iframe id="rightFrame" name="rightFrame" src="" style="overflow:visible;"
					scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
	</div>
</body>
</html>
