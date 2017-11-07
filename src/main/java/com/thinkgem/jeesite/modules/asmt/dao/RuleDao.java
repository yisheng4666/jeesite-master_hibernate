/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.asmt.entity.Rule;

/**
 * 考评规则DAO接口
 * @author Yyz
 * @version 2017-08-25
 */
@Repository
public class RuleDao extends BaseDao<Rule> {
	
	public List<Rule> findByAssementId(String asmtId) {
		return find("from Rule where assement.id=:p1 order by sort", new Parameter(asmtId));
	}
	
	public List<Rule> findByParentIdsLike(String parentIds){
		return find("from Menu where parentIds like :p1", new Parameter(parentIds));
	}
	
	public Integer findMaxSortByRule(Assement assement) {
		Object obj = uniqueResult("select max(sort) from Rule where assement.id = :p1",
				new Parameter(assement.getId()));
		if (obj == null) {
			return 0;
		} else {
			return Integer.parseInt(obj.toString());
		}
	}

}
