package com.thinkgem.jeesite.modules.asmt.service;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.test.BaseServiceJunit4Test;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;
import com.thinkgem.jeesite.modules.asmt.entity.AssementUser;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 
 * @author JianHui
 * @date 2017年9月9日--下午3:24:54
 *
 */

public class AssementUserserviceTest extends BaseServiceJunit4Test {

	@Autowired
	AssementUserService assementUserservice;

	@Test
	public void testGet() {
		String id = "";
		AssementUser assementUser = assementUserservice.get(id);
		Assert.assertNull(assementUser);
	}

	@Test
	public void testFindTree() {
		String id = "";
		List<AssementUser> list = assementUserservice.find(id);
	}

	@Test
	public void testSave() {
		AssementUser au = new AssementUser();
		Assement assement = new Assement();
		assement.setId("9ed5c421ade3409682baca2783a0c873");
		Office office = new Office();
		office.setId("4");
		User user = new User();
		user.setId("685eceb3ceb14342ba6ac2cf7f5635cf");
		au.setAssement(assement);
		au.setOffice(office);
		au.setUser(user);
		au.setUserName("aa");
		au.setOrganizationNo("200");
		assementUserservice.save(au);
		assertNotEquals("测试失败：保存数据不成功", null, au.getId());
	}

	@Test
	public void testDelete() {
		AssementUser au = new AssementUser();
		Assement assement = new Assement();
		assement.setId("9ed5c421ade3409682baca2783a0c873");
		Office office = new Office();
		office.setId("4");
		User user = new User();
		user.setId("685eceb3ceb14342ba6ac2cf7f5635cf");
		au.setAssement(assement);
		au.setOffice(office);
		au.setUser(user);
		au.setUserName("aa");
		au.setOrganizationNo("200");
		assementUserservice.save(au);
		String id = au.getId();
		System.out.println(id);
		assementUserservice.delete(id);
		AssementUser au2 = assementUserservice.get(id);
		Assert.assertNotNull("测试失败：删除数据失败", au2);
	}

}
