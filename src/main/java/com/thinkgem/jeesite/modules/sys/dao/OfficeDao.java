/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * 
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class OfficeDao extends BaseDao<Office> {

	public List<Office> findByParentIdsLike(String parentIds) {
		return find("from Office where parentIds like :p1", new Parameter(parentIds));
	}

	/**
	 * 根据考评查询考评组
	 * 
	 * @author JianHui
	 * @param assement
	 *            考评
	 * @return
	 */
	public List<Office> findByAssement(Assement assement) {
		return find("from Office where assement.id = :p1 order by sort asc", new Parameter(assement.getId()));
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
		Object obj = uniqueResult("select max(sort) from Office where assement.id = :p1",
				new Parameter(assement.getId()));
		if (obj == null) {
			return 0;
		} else {
			return Integer.parseInt(obj.toString());
		}
	}

	// @Query("from Office where (id=?1 or parent.id=?1 or parentIds like ?2) and
	// delFlag='" + Office.DEL_FLAG_NORMAL + "' order by code")
	// public List<Office> findAllChild(Long parentId, String likeParentIds);

}
