<%@page import="java.io.InputStream"%>
<%@page import="org.activiti.engine.impl.*"%>
<%@page import="org.activiti.engine.impl.pvm.*"%>
<%@page import="org.activiti.engine.impl.pvm.process.*"%>
<%@page import="org.activiti.engine.repository.*"%>
<%@page import="org.activiti.engine.*"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<style type="text/css">
		.procPic{position:absolute;left:0;top:0;}
		.actImpl{position:absolute;border:2px solid red;-moz-border-radius:12px;-webkit-border-radius:12px;-khtml-border-radius:12px;border-radius:12px;}
	</style>
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
	<div style="width: 60%; height:auto; float:left; display:inline">
		<div class="breadcrumb">
			<label>考评进展情况</label>
		</div>
		<div align="center">
			<img src="${ctx}/sys/workflow/processPic?procDefId=${procDefId}" class="procPic" />
			<c:forEach items="${actImpls}" var="a">
				<div class="actImpl" style="left:${a.x-2}px;top:${a.y-2}px;width:${a.width}px;height:${a.height}px;"></div>
			</c:forEach>
		</div>
	</div>
	<div style="width: 2%; height:auto; float:left; display:inline">
	&nbsp;<!-- 此div用于隔开两个div  -->
	</div>
	<div style="width: 38%; height:auto; float:left; display:inline">
		<div class="breadcrumb">
			<label>当前业务</label>
		</div>
		<form id="listForm" method="post" target="mainFrame">
			<table id="treeTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th style="text-align:center;">业务名称</th>
					<shiro:hasPermission name="asmt:assementUser:edit">
						<th style="text-align:center;width: 200px;">操作</th>
					</shiro:hasPermission>
				</tr>
				<c:forEach items="${tasks }" var="task">
				<tr>
					<td style="text-align:center;">${task.task.name }</td>
					<td>
						<a href="${ctx}${task.processUrl}">办理</a>
						<a href="${ctx}${task.detailUrl}?id=" target="mainFrame" >查看详情</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${ctx}${task.completeUrl}?id=" target="mainFrame" >完成</a>
					</td>
				</tr>
			</c:forEach>
			</table>
		</form>
	</div>
</body>
</html>
