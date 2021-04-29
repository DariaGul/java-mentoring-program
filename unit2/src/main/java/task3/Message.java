package task3;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {

    private String textMessage;
    private UUID clientIdTo;
    private UUID clientIdFrom;
    private Messenger messenger;
    private LocalDateTime timeDispatch;

    public Message(String textMessage, UUID clientIdTo, UUID clientIdFrom, Messenger messenger) {
        this.textMessage = textMessage;
        this.clientIdTo = clientIdTo;
        this.clientIdFrom = clientIdFrom;
        this.messenger = messenger;
        timeDispatch = LocalDateTime.now();
    }

    public String getTextMessage() {
        return textMessage;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public LocalDateTime getTimeDispatch() {
        return timeDispatch;
    }

    public UUID getClientIdTo() {
        return clientIdTo;
    }

    public UUID getClientIdFrom() {
        return clientIdFrom;
    }

    @Override
    public String toString() {
        return String.format("textMessage = %s, clientIdTo = %s, clientIdFrom = %s, messenger = %s, timeDispatch = %s",
                             textMessage, clientIdTo, clientIdFrom, messenger.getClass().getSimpleName(), timeDispatch);
    }
}
