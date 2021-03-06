package com.thinkgem.jeesite.modules.sys.web;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.thinkgem.jeesite.common.test.BaseControllerTest;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class OfficeControllerTest extends BaseControllerTest {
	@Test
	public void listTest() throws Exception {

		// 准备参数
		// String postJson = "{\"mac\":\"h\",\"dtClient\":\"2015-06-03 13:20:10\"}";
		// 发送请求
		// ResultActions resultActions =
		// this.mockMvc.perform(MockMvcRequestBuilders.post("/a/sys/office/list").accept(MediaType.APPLICATION_JSON).param("criJson",postJson));

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/a/sys/office").session(mockSession));
		MvcResult mvcResult = resultActions.andReturn();

		Map<String, Object> resultModel = mvcResult.getModelAndView().getModel();
		List<Office> offices = (List<Office>) resultModel.get("list");

		// 判断是否有【省发改委】这个组
		boolean flag = false;
		for (Office office : offices) {
			if ("省发改委".equals(office.getName())) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue("测试失败：无【省发改委】这个组", flag);

		// String result = mvcResult.getResponse().getContentAsString();
		// System.out.println("=====客户端获得反馈数据:" + result);
	}

}
