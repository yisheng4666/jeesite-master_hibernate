/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.asmt.dao.EntryIncidentDao;
import com.thinkgem.jeesite.modules.asmt.entity.EntryIncident;

/**
 * 事件录入Service
 * @author Yyz
 * @version 2017-10-12
 */
@Component
@Transactional(readOnly = true)
public class EntryIncidentService extends BaseService {

	@Autowired
	private EntryIncidentDao entryIncidentDao;
	
	public EntryIncident get(String id) {
		return entryIncidentDao.get(id);
	}
	
	public Page<EntryIncident> find(Page<EntryIncident> page, EntryIncident entryIncident) {
		DetachedCriteria dc = entryIncidentDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(entryIncident.getContent())){
			dc.add(Restrictions.like("name", "%"+entryIncident.getContent()+"%"));
		}
		dc.add(Restrictions.eq(EntryIncident.FIELD_DEL_FLAG, EntryIncident.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return entryIncidentDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EntryIncident entryIncident) {
		entryIncidentDao.save(entryIncident);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		entryIncidentDao.deleteById(id);
	}
	
}
