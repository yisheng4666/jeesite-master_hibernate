/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.asmt.dao.AssementDao;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;

/**
 * 考评Service
 * 
 * @author JianHui 2017年9月5日--下午3:09:29
 *
 */

@Component
@Transactional(readOnly = true)
public class AssementService extends BaseService {

	@Autowired
	private AssementDao assementDao;

	/**
	 * 获取实体
	 * 
	 * @author JianHui
	 * @param id
	 *            实体ID
	 * @return 返回一个实体
	 */
	public Assement get(String id) {
		return assementDao.get(id);
	}

	/**
	 * 查询所有
	 * 
	 * @author JianHui
	 * @return 返回一个实体List集合
	 */
	public List<Assement> findAll() {
		return assementDao.findAll();
	}

	/**
	 * 使用检索标准对象分页查询
	 * 
	 * @author JianHui
	 * @param page
	 *            分页
	 * @param assement
	 *            实体条件
	 * @return 分页集合
	 */
	public Page<Assement> find(Page<Assement> page, Assement assement) {
		DetachedCriteria dc = assementDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(assement.getName())) {
			dc.add(Restrictions.like("name", "%" + assement.getName() + "%"));
		}
		dc.add(Restrictions.eq(Assement.FIELD_DEL_FLAG, Assement.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return assementDao.find(page, dc);
	}

	/**
	 * 保存实体
	 * 
	 * @author JianHui
	 * @param assement
	 *            实体
	 */
	@Transactional(readOnly = false)
	public void save(Assement assement) {
		assementDao.save(assement);
	}

	/**
	 * 删除实体
	 * 
	 * @author JianHui
	 * @param id
	 *            实体ID
	 */
	@Transactional(readOnly = false)
	public void delete(String id) {
		assementDao.deleteById(id);
	}

	/**
	 * 查询所有考评
	 * 
	 * @author JianHui
	 * @return 返回一个map集合
	 */
	public Map<String, String> findAllBySelect() {
		DetachedCriteria dc = assementDao.createDetachedCriteria();
		dc.add(Restrictions.eq(Assement.FIELD_DEL_FLAG, Assement.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("year"));
		dc.addOrder(Order.desc("id"));

		List<Assement> list = assementDao.find(dc);
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Assement asmt : list) {
			map.put(asmt.getId(), asmt.getName() + "[" + asmt.getYear() + "]");
		}
		return map;
	}

	/**
	 * 获得最近一次已完成的考评
	 * 
	 * @return 返回一个考评
	 */
	public Assement getMaxAssement() {
		Assement assement = assementDao.getMaxAssement();
		return assement;
	}

}
