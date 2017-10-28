/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.sys.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 消息Entity
 * @author Yyz
 * @version 2017-10-23
 */
@Entity
@Table(name = "sys_msg")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Msg extends IdEntity<Msg> {
	
	private static final long serialVersionUID = 1L;
	private String title; // 标题
	private String content; // 内容
	private String type; // 类型（系统消息：0，用户消息：1）
	private String sendType; // 发送类型（点播：0，组播：1，广播：2）
	private String recipient; // 接收者
	private String recvType; // 接收类型
//	private String isAttach; // 是否有附件（无：0，有：1）

	public Msg() {
		super();
	}

	public Msg(String id){
		this();
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecvType() {
		return recvType;
	}

	public void setRecvType(String recvType) {
		this.recvType = recvType;
	}

//	public String getIsAttach() {
//		return isAttach;
//	}
//
//	public void setIsAttach(String isAttach) {
//		this.isAttach = isAttach;
//	}
}


