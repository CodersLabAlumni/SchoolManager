package pl.schoolmanager.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "message")
	private Set<MessageResponse> responses;

	@CreationTimestamp
	private Date created;

	private int checked;

	public Message() {
		sender = new User();
		receiver = new User();
		messageData = new MessageData();
		responses = new LinkedHashSet<>();
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
		this.receiver.setEmail(receiverEmail);
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public String getReceiverDescription() {
		return messageData.getReceiverDescription();
	}

	public String getSenderDescription() {
		return messageData.getSenderDescription();
	}

	public Set<MessageResponse> getResponses() {
		return responses;
	}

	public void setResponses(Set<MessageResponse> responses) {
		this.responses = responses;
	}

}
