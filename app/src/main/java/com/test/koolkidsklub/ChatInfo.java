package com.test.koolkidsklub;

import java.util.Date;

public class ChatInfo {

    private String messageText;
    private String user_name;
    private long messageTime;

    public ChatInfo(String messageText, String user_name) {
        this.messageText = messageText;
        this.user_name = user_name;

        // Get current date and time
        messageTime = new Date().getTime();
    }

    public ChatInfo(){

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

}
