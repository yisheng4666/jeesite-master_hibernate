package com.thinkgem.jeesite.modules.asmt.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 排名明细Entity
 * 
 * @author JianHui
 * @date 2017年9月15日--下午2:44:49
 *
 */

@Entity
@Table(name = "t_rank_detail")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RankDetail extends IdEntity<RankDetail> {

	private User user; // 用户ID，外键
	private String userName; // 用户名
	private int sort; // 排名
	private int scort; // 得分

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getScort() {
		return scort;
	}

	public void setScort(int scort) {
		this.scort = scort;
	}

	@Override
	public String toString() {
		return "RankDetail [userName=" + userName + ", sort=" + sort + ", scort=" + scort + "]";
	}
}
