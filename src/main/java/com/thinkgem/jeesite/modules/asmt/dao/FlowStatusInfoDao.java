package com.thinkgem.jeesite.modules.asmt.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.asmt.entity.FlowStatusInfo;

/**
 * @author JianHui
 * @date 2017年10月20日--上午11:21:41
 */

@Repository
public class FlowStatusInfoDao extends BaseDao<FlowStatusInfo> {

	/**
	 * 根据考评、流程发起用户、流程类型查询流程信息
	 * 
	 * @author JianHui
	 * @param assement
	 *            考评
	 * @param userId
	 *            流程发起用户ID
	 * @param flowType
	 *            流程类型
	 * @return 流程信息
	 */
	public FlowStatusInfo findByType(Assement assement, String userId, String flowType) {
		return getByHql(
				"from FlowStatusInfo where assement.id = :p1 and user.id = :p2 and flowType = :p3 and delFlag = :p4",
				new Parameter(assement.getId(), userId, flowType, FlowStatusInfo.DEL_FLAG_NORMAL));
	}

}