<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加分审核管理</title>
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
		<li class="active"><a href="${ctx}/asmt/awardedMark/groupAuditlist">审核列表</a></li>
		<!--  
		<shiro:hasPermission name="asmt:awardedMark:view"><li><a href="">审核</a></li></shiro:hasPermission>
	    -->
	</ul>

	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr ><th style="text-align: center;">序号</th><th style="text-align: center;">申请单位</th><th style="text-align: center;">加分规则</th><th style="text-align: center;">申请原因</th><th style="text-align: center;">申请分值</th><shiro:hasPermission name="asmt:awardedMark:edit"><th style="text-align: center;">操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="awardedMark" varStatus="status">
			<tr>
				<td style="text-align: center;width:5%">${status.index+1}</td>
				<td style="width:10%">${awardedMark.assementUserRule.assementUser.userName}</td>
				<td style="width:50%">${awardedMark.assementUserRule.rule.content}</td>
				<td style="width:20%">${awardedMark.applyReason}</td>
				<td style="text-align: center;width:5%">${awardedMark.applyScore}</td>							
				<shiro:hasPermission name="asmt:awardedMark:edit"><td style="text-align: center;">
    				<a href="${ctx}/asmt/awardedMark/groupform?id=${awardedMark.id}">审核</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
