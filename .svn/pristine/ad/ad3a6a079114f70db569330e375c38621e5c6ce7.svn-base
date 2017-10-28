package com.thinkgem.jeesite.modules.asmt.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.asmt.dao.IncidentBaseObjectionDao;
import com.thinkgem.jeesite.modules.asmt.entity.IncidentBaseObjection;

/**
 * @author JianHui
 * @date 2017年10月26日--下午02:43:14
 */

@Component
@Transactional(readOnly = true)
public class IncidentBaseObjectionService extends BaseService{

    @Autowired
    private IncidentBaseObjectionDao incidentBaseObjectionDao;

    public IncidentBaseObjection get(String id) {
        return incidentBaseObjectionDao.get(id);
    }

    public List<IncidentBaseObjection> findAll() {
        return incidentBaseObjectionDao.findAll();
    }

    public Page<IncidentBaseObjection> find(Page<IncidentBaseObjection> page, IncidentBaseObjection incidentBaseObjection) {
        DetachedCriteria dc = incidentBaseObjectionDao.createDetachedCriteria();
//        if (StringUtils.isNotEmpty(incidentBaseObjection.getName())) {
//            dc.add(Restrictions.like("name", incidentBaseObjection.getName(), MatchMode.ANYWHERE));
//        }
        dc.add(Restrictions.eq(IncidentBaseObjection.FIELD_DEL_FLAG, IncidentBaseObjection.DEL_FLAG_NORMAL));
        dc.addOrder(Order.desc("id"));
        return incidentBaseObjectionDao.find(page, dc);
    }

    @Transactional(readOnly = true)
    public void save(IncidentBaseObjection incidentBaseObjection) {
        incidentBaseObjectionDao.save(incidentBaseObjection);
    }

    @Transactional(readOnly = true)
    public void delete(String id) {
        incidentBaseObjectionDao.deleteById(id);
    }

}