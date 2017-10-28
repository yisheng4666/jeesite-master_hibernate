package com.thinkgem.jeesite.modules.asmt.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.asmt.dao.AssementUserRuleDao;
import com.thinkgem.jeesite.modules.asmt.entity.AssementUserRule;

/**
 * @author JianHui
 * @date 2017年10月25日--下午05:02:59
 */

@Component
@Transactional(readOnly = true)
public class AssementUserRuleService extends BaseService {

	@Autowired
	private AssementUserRuleDao assementUserRuleDao;

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
		if (dc != null) {
			dc.add(Restrictions.in("assementUser.id", auIds));
		}
		return assementUserRuleDao.find(dc);
	}

}