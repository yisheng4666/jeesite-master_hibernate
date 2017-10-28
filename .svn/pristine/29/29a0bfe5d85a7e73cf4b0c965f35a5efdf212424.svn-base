package com.thinkgem.jeesite.modules.asmt.web;

import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.asmt.entity.RankDetail;
import com.thinkgem.jeesite.modules.asmt.service.RankDetailService;

/**
 * @author JianHui
 * @date 2017年09月16日--下午03:53:07
 */

@Controller
@RequestMapping(value = "${adminPath}/asmt/rankDetail")
public class RankDetailController extends BaseController {

	@Autowired
	private RankDetailService rankDetailService;

	@ModelAttribute
	public RankDetail get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return rankDetailService.get(id);
		} else {
			return new RankDetail();
		}
	}

	@RequiresPermissions("asmt:rankDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(RankDetail rankDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<RankDetail> list = rankDetailService.findAll();
		Collections.sort(list, new Comparator<RankDetail>() {
			public int compare(RankDetail r1, RankDetail r2) {
				if (r1.getSort() > r2.getSort()) {
					return 1;
				}
				if (r1.getSort() == r2.getSort()) {
					return 0;
				}
				return -1;
			}
		});
		model.addAttribute("list", list);
		return "modules/asmt/rankDetailList";
	}

	@RequiresPermissions("asmt:rankDetail:view")
	@RequestMapping(value = "form")
	public String form(RankDetail rankDetail, Model model) {
		model.addAttribute("rankDetail", rankDetail);
		return "modules/asmt/rankDetailFrom";
	}

	@RequiresPermissions("asmt:rankDetail:edit")
	@RequestMapping(value = "save")
	public String save(RankDetail rankDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rankDetail)) {
			return form(rankDetail, model);
		}
		rankDetailService.save(rankDetail);
		addMessage(redirectAttributes, "保存XX'" + rankDetail.getUserName() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/asmt/rankDetail/?repage";
	}

	@RequiresPermissions("asmt:rankDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		rankDetailService.delete(id);
		addMessage(redirectAttributes, "删除XX成功");
		return "redirect:" + Global.getAdminPath() + "/asmt/rankDetail/?repage";
	}

	/**
	 * 批量修改排名
	 * 
	 * @param ids
	 *            ID
	 * @param sorts
	 *            排名
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("asmt:rankDetail:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath() + "/asmt/rankDetail/";
		}
		int len = ids.length;
		RankDetail[] ranks = new RankDetail[len];
		for (int i = 0; i < len; i++) {
			ranks[i] = rankDetailService.get(ids[i]);
			ranks[i].setSort(sorts[i]);
			rankDetailService.save(ranks[i]);
		}
		addMessage(redirectAttributes, "保存组排序成功!");
		return "redirect:" + Global.getAdminPath() + "/asmt/rankDetail/";
	}

}