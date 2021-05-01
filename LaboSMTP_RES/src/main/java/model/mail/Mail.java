package model.mail;

import java.util.List;

public class Mail {
    private String subject;
    private String message;
    private String from;
    private List<String> to;

    public Mail(String s, String m, String f, List<String> to) {
        subject = s;
        message = m;
        from = f;
        this.to = to;
    }

    public List<String> getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }
}
