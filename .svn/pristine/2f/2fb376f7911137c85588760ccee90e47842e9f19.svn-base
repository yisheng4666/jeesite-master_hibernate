/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.asmt.entity.AnewUnitCataLog;
import com.thinkgem.jeesite.modules.asmt.dao.AnewUnitCataLogDao;

/**
 * 单位列表Service
 * @author Lucy
 * @version 2017-10-17
 */
@Component
@Transactional(readOnly = true)
public class AnewUnitCataLogService extends BaseService {

	@Autowired
	private AnewUnitCataLogDao anewUnitCataLogDao;
	
	public AnewUnitCataLog get(String id) {
		return anewUnitCataLogDao.get(id);
	}
	
	public Page<AnewUnitCataLog> find(Page<AnewUnitCataLog> page, AnewUnitCataLog anewUnitCataLog) {
		DetachedCriteria dc = anewUnitCataLogDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(anewUnitCataLog.getName())){
			dc.add(Restrictions.like("name", "%"+anewUnitCataLog.getName()+"%"));
		}
		dc.add(Restrictions.eq(AnewUnitCataLog.FIELD_DEL_FLAG, AnewUnitCataLog.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return anewUnitCataLogDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(AnewUnitCataLog anewUnitCataLog) {
		anewUnitCataLogDao.save(anewUnitCataLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		anewUnitCataLogDao.deleteById(id);
	}
	
}
