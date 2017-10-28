package com.thinkgem.jeesite.modules.asmt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.asmt.entity.FlowStatusInfo;
import com.thinkgem.jeesite.modules.asmt.service.FlowStatusInfoService;

/**
 * @author JianHui
 * @date 2017年10月20日--上午11:21:41
 */

@Controller
@RequestMapping(value = "${adminPath}/asmt/flowStatusInfo")
public class FlowStatusInfoController extends BaseController {

	@Autowired
	private FlowStatusInfoService flowStatusInfoService;

	@ModelAttribute
	public FlowStatusInfo get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return flowStatusInfoService.get(id);
		} else {
			return new FlowStatusInfo();
		}
	}

	@RequiresPermissions("asmt:flowStatusInfo:view")
	@RequestMapping(value = { "list", "" })
	public String list(FlowStatusInfo flowStatusInfo, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<FlowStatusInfo> page = flowStatusInfoService.find(new Page<FlowStatusInfo>(request, response),
				flowStatusInfo);
		model.addAttribute("page", page);
		return "modules/asmt/flowStatusInfoList";
	}

	@RequiresPermissions("asmt:flowStatusInfo:view")
	@RequestMapping(value = "form")
	public String form(FlowStatusInfo flowStatusInfo, Model model) {
		model.addAttribute("flowStatusInfo", flowStatusInfo);
		return "modules/asmt/flowStatusInfoFrom";
	}

	@RequiresPermissions("asmt:flowStatusInfo:edit")
	@RequestMapping(value = "save")
	public String save(FlowStatusInfo flowStatusInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, flowStatusInfo)) {
			return form(flowStatusInfo, model);
		}
		flowStatusInfoService.save(flowStatusInfo);
		addMessage(redirectAttributes, "保存XX'" + flowStatusInfo.getId() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/asmt/flowStatusInfo/?repage";
	}

	@RequiresPermissions("asmt:flowStatusInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		flowStatusInfoService.delete(id);
		addMessage(redirectAttributes, "删除XX成功");
		return "redirect:" + Global.getAdminPath() + "/asmt/flowStatusInfo/?repage";
	}

}