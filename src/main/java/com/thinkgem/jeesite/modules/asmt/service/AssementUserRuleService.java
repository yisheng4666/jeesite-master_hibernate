package com.thinkgem.jeesite.modules.asmt.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.asmt.dao.AssementUserDao;
import com.thinkgem.jeesite.modules.asmt.dao.AssementUserRuleDao;
import com.thinkgem.jeesite.modules.asmt.entity.AssementUser;
import com.thinkgem.jeesite.modules.asmt.entity.AssementUserRule;
import com.thinkgem.jeesite.modules.asmt.entity.Rule;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * @author JianHui
 * @date 2017年10月25日--下午05:02:59
 */

@Component
@Transactional(readOnly = true)
public class AssementUserRuleService extends BaseService {

	@Autowired
	private AssementUserRuleDao assementUserRuleDao;
	
	@Autowired
	private AssementUserDao assementUserDao;
	
	@Autowired
	private SystemService systemService;

	/**
	 * 根据被考评用户查询数据
	 * 
	 * @author JianHui
	 * @param auIds
	 *            被考评用户ID数组
	 * @return
	 */
	public List<AssementUserRule> find(String[] auIds) {
		DetachedCriteria dc = assementUserRuleDao.createDetachedCriteria();
		if (auIds != null) {
			dc.add(Restrictions.in("assementUser.id", auIds));
		}
		return assementUserRuleDao.find(dc);
	}
	
	/**
	 * 查询考评用户加分规则
	 * 
	 * @author lucy
	 * @param auIds
	 * @return
	 */
	public List<AssementUserRule> find(String auIds){
	  DetachedCriteria dc = assementUserRuleDao.createDetachedCriteria();
    if (dc != null) {
      dc.add(Restrictions.eq("assementUser.id", auIds));
      dc.createAlias("rule", "rule"); 
      dc.add(Restrictions.like("rule.type", "%"+2+"%"));
    }
    return assementUserRuleDao.find(dc);
	}
	
	public List<AssementUserRule> findByAsmtUserAndRule(String uId,String rId){
	  return assementUserRuleDao.findByAsmtUserAndRule(uId, rId);
	}
	@Transactional(readOnly = false)
	public void saveAllByRule(Rule rule) {
		List<AssementUserRule> assementUserRules = assementUserRuleDao.findByRule(rule.getId());
		List<AssementUser> extraAssementUsers = Lists.newArrayList();
		for (AssementUserRule assementUserRule : assementUserRules) { // 获得已存在的被考评用户规则
			extraAssementUsers.add(assementUserRule.getAssementUser());
		}
		
		Role groupRole = systemService.findRoleByName("组长单位"); // 获得组织单位角色
		boolean isContainGroupRole = rule.getRoleList().contains(groupRole); // 判断是否为组长单位评分规则
		
		List<AssementUser> assementUsers = assementUserDao.findByAssement(rule.getAssement().getId());
		assementUsers.removeAll(extraAssementUsers);
		
		for (AssementUser assementUser : assementUsers) { // 保存未添加的被考评用户规则
			AssementUserRule assementUserRule = new AssementUserRule();
			assementUserRule.setAssementUser(assementUser);
			assementUserRule.setRule(rule);
			assementUserRule.setIsGroupAssement(isContainGroupRole ? AssementUserRule.YES : AssementUserRule.NO);
			
			assementUserRuleDao.save(assementUserRule);
		}
		
		assementUserRuleDao.flush();
	}

	@Transactional(readOnly = false)
	public void deleteAllByRuleId(String id) {
		List<AssementUserRule> assementUserRules = assementUserRuleDao.findByRule(id);
		for (AssementUserRule assementUserRule : assementUserRules) {
			assementUserRuleDao.deleteById(assementUserRule.getId());
		}
		
		assementUserRuleDao.flush();
	}
}