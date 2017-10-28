<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单位目录管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
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
			$("#treeTable").treeTable({expandLevel : 3});
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
         
         function check1(){
			document.getElementById("state").value=1;
			document.getElementById("inputForm").submit();

         }
         function check2(){
			document.getElementById("state").value=2;
			document.getElementById("inputForm").submit();
         }         
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/asmt/auditUnit/">单位列表</a></li>
		<li class="active"><a href="" >单位审核</a></li>
	</ul><br/>

	<tags:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<tr><th>单位名称</th><th>编制序列</th><th>人数</th><th>地址</th><th>联系人</th><th>联系电话</th><th>公章图片</th></tr>
		<tbody>
		<c:forEach items="${page.list}" var="unitCatalog">
			<tr id="${unitCatalog.id}" pId="${unitCatalog.parent.id ne '0' ? unitCatalog.parent.id : '0'}">
				<td>${unitCatalog.name}</td>
				<td>${unitCatalog.organization_no}</td>
				<td>${unitCatalog.population}</td>
				<td>${unitCatalog.address}</td>
				<td>${unitCatalog.contact_person}</td>
				<td>${unitCatalog.phone_no}</td>
				<td ><a href="javascript:void(0)" style="cursor：pointer" onclick="openModal('${unitCatalog.cachet_url}')">查看</a></td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!--  
	<div class="pagination">${page}</div>
	<div ><img id="imgBanner1"/> </div> 
	-->
	<div id="myModal" class="modal">
    <span class="close cursor" onclick="closeModal()">&times;</span>
    <div class="modal-content">
        <img id="openimg" style="width: 100%"/>
    </div>
    </div>
    
    	<form:form id="inputForm" modelAttribute="unitCatalog" action="${ctx}/asmt/auditUnit/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state"/>
		<div class="control-group">
			<label class="control-label" for="remarks">审核意见:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="asmt:auditUnit:edit">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="审核通过" onclick = "check1();"/>&nbsp;
			</shiro:hasPermission>
			<shiro:hasPermission name="asmt:auditUnit:edit">
			<input id="btnCancel" class="btn btn-primary" type="button" value="退 回" onclick = "check2();"/>
			</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>
