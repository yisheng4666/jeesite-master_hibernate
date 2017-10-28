/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 单位列表Entity
 * @author Lucy
 * @version 2017-10-17
 */
@Entity
@Table(name = "asmt_anewUnitCataLog")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnewUnitCataLog extends IdEntity<AnewUnitCataLog> {
	
	private static final long serialVersionUID = 1L;
	private String id; 		// 编号
	private String name; 	// 名称

	public AnewUnitCataLog() {
		super();
	}

	public AnewUnitCataLog(String id){
		this();
		this.id = id;
	}
	 
	@Length(min=1, max=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}


