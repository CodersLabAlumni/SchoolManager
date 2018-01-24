package pl.schoolmanager.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

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

	@NotEmpty
	@Email
	private String receiverEmail;

	private String receiverDescription;

	private String senderDescription;
	
	private Date created;

	private int checked;

	public Message() {
		super();
		this.created = new Date();
		this.checked = 0;
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

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
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
		return receiverDescription;
	}

	public void setReceiverDescription(String receiverDescription) {
		this.receiverDescription = receiverDescription;
	}

	public String getSenderDescription() {
		return senderDescription;
	}

	public void setSenderDescription(String senderDescription) {
		this.senderDescription = senderDescription;
	}

}
