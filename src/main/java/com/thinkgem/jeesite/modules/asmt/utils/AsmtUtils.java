package com.thinkgem.jeesite.modules.asmt.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.asmt.dao.AssementDao;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;

/**
 * 考评工具类
 * 
 * @author Yyz
 * @version 2017-09-27
 */
public class AsmtUtils {

	private static AssementDao asmtDao = SpringContextHolder.getBean(AssementDao.class);

	/**
	 * 获取当前考评
	 * 
	 * @return
	 */
	public static Assement getCurrAssement() {
		DetachedCriteria dc = asmtDao.createDetachedCriteria();
		dc.add(Restrictions.eq(Assement.FIELD_DEL_FLAG, Assement.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("status", Assement.STATUS_OPENING));

		List<Assement> list = asmtDao.find(dc);
		if (!list.isEmpty())
			return list.get(0);

		return null;
	}
}
