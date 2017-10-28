package com.thinkgem.jeesite.modules.sys.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.test.SpringTransactionalContextTests;

/**
 * 
 * @author JianHui
 * @date 2017年9月14日--上午10:25:35
 *
 */

public class UserDaoTest extends SpringTransactionalContextTests {

	@Autowired
	UserDao userDao;

	// @Test
	// public void testFindByOffice() {
	// List<User> users = userDao.findByOffice();
	// // 判断是否有【所有组】的用户
	// boolean flag = true;
	// for (User user : users) {
	// System.out.println(user.getOffice().getName());
	// if ("所有组".equals(user.getOffice().getName())) {
	// flag = false;
	// break;
	// }
	// }
	// Assert.assertTrue("测试失败：有【所有组】的用户", flag);
	// }

}
