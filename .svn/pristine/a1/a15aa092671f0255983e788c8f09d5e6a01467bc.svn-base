<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考评规则管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 3});
		});
    	function updateSort() {
			loading('正在提交，请稍等...');
	    	$("#listForm").attr("action", "${ctx}/asmt/ruler/updateSort");
	    	$("#listForm").submit();
    	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/asmt/rule/">规则列表</a></li>
		<shiro:hasPermission name="asmt:rule:edit"><li><a href="${ctx}/asmt/rule/form">规则添加</a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<form id="listForm" method="post">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<thead><tr><th>规则内容</th><shiro:hasPermission name="asmt:rule:edit"><th style="text-align:center;">排序</th></shiro:hasPermission><th>是否评分项</th><th>类型</th><th>分值上限</th><shiro:hasPermission name="asmt:rule:edit"><th>操作</th></shiro:hasPermission></tr></thead>
			<tbody>
			<c:forEach items="${page.list}" var="rule">
				<tr id="${rule.id}" pId="${rule.parent.id ne '1' ? rule.parent.id : '0'}">
					<td><a href="${ctx}/asmt/rule/form?id=${rule.id}">${rule.content}</a></td>
					<shiro:hasPermission name="asmt:rule:edit">
						<td style="text-align:center;">
							<input type="hidden" name="ids" value="${rule.id}"/>
							<input name="sorts" type="text" value="${rule.sort}" style="width:50px;margin:0;padding:0;text-align:center;">
						</td>
					</shiro:hasPermission>
					<td style="text-align:center;">${rule.isScoreTime eq '1'?'是':'否'}</td>
					<td>${fns:getDictLabel(rule.type, 'rule_type', '无')}</td>
					<td style="text-align:center;">${rule.scoreLimit}</td>
					<shiro:hasPermission name="asmt:rule:edit"><td>
	    				<a href="${ctx}/asmt/rule/form?id=${rule.id}">修改</a>
						<a href="${ctx}/asmt/rule/delete?id=${rule.id}" onclick="return confirmx('确认要删除该规则吗？', this.href)">删除</a>
						<a href="${ctx}/asmt/rule/form?parent.id=${rule.id}">添加下级规则</a> 
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<shiro:hasPermission name="asmt:rule:edit"><div class="form-actions pagination-left">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/>
		</div></shiro:hasPermission>
	</form>
</body>
</html>
