package com.thinkgem.jeesite.modules.asmt.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.asmt.service.UnitService;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * @author JianHui
 * @date 2017年10月17日--上午10:30:24
 */

@Controller
@RequestMapping(value = "${adminPath}/asmt/unit")
public class UnitController extends BaseController {

	@Autowired
	private UnitService unitService;
	@Autowired
	private SystemService systemService;

	@ModelAttribute
	public User get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return unitService.get(id);
		} else {
			return new User();
		}
	}

	@RequiresPermissions("asmt:unit:view")
	@RequestMapping(value = { "list", "" })
	public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = unitService.find(new Page<User>(request, response), user);
		model.addAttribute("page", page);
		return "modules/asmt/unitList";
	}

	@RequiresPermissions("asmt:unit:view")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole(true));
		return "modules/asmt/unitForm";
	}

	@RequiresPermissions("asmt:unit:edit")
	@RequestMapping(value = "save")
	public String save(User user, String oldLoginName, String newPassword, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(newPassword)) {
			user.setPassword(SystemService.entryptPassword(newPassword));
		}
		if (!beanValidator(model, user)) {
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(oldLoginName, user))) {
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		// 添加时设置为关联单位
		if ("".equals(user.getId())) {
			user.setIsOrganization("1");
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findAllRole(true)) {
			if (roleIdList.contains(r.getId())) {
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);

		// 保存用户信息
		unitService.save(user);

		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())) {
			UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存单位'" + user.getName() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/asmt/unit/?repage";
	}

	@RequiresPermissions("asmt:unit:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		unitService.delete(id);
		addMessage(redirectAttributes, "删除单位成功");
		return "redirect:" + Global.getAdminPath() + "/asmt/unit/?repage";
	}

	/**
	 * 检查登陆名是否存在
	 * 
	 * @author JianHui
	 * @param oldLoginName
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("asmt:unit:edit")
	@RequestMapping("checkLoginName")
	public String checkLoginName(String oldLoginName, User user) {
		String loginName = user.getLoginName();
		if (loginName != null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName != null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	// @ResponseBody
	// @RequiresPermissions("asmt:unit:edit")
	// @RequestMapping("checkLoginName")
	// public String checkLoginName(String oldLoginName, String loginName) {
	// if (loginName != null && loginName.equals(oldLoginName)) {
	// return "true";
	// } else if (loginName != null && systemService.getUserByLoginName(loginName)
	// == null) {
	// return "true";
	// }
	// return "false";
	// }

}