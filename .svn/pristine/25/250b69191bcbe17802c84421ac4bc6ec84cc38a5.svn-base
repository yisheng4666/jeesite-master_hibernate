package com.thinkgem.jeesite.modules.asmt.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.asmt.dao.FlowStatusInfoDao;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.asmt.entity.FlowStatusInfo;

/**
 * @author JianHui
 * @date 2017年10月20日--上午11:21:41
 */

@Component
@Transactional(readOnly = true)
public class FlowStatusInfoService extends BaseService {

	@Autowired
	private FlowStatusInfoDao flowStatusInfoDao;

	public FlowStatusInfo get(String id) {
		return flowStatusInfoDao.get(id);
	}

	public List<FlowStatusInfo> findAll() {
		return flowStatusInfoDao.findAll();
	}

	public Page<FlowStatusInfo> find(Page<FlowStatusInfo> page, FlowStatusInfo flowStatusInfo) {
		DetachedCriteria dc = flowStatusInfoDao.createDetachedCriteria();
		// if (StringUtils.isNotEmpty(flowStatusInfo.getName())) {
		// dc.add(Restrictions.like("name", "%" + flowStatusInfo.getName() + "%"));
		// }
		dc.add(Restrictions.eq(FlowStatusInfo.FIELD_DEL_FLAG, FlowStatusInfo.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return flowStatusInfoDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(FlowStatusInfo flowStatusInfo) {
		flowStatusInfoDao.save(flowStatusInfo);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		flowStatusInfoDao.deleteById(id);
	}

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
		FlowStatusInfo fsi = flowStatusInfoDao.findByType(assement, userId, flowType);
		return fsi;
	}

	/**
	 * 根据考评和流程类型查询流程状态信息
	 * 
	 * @author JianHui
	 * @param assement
	 *            考评
	 * @param flowType
	 *            流程类型
	 * @return
	 */
	public List<FlowStatusInfo> findByType(Assement assement, String flowType) {
		DetachedCriteria dc = flowStatusInfoDao.createDetachedCriteria();
		dc.add(Restrictions.eq("assement.id", assement.getId()));
		dc.add(Restrictions.eq("flowType", flowType));
		dc.add(Restrictions.eq(FlowStatusInfo.FIELD_DEL_FLAG, FlowStatusInfo.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("id"));
		return flowStatusInfoDao.find(dc);
	}

}