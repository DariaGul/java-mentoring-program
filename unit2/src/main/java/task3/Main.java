package task3;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        Client client1 = new Client(server);
        Client client2 = new Client(server);
        Client client3 = new Client(server);

        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client3);

        startDialogue(client1, client2, "Hello, it's me",
                      "I was wondering if after all these years you'd like to meet");

        startDialogue(client2, client3, "Long as my heart's beating in my chest",
                      "This old dog ain't about to forget");

        startDialogue(client3, client1, "We may as well go home", "As I did on my own");
    }

    private static void startDialogue(Client client1, Client client2, String text1, String text2) {
        System.out.println();
        client1.getClientMessenger().sendMessage(text1, client1, client2);
        System.out.println(client1.getClientMessenger().readMessage(client1, client2));
        System.out.println(client1.getClientMessenger().readMessage(client2, client1));
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client2.getClientMessenger().sendMessage(text2, client1, client2);
        System.out.println(client1.getClientMessenger().readMessage(client1, client2));
        System.out.println(client1.getClientMessenger().readMessage(client2, client1));
    }
}
