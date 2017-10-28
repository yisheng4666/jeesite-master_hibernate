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
 * 流程状态信息Entity
 * 
 * @author JianHui
 * @date 2017年10月20日--上午11:01:16
 *
 */

@Entity
@Table(name = "t_flow_status_info")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FlowStatusInfo extends IdEntity<FlowStatusInfo> {

	private Assement assement; // 考评
	private String flowType; // 流程类型【0-单位目录上报; 1-评分单位评分; 2-组长单位评分】
	private User user; // 流程发起用户
	private String currentStatus; // 当前状态【目录上报{等待审核, 审核通过, 退回修改};评分单位评分和组长单位评分不需要当前状态 】
	private String completeStatus; // 完成状态【已完成, 未完成】
	private String approveInfo; // 审核信息

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assement_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	public Assement getAssement() {
		return assement;
	}

	public void setAssement(Assement assement) {
		this.assement = assement;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
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

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}

	public String getApproveInfo() {
		return approveInfo;
	}

	public void setApproveInfo(String approveInfo) {
		this.approveInfo = approveInfo;
	}

	@Override
	public String toString() {
		return "FlowStatusInfo [assement=" + assement + ", flowType=" + flowType + ", user=" + user + ", currentStatus="
				+ currentStatus + ", completeStatus=" + completeStatus + ", approveInfo=" + approveInfo + "]";
	}

}
