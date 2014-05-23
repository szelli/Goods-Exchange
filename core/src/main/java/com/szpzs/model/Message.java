package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MESSAGES database table.
 * 
 */
@Entity
@Table(name="MESSAGES")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="\"MESSAGE\"")
	private String message;

	@Column(name="\"READ\"")
	private BigDecimal read;

	@Column(name="RECEIVER_ID")
	private BigDecimal receiverId;

	@Column(name="RECEIVER_STATUS")
	private BigDecimal receiverStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="SEND_DATE")
	private Date sendDate;

	@Column(name="SENDER_ID")
	private BigDecimal senderId;

	@Column(name="SENDER_STATUS")
	private BigDecimal senderStatus;

	private String subject;

	public Message() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BigDecimal getRead() {
		return this.read;
	}

	public void setRead(BigDecimal read) {
		this.read = read;
	}

	public BigDecimal getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(BigDecimal receiverId) {
		this.receiverId = receiverId;
	}

	public BigDecimal getReceiverStatus() {
		return this.receiverStatus;
	}

	public void setReceiverStatus(BigDecimal receiverStatus) {
		this.receiverStatus = receiverStatus;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public BigDecimal getSenderId() {
		return this.senderId;
	}

	public void setSenderId(BigDecimal senderId) {
		this.senderId = senderId;
	}

	public BigDecimal getSenderStatus() {
		return this.senderStatus;
	}

	public void setSenderStatus(BigDecimal senderStatus) {
		this.senderStatus = senderStatus;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}