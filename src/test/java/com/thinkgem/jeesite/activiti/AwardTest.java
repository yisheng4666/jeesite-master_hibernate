package com.thinkgem.jeesite.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:applicationContext-test-activiti.xml")
public class AwardTest extends SpringActivitiTestCase {
	@Test
	@Deployment(resources = "diagrams/award.bpmn")
	public void testAllApproved() throws Exception {
		// 验证是否部署成功
		long count = repositoryService.createProcessDefinitionQuery().processDefinitionKey("award").count();
		assertEquals(1, count);

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("award").singleResult();

		// 设置当前用户
		String currentUserId = "testUserId";
		identityService.setAuthenticatedUserId(currentUserId);

		// 用于传递参数
        Map<String, Object> variables4Process = new HashMap<String, Object>();
        variables4Process.put("groupUserId", "groupUserId");
        variables4Process.put("confirmUserId", "confirmUserId");
        
		// 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),variables4Process);
		assertNotNull(processInstance);
		
		// 任务
		Task task = taskService.createTaskQuery().taskAssignee("groupUserId").singleResult();
        assertNotNull(task);
        assertEquals("组长审核", task.getName());
        taskService.complete(task.getId());
             
        task = taskService.createTaskQuery().taskAssignee("confirmUserId").singleResult();
        assertNotNull(task);
        assertEquals("加分确认单位确认", task.getName());
        taskService.complete(task.getId());
        
        // 验证流程是否已经结束
        ProcessInstance temp = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
        assertNull(temp);
	}
}
