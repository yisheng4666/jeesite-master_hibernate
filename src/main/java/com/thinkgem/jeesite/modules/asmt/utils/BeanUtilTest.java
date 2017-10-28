package com.thinkgem.jeesite.modules.asmt.utils;

import com.thinkgem.jeesite.modules.asmt.entity.FlowStatusInfo;

/**
 * 代码生成工具
 * 
 * @author JianHui
 * @date 2017年9月15日--下午5:52:25
 *
 */

public class BeanUtilTest {

	public static void main(String[] args) throws Exception {
		BeanUtilTest beanUtilTest = new BeanUtilTest();
		BeanUtils beanUtils = new BeanUtils();
		/**
		 * 注： 本自动生成工具用来生成dao层、service层、controller层。 生成代码前请先建好实体类
		 * 生成的代码为基础常用代码，可能与实际需求不同，请自行修改。
		 * service层中的find方法和controller层中的save方法，方法中调用了实体类中的name属性，如果该实体类没有name属性，请手动修改。
		 */
		beanUtilTest.beanTool(beanUtils, FlowStatusInfo.class);
	}

	public void beanTool(BeanUtils beanUtils, Class c) throws Exception {
		beanUtils.createBeanDao(c);
		beanUtils.createBeanService(c);
		beanUtils.createBeanController(c);
	}

}
