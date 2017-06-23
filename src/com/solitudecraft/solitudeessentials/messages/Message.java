package com.solitudecraft.solitudeessentials.messages;

import java.util.Date;

/**
 * Created by nolan on 6/21/2017.
 */
public class Message {
    String UUID1;
    String UUID2;
    String messageContent;
    Date messageDate;

    public Message(String sender, String receiver, String content, Date date) {
        UUID1 = sender;
        UUID2 = receiver;
        messageContent = content;
        messageDate = date;
    }

}
