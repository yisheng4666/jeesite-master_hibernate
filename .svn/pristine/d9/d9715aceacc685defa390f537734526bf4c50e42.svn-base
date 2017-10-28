/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.entity;

import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 事件录入Entity
 * @author Yyz
 * @version 2017-10-12
 */
@Entity
@Table(name = "t_entry_incident")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EntryIncident extends IdEntity<EntryIncident> {
	
	private static final long serialVersionUID = 1L;
	private User entryUser; // 录入用户
	private Rule rule; // 规则
	private UnitCatalog unitCatalog; // 单位目录
	private String incidentNo; // 事件编码
	private String content; // 内容
	private String level; // 等级
	private String isJoin; // 是否已关联

	public EntryIncident() {
		super();
	}

	public EntryIncident(String id){
		this();
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="entry_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message="录入用户不能为空")
	public User getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(User entryUser) {
		this.entryUser = entryUser;
	}

	@ManyToOne
	@JoinColumn(name="rule_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message="规则不能为空")
	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	@ManyToOne
	@JoinColumn(name="unit_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message="单位目录不能为空")
	public UnitCatalog getUnitCatalog() {
		return unitCatalog;
	}

	public void setUnitCatalog(UnitCatalog unitCatalog) {
		this.unitCatalog = unitCatalog;
	}

	public String getIncidentNo() {
		return incidentNo;
	}

	public void setIncidentNo(String incidentNo) {
		this.incidentNo = incidentNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIsJoin() {
		return isJoin;
	}

	public void setIsJoin(String isJoin) {
		this.isJoin = isJoin;
	}
}


