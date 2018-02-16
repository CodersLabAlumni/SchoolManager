package pl.schoolmanager.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;

	private String content;

	@ManyToOne
	private User sender;

	@ManyToOne
	private User receiver;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "message", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	private MessageData messageData;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "message", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private Set<MessageResponse> responses;

	@CreationTimestamp
	private Date created;

	private boolean openBySender;

	private boolean openByReceiver;

	public Message() {
		sender = new User();
		receiver = new User();
		messageData = new MessageData();
		responses = new HashSet<>();
		openBySender = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public MessageData getMessageData() {
		return messageData;
	}

	public void setMessageData(MessageData messageData) {
		this.messageData = messageData;
	}

	public String getReceiverEmail() {
		return receiver.getEmail();
	}

	public void setReceiverEmail(String receiverEmail) {
		receiver.setEmail(receiverEmail);
	}

	public String getSenderEmail() {
		return sender.getEmail();
	}

	public void setSenderEmail(String senderEmail) {
		sender.setEmail(senderEmail);
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getReceiverDescription() {
		return messageData.getReceiverDescription();
	}

	public String getSenderDescription() {
		return messageData.getSenderDescription();
	}

	public boolean isOpenBySender() {
		return openBySender;
	}

	public void setOpenBySender(boolean openBySender) {
		this.openBySender = openBySender;
	}

	public boolean isOpenByReceiver() {
		return openByReceiver;
	}

	public void setOpenByReceiver(boolean openByReceiver) {
		this.openByReceiver = openByReceiver;
	}

	public Set<MessageResponse> getResponses() {
		return responses;
	}

	public void setResponses(Set<MessageResponse> responses) {
		this.responses = responses;
	}

	public int getResponsesNum() {
		return responses.size();
	}

}
