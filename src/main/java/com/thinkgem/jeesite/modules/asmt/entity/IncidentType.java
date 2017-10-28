package com.thinkgem.jeesite.modules.asmt.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 
 * @author JianHui
 * @date 2017年10月26日--下午2:08:33
 *
 */
@Entity
@Table(name = "t_incident_type")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IncidentType extends IdEntity<IncidentType> {

	private String name; // 名称
	private String isUsable; // 是否启用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsUsable() {
		return isUsable;
	}

	public void setIsUsable(String isUsable) {
		this.isUsable = isUsable;
	}
}
