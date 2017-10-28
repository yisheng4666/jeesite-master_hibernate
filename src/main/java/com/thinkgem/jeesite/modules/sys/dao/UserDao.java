/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 用户DAO接口
 * 
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class UserDao extends BaseDao<User> {

	public List<User> findAllList() {
		return find("from User where delFlag=:p1 order by id", new Parameter(User.DEL_FLAG_NORMAL));
	}

	public User findByLoginName(String loginName) {
		return getByHql("from User where loginName = :p1 and delFlag = :p2",
				new Parameter(loginName, User.DEL_FLAG_NORMAL));
	}

	public int updatePasswordById(String newPassword, String id) {
		return update("update User set password=:p1 where id = :p2", new Parameter(newPassword, id));
	}

	public int updateLoginInfo(String loginIp, Date loginDate, String id) {
		return update("update User set loginIp=:p1, loginDate=:p2 where id = :p3",
				new Parameter(loginIp, loginDate, id));
	}

	// public List<User> findAllByRole() {
	// // findBySql("select * from sys_user where roleList. and delFlag=:p2")
	// return find("from User u join where and u.delFlag=:p2", new
	// Parameter(User.DEL_FLAG_NORMAL));
	// }
	public List<User> findAllByRole(String id) {
		// findBySql("select * from sys_user where roleList. and delFlag=:p2")
		return find("from User u join u.roleList rl join Role r  where r.id=:p1  and u.delFlag=:p2",
				new Parameter(id, User.DEL_FLAG_NORMAL));
	}

//	/**
//	 * 查询除了所有组的用户之外的所有用户(用户状态为可用)
//	 * 
//	 * @author JianHui
//	 * @return
//	 */
//	public List<User> findByOffice() {
//		return find("from User u where u.office.name <>:p1 and delFlag=:p2",
//				new Parameter("所有组", User.DEL_FLAG_NORMAL));
//	}
}
