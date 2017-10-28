/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.workflow.WorkflowUtils;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.asmt.service.AssementService;
import com.thinkgem.jeesite.modules.asmt.utils.AsmtUtils;
import com.thinkgem.jeesite.modules.oa.entity.MyTaskEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 考评Controller
 * 
 * @author JianHui
 * @date 2017年9月5日--下午3:13:32
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/asmt/assement")
public class AssementController extends BaseController {

	@Autowired
	private AssementService assementService;
	@Autowired
	protected SystemService systemService;

	@ModelAttribute
	public Assement get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return assementService.get(id);
		} else {
			return new Assement();
		}
	}

	@RequiresPermissions("asmt:assement:view")
	@RequestMapping("confirmAuditUser")
	public String confirmAuditUser(String id, Model model) {
		// 获取当前考评
		Assement assement = AsmtUtils.getCurrAssement();
		if (assement == null) {
			return "modules/asmt/noAssement";
		} else {
			List<User> users = systemService.findAllUser();
			model.addAttribute("users", users);
			if (StringUtils.isNotBlank(id)) {
				Assement at = assementService.get(id);
				model.addAttribute("assement", at);
			} else {
				model.addAttribute("assement", assement);
			}
			return "modules/asmt/confirmAuditUser";
		}
	}

	@RequiresPermissions("asmt:assement:view")
	@RequestMapping(value = { "scheduleManagement" })
	public String scheduleManagement(Model model) {
		Assement currAssement = AsmtUtils.getCurrAssement();
		if (currAssement == null) {
			return "modules/asmt/noAssement";
		}
		/*** ↓↓↓ 与工作流结合 ↓↓↓ ***/
		// 根据业务ID获取流程实例
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processDefinitionKey("all")
				.processInstanceBusinessKey(currAssement.getId()).singleResult();
		String processInstanceId = processInstance.getId();
		String processDefinitionId = processInstance.getProcessDefinitionId();

		// ProcessDefinition processDefinition =
		// repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		List<ActivityImpl> actImpls = new ArrayList<ActivityImpl>();
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		List<ActivityImpl> activitiList = def.getActivities();// 获得当前任务的所有节点
		List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
		for (String activeId : activeActivityIds) {
			for (ActivityImpl activityImpl : activitiList) {
				String id = activityImpl.getId();
				if (activityImpl.isScope()) {
					if (activityImpl.getActivities().size() > 1) {
						List<ActivityImpl> subAcList = activityImpl.getActivities();
						for (ActivityImpl subActImpl : subAcList) {
							String subid = subActImpl.getId();
							if (activeId.equals(subid)) {// 获得执行到那个节点
								actImpls.add(subActImpl);
								break;
							}
						}
					}
				}
				if (activeId.equals(id)) {// 获得执行到那个节点
					actImpls.add(activityImpl);
				}
			}
		}

		// 读取直接分配给当前人或者已经签收的任务
		List<Task> allTasks = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.taskAssignee(UserUtils.getUser().getId()).list();
		List<MyTaskEntity> allMyTaskEntitys = new ArrayList<MyTaskEntity>();
		for (Task task : allTasks) {
			MyTaskEntity myTaskEntity = new MyTaskEntity();
			myTaskEntity.setTask(task);
			HistoricProcessInstance hi = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			myTaskEntity.setStartUserId(hi.getStartUserId());
			myTaskEntity.setStartUserName(systemService.getUser(hi.getStartUserId()).getName());
			myTaskEntity.setAssigneeName(systemService.getUser(task.getAssignee()).getName());
			List<FormProperty> formProperties = formService.getTaskFormData(task.getId()).getFormProperties();
			myTaskEntity.setProcessUrl(WorkflowUtils.getUrlFromFormPropertiesById(formProperties, "processUrl"));
			myTaskEntity.setCompleteUrl(WorkflowUtils.getUrlFromFormPropertiesById(formProperties, "completeUrl"));
			myTaskEntity.setDetailUrl(WorkflowUtils.getUrlFromFormPropertiesById(formProperties, "detailUrl"));
			allMyTaskEntitys.add(myTaskEntity);
		}
		// List<Task> doingTasks =
		// taskService.createTaskQuery().taskAssignee(UserUtils.getUser().getId()).list();
		/*** ↑↑↑ 与工作流结合 ↑↑↑ ***/

		model.addAttribute("procDefId", processDefinitionId);
		model.addAttribute("proInstId", processInstanceId);
		model.addAttribute("actImpls", actImpls);
		model.addAttribute("tasks", allMyTaskEntitys);
		return "modules/asmt/scheduleManagement";
	}

	@RequiresPermissions("asmt:assement:edit")
	@RequestMapping(value = "start")
	public String start(Assement assement, Model model, RedirectAttributes redirectAttributes) {
		/*** ↓↓↓ 与工作流结合 ↓↓↓ ***/
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("all").singleResult();
		User currentUser = UserUtils.getUser();
		// 设置当前用户ID
		String currentUserId = currentUser.getId();
		identityService.setAuthenticatedUserId(currentUserId);
		// 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),
				assement.getId());
		/*** ↑↑↑ 与工作流结合 ↑↑↑ ***/
		// 将考评设置为正在进行中
		assement.setStatus("1");
		assementService.save(assement);
		addMessage(redirectAttributes, "启动考评'" + assement.getName() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/asmt/assement/?repage";
	}

	@RequiresPermissions("asmt:assement:edit")
	@RequestMapping(value = "trace")
	public String trace(Assement assement, Model model) {
		/*** ↓↓↓ 与工作流结合 ↓↓↓ ***/
		// 根据业务ID获取流程实例
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceBusinessKey(assement.getId()).singleResult();
		String processInstanceId = processInstance.getId();
		String processDefinitionId = processInstance.getProcessDefinitionId();

		// ProcessDefinition processDefinition =
		// repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		List<ActivityImpl> actImpls = new ArrayList<ActivityImpl>();
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		List<ActivityImpl> activitiList = def.getActivities();// 获得当前任务的所有节点
		List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
		for (String activeId : activeActivityIds) {
			for (ActivityImpl activityImpl : activitiList) {
				String id = activityImpl.getId();
				if (activityImpl.isScope()) {
					if (activityImpl.getActivities().size() > 1) {
						List<ActivityImpl> subAcList = activityImpl.getActivities();
						for (ActivityImpl subActImpl : subAcList) {
							String subid = subActImpl.getId();
							if (activeId.equals(subid)) {// 获得执行到那个节点
								actImpls.add(subActImpl);
								break;
							}
						}
					}
				}
				if (activeId.equals(id)) {// 获得执行到那个节点
					actImpls.add(activityImpl);
				}
			}
		}

		/*** ↑↑↑ 与工作流结合 ↑↑↑ ***/

		model.addAttribute("procDefId", processDefinitionId);
		model.addAttribute("proInstId", processInstanceId);
		model.addAttribute("actImpls", actImpls);

		return "modules/sys/processMap";
	}

	@RequiresPermissions("asmt:assement:view")
	@RequestMapping(value = { "list", "" })
	public String list(Assement assement, HttpServletRequest request, HttpServletResponse response, Model model) {
		// User user = UserUtils.getUser();
		// if (!user.isAdmin()){
		// assement.setCreateBy(user);
		// }

		// List<Assement> list = assementService.findAll();
		// model.addAttribute("list", list);
		Page<Assement> page = assementService.find(new Page<Assement>(request, response), assement);
		model.addAttribute("page", page);
		return "modules/asmt/assementList";
	}

	@RequiresPermissions("asmt:assement:view")
	@RequestMapping(value = "form")
	public String form(Assement assement, Model model) {
		// 组装年份数组
		List<String> yearlist = new ArrayList<String>();
		// 获得当前年份
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		for (int i = year; i < year + 21; i++) {
			yearlist.add(i + "");
		}
		model.addAttribute("yearList", yearlist);
		model.addAttribute("assement", assement);
		return "modules/asmt/assementForm";
	}

	@RequiresPermissions("asmt:assement:edit")
	@RequestMapping(value = "save")
	public String save(Assement assement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, assement)) {
			return form(assement, model);
		}
		//
		// if (!user.isAdmin()) {
		// // 考评创建人
		// assement.setCreateBy(user);
		// }
		User user = UserUtils.getUser();
		if ("".equals(assement.getId())) {
			// 新增数据时设置创建时间,创建人和下发规则状态
			// 考评创建时间
			assement.setCreateDate(new Date());
			// 考评创建人
			assement.setCreateBy(user);
			// 默认未未下发规则 0-未下发规则 1-已下发规则
			assement.setIsPublish("0");
			// 设为当前考评
			assement.setStatus("0");
		} else {
			// 修改数据时添加修改时间和修改人
			// 考评修改时间
			assement.setUpdateDate(new Date());
			// 考评修改人
			assement.setUpdateBy(user);
		}
		assementService.save(assement);
		addMessage(redirectAttributes, "保存考评'" + assement.getName() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/asmt/assement/?repage";
	}

	@RequiresPermissions("asmt:assement:edit")
	@RequestMapping(value = "saveAuditUser")
	public String saveAuditUser(Assement assement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, assement)) {
			return form(assement, model);
		}
		Assement at = null;
		User unitCheckUser = systemService.getUser(assement.getUnitCheckUser().getId());
		User awardConfirmUser = systemService.getUser(assement.getAwardConfirmUser().getId());
		User objectionJudgeUser = systemService.getUser(assement.getObjectionJudgeUser().getId());
		if ("".equals(assement.getId())) {
			// 获取当前考评
			at = AsmtUtils.getCurrAssement();
		} else {
			at = assementService.get(assement.getId());
		}
		at.setUnitCheckUser(unitCheckUser);
		at.setAwardConfirmUser(awardConfirmUser);
		at.setObjectionJudgeUser(objectionJudgeUser);
		assementService.save(at);
		addMessage(redirectAttributes, "确认审核用户成功");
		return "redirect:" + Global.getAdminPath() + "/asmt/assement/confirmAuditUser?id=" + at.getId();
	}

	@RequiresPermissions("asmt:assement:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		assementService.delete(id);
		addMessage(redirectAttributes, "删除考评成功");
		return "redirect:" + Global.getAdminPath() + "/asmt/assement/?repage";
	}

}
