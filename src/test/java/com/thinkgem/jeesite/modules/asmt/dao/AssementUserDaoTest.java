package com.thinkgem.jeesite.modules.asmt.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.test.SpringTransactionalContextTests;
import com.thinkgem.jeesite.modules.asmt.entity.AssementUser;

/**
 * 
 * @author JianHui
 * @date 2017年9月14日--下午12:03:46
 *
 */

public class AssementUserDaoTest extends SpringTransactionalContextTests {

	@Autowired
	AssementUserDao assementUserDao;

	@Test
	public void testFindByAsseent() {
		String id = "9ed5c421ade3409682baca2783a0c873";
		List<AssementUser> list = assementUserDao.findByAsseent(id);
	}

}
