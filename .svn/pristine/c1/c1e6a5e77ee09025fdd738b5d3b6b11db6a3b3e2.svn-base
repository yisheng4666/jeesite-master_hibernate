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
		function selectAll () {  
			$(':input[type="checkbox"]').prop("checked", true); 
	    }  
		function reverse () {  
			$(':input[type="checkbox"]').each(function () {  
		        $(this).prop("checked", !$(this).prop("checked"));  
		    });
	    } 
		function joinOffice() { 
			var idArr = new Array;
			$('input[type="checkbox"]:checked').each(function(i){
	        	idArr[i] = $(this).val();
	        });
			if (idArr.length==0) {
				top.$.jBox.tip("请勾选考评单位后再移入考评组", 'info');
				return false;
			} else {
				loading('正在移入分组中，请稍等...');
		    	$("#listForm").attr("action", "${ctx}/asmt/assementUser/joinOffice?officeId=${officeId}");
		    	$("#listForm").submit();
			}
    	}
	</script>
</head>
<body>
	<tags:message content="${message}"/>
	<div class="breadcrumb">
		<label>可添加至当前组内单位</label>
	</div>
	<form:form id="searchForm" modelAttribute="assementUser" action="${ctx}/asmt/assementUser/notGroupMembersList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="officeId" name="officeId" type="hidden" value="${officeId}"/>
		<label>单位名称：</label><form:input path="userName" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<form id="listForm" method="post" target="mainFrame">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<th style="text-align:center; width: 60px;">
					<a href="javascript:void(0)" onclick="selectAll()">全选</a>&nbsp;
					<a href="javascript:void(0)" onclick="reverse()">反选</a>
				</th>
				<th style="text-align:center;">单位名称</th>
			</tr>
			<c:forEach items="${page.list }" var="au">
				<tr>
					<td style="text-align:center;"><input type="checkbox" name="ids" value="${au.id }" /></td>
					<td style="text-align:center;">${au.userName }</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pagination">${page}</div>
		<shiro:hasPermission name="asmt:assementUser:edit">
			<div class="form-actions pagination-left">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="移入" onclick="joinOffice();"/>
			</div>
		</shiro:hasPermission>
	</form>
</body>
</html>
