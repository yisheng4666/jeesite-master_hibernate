package com.thinkgem.jeesite.modules.asmt.web;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.thinkgem.jeesite.common.test.BaseControllerTest;

/**
 * 
 * @author JianHui
 * @date 2017年9月14日--下午2:59:12
 *
 */

public class AssementUserControllerTest extends BaseControllerTest {

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAssembleList() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopyProperties() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOfficeConvertAssementUserTree() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsExist() {
		fail("Not yet implemented");
	}

	@Test
	public void testForm() {
		fail("Not yet implemented");
	}

	@Test
	public void testImportUser() throws Exception {
		String assementId = "9ed5c421ade3409682baca2783a0c873";
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/a/asmt/assementUser/importUser")
				.param("id", assementId).session(mockSession));
		MvcResult mvcResult = resultActions.andReturn();
		String resultView = mvcResult.getModelAndView().getViewName();
		Assert.assertEquals("测试失败，返回字符串不对", "redirect:/a/asmt/assementUser/?repage", resultView);
	}

}
