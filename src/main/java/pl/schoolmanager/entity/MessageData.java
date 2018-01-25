package pl.schoolmanager.entity;

import javax.persistence.*;

@Entity
@Table(name = "message_data")
public class MessageData {

    @Id
    private long id;

    private String receiverDescription;

    private String senderDescription;

    @OneToOne
    @JoinColumn(name = "message_id")
    @MapsId
    private Message message;

    public MessageData() {
    }

    public MessageData(Message message) {
        setMessage(message);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        setReceiverDescription(message.getReceiver().getUsername() + "<" + message.getReceiver().getEmail() + ">");
        setSenderDescription(message.getSender().getEmail());
        this.message = message;
    }

}
