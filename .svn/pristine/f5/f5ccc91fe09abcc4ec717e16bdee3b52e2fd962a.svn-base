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

/**
 * 
 * @author JianHui
 * @date 2017年10月26日--上午11:44:26
 *
 */
@Entity
@Table(name = "t_incident_base_objection")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IncidentBaseObjection extends IdEntity<IncidentBaseObjection> {

	private IncidentBase incidentBase; // 事件基本信息
	private IncidentBaseHistory applyIncidentBaseHistory; // 申请评分修改历史
	private String applyReason; // 申请原因
	private IncidentBaseHistory respondIncidentBaseHistory; // 响应评分修改历史
	private String respondResult; // 响应结果
	private String respondSuggest; // 响应意见
	private IncidentBaseHistory judgeIncidentBaseHistory; // 裁定评分修改历史
	private String judgeResult; // 裁定结果
	private String judgeSuggest; // 裁定意见
	private String statusFlag; // 状态标记
	private String currentStatus; // 当前状态
	private String completeStatus; // 完成状态

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "apply_incident_base_history_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public IncidentBaseHistory getApplyIncidentBaseHistory() {
		return applyIncidentBaseHistory;
	}

	public void setApplyIncidentBaseHistory(IncidentBaseHistory applyIncidentBaseHistory) {
		this.applyIncidentBaseHistory = applyIncidentBaseHistory;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "respond_incident_base_history_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public IncidentBaseHistory getRespondIncidentBaseHistory() {
		return respondIncidentBaseHistory;
	}

	public void setRespondIncidentBaseHistory(IncidentBaseHistory respondIncidentBaseHistory) {
		this.respondIncidentBaseHistory = respondIncidentBaseHistory;
	}

	public String getRespondResult() {
		return respondResult;
	}

	public void setRespondResult(String respondResult) {
		this.respondResult = respondResult;
	}

	public String getRespondSuggest() {
		return respondSuggest;
	}

	public void setRespondSuggest(String respondSuggest) {
		this.respondSuggest = respondSuggest;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "judge_incident_base_history_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public IncidentBaseHistory getJudgeIncidentBaseHistory() {
		return judgeIncidentBaseHistory;
	}

	public void setJudgeIncidentBaseHistory(IncidentBaseHistory judgeIncidentBaseHistory) {
		this.judgeIncidentBaseHistory = judgeIncidentBaseHistory;
	}

	public String getJudgeResult() {
		return judgeResult;
	}

	public void setJudgeResult(String judgeResult) {
		this.judgeResult = judgeResult;
	}

	public String getJudgeSuggest() {
		return judgeSuggest;
	}

	public void setJudgeSuggest(String judgeSuggest) {
		this.judgeSuggest = judgeSuggest;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
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

}
