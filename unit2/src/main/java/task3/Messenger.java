package task3;

import java.util.UUID;

public interface Messenger {

    void sendMessage(String textMessage, Client clientIdTo, Client clientIdFrom);

    Message readMessage(Client owner, Client friend);

    int getId();

}
