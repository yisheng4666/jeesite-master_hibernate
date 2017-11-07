<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单位目录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					population: {
					      required: true,
					      digits: true,
					      min: 0
					    }
				}
			});
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/asmt/anewUnitCataLog/">单位列表</a></li>
		<li class="active"><a href="${ctx}/asmt/anewUnitCataLog/form?id=${unitCatalog.id}">单位<shiro:hasPermission name="asmt:unitCatalog:edit">${not empty unitCatalog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="asmt:unitCatalog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm"  enctype="multipart/form-data" modelAttribute="unitCatalog" action="${ctx}/asmt/anewUnitCataLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="assementUser.id"/>
		<tags:message content="${message}"/>
			<div class="control-group" style="display:${empty unitCatalog.parent  ? 'none':'block'};">
			<label class="control-label" >上级目录:</label>
			<div class="controls">
                <tags:treeselect id="UnitCatalog" name="parent.id" value="${unitCatalog.parent.id}" labelName="parent.name" labelValue="${unitCatalog.parent.name}"
					title="单位目录" url="/asmt/anewUnitCataLog/treeData" extId="${unitCatalog.assementUser.id}" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">单位名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="organizationNo">编制序列:</label>
			<div class="controls">
				<form:input path="organizationNo" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="population">人数:</label>
			<div class="controls">
				<form:input path="population" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">地址:</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="contactPerson">联系人:</label>
			<div class="controls">
				<form:input path="contactPerson" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="phoneNo">联系电话:</label>
			<div class="controls">
				<form:input path="phoneNo" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		< 
		<div class="control-group">
			<label class="control-label" for="cachetUrl">公章图片:</label>
			<div class="controls">
				<input  type="file" name="file"  class="required"/>
			</div>
		</div>	
		
		<div class="control-group">
			<label class="control-label" for="isSelfAssement">是否纳入本级考评:</label>
			<div class="controls">
				<form:radiobuttons path="isSelfAssement" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>									

		<div class="form-actions">
			<shiro:hasPermission name="asmt:unitCatalog:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
