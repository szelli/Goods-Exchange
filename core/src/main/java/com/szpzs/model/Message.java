package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * The persistent class for the MESSAGES database table.
 * 
 */
@Entity
@Table(name="MESSAGES")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final Long serialVersionUID = 1L;

	@SequenceGenerator(name="Message", sequenceName="MESSAGES_SEQ")
	@Id @GeneratedValue(generator="Message")
	private Long id;

	@Column(name="\"MESSAGE\"")
	private String message;

	@Column(name="\"READ\"")
	private Boolean read;

	@Column(name="RECEIVER_ID")
	private BigInteger receiverId;

	@Column(name="RECEIVER_STATUS")
	private Boolean receiverStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="SEND_DATE")
	private Date sendDate;

	@Column(name="SENDER_ID")
	private BigInteger senderId;

	@Column(name="SENDER_STATUS")
	private Boolean senderStatus;
	
	@Column(name="SUBJECT")
	private String subject;

	public Message() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getRead() {
		return this.read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public BigInteger getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(BigInteger receiverId) {
		this.receiverId = receiverId;
	}

	public Boolean getReceiverStatus() {
		return this.receiverStatus;
	}

	public void setReceiverStatus(Boolean receiverStatus) {
		this.receiverStatus = receiverStatus;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public BigInteger getSenderId() {
		return this.senderId;
	}

	public void setSenderId(BigInteger senderId) {
		this.senderId = senderId;
	}

	public Boolean getSenderStatus() {
		return this.senderStatus;
	}

	public void setSenderStatus(Boolean senderStatus) {
		this.senderStatus = senderStatus;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}