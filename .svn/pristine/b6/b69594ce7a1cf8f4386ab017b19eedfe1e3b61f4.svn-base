<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核上报单位管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.table td i{margin:0 2px;}
		.modal {
		  display: none;
		  margin-top:5%;
		  margin-left:10%;
		  z-index: 1;
		  padding-top: 6%;
		  left: 0;
		  top: 0;
		  width: 40%;
		  height:60%;
		  overflow: auto;
		}
		.modal-content {
		  position: relative;
		  margin: auto;
		  padding: 0;
		  width: 80%;
		  max-width: 1200px;
		}
		.close {
		  color: red;
		  position: absolute;
		  top: 10px;
		  right: 25px;
		  font-size: 35px;
		  font-weight: bold;
		}
		.close:hover,
		
		.close:focus {
		  color: #999;
		  text-decoration: none;
		  cursor: pointer;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        };
        function openModal(url) {
            document.getElementById('openimg').src=url;
            document.getElementById('myModal').style.display = "block";
         };

         function closeModal() {
            document.getElementById('myModal').style.display = "none";
         }
         
         function ob(){
         alert("ob");
         }
         
         function of(){
         alert("of");
         }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/asmt/auditUnit/">单位列表</a></li>
		<shiro:hasPermission name="asmt:auditUnit:edit"><li><a href="">单位审核</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="unitCatalog" action="${ctx}/asmt/auditUnit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>组名称 ：</label><form:input path="assementUser.office.name" htmlEscape="false" maxlength="50" class="input-small"/>
		<label>单位名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		
		<label>状态 ：</label>
		<select name="state">
		  <option value ="0">待审核</option>
		  <option value ="1">审核通过</option>
		  <option value="2">审核未通过</option>
		</select>
		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>所属组</th><th>单位名称</th><th>联系人</th><th>联系电话</th><th>公章图片</th><th>状态</th><shiro:hasPermission name="asmt:auditUnit:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="unitCatalog">
			<tr>
				<td>${unitCatalog.assementUser.office.name}</td>
				<td>${unitCatalog.name}</td>
				<td>${unitCatalog.contact_person}</td>
				<td>${unitCatalog.phone_no}</td>
				<td><a href="javascript:void(0)" style="cursor：pointer" onclick="openModal('${unitCatalog.cachet_url}')">查看</a></td>
				<td>${unitCatalog.state eq 0 ?'待审核':unitCatalog.state eq 1?'审核通过':'审核未通过' }</td>
				
				<shiro:hasPermission name="asmt:auditUnit:edit"><td>
    				<a href="${ctx}/asmt/auditUnit/audit?id=${unitCatalog.id}">${unitCatalog.state eq 0 ?'审核':'查看'}</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
		<div id="myModal" class="modal">
    <span class="close cursor" onclick="closeModal()">&times;</span>
    <div class="modal-content">
        <img id="openimg" style="width: 100%"/>
    </div>
    </div>
</body>
</html>
