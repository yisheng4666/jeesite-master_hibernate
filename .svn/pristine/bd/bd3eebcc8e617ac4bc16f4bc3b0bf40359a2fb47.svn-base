package com.thinkgem.jeesite.modules.asmt.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.asmt.dao.UnitDao;
import com.thinkgem.jeesite.modules.sys.dao.MenuDao;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm;

/**
 * @author JianHui
 * @date 2017年10月17日--上午10:30:24
 */

@Component
@Transactional(readOnly = true)
public class UnitService extends BaseService {

	@Autowired
	private UnitDao unitDao;
	@Autowired
	private SystemAuthorizingRealm systemRealm;
	@Autowired
	private MenuDao menuDao;

	public User get(String id) {
		return unitDao.get(id);
	}

	public List<User> findAll() {
		return unitDao.findAll();
	}

	public Page<User> find(Page<User> page, User user) {
		DetachedCriteria dc = unitDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(user.getLoginName())) {
			dc.add(Restrictions.like("loginName", "%" + user.getLoginName() + "%"));
		}
		if (StringUtils.isNotEmpty(user.getName())) {
			dc.add(Restrictions.like("name", "%" + user.getName() + "%"));
		}
		dc.add(Restrictions.eq("isOrganization", "1"));
		dc.add(Restrictions.eq(User.FIELD_DEL_FLAG, User.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("organizationNo"));
		return unitDao.find(page, dc);
	}

	@Transactional(readOnly = true)
	public void save(User user) {
		unitDao.clear();
		unitDao.save(user);
		systemRealm.clearAllCachedAuthorizationInfo();
		// 同步到Activiti
		saveActiviti(user);
	}

	@Transactional(readOnly = true)
	public void delete(String id) {
		unitDao.deleteById(id);
	}

	private void saveActiviti(User user) {
		if (!Global.isSynActivitiIndetity()) {
			return;
		}
		try {
			if (user != null) {
				String userId = ObjectUtils.toString(user.getId());
				org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(userId)
						.singleResult();
				// 是新增用户
				if (activitiUser == null) {
					activitiUser = identityService.newUser(userId);
					identityService.saveUser(activitiUser);
				}
				// 同步用户角色关联数据
				List<Menu> menuList = menuDao.findAllActivitiList(user.getId());
				merge(user, menuList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void merge(User user, List<Menu> menuList) {
		if (!Global.isSynActivitiIndetity()) {
			return;
		}
		try {
			String userId = ObjectUtils.toString(user.getId());
			List<Group> activitiGroupList = identityService.createGroupQuery().groupMember(userId).list();
			if (Collections3.isEmpty(menuList)) {
				for (Group group : activitiGroupList) {
					identityService.deleteMembership(userId, group.getId());
				}
			} else {
				Map<String, String> groupMap = Maps.newHashMap();
				for (Menu menu : menuList) {
					groupMap.put(menu.getActivitiGroupId(), menu.getActivitiGroupName());
				}
				Map<String, String> activitiGroupMap = Collections3.extractToMap(activitiGroupList, "id", "name");
				for (String groupId : activitiGroupMap.keySet()) {
					if (StringUtils.isNotBlank(groupId) && !groupMap.containsKey(groupId)) {
						identityService.deleteMembership(userId, groupId);
					}
				}
				for (String groupId : groupMap.keySet()) {
					if (StringUtils.isNotBlank(groupId) && !activitiGroupMap.containsKey(groupId)) {
						identityService.createMembership(userId, groupId);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}