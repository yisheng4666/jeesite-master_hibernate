/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 考评Entity
 * 
 * @author JianHui
 * @date 2017年9月5日--下午3:04:08
 *
 */

@Entity
@Table(name = "t_assement")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Assement extends IdEntity<Assement> {

	private static final long serialVersionUID = 1L;
	private String name; // 考评名称
	private String year; // 考评年份
	private String description; // 考评描述
	private String isPublish; // 是否已下发规则 0-未下发规则 1-已下发规则
	private String status; // 考评状态 0-未开启，1-进行中，2-已完成
	private User unitCheckUser; // 上报审核用户
	private User awardConfirmUser; // 加分确认用户
	private User objectionJudgeUser; // 异议裁定用户

	private List<Rule> ruleList = Lists.newArrayList(); // 拥有的考评规则
	private List<AssementUser> assementUserList = Lists.newArrayList(); // 拥有的被考评用户
	
	public Assement() {
		super();
	}

	public Assement(String id) {
		this();
		this.id = id;
	}

	@PrePersist
	public void prePersist() {
		this.id = IdGen.uuid();
	}

	@Length(min = 1, max = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(max = 1000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_check_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getUnitCheckUser() {
		return unitCheckUser;
	}

	public void setUnitCheckUser(User unitCheckUser) {
		this.unitCheckUser = unitCheckUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "award_confirm_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getAwardConfirmUser() {
		return awardConfirmUser;
	}

	public void setAwardConfirmUser(User awardConfirmUser) {
		this.awardConfirmUser = awardConfirmUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "objection_judge_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getObjectionJudgeUser() {
		return objectionJudgeUser;
	}

	public void setObjectionJudgeUser(User objectionJudgeUser) {
		this.objectionJudgeUser = objectionJudgeUser;
	}

	@OneToMany(mappedBy = "assement", fetch = FetchType.LAZY)
	@Where(clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy(value = "id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Rule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<Rule> ruleList) {
		this.ruleList = ruleList;
	}

	@OneToMany(mappedBy = "assement", fetch = FetchType.LAZY)
	@Where(clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy(value = "id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<AssementUser> getAssementUserList() {
		return assementUserList;
	}

	public void setAssementUserList(List<AssementUser> assementUserList) {
		this.assementUserList = assementUserList;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Assement [name=" + name + ", year=" + year + ", isPublish=" + isPublish + ", status=" + status
				+ ", ruleList=" + ruleList + "]";
	}

	public static final String STATUS_INIT = "0"; // 未开启
	public static final String STATUS_OPENING = "1"; // 进行中
	public static final String STATUS_FINISH = "2"; // 已完成
}
