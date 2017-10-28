/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.asmt.entity.UnitCatalog;

/**
 * 单位目录DAO接口
 * @author lcy
 * @version 2017-10-13
 */
@Repository
public class UnitCatalogDao extends BaseDao<UnitCatalog> {

  public List<UnitCatalog> findByassementUser(String asmUserId) {
    return find("from UnitCatalog where assementUser.id=:p1 order by organization_no", new Parameter(asmUserId));
  }
  
  public List<UnitCatalog> findByassementUserandparent(String asmUserId) {
    return find("from UnitCatalog where assementUser.id=:p1 and parent.id='0' order by organization_no", new Parameter(asmUserId));
  }
	/**
	 * 获得当前考评下的所有被考评单位下的单位目录
	 * @return
	 */
	public List<UnitCatalog> findAllListByCurrAssement() {
		return find("select distinct uc from UnitCatalog uc, AssementUser au, Assement a where uc in elements (au.unitCatalogList) and au in elements (a.assementUserList)"
				+ " and uc.delFlag=:p1 and au.delFlag=:p1 and a.delFlag=:p1 and a.status=:p2 order by uc.name", new Parameter(UnitCatalog.DEL_FLAG_NORMAL, Assement.STATUS_OPENING));
	}
}
