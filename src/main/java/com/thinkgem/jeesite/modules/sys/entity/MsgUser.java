/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.entity;

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

/**
 * 消息用户Entity
 * @author Yyz
 * @version 2017-10-23
 */
@Entity
@Table(name = "sys_msg_user")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MsgUser extends IdEntity<MsgUser> {
	
	private static final long serialVersionUID = 1L;
	private Msg msg; // 消息
	private User user; // 消息用户
	private String isRead; // 是否已读（未读：0，已读：1）

	public MsgUser() {
		super();
		this.isRead = MsgUser.NO;
	}

	public MsgUser(String id){
		this();
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="msg_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message="消息不能为空")
	public Msg getMsg() {
		return msg;
	}

	public void setMsg(Msg msg) {
		this.msg = msg;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message="用户不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
}


