<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#listForm").validate();
			window.parent.rightFrame.location.href='${ctx}/asmt/assementUser/notGroupMembersList?officeId=${officeId}';
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
		function removeOffice() { 
			var idArr = new Array;
			$('input[type="checkbox"]:checked').each(function(i){
	        	idArr[i] = $(this).val();
	        });
			if (idArr.length==0) {
				top.$.jBox.tip("请勾选考评单位后再移出当前组", 'info');
				return false;
			} else {
				loading('正在移出分组，请稍等...');
		    	$("#listForm").attr("action", "${ctx}/asmt/assementUser/removeOffice?officeId=${officeId}");
		    	$("#listForm").submit();
			}
    	}
		function setGroupLeader(id,officeId) {
			loading('正在设置组长，请稍等...');
			window.parent.centerFrame.location.href='${ctx}/asmt/assementUser/setGroupLeader?id='+id+'&officeId='+officeId;
		}
		function cancelGroupLeader(id,officeId) {
			loading('正在取消组长，请稍等...');
			window.parent.centerFrame.location.href='${ctx}/asmt/assementUser/cancelGroupLeader?id='+id+'&officeId='+officeId;
		}
	</script>
</head>
<body>
	<tags:message content="${message}"/>
	<div class="breadcrumb">
		<label>当前组内单位</label>
	</div>
	<form:form id="searchForm" modelAttribute="assementUser" action="${ctx}/asmt/assementUser/groupMembersList" method="post" class="breadcrumb form-search">
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
				<th style="text-align:center; width: 60px;">是否组长</th>
				<shiro:hasPermission name="asmt:assementUser:edit">
					<th style="text-align:center; width: 100px;">操作</th>
				</shiro:hasPermission>
			</tr>
			<c:forEach items="${page.list }" var="au">
				<tr>
					<td style="text-align:center;"><input type="checkbox" name="ids" value="${au.id }" /></td>
					<td style="text-align:center;">${au.userName }</td>
					<td style="text-align:center;">${au.isGroupLeader == '0' ? '否' : '<font color="green">是</font>' }</td>
					<shiro:hasPermission name="asmt:assementUser:edit">
						<td style="text-align:center;">
							<c:if test="${au.isGroupLeader == '0'}">
								<a onclick="setGroupLeader('${au.id}','${officeId}');" href="#" target="centerFrame" >设为组长</a>
							</c:if>
							<c:if test="${au.isGroupLeader == '1'}">
								<a onclick="cancelGroupLeader('${au.id}','${officeId}');" href="#" target="centerFrame" >取消组长</a>
							</c:if>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</table>
		<div class="pagination">${page}</div>
		<shiro:hasPermission name="asmt:assementUser:edit">
			<div class="form-actions pagination-left">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="移出" onclick="removeOffice();"/>
			</div>
		</shiro:hasPermission>
	</form>
</body>
</html>
