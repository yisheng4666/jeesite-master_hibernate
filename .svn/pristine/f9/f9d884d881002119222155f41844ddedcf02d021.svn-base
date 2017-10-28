<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>组内管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
		function updateSort() {
			loading('正在提交，请稍等...');
	    	$("#listForm").attr("action", "${ctx}/asmt/rankDetail/updateSort");
	    	$("#listForm").submit();
    	}
	</script>
	<script type="text/javascript">  
		/* 
			params 
			t:触发事件的元素 
			oper:操作方式 
		*/  
		function check(t,oper){  
			var data_tr=$(t).parent().parent(); //获取到触发的tr  
			if(oper=="MoveUp"){    //向上移动  
				if($(data_tr).prev().html()==null){ //获取tr的前一个相同等级的元素是否为空  
					top.$.jBox.tip("已经是第一名了!", 'info');
					return;  
				} else {  
					//当前行ID
					var trId = $(data_tr).attr("id");
					//上一行ID
					var prevTrId = $(data_tr).prev().attr("id");
					//获取当前行和上一行的当前排名
					var sort = $("#td"+trId).attr("value");
					var prevSort = $("#td"+prevTrId).attr("value");
					//将当前行和上一行的排名互换
					$("#td"+trId).attr("value",prevSort);
					$("#td"+prevTrId).attr("value",sort);
					$(data_tr).insertBefore($(data_tr).prev()); //将本身插入到目标tr的前面   
				}  
			} else {  
				if($(data_tr).next().html()==null){  
					top.$.jBox.tip("已经是最后一名了!", 'info');
					return;  
				} else {  
					//当前行ID
					var trId = $(data_tr).attr("id");
					//下一行ID
					var nextTrId = $(data_tr).next().attr("id");
					//获取当前行和下一行的当前排名
					var sort = $("#td"+trId).attr("value");
					var nextSort = $("#td"+nextTrId).attr("value");
					//将当前行和下一行的排名互换
					$("#td"+trId).attr("value",nextSort);
					$("#td"+nextTrId).attr("value",sort);
					$(data_tr).insertAfter($(data_tr).next()); //将本身插入到目标tr的后面   
				}  
			}  
		}  
	</script>  
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/asmt/rankDetail/">组列表</a></li>
		<shiro:hasPermission name="asmt:rankDetail:edit"><li><a href="${ctx}/asmt/rankDetail/form">组添加</a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<form id="listForm" method="post">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<thead>  
				<tr>
					<th style="text-align:center;">名称</th>
					<th style="text-align:center;">得分</th>
					<th style="text-align:center;">排序</th>
					<shiro:hasPermission name="asmt:rankDetail:edit">
						<th style="text-align:center;">操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="rank">
					<tr id="${rank.id}" >
						<td><a href="${ctx}/asmt/rankDetail/form?id=${rank.id}">${rank.userName}</a></td>
						<td><a href="${ctx}/asmt/rankDetail/form?id=${rank.id}">${rank.scort}</a></td>
						<td style="text-align:center;">
								<shiro:hasPermission name="asmt:rankDetail:edit">
									<input type="hidden" name="ids" value="${rank.id}"/>
									<input id="td${rank.id}" name="sorts" type="text" value="${rank.sort}" style="width:50px;margin:0;padding:0;text-align:center;">
								</shiro:hasPermission>
								<shiro:lacksPermission name="asmt:rankDetail:edit">
									${rank.sort}
								</shiro:lacksPermission>
						</td>
						
						<shiro:hasPermission name="asmt:rankDetail:edit"><td style="text-align:center;">
							<a href="#" onclick="check(this,'MoveUp')" ><i class="icon-arrow-up" title="上升"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#" onclick="check(this,'MoveDown')" ><i class="icon-arrow-down" title="下降"></i></a>
						</td></shiro:hasPermission>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<shiro:hasPermission name="asmt:rankDetail:edit"><div class="form-actions pagination-left">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/>
		</div></shiro:hasPermission>
	</form>
</body>
</html>