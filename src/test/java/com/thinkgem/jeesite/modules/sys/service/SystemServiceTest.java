package com.thinkgem.jeesite.modules.sys.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.test.BaseServiceJunit4Test;

/**
 * 
 * @author JianHui
 * @date 2017年9月14日--上午10:45:28
 *
 */

public class SystemServiceTest extends BaseServiceJunit4Test {

	@Autowired
	SystemService systemService;

	// @Test
	// public void testFindByOffice() {
	// List<User> users = systemService.findByOffice();
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
