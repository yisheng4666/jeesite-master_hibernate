/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
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
import com.thinkgem.jeesite.modules.asmt.dao.UnitCatalogDao;
import com.thinkgem.jeesite.modules.asmt.entity.AssementUser;
import com.thinkgem.jeesite.modules.asmt.entity.UnitCatalog;

/**
 * 单位目录Service
 * @author lcy
 * @version 2017-10-13
 */
@Component
@Transactional(readOnly = true)
public class UnitCatalogService extends BaseService {

	@Autowired
	private UnitCatalogDao unitCatalogDao;
	
	public UnitCatalog get(String id) {
		return unitCatalogDao.get(id);
	}
	
	public Page<UnitCatalog> find(Page<UnitCatalog> page, UnitCatalog unitCatalog) {
		DetachedCriteria dc = unitCatalogDao.createDetachedCriteria();
		if(null != unitCatalog.getAssementUser() && StringUtils.isNotEmpty(unitCatalog.getAssementUser().getId())){
		dc.add(Restrictions.eq(UnitCatalog.FIELD_DEL_FLAG, UnitCatalog.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("assementUser.id", unitCatalog.getAssementUser().getId()));
		dc.addOrder(Order.asc("organizationNo"));
		return unitCatalogDao.find(page, dc);
		}
		return page;
	}
	/**
	 * 
	 * @param page
	 * @param unitCatalog
	 * @return 
	 */
  public Page<UnitCatalog> findByGroup(Page<UnitCatalog> page, UnitCatalog unitCatalog) {
    DetachedCriteria dc = unitCatalogDao.createDetachedCriteria();
    
   
    if (StringUtils.isNotEmpty(unitCatalog.getName())){
      dc.add(Restrictions.like("name", "%"+unitCatalog.getName()+"%"));
    }
    
    if (null!=unitCatalog.getAssementUser() && StringUtils.isNotEmpty(unitCatalog.getAssementUser().getOffice().getName())){
      dc.createAlias("assementUser", "assementUser"); 
      dc.createAlias("assementUser.office", "office");
      dc.add(Restrictions.like("office.name", "%"+unitCatalog.getAssementUser().getOffice().getName()+"%"));
    }
    if (StringUtils.isNotEmpty(unitCatalog.getState())){
    dc.add(Restrictions.eq("state", unitCatalog.getState()));
    }

    dc.add(Restrictions.eq(UnitCatalog.FIELD_DEL_FLAG, UnitCatalog.DEL_FLAG_NORMAL));
    dc.add(Restrictions.isNull("parent"));

    dc.addOrder(Order.asc("organizationNo"));
   
    return unitCatalogDao.find(page, dc);
//    }
//    return page;
  }
	
	@Transactional(readOnly = false)
	public void save(UnitCatalog unitCatalog) {
    
//    if (null == unitCatalog.getParent() || StringUtils.isEmpty(unitCatalog.getParent().getId())
//        || "0".equals(unitCatalog.getParent().getId()) ) {
//      UnitCatalog prule = new UnitCatalog();
//      prule.setId("0");
//      unitCatalog.setParent(prule);
//      unitCatalogDao.save(unitCatalog);
//    } else {
	    if(null!=unitCatalog.getParent())
      unitCatalog.setParent(this.get(unitCatalog.getParent().getId()));
	    
      unitCatalogDao.clear();
      unitCatalogDao.save(unitCatalog);
//    }
		
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		unitCatalogDao.deleteById(id);
	}

  /**
   * @param id
   * @return
   */
  public List<UnitCatalog> findByassementUser(String id) {
    return unitCatalogDao.findByassementUser(id);
  }
  
  public List<UnitCatalog>  findByassementUser(AssementUser asmtUser){
    
      return unitCatalogDao.findByassementUserandparent(asmtUser.getId());
   
  }
	
	public List<UnitCatalog> findAllListByCurrAssement() {
		return unitCatalogDao.findAllListByCurrAssement();
	}
	
}
