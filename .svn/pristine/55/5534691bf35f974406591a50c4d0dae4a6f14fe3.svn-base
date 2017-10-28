package com.thinkgem.jeesite.modules.asmt.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.test.BaseServiceJunit4Test;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;

/**
 * 
 * @author JianHui
 * @date 2017年9月7日--下午4:51:32
 *
 */

public class AssementServiceTest extends BaseServiceJunit4Test {

	@Autowired
	AssementService assementService;

	@Test
	public void testGet() {
		String id = "4b618ad260804265a895a6c3dc72ab23";
		Assement assement = assementService.get(id);
		assertEquals(id, assement.getId());
		System.out.println(assement);
	}

	@Test
	public void testFindAll() {
		List<Assement> list = assementService.findAll();
		System.out.println(list.toArray());
	}

	@Test
	public void testFind() {
		Page<Assement> page = new Page<Assement>();
		page.setPageNo(1);
		page.setPageSize(10);
		Assement assement = new Assement();
		assement.setName("2017年考评");
		Page<Assement> list = assementService.find(page, assement);
		assertEquals("2017年考评", list.getList().get(0).getName());
	}

	@Test
	public void testSave() {
		Assement assement = new Assement();
		assement.setName("单元测试");
		assement.setYear("2017");
		assement.setIsPublish("0");
		assement.setRemarks("单元测试数据");
		assementService.save(assement);
		assertNotEquals(null, assement.getId());
	}

	@Test
	public void testDelete() {
		Assement assement = new Assement();
		assement.setName("单元测试");
		assement.setYear("2017");
		assement.setIsPublish("0");
		assement.setRemarks("单元测试数据");
		assementService.save(assement);
		String id = assement.getId();
		System.out.println(id);
		assementService.delete(id);
		Assement assement2 = assementService.get(id);
		Assert.assertNotNull(assement2);
	}
}
