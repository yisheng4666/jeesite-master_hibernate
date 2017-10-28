/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.asmt.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 单位目录Entity
 * @author lcy
 * @version 2017-10-13
 */
@Entity
@Table(name = "t_unit_catalog")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UnitCatalog extends IdEntity<UnitCatalog> {
	
	private static final long serialVersionUID = 1L;
  private UnitCatalog parent; //父级单位目录
  private AssementUser assementUser; // 被考评单位用户
  private String organization_no;//编制序号
  private Integer population;//单位人数
  private String address;//单位地址
  private String is_self_assement;//是否纳入本级考评
  private String  name;//名称
  private String  contact_person; //联系人
  private String  phone_no;//联系电话
  private String cachet_url;  //公章Url
  private String state;
  private FlowStatusInfo flowStatusInfo;
 

  public UnitCatalog() {
		super();
	}

  public UnitCatalog(String id){
    this();
    this.id = id;
  }



	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
  public UnitCatalog getParent() {
    return parent;
  }

  public void setParent(UnitCatalog parent) {
    this.parent = parent;
  }

//  @Length(min = 1, max = 255)
//  public String getParentIds() {
//    return parentIds;
//  }
//
//  public void setParentIds(String parentIds) {
//    this.parentIds = parentIds;
//  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assement_user_id")
  @NotFound(action = NotFoundAction.IGNORE)
  @JsonIgnore
//  @NotNull(message = "被考评用户不能为空")
//  @ExcelField(title = "被考评用户", align = 2, sort = 25)
  public AssementUser getAssementUser() {
    return assementUser;
  }

  public void setAssementUser(AssementUser assementUser) {
    this.assementUser = assementUser;
  }

  public String getOrganization_no() {
    return organization_no;
  }

  public void setOrganization_no(String organization_no) {
    this.organization_no = organization_no;
  }

  public Integer getPopulation() {
    return population;
  }

  public void setPopulation(Integer population) {
    this.population = population;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Length(min = 1, max =1)
  public String getIs_self_assement() {
    return is_self_assement;
  }

  public void setIs_self_assement(String is_self_assement) {
    this.is_self_assement = is_self_assement;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContact_person() {
    return contact_person;
  }

  public void setContact_person(String contact_person) {
    this.contact_person = contact_person;
  }

  public String getPhone_no() {
    return phone_no;
  }

  public void setPhone_no(String phone_no) {
    this.phone_no = phone_no;
  }

  public String getCachet_url() {
    return cachet_url;
  }

  public void setCachet_url(String cachet_url) {
    this.cachet_url = cachet_url;
  }
  
  @Transient
  public boolean isRoot(){
    return isRoot(this.id);
  }
  
  @Transient
  public static boolean isRoot(String id){
    return id != null && id.equals("1");
  }



  @Length(min = 1, max =1)
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="flowStatusInfo_id")
  @NotFound(action = NotFoundAction.IGNORE)
  public FlowStatusInfo getFlowStatusInfo() {
    return flowStatusInfo;
  }

  public void setFlowStatusInfo(FlowStatusInfo flowStatusInfo) {
    this.flowStatusInfo = flowStatusInfo;
  }
  
}


