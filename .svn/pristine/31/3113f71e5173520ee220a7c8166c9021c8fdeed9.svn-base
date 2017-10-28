package com.thinkgem.jeesite.modules.asmt.utils;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 代码生成工具类
 * 
 * @author JianHui
 * @date 2017年9月15日--下午3:59:56
 *
 */

public class BeanUtils {
	// 公共部分
	private static final String RT_1 = "\r\n";
	private static final String RT_2 = RT_1 + RT_1;
	private static final String BLANK_1 = " ";
	private static final String BLANK_4 = "    ";
	private static final String BLANK_8 = BLANK_4 + BLANK_4;

	// 注释部分
	private static final String ANNOTATION_AUTHOR_PARAMTER = "@author ";
	private static final String ANNOTATION_AUTHOR_NAME = "JianHui";
	private static final String ANNOTATION_AUTHOR = ANNOTATION_AUTHOR_PARAMTER + ANNOTATION_AUTHOR_NAME;
	private static final String ANNOTATION_DATE = "@date ";
	private static final String ANNOTATION = "/**" + RT_1 + BLANK_1 + "*" + BLANK_1 + ANNOTATION_AUTHOR + RT_1 + BLANK_1
			+ "*" + BLANK_1 + ANNOTATION_DATE + getDate() + RT_1 + BLANK_1 + "*/" + RT_1;

	// 文件 地址
	private static final String BASE_PATH = "com/thinkgem/jeesite/modules/asmt/";
	private static final String DAO = "dao";
	private static final String SERVICE = "service";
	private static final String WEB = "web";

	// 注解
	private static final String REPOSITORY = "@Repository";
	private static final String COMPONENT = "@Component";
	private static final String TRANSACTIONAL = "@Transactional(readOnly = true)";
	private static final String AUTOWIRED = "@Autowired";
	private static final String CONTROLLER = "@Controller";
	private static final String REQUESTMAPPING = "@RequestMapping";
	private static final String MODELATTRIBUTE = "@ModelAttribute";
	private static final String REQUIRESPERMISSIONS = "@RequiresPermissions";

	// 包名
	private static final String BASE_PACKAGE_NAME = "com.thinkgem.jeesite.modules.asmt.";
	private static final String ENTITY_URL = "import com.thinkgem.jeesite.modules.asmt.entity.";
	private static final String BASEDAO_URL = "import com.thinkgem.jeesite.common.persistence.BaseDao;";
	private static final String REPOSITORY_URL = "import org.springframework.stereotype.Repository;";
	private static final String LIST_URL = "import java.util.List;";
	private static final String AUTOWIRED_URL = "import org.springframework.beans.factory.annotation.Autowired;";
	private static final String COMPONENT_URL = "import org.springframework.stereotype.Component;";
	private static final String TRANSACTIONAL_URL = "import org.springframework.transaction.annotation.Transactional;";
	private static final String BASESERVICE_URL = "import com.thinkgem.jeesite.common.service.BaseService;";
	private static final String CONTROLLER_URL = "import org.springframework.stereotype.Controller;";
	private static final String REQUESTMAPPING_URL = "import org.springframework.web.bind.annotation.RequestMapping;";
	private static final String BASECONTROLLER_URL = "import com.thinkgem.jeesite.common.web.BaseController;";
	private static final String MODELATTRIBUTE_URL = "import org.springframework.web.bind.annotation.ModelAttribute;";
	private static final String REQUESTPARAM_URL = "import org.springframework.web.bind.annotation.RequestParam;";
	private static final String STRINGUTILS_URL = "import com.thinkgem.jeesite.common.utils.StringUtils;";
	private static final String REQUIRESPERMISSIONS_URL = "import org.apache.shiro.authz.annotation.RequiresPermissions;";
	private static final String HTTPSERVLETREQUEST_URL = "import javax.servlet.http.HttpServletRequest;";
	private static final String HTTPSERVLETRESPONSE_URL = "import javax.servlet.http.HttpServletResponse;";
	private static final String MODEL_URL = "import org.springframework.ui.Model;";
	private static final String PAGE_URL = "import com.thinkgem.jeesite.common.persistence.Page;";
	private static final String REDIRECTATTRIBUTES_URL = "import org.springframework.web.servlet.mvc.support.RedirectAttributes;";
	private static final String GLOBAL_URL = "import com.thinkgem.jeesite.common.config.Global;";
	private static final String DETACHEDCRITERIA_URL = "import org.hibernate.criterion.DetachedCriteria;";
	private static final String RESTRICTIONS_URL = "import org.hibernate.criterion.Restrictions;";
	private static final String ORDER_URL = "import org.hibernate.criterion.Order;";
	private static final String MATCHMODE_URL = "import org.hibernate.criterion.MatchMode;";

