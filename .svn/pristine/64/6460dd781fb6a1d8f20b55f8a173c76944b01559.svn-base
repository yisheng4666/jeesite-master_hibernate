/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 机构Service
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends BaseService {

	@Autowired
	private OfficeDao officeDao;

	public Office get(String id) {
		return officeDao.get(id);
	}

	public List<Office> findAll() {
		return UserUtils.getOfficeList();
	}

	/**
	 * 查询当前考评中的考评组
	 * 
	 * @author JianHui
	 * @param assement考评
	 * @return
	 */
	public List<Office> findByAssement(Assement assement) {
		return officeDao.findByAssement(assement);
	}

	/**
	 * 查询当前考评中的考评组（分页）
	 * 
	 * @author JianHui
	 * @param page
	 * @param assement
	 * @param officeName
	 * @return
	 */
	public Page<Office> findByAssement(Page<Office> page, Assement assement, String officeName) {
		DetachedCriteria dc = officeDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(assement.getId())) {
			dc.add(Restrictions.eq("assement.id", assement.getId()));
		}
		if (StringUtils.isNotEmpty(officeName)) {
			dc.add(Restrictions.like("name", officeName));
		}
		dc.add(Restrictions.eq(Office.FIELD_DEL_FLAG, Office.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("sort"));
		return officeDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(Office office) {
		office.setParent(this.get(office.getParent().getId()));
		String oldParentIds = office.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		office.setParentIds(office.getParent().getParentIds() + office.getParent().getId() + ",");
		officeDao.clear();
		officeDao.save(office);
		// 更新子节点 parentIds
		List<Office> list = officeDao.findByParentIdsLike("%," + office.getId() + ",%");
		for (Office e : list) {
			e.setParentIds(e.getParentIds().replace(oldParentIds, office.getParentIds()));
		}
		officeDao.save(list);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		officeDao.deleteById(id, "%," + id + ",%");
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	/**
	 * 查询当前考评组中最大的排序值
	 * 
	 * @author JianHui
	 * @param assement
	 *            考评
	 * @return
	 */
	public Integer findMaxSortByAssement(Assement assement) {
		return officeDao.findMaxSortByAssement(assement);
	}

}
