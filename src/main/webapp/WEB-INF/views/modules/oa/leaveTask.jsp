<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务一览</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/leave/">待办任务</a></li>
		<li><a href="${ctx}/oa/leave/list">所有任务</a></li>
		<shiro:hasPermission name="oa:leave:edit"><li><a href="${ctx}/oa/leave/form">请假申请</a></li></shiro:hasPermission>
		<li><a href="${ctx}/oa/leave/list">申请加分</a></li>
		<li><a href="${ctx}/oa/leave/list">上报单位目录</a></li>
		<li><a href="${ctx}/oa/leave/list">组长考核评分</a></li>
		<li><a href="${ctx}/oa/leave/list">评分单位评分</a></li>
	</ul>
	<tags:message content="${message}"/>
	<table width="100%" class="table table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<!-- <th>任务ID</th> 
				<th>流程实例ID</th>
				<th>流程定义ID</th>-->
				<th>任务发起人</th>
				<th>任务名称</th>
				<th>任务创建时间</th>
				<th>办理人</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tasks }" var="task">
				<tr>
					<!-- <td>${task.task.id }</td> 
					<td>${task.task.processInstanceId }</td>
					<td>${task.task.processDefinitionId }</td>-->
					<td>${task.startUserName }</td>
					<td>${task.task.name }</td>
					<td><fmt:formatDate value="${task.task.createTime }" type="both" dateStyle="full"/></td>
					<td>${task.assigneeName }</td>
					<td>
						<a href="${ctx}${task.processUrl}">办理</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
