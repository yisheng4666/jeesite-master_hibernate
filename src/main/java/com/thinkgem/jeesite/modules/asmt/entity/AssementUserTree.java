package com.thinkgem.jeesite.modules.asmt.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 被考评组和被考评单位Tree(虚拟表)
 * 
 * @author JianHui 2017年9月6日--上午10:26:04
 *
 */
public class AssementUserTree {

	private String id; // ID
	private Assement assement; // 考评ID
	private Office office; // 被考评组ID
	private User user; // 用户ID
	private String userName; // 用户名称
	private String organizationNo; // 排序

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Assement getAssement() {
		return assement;
	}

	public void setAssement(Assement assement) {
		this.assement = assement;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrganizationNo() {
		return organizationNo;
	}

	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}

	/**
	 * 排序
	 * 
	 * @author JianHui
	 * @param list
	 *            目标数组
	 * @param sourcelist
	 *            数据源
	 * @param parentId
	 *            父ID
	 * @param cascade
	 *            boolean
	 */
	@JsonIgnore
	public static void sortList(List<AssementUserTree> list, List<AssementUserTree> sourcelist, String parentId,
			boolean cascade) {
		for (int i = 0; i < sourcelist.size(); i++) {
			AssementUserTree aut = sourcelist.get(i);
			if (aut.getOffice() == null) {
				list.add(aut);
				if (cascade) {
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j = 0; j < sourcelist.size(); j++) {
						AssementUserTree child = sourcelist.get(j);
						if (child.getOffice() != null && child.getOffice().getId() != null
								&& child.getOffice().getId().equals(aut.getId())) {
							sortList(list, sourcelist, aut.getId(), true);
							break;
						}
					}
				}
			}
		}
	}

}
