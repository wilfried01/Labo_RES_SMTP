package model.mail;

import java.util.List;

public class Group {

    private int idGroup;
    private List<Victims> recipients;
    private Victims sender;

    public Group(int id, List<Victims> recipients, Victims sender) {
        this.idGroup = id;
        this.recipients = recipients;
        this.sender = sender;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public List<Victims> getRecipients() {
        return recipients;
    }

    public Victims getSender() {
        return sender;
    }

    public void setRecipients(List<Victims> recipients) {
        this.recipients = recipients;
    }

    public void addRecipients(Victims recipient) {
        this.recipients.add(recipient);
    }

    public void setSender(Victims sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Group {" +
                "\n\tidGroup = " + idGroup +
                ", \n\trecipients = " + recipients.size() +
                ", \n\tsender = " + sender +
                "\n}";
    }
}
