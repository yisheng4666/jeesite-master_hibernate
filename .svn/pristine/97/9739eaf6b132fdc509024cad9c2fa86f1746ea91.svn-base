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
    	function exchg(id, oper) {
			loading('正在调整，请稍等...');
	    	$("#listForm").attr("action", "${ctx}/asmt/rule/updateSort?id=" + id + "&oper=" + oper);
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
			<thead><tr><th>规则内容</th><th>是否评分项</th><th>类型</th><th>分值上限</th><shiro:hasPermission name="asmt:rule:edit"><th>操作</th></shiro:hasPermission></tr></thead>
			<tbody>
			<c:forEach items="${list}" var="rule">
				<tr id="${rule.id}" pId="${rule.parent.id ne '1' ? rule.parent.id : '0'}">
					<td><a href="${ctx}/asmt/rule/form?id=${rule.id}">${rule.content}</a></td>
					<td style="text-align:center;">${rule.isScoreTime eq '1'?'是':'否'}</td>
					<td>${fns:getDictLabel(rule.type, 'rule_type', '无')}</td>
					<td style="text-align:center;">${rule.scoreLimit}</td>
					<shiro:hasPermission name="asmt:rule:edit"><td>
	    				<a href="${ctx}/asmt/rule/form?id=${rule.id}">修改</a>
						<a href="${ctx}/asmt/rule/delete?id=${rule.id}" onclick="return confirmx('确认要删除该规则吗？', this.href)">删除</a>
						<a href="${ctx}/asmt/rule/form?parent.id=${rule.id}">添加下级规则</a> 
						<a href="#" onclick="exchg('${rule.id}','moveUp')" ><i class="icon-arrow-up" title="上调"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" onclick="exchg('${rule.id}','moveDown')" ><i class="icon-arrow-down" title="下调"></i></a>
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>
