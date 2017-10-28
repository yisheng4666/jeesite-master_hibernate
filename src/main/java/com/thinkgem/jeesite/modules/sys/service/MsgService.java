/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.sys.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.sys.dao.MsgDao;
import com.thinkgem.jeesite.modules.sys.entity.Msg;

/**
 * 消息Service
 * @author Yyz
 * @version 2017-10-23
 */
@Component
@Transactional(readOnly = true)
public class MsgService extends BaseService {

	@Autowired
	private MsgDao msgDao;
	
	public Msg get(String id) {
		return msgDao.get(id);
	}
	
	public Page<Msg> find(Page<Msg> page, Msg msg) {
		DetachedCriteria dc = msgDao.createDetachedCriteria();
//		if (StringUtils.isNotEmpty(msg.getName())){
//			dc.add(Restrictions.like("name", "%"+msg.getName()+"%"));
//		}
		dc.add(Restrictions.eq(Msg.FIELD_DEL_FLAG, Msg.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return msgDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Msg msg) {
		msgDao.save(msg);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		msgDao.deleteById(id);
	}
	
}
