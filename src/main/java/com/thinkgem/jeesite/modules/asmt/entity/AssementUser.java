package com.thinkgem.jeesite.modules.asmt.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 被考评用户
 * 
 * @author JianHui
 * @date 2017年9月6日--上午10:37:47
 *
 */

@Entity
@Table(name = "t_assement_user")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AssementUser extends IdEntity<AssementUser> {

	private static final long serialVersionUID = 1L;
	private Assement assement; // 考评ID
	private Office office; // 被考评组ID
	private User user; // 用户ID
	private String userName; // 用户名称
	private int scoreIncludeGroup; // 含组长评分得分
	private int scoreExcludeGroup; // 不含组长评分得分
	private String organizationNo; // 编制序列
	private String isGroupLeader; // 是否组长
	private String attribute; // 单位属性 一般单位：0，省综治委成员单位：1，组长单位：2，专项组牵头单位：3
	private String appropriationType; // 财务拨款情况 全额拨款：0，差额拨款：1
	private String isSafe; // 是否平安单位 否：0，是：1
	private String assementResult; // 考评结果 先进：0，合格：1，不合格：2
	private int sort; // 排序

	private List<UnitCatalog> unitCatalogList = Lists.newArrayList(); // 被考评单位的单位目录

	private String currentStatus; // 当前状态：虚拟属性，不保存数据库
	private String completeStatus; // 完成状态：虚拟属性，不保存数据库

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assement_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Assement getAssement() {
		return assement;
	}

	public void setAssement(Assement assement) {
		this.assement = assement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "office_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

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

	public int getScoreIncludeGroup() {
		return scoreIncludeGroup;
	}

	public void setScoreIncludeGroup(int scoreIncludeGroup) {
		this.scoreIncludeGroup = scoreIncludeGroup;
	}

	public int getScoreExcludeGroup() {
		return scoreExcludeGroup;
	}

	public void setScoreExcludeGroup(int scoreExcludeGroup) {
		this.scoreExcludeGroup = scoreExcludeGroup;
	}

	public String getOrganizationNo() {
		return organizationNo;
	}

	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}

	public String getIsGroupLeader() {
		return isGroupLeader;
	}

	public void setIsGroupLeader(String isGroupLeader) {
		this.isGroupLeader = isGroupLeader;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getAppropriationType() {
		return appropriationType;
	}

	public void setAppropriationType(String appropriationType) {
		this.appropriationType = appropriationType;
	}

	public String getIsSafe() {
		return isSafe;
	}

	public void setIsSafe(String isSafe) {
		this.isSafe = isSafe;
	}

	public String getAssementResult() {
		return assementResult;
	}

	public void setAssementResult(String assementResult) {
		this.assementResult = assementResult;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@OneToMany(mappedBy = "assementUser", fetch = FetchType.LAZY)
	@Where(clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy(value = "id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<UnitCatalog> getUnitCatalogList() {
		return unitCatalogList;
	}

	@Transient
	public String getCurrentStatus() {
		return currentStatus;
	}

	@Transient
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	@Transient
	public String getCompleteStatus() {
		return completeStatus;
	}

	@Transient
	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}

	public void setUnitCatalogList(List<UnitCatalog> unitCatalogList) {
		this.unitCatalogList = unitCatalogList;
	}

	@Override
	public String toString() {
		return "AssementUser [assement=" + assement + ", office=" + office + ", user=" + user + ", userName=" + userName
				+ ", scoreIncludeGroup=" + scoreIncludeGroup + ", scoreExcludeGroup=" + scoreExcludeGroup
				+ ", organizationNo=" + organizationNo + ", isGroupLeader=" + isGroupLeader + ", attribute=" + attribute
				+ ", appropriationType=" + appropriationType + ", isSafe=" + isSafe + ", assementResult="
				+ assementResult + ", sort=" + sort + "]";
	}

}
