package model.mail;

public class Message {
    private String subject;
    private String content;

    public Message(String s, String c) {
        this.content = c;
        this.subject = s;
    }

    public static Message parseTextToMessage(String text) {

        return null;
    }

    @Override
    public String toString() {
        return "Message {\n" +
                "\n\tsubject = '" + subject + '\'' +
                ", \n\tcontent = '" + content + '\'' +
                "\n}";
    }
}
