package com.thinkgem.jeesite.modules.asmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.asmt.entity.AssementUser;
import com.thinkgem.jeesite.modules.asmt.entity.AssementUserRule;

/**
 * @author JianHui
 * @date 2017年10月25日--下午05:02:59
 */

@Repository
public class AssementUserRuleDao extends BaseDao<AssementUserRule> {

	public List<AssementUserRule> findByRule(String id) {
		return find("from AssementUserRule u where u.rule.id=:p1 and delFlag=:p2",
				new Parameter(id, AssementUser.DEL_FLAG_NORMAL));
	}
	
	public List<AssementUserRule> findByAsmtUserAndRule(String uId,String rId){
	   return find("from AssementUserRule u where u.assementUser.id=:p1 and u.rule.id=:p2 and delFlag=:p3", new Parameter(uId,rId, AssementUser.DEL_FLAG_NORMAL)); 
	}

}