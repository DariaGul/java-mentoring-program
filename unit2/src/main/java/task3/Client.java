package task3;

import java.util.UUID;

public class Client {

    private UUID id;
    private Messenger clientMessenger;

    public Client(Server server) {
        clientMessenger = server.chooseRandomMessenger();
        id = UUID.randomUUID();
    }

    public Messenger getClientMessenger() {
        return clientMessenger;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("clientId = %s, clientMessenger = %s", id, clientMessenger.getClass().getSimpleName());
    }
}
