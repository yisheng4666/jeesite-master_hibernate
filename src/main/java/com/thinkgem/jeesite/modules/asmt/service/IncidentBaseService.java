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
import com.thinkgem.jeesite.modules.asmt.dao.IncidentBaseDao;
import com.thinkgem.jeesite.modules.asmt.entity.IncidentBase;

/**
 * @author JianHui
 * @date 2017年10月26日--下午02:21:55
 */

@Component
@Transactional(readOnly = true)
public class IncidentBaseService extends BaseService{

    @Autowired
    private IncidentBaseDao incidentBaseDao;

    public IncidentBase get(String id) {
        return incidentBaseDao.get(id);
    }

    public List<IncidentBase> findAll() {
        return incidentBaseDao.findAll();
    }

    public Page<IncidentBase> find(Page<IncidentBase> page, IncidentBase incidentBase) {
        DetachedCriteria dc = incidentBaseDao.createDetachedCriteria();
//        if (StringUtils.isNotEmpty(incidentBase.getName())) {
//            dc.add(Restrictions.like("name", incidentBase.getName(), MatchMode.ANYWHERE));
//        }
        dc.add(Restrictions.eq(IncidentBase.FIELD_DEL_FLAG, IncidentBase.DEL_FLAG_NORMAL));
        dc.addOrder(Order.desc("id"));
        return incidentBaseDao.find(page, dc);
    }

    @Transactional(readOnly = true)
    public void save(IncidentBase incidentBase) {
        incidentBaseDao.save(incidentBase);
    }

    @Transactional(readOnly = true)
    public void delete(String id) {
        incidentBaseDao.deleteById(id);
    }

}