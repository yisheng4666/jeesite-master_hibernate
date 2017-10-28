package com.thinkgem.jeesite.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:applicationContext-test-activiti.xml")
public class ObjectionTest extends SpringActivitiTestCase {
	
	/**
	 * 测试接受响应
	 */
	@Test
	@Deployment(resources = "diagrams/objection.bpmn")
	public void testAcceptResult() throws Exception {
		// 验证是否部署成功
		long count = repositoryService.createProcessDefinitionQuery().processDefinitionKey("objection").count();
		assertEquals(1, count);

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("objection").singleResult();

		// 设置当前用户
		String currentUserId = "testUserId";
		identityService.setAuthenticatedUserId(currentUserId);

		// 用于传递参数
        Map<String, Object> variables4Process = new HashMap<String, Object>();
        variables4Process.put("respondUserId", "respondUserId");
        variables4Process.put("judgeUserId", "judgeUserId");
        
		// 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),variables4Process);
		assertNotNull(processInstance);
		
		// 任务
		Task task = taskService.createTaskQuery().taskAssignee("respondUserId").singleResult();
        assertNotNull(task);
        assertEquals("评分单位响应", task.getName());
        taskService.complete(task.getId());
             
        task = taskService.createTaskQuery().taskAssignee("testUserId").singleResult();
        assertNotNull(task);
        assertEquals("复核响应", task.getName());
        variables4Process.clear();
        variables4Process.put("acceptResult", true);
        taskService.complete(task.getId(),variables4Process);
        
        // 验证流程是否已经结束
        ProcessInstance temp = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
        assertNull(temp); 
	}
	
	/**
	 * 测试申请裁定
	 */
	@Test
	@Deployment(resources = "diagrams/objection.bpmn")
	public void testJudge() throws Exception {
		// 验证是否部署成功
		long count = repositoryService.createProcessDefinitionQuery().processDefinitionKey("objection").count();
		assertEquals(1, count);

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("objection").singleResult();

		// 设置当前用户
		String currentUserId = "testUserId";
		identityService.setAuthenticatedUserId(currentUserId);

		// 用于传递参数
        Map<String, Object> variables4Process = new HashMap<String, Object>();
        variables4Process.put("respondUserId", "respondUserId");
        variables4Process.put("judgeUserId", "judgeUserId");
        
		// 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),variables4Process);
		assertNotNull(processInstance);
		
		// 任务
		Task task = taskService.createTaskQuery().taskAssignee("respondUserId").singleResult();
        assertNotNull(task);
        assertEquals("评分单位响应", task.getName());
        taskService.complete(task.getId());
             
        task = taskService.createTaskQuery().taskAssignee("testUserId").singleResult();
        assertNotNull(task);
        assertEquals("复核响应", task.getName());
        variables4Process.clear();
        variables4Process.put("acceptResult", false);
        taskService.complete(task.getId(),variables4Process);
                
        task = taskService.createTaskQuery().taskAssignee("judgeUserId").singleResult();
        assertNotNull(task);
        assertEquals("裁定", task.getName());
        taskService.complete(task.getId());
        
        // 验证流程是否已经结束
        ProcessInstance temp = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
        assertNull(temp); 
	}
}
