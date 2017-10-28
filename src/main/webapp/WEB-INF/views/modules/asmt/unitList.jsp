<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<style type="text/css">.sort{color:#0663A2;cursor:pointer;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
			// 表格排序
			tableSort({callBack : page});
			
			/* $("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						$("#searchForm").attr("action","${ctx}/asmt/user/export").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			}); */
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/asmt/unit/").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/asmt/unit/">单位列表</a></li>
		<shiro:hasPermission name="asmt:unit:edit"><li><a href="${ctx}/asmt/unit/form">单位添加</a></li></shiro:hasPermission>
	</ul>
	
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/asmt/unit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%-- <input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/> --%>
		<div style="margin-top:8px;">
			<label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-small"/>
			<label>单位名称：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
		</div>
	</form:form>
	
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;" class="sort loginName">系统登录名</th>
				<th style="text-align: center;" class="sort name">单位名称</th>
				<th style="text-align: center;">编制序列</th>
				<th style="text-align: center;">角色</th>
				<shiro:hasPermission name="asmt:unit:edit">
					<th style="text-align: center;">操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td style="text-align: center;"><a href="${ctx}/asmt/unit/form?id=${user.id}">${user.loginName}</a></td>
				<td style="text-align: center;">${user.name}</td>
				<td style="text-align: center;">${user.organizationNo}</td>
				<td>${user.roleNames}</td>
				<shiro:hasPermission name="sys:user:edit">
					<td style="text-align: center;">
	    				<a href="${ctx}/asmt/unit/form?id=${user.id}">修改</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>