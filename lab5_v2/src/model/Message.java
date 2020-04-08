package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable {
    private String text;
    private User fromUser;
    private Date dateOfSending;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("'['yyyy-MM-dd 'at' HH:mm:ss']'");

    public Message(String text, User fromUser) {
        if (text == null || text.isEmpty())
            return;
        this.text = text;
        this.fromUser = fromUser;
        this.dateOfSending = new Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        try {
            return dateFormat.format(dateOfSending) + " " + fromUser.getName() + ": " + text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return fromUser;
    }

}
