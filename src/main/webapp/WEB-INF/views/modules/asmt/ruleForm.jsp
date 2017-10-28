<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考评规则管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.table {width:60%;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#content").focus();
			$("#inputForm").validate({
				rules: {
					scoreLimit: {
					      required: true,
					      digits: true,
					      min: 0
					    }
				}
			});
			
			if ('${rule.isIncident}' == '1')
				$("#incItems").show();
			else
				$("#incItems").hide();
			
 			$("input[name='isIncident']").click(function(){ 
 				if ($(this).val() == '1')
 					$("#incItems").show();
 				else
 					$("#incItems").hide();
			  }); 
		});
		function assignUser(type) {
			if (!'${rule.id}') {
				top.$.jBox.tip("请先保存规则，再添加关联用户！", 'info');
				return false;
			}
				
			var submit = function(v, h, f) {
				var ruserIds = h.find("iframe")[0].contentWindow.ruserIds;
				var ruserType = h.find("iframe")[0].contentWindow.ruserType;
				if (v=='assign') {
					if (!ruserIds) {
						top.$.jBox.tip("请选择需要关联的用户！", 'info');
						return false;
					}
					if (type == 1 ) {
						if (!ruserType) {
							top.$.jBox.tip("请选择使用类型！", 'info');
							return false;
						}
					} else 
						ruserType = '3'; // 针对关联事件录入用户
					
					var actionurl = '${ctx}/asmt/rule/saveSelectUser?id=${rule.id}&ruserIds='+ruserIds+'&ruserType='+ruserType;
					
					$('#assignRuleForm').attr('action',actionurl).submit();
					return true;
				}
			};
			top.$.jBox.open("iframe:${ctx}/asmt/rule/usertorule?id=${rule.id}&type="+type, type==1 ? "关联评分用户" : "关联事件录入用户", 460, 200, 
					{buttons:{"关联":"assign", "关闭":true}, bottomText:"通过选择下拉列表中的用户来关联用户使用（可多选）！", submit:submit, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/asmt/rule/">规则列表</a></li>
		<li class="active"><a href="${ctx}/asmt/rule/form?id=${rule.id}">考评规则<shiro:hasPermission name="asmt:rule:edit">${not empty rule.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="asmt:rule:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="rule" action="${ctx}/asmt/rule/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">上级规则:</label>
			<div class="controls">
                <tags:treeselect id="rule" name="parent.id" value="${rule.parent.id}" labelName="parent.content" labelValue="${rule.parent.content}"
					title="规则" url="/asmt/rule/treeData" extId="${rule.id}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="content">规则内容:</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="3" maxlength="1000" class="required input-xlarge" cols="30"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="isScoreTime">是否评分项:</label>
			<div class="controls">
				<form:radiobuttons path="isScoreTime" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="type">类型:</label>
			<div class="controls">
				<form:radiobuttons path="type" items="${fns:getDictList('rule_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="scoreLimit">分值上限:</label>
			<div class="controls">
				<form:input path="scoreLimit" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sort">排序:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="50" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for=roleIdList>评分角色:</label>
			<div class="controls">
				<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评分用户:</label>
			<div class="controls">
				<shiro:hasPermission name="asmt:rule:edit"><input id="assignUserBtn4Scr" class="btn btn-primary" type="button" value="关联用户" onclick="assignUser(1);"/></shiro:hasPermission>
				<c:if test="${!empty scrRus}">
				<table class="table table-hover">
				  <thead>
				    <tr>
				      <th>用户名</th>
				      <th>使用类型</th>
				      <shiro:hasPermission name="asmt:rule:edit"><th>操作</th></shiro:hasPermission>
				    </tr>
				  </thead>
				  <tbody>
					  <c:forEach items="${scrRus}" var="rs">
					  	<tr>
					  		<td>${rs.user.name }</td>
					  		<td>${fns:getDictLabel(rs.useType, 'ruleUser_useType', '无')}</td>
					  		<shiro:hasPermission name="asmt:rule:edit">
					  			<td><a href="${ctx}/asmt/rule/deleteRuleUser?id=${rule.id}&ruId=${rs.id}">移除</a></td>
					  		</shiro:hasPermission>
					  	</tr>
					  </c:forEach>
				  </tbody>
				</table>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="isIncident">是否关联案事件:</label>
			<div class="controls">
				<form:radiobuttons path="isIncident" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div id="incItems">
			<div class="control-group">
				<label class="control-label">事件录入用户:</label>
				<div class="controls">
					<shiro:hasPermission name="asmt:rule:edit"><input id="assignUserBtn4Inc" class="btn btn-primary" type="button" value="关联用户" onclick="assignUser(2);"/></shiro:hasPermission>
					<c:if test="${!empty incRus}">
						<table class="table table-hover">
						  <thead>
						    <tr>
						      <th>用户名</th>
						      <shiro:hasPermission name="asmt:rule:edit"><th>操作</th></shiro:hasPermission>
						    </tr>
						  </thead>
						  <tbody>
							  <c:forEach items="${incRus}" var="rs">
							  	<tr>
							  		<td>${rs.user.name }</td>
							  		<shiro:hasPermission name="asmt:rule:edit">
							  			<td><a href="${ctx}/asmt/rule/deleteRuleUser?id=${rule.id}&ruId=${rs.id}">移除</a></td>
							  		</shiro:hasPermission>
							  	</tr>
							  </c:forEach>
						  </tbody>
						</table>
					</c:if>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="incidentLevels">事件等级:</label>
				<div class="controls">
					<form:checkboxes path="incidentLevels" items="${fns:getDictList('rule_incidentLevel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</div>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="asmt:rule:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<form id="assignRuleForm" action="" method="post" class="hide"></form>
</body>
</html>
