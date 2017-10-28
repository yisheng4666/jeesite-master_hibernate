/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.sys.web;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.entity.Msg;
import com.thinkgem.jeesite.modules.sys.service.MsgService;

/**
 * 消息Controller
 * @author Yyz
 * @version 2017-10-23
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/msg")
public class MsgController extends BaseController {

	@Autowired
	private MsgService msgService;
	
	@ModelAttribute
	public Msg get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return msgService.get(id);
		}else{
			return new Msg();
		}
	}
	
	@RequiresPermissions("sys:msg:view")
	@RequestMapping(value = {"list", ""})
	public String list(Msg msg, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			msg.setCreateBy(user);
		}
        Page<Msg> page = msgService.find(new Page<Msg>(request, response), msg); 
        model.addAttribute("page", page);
		return "modules/" + "sys/msgList";
	}

	@RequiresPermissions("sys:msg:view")
	@RequestMapping(value = "form")
	public String form(Msg msg, Model model) {
		model.addAttribute("msg", msg);
		return "modules/" + "sys/msgForm";
	}

	@RequiresPermissions("sys:msg:edit")
	@RequestMapping(value = "save")
	public String save(Msg msg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, msg)){
			return form(msg, model);
		}
		msgService.save(msg);
//		addMessage(redirectAttributes, "保存消息'" + msg.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/sys/msg/?repage";
	}
	
	@RequiresPermissions("sys:msg:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		msgService.delete(id);
		addMessage(redirectAttributes, "删除消息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/msg/?repage";
	}

}
