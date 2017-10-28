package com.thinkgem.jeesite.modules.asmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.asmt.entity.RuleUser;

/**
 * 规则用户DAO接口
 * @author Yyz
 * @version 2017-08-25
 */
@Repository
public class RuleUserDao extends BaseDao<RuleUser> {

	public List<RuleUser> findByRuleId(String ruleId) {
		return find("from RuleUser where rule.id=:p1 order by useType", new Parameter(ruleId));
	}
}
