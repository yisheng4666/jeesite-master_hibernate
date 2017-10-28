/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;

/**
 * 考评DAO接口
 * 
 * @author JianHui
 * @date 2017年9月5日--下午3:05:30
 *
 */
@Repository
public class AssementDao extends BaseDao<Assement> {

	/**
	 * 查询最近一条已完成的考评记录
	 * 
	 * @return
	 */
	public Assement getMaxAssement() {
		// TODO Auto-generated method stub
		return getByHql("from Assement where createDate=(select max(createDate) from Assement where status=2)");
	}
}
