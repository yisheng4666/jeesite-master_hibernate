package com.thinkgem.jeesite.modules.asmt.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 
 * @author JianHui
 * @date 2017年10月26日--下午2:01:32
 *
 */

@Entity
@Table(name = "t_incident_base_history")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IncidentBaseHistory extends IdEntity<IncidentBaseHistory> {

	private IncidentBase incidentBase; // 事件基本信息
	private int indexNo; // 索引号
	private Date editTime; // 修改时间
	private User editUser; // 修改用户
	private String editReason; // 修改原因

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "incident_base_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	public IncidentBase getIncidentBase() {
		return incidentBase;
	}

	public void setIncidentBase(IncidentBase incidentBase) {
		this.incidentBase = incidentBase;
	}

	public int getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "edit_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	public User getEditUser() {
		return editUser;
	}

	public void setEditUser(User editUser) {
		this.editUser = editUser;
	}

	public String getEditReason() {
		return editReason;
	}

	public void setEditReason(String editReason) {
		this.editReason = editReason;
	}
}