	// 基本类名称
	private static final String BASEDAO = "BaseDao";
	private static final String BASESERVICE = "BaseService";

	/**
	 * 创建bean的Dao<br>
	 * 
	 * @param c
	 * @throws Exception
	 */
	public void createBeanDao(Class c) throws Exception {
		String cName = c.getName();
		String name = getLastChar(cName);
		String fileName = System.getProperty("user.dir") + "/src/main/java/" + BASE_PATH + DAO + "/" + name
				+ "Dao.java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write("package " + BASE_PACKAGE_NAME + DAO + ";" + RT_2 + REPOSITORY_URL + RT_2 + BASEDAO_URL + RT_1
				+ ENTITY_URL + name + ";" + RT_2 + ANNOTATION + RT_1 + REPOSITORY + RT_1 + "public class " + name
				+ "Dao extends " + BASEDAO + "<" + name + "> {" + RT_2 + "}");
		fw.flush();
		fw.close();
		showInfo(fileName);
	}

	/**
	 * 创建bean的service
	 * 
	 * @param c
	 * @throws Exception
	 */
	public void createBeanService(Class c) throws Exception {
		String cName = c.getName();
		String xName = getLastChar(cName);
		String name = getLowercaseChar(xName);
		String daoName = name + "Dao";
		String fileName = System.getProperty("user.dir") + "/src/main/java/" + BASE_PATH + SERVICE + "/" + xName
				+ "Service.java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write("package " + BASE_PACKAGE_NAME + SERVICE + ";" + RT_2

				+ LIST_URL + RT_2

				+ DETACHEDCRITERIA_URL + RT_1 + MATCHMODE_URL + RT_1 + ORDER_URL + RT_1 + RESTRICTIONS_URL + RT_1
				+ AUTOWIRED_URL + RT_1 + COMPONENT_URL + RT_1 + TRANSACTIONAL_URL + RT_2

				+ PAGE_URL + RT_1 + BASESERVICE_URL + RT_1 + STRINGUTILS_URL + RT_1 + "import " + BASE_PACKAGE_NAME
				+ DAO + "." + xName + "Dao;" + RT_1 + ENTITY_URL + xName + ";" + RT_2

				+ ANNOTATION + RT_1 + COMPONENT + RT_1 + TRANSACTIONAL + RT_1 + "public class " + xName
				+ "Service extends " + BASESERVICE + "{" + RT_2

				+ BLANK_4 + AUTOWIRED + RT_1 + BLANK_4 + "private " + xName + "Dao " + daoName + ";" + RT_2

				+ BLANK_4 + "public " + xName + " get(String id) {" + RT_1 + BLANK_8 + "return " + daoName + ".get(id);"
				+ RT_1 + BLANK_4 + "}" + RT_2

				+ BLANK_4 + "public List<" + xName + "> findAll() {" + RT_1 + BLANK_8 + "return " + daoName
				+ ".findAll();" + RT_1 + BLANK_4 + "}" + RT_2

				+ BLANK_4 + "public Page<" + xName + "> find(Page<" + xName + "> page, " + xName + " " + name + ") {"
				+ RT_1 + BLANK_8 + "DetachedCriteria dc = " + daoName + ".createDetachedCriteria();" + RT_1 + BLANK_8
				+ "if (StringUtils.isNotEmpty(" + name + ".getName())) {" + RT_1 + BLANK_8 + BLANK_4
				+ "dc.add(Restrictions.like(\"name\", " + name + ".getName(), MatchMode.ANYWHERE));" + RT_1 + BLANK_8
				+ "}" + RT_1 + BLANK_8 + "dc.add(Restrictions.eq(" + xName + ".FIELD_DEL_FLAG, " + xName
				+ ".DEL_FLAG_NORMAL));" + RT_1 + BLANK_8 + "dc.addOrder(Order.desc(\"id\"));" + RT_1 + BLANK_8
				+ "return " + daoName + ".find(page, dc);" + RT_1 + BLANK_4 + "}" + RT_2

				+ BLANK_4 + TRANSACTIONAL + RT_1 + BLANK_4 + "public void save(" + getLastChar(cName) + BLANK_1 + name
				+ ") {" + RT_1 + BLANK_8 + daoName + ".save(" + name + ");" + RT_1 + BLANK_4 + "}" + RT_2

				+ BLANK_4 + TRANSACTIONAL + RT_1 + BLANK_4 + "public void delete(String id) {" + RT_1 + BLANK_8
				+ daoName + ".deleteById(id);" + RT_1 + BLANK_4 + "}" + RT_2

				+ "}");
		fw.flush();
		fw.close();
		showInfo(fileName);
	}

	/**
	 * 创建bean的Controller
	 * 
	 * @param c
	 * @throws Exception
	 */
	public void createBeanController(Class c) throws Exception {
		String cName = c.getName();
		String xName = getLastChar(cName);
		String name = getLowercaseChar(xName);
		String serviceName = name + "Service";
		String fileName = System.getProperty("user.dir") + "/src/main/java/" + BASE_PATH + WEB + "/" + xName
				+ "Controller.java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write("package " + BASE_PACKAGE_NAME + WEB + ";" + RT_2

				+ HTTPSERVLETREQUEST_URL + RT_1 + HTTPSERVLETRESPONSE_URL + RT_2

				+ REQUIRESPERMISSIONS_URL + RT_1 + AUTOWIRED_URL + RT_1 + CONTROLLER_URL + RT_1 + MODEL_URL + RT_1
				+ MODELATTRIBUTE_URL + RT_1 + REQUESTMAPPING_URL + RT_1 + REQUESTPARAM_URL + RT_1
				+ REDIRECTATTRIBUTES_URL + RT_2

				+ GLOBAL_URL + RT_1 + PAGE_URL + RT_1 + STRINGUTILS_URL + RT_1 + BASECONTROLLER_URL + RT_1 + ENTITY_URL
				+ xName + ";" + RT_1 + "import " + BASE_PACKAGE_NAME + SERVICE + "." + xName + "Service;" + RT_2

				+ ANNOTATION + RT_2 + CONTROLLER + RT_1 + REQUESTMAPPING + "(value = \"${adminPath}/asmt/" + name
				+ "\")" + RT_1 + "public class " + xName + "Controller extends BaseController {" + RT_2

				+ BLANK_4 + AUTOWIRED + RT_1 + BLANK_4 + "private " + xName + "Service " + serviceName + ";" + RT_2

				+ BLANK_4 + MODELATTRIBUTE + RT_1 + BLANK_4 + "public " + xName
				+ " get(@RequestParam(required = false) String id) {" + RT_1 + BLANK_8
				+ "if (StringUtils.isNotBlank(id)) {" + RT_1 + BLANK_8 + BLANK_4 + "return " + serviceName + ".get(id);"
				+ RT_1 + BLANK_8 + "} else {" + RT_1 + BLANK_8 + BLANK_4 + "return new " + xName + "();" + RT_1
				+ BLANK_8 + "}" + RT_1 + BLANK_4 + "}" + RT_2

				+ BLANK_4 + REQUIRESPERMISSIONS + "(\"asmt:" + name + ":view\")" + RT_1 + BLANK_4 + REQUESTMAPPING
				+ "(value = {\"list\", \"\"})" + RT_1 + BLANK_4 + "public String list(" + xName + BLANK_1 + name
				+ ", HttpServletRequest request, HttpServletResponse response, Model model) {" + RT_1 + BLANK_8
				+ "Page<" + xName + "> page = " + serviceName + ".find(new Page<" + xName + ">(request, response), "
				+ name + "); " + RT_1 + BLANK_8 + "model.addAttribute(\"page\", page);" + RT_1 + BLANK_8
				+ "return \"modules/asmt/" + name + "List\";" + RT_1 + BLANK_4 + "}" + RT_2

				+ BLANK_4 + REQUIRESPERMISSIONS + "(\"asmt:" + name + ":view\")" + RT_1 + BLANK_4 + REQUESTMAPPING
				+ "(value = \"form\")" + RT_1 + BLANK_4 + "public String form(" + xName + BLANK_1 + name
				+ ", Model model) {" + RT_1 + BLANK_8 + "model.addAttribute(\"" + name + "\", " + name + ");" + RT_1
				+ BLANK_8 + "return \"modules/asmt/" + name + "From\";" + RT_1 + BLANK_4 + "}" + RT_2

				+ BLANK_4 + REQUIRESPERMISSIONS + "(\"asmt:" + name + ":edit\")" + RT_1 + BLANK_4 + REQUESTMAPPING
				+ "(value = \"save\")" + RT_1 + BLANK_4 + "public String save(" + xName + BLANK_1 + name
				+ ", Model model, RedirectAttributes redirectAttributes) {" + RT_1 + BLANK_8
				+ "if (!beanValidator(model, " + name + ")) {" + RT_1 + BLANK_8 + BLANK_4 + "return form(" + name
				+ ", model);" + RT_1 + BLANK_8 + "}" + RT_1 + BLANK_8 + serviceName + ".save(" + name + ");" + RT_1
				+ BLANK_8 + "addMessage(redirectAttributes, \"保存XX'\" + " + name + ".getName() + \"'成功\");" + RT_1
				+ BLANK_8 + "return \"redirect:\" + Global.getAdminPath() + \"/asmt/" + name + "/?repage\";" + RT_1
				+ BLANK_4 + "}" + RT_2

				+ BLANK_4 + REQUIRESPERMISSIONS + "(\"asmt:" + name + ":edit\")" + RT_1 + BLANK_4 + REQUESTMAPPING
				+ "(value = \"delete\")" + RT_1 + BLANK_4
				+ "public String delete(String id, RedirectAttributes redirectAttributes) {" + RT_1 + BLANK_8
				+ serviceName + ".delete(id);" + RT_1 + BLANK_8 + "addMessage(redirectAttributes, \"删除XX成功\");" + RT_1
				+ BLANK_8 + "return \"redirect:\" + Global.getAdminPath() + \"/asmt/" + name + "/?repage\";" + RT_1
				+ BLANK_4 + "}" + RT_2

				+ "}");

		fw.flush();
		fw.close();
		showInfo(fileName);
	}

	/**
	 * 获取路径的最后面字符串<br>
	 * 如：<br>
	 * <code>str = "com.b510.base.bean.User"</code><br>
	 * <code> return "User";<code>
	 * 
	 * @param str
	 * @return
	 */
	public String getLastChar(String str) {
		if ((str != null) && (str.length() > 0)) {
			int dot = str.lastIndexOf('.');
			if ((dot > -1) && (dot < (str.length() - 1))) {
				return str.substring(dot + 1);
			}
		}
		return str;
	}

	/**
	 * 把第一个字母变为小写<br>
	 * 如：<br>
	 * <code>str = "UserDao";</code><br>
	 * <code>return "userDao";</code>
	 * 
	 * @param str
	 * @return
	 */
	public String getLowercaseChar(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 显示信息
	 * 
	 * @param info
	 */
	public void showInfo(String info) {
		System.out.println("创建文件：" + info + "成功！");
	}

	/**
	 * 获取系统时间
	 * 
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日--ahh:mm:ss");
		return simpleDateFormat.format(new Date());
	}
}
