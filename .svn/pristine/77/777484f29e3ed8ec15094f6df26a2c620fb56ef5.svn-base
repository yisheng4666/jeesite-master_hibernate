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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.asmt.dao.RankDetailDao;
import com.thinkgem.jeesite.modules.asmt.entity.RankDetail;

/**
 * @author JianHui
 * @date 2017年09月16日--下午03:53:07
 */

@Component
@Transactional(readOnly = true)
public class RankDetailService extends BaseService {

	@Autowired
	private RankDetailDao rankDetailDao;

	public RankDetail get(String id) {
		return rankDetailDao.get(id);
	}

	public List<RankDetail> findAll() {
		return rankDetailDao.findAll();
	}

	public Page<RankDetail> find(Page<RankDetail> page, RankDetail rankDetail) {
		DetachedCriteria dc = rankDetailDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(rankDetail.getUserName())) {
			dc.add(Restrictions.like("name", "%" + rankDetail.getUserName() + "%"));
		}
		dc.add(Restrictions.eq(RankDetail.FIELD_DEL_FLAG, RankDetail.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return rankDetailDao.find(page, dc);
	}

	@Transactional(readOnly = true)
	public void save(RankDetail rankDetail) {
		rankDetailDao.save(rankDetail);
	}

	@Transactional(readOnly = true)
	public void delete(String id) {
		rankDetailDao.deleteById(id);
	}

}