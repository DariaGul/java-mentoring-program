package task3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Server {

    private List<Message> listMessage;
    private List<Messenger> listMessenger;

    public Server() {
        listMessage = new ArrayList<>();
        listMessenger = new ArrayList<>();
        listMessenger.add(new Telegram(this));
        listMessenger.add(new VK(this));
        listMessenger.add(new Viber(this));
    }

    public List<Message> getListMessage(Messenger typeMessenger) {
        return listMessage.stream().filter(c -> c.getMessenger().equals(typeMessenger)).collect(Collectors.toList());
    }

    public void addMessage(Message message) {
        listMessage.add(message);
    }

    public Messenger chooseRandomMessenger() {
        int randomMessenger = (int) (Math.random() * 3);
        return listMessenger.stream().filter(c -> c.getId() == randomMessenger).findFirst().get();
    }
}
