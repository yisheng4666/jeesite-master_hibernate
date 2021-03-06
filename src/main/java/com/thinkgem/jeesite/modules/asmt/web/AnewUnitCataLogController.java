/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.web;

import java.io.File;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.asmt.entity.AnewUnitCataLog;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.asmt.entity.AssementUser;
import com.thinkgem.jeesite.modules.asmt.entity.UnitCatalog;
import com.thinkgem.jeesite.modules.asmt.service.AnewUnitCataLogService;
import com.thinkgem.jeesite.modules.asmt.service.AssementUserService;
import com.thinkgem.jeesite.modules.asmt.service.UnitCatalogService;
import com.thinkgem.jeesite.modules.asmt.utils.AsmtUtils;

/**
 * 单位列表Controller
 * @author Lucy
 * @version 2017-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/asmt/anewUnitCataLog")
public class AnewUnitCataLogController extends BaseController {

  @Autowired
  private UnitCatalogService unitCatalogService;
  
  @Autowired
  private AssementUserService assementUserService;
  
  @ModelAttribute
  public UnitCatalog get(@RequestParam(required=false) String id) {
    if (StringUtils.isNotBlank(id)){
      return unitCatalogService.get(id);
    }else{
      return new UnitCatalog();
    }
  }
  
	
	@RequiresPermissions("asmt:anewUnitCataLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(UnitCatalog unitCatalog, HttpServletRequest request, HttpServletResponse response, Model model) {
	  User user = UserUtils.getUser();
    if (!user.isAdmin()){
      unitCatalog.setCreateBy(user);
    }
    Assement asmt = AsmtUtils.getCurrAssement();
    AssementUser asmtUser =assementUserService.findByAssementAndUserId(asmt, user);
    unitCatalog.setAssementUser(asmtUser);
        Page<UnitCatalog> page = unitCatalogService.find(new Page<UnitCatalog>(request, response), unitCatalog); 
        model.addAttribute("page", page);
      List<UnitCatalog> list=  page.getList();
      
      for(UnitCatalog u:list){
        if(null==u.getParent()||"0".equals(u.getParent().getId())){
          model.addAttribute("unitCatalog", u);
        }
      }
        
		return "modules/" + "asmtUnitCatalog/anewUnitCataLogList";
	}

	@RequiresPermissions("asmt:anewUnitCataLog:view")
	@RequestMapping(value = "form")
	public String form(UnitCatalog unitCatalog, Model model) {
    if (unitCatalog.getParent()==null||unitCatalog.getParent().getId()==null){
      unitCatalog.setParent(new UnitCatalog("0"));
    }
    unitCatalog.setParent(unitCatalogService.get(unitCatalog.getParent().getId()));
     Assement asmt = AsmtUtils.getCurrAssement();
      User user = UserUtils.getUser();
      if (!user.isAdmin()){
        unitCatalog.setCreateBy(user);
      }
      AssementUser asmtUser =assementUserService.findByAssementAndUserId(asmt, user);
      unitCatalog.setAssementUser(asmtUser);
    model.addAttribute("unitCatalog", unitCatalog);
		return "modules/" + "asmtUnitCatalog/anewUnitCataLogForm";
	}

	@RequiresPermissions("asmt:anewUnitCataLog:edit")
	@RequestMapping(value = "save")
	public String save(@RequestParam(value = "file") MultipartFile file,UnitCatalog unitCatalog, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
    Map<String, Object> map = new HashMap<String, Object>();  
    if (!beanValidator(model, unitCatalog)){
      return form(unitCatalog, model);
    }

    String path = request.getSession().getServletContext().getRealPath("upload");  
    String fileName = file.getOriginalFilename(); 
    
   
    File targetFile = new File(path, fileName);  
    if(!targetFile.exists()){  
        targetFile.mkdirs();  
    }  

    //保存  
    try {  
        file.transferTo(targetFile);  
    } catch (Exception e) {  
        e.printStackTrace();  
    }  

    unitCatalog.setCachetUrl(request.getContextPath()+"/upload/"+fileName);
    unitCatalogService.save(unitCatalog);
    addMessage(redirectAttributes, "保存单位目录'" + unitCatalog.getName() + "'成功");
    return "redirect:"+Global.getAdminPath()+"/asmt/anewUnitCataLog/?repage";
  }
  
  @RequiresPermissions("asmt:unitCatalog:edit")
  @RequestMapping(value = "delete")
  public String delete(String id, RedirectAttributes redirectAttributes) {
    unitCatalogService.delete(id);
    addMessage(redirectAttributes, "删除单位目录成功");
		return "redirect:"+Global.getAdminPath()+"/asmt/anewUnitCataLog/?repage";
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
//    String extId="";
//    if(null==unitCatalog.getAssementUser())
//     asmUserId="00f1c89106434496b2845597d5edca19";
//    else
//       asmUserId=unitCatalog.getAssementUser().getId();
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
