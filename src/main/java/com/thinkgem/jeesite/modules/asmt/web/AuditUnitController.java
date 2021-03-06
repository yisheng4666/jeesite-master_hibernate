/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.web;


import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.asmt.entity.FlowStatusInfo;
import com.thinkgem.jeesite.modules.asmt.entity.UnitCatalog;
import com.thinkgem.jeesite.modules.asmt.service.FlowStatusInfoService;
import com.thinkgem.jeesite.modules.asmt.service.UnitCatalogService;
import com.thinkgem.jeesite.modules.asmt.utils.AsmtUtils;

/**
 * 单位目录Controller.
 * @author lcy
 * @version 2017-10-13
 */
@Controller
@RequestMapping(value = "${adminPath}/asmt/auditUnit")
public class AuditUnitController extends BaseController {

	@Autowired
	private UnitCatalogService unitCatalogService;
	
  @Autowired
  private FlowStatusInfoService flowStatusInfoService;   
	
	@ModelAttribute
	public UnitCatalog get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return unitCatalogService.get(id);
		}else{
			return new UnitCatalog();
		}
	}
	
	@RequiresPermissions("asmt:auditUnit:view")
	@RequestMapping(value = {"list", ""})
	public String list(UnitCatalog unitCatalog, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<UnitCatalog> page = unitCatalogService.findByGroup(new Page<UnitCatalog>(request, response), unitCatalog); 
    model.addAttribute("page", page);
		return "modules/" + "asmtUnitCatalog/auditUnitList";
	}

	@RequiresPermissions("asmt:auditUnit:view")
	@RequestMapping(value = "audit")
	public String form(UnitCatalog unitCatalog, HttpServletRequest request, HttpServletResponse response, Model model) {
	  Page<UnitCatalog> page = unitCatalogService.find(new Page<UnitCatalog>(request, response), unitCatalog); 
    model.addAttribute("page", page);
		return "modules/" + "asmtUnitCatalog/auditUnitForm";
	}

	@RequiresPermissions("asmt:auditUnit:edit")
	@RequestMapping(value = "save")
	public String save(UnitCatalog unitCatalog, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		unitCatalogService.save(unitCatalog);
		Assement asmt = AsmtUtils.getCurrAssement();
		FlowStatusInfo fi=flowStatusInfoService.findByType(asmt, unitCatalog.getAssementUser().getUser().getId(), "0");
		if("1".equals(unitCatalog.getState())){
		fi.setCurrentStatus("审核通过");
		fi.setCompleteStatus("已完成");
		}else{
		fi.setCurrentStatus("退回修改");
	  fi.setCompleteStatus("未完成");
		}
		flowStatusInfoService.save(fi);
		addMessage(redirectAttributes, "审核通过'" + unitCatalog.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/asmt/auditUnit/?repage";
	}

  @RequiresPermissions("asmt:auditUnit:edit")
  @RequestMapping(value = "back")
  public String back(UnitCatalog unitCatalog, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
    unitCatalog.setState("2");// 审核通过
    unitCatalogService.save(unitCatalog);
    Assement asmt = AsmtUtils.getCurrAssement();
    FlowStatusInfo fi=flowStatusInfoService.findByType(asmt, unitCatalog.getAssementUser().getUser().getId(), "0");
    fi.setCurrentStatus("退回修改");
    fi.setCompleteStatus("未完成");
    fi.setApproveInfo(unitCatalog.getRemarks());
    flowStatusInfoService.save(fi);
    addMessage(redirectAttributes, "审核不通过'" + unitCatalog.getName() + "'成功");
    return "redirect:"+Global.getAdminPath()+"/asmt/auditUnit/?repage";
  }
  
	@RequiresPermissions("asmt:auditUnit:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		unitCatalogService.delete(id);
		addMessage(redirectAttributes, "删除单位目录成功");
		return "redirect:"+Global.getAdminPath()+"/asmt/unitCatalog/?repage";
	}
	
	@RequiresUser
  @ResponseBody
  @RequestMapping(value = "treeData")
  public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,UnitCatalog unitCatalog, HttpServletResponse response) {
    response.setContentType("application/json; charset=UTF-8");
    List<Map<String, Object>> mapList = Lists.newArrayList();
    Map<String, Object> root = Maps.newHashMap();
    root.put("id", "0");
    root.put("pId", "-1");
    root.put("name", "单位目录");
    mapList.add(root);
    List<UnitCatalog> list = unitCatalogService.findByassementUser(extId);
    for (int i=0; i<list.size(); i++){
      UnitCatalog e = list.get(i);
      if (extId == null || (extId!=null && !extId.equals(e.getId()) )){
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", e.getId());
        map.put("pId", e.getParent()!=null?e.getParent().getId():0);
        map.put("name", e.getName());
        mapList.add(map);
      }
    }
    return mapList;
  }
}
