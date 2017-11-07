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
	</script>
</head>
<body>
<!--  
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/asmt/unitCatalog/">单位列表</a></li>
		<shiro:hasPermission name="asmt:unitCatalog:edit"><li><a href="${ctx}/asmt/unitCatalog/form">单位添加</a></li></shiro:hasPermission>
	</ul>

	<tags:message content="${message}"/>
-->	
	
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<tr><th>单位名称</th><th>编制序列</th><th>人数</th><th>地址</th><th>联系人</th><th>联系电话</th><th>公章图片</th></tr>
		<tbody>
		<c:forEach items="${page.list}" var="unitCatalog">
			<tr id="${unitCatalog.id}" pId="${unitCatalog.parent.id ne '0' ? unitCatalog.parent.id : '0'}">
				<td>${unitCatalog.name}</td>
				<td>${unitCatalog.organizationNo}</td>
				<td>${unitCatalog.population}</td>
				<td>${unitCatalog.address}</td>
				<td>${unitCatalog.contactPerson}</td>
				<td>${unitCatalog.phoneNo}</td>
				<td ><a href="javascript:void(0)" style="cursor：pointer" onclick="openModal('${unitCatalog.cachetUrl}')">查看</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<div ><img id="imgBanner1"/> </div> 
	
	<div id="myModal" class="modal">
    <span class="close cursor" onclick="closeModal()">&times;</span>
    <div class="modal-content">
        <img id="openimg" style="width: 100%"/>
    </div>
    </div>
      
</body>
</html>
