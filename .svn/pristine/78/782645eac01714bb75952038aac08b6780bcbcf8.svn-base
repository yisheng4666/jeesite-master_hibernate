package com.thinkgem.jeesite.modules.asmt.web;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.thinkgem.jeesite.common.test.BaseControllerTest;
import com.thinkgem.jeesite.modules.asmt.entity.Assement;

/**
 * 
 * @author JianHui
 * @date 2017年9月13日--上午11:38:29
 *
 */

public class AssementControllerTest extends BaseControllerTest {

	@Test
	public void testList() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.get("/a/asmt/assement/list").session(mockSession));
		MvcResult mvcResult = resultActions.andReturn();
		Map<String, Object> resultModel = mvcResult.getModelAndView().getModel();
		List<Assement> assements = (List<Assement>) resultModel.get("list");
		// 判断是否有【2017考评测试】这个考评
		boolean flag = false;
		for (Assement assement : assements) {
			if ("2017考评测试".equals(assement.getName())) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue("测试失败：无【2017考评测试】这个考评", flag);
	}

	@Test
	public void testForm() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.post("/a/asmt/assement/form").session(mockSession));
		MvcResult mvcResult = resultActions.andReturn();
		String resultView = mvcResult.getModelAndView().getViewName();
		Assert.assertEquals("测试失败，返回字符串不对", "modules/asmt/assementForm", resultView);
	}

	@Test
	public void testSave() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/a/asmt/assement/save")
				.param("name", "Junit考评测试").param("year", "2018").param("remarks", "Junit测试数据").session(mockSession));
		MvcResult mvcResult = resultActions.andReturn();
		String resultView = mvcResult.getModelAndView().getViewName();
		Assert.assertEquals("测试失败，返回字符串不对", "redirect:/a/asmt/assement/?repage", resultView);
	}

	@Test
	public void testDelete() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/a/asmt/assement/delete")
				.param("id", "4ccfa1ce77cd4c7893054376a958c7bf").session(mockSession));
		MvcResult mvcResult = resultActions.andReturn();
		String resultView = mvcResult.getModelAndView().getViewName();
		Assert.assertEquals("测试失败，返回字符串不对", "redirect:/a/asmt/assement/?repage", resultView);
	}

}
