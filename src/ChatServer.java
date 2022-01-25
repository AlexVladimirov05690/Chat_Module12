import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ChatServer {
    ArrayList <Client> clients = new ArrayList<>();
    ServerSocket serverSocket;


    public ChatServer() {
        try {
            serverSocket = new ServerSocket(1234);
            run();
        } catch (IOException e) {
            System.out.println("Can't create soket");
            e.printStackTrace();
        }
    }

    public void run(){
        while(true) {
            System.out.println("Waiting...");
            // ждем клиента из сети
            try {
                Socket socket = serverSocket.accept();
                clients.add(new Client(socket,this));
                System.out.println("Client connected!" + clients.size() + " client(s) in chat");
            } catch (IOException e) {
                System.out.println("Error accept!");
                e.printStackTrace();
            }
        }
    }

    public void sendAll(String message) {
        for(Client client: clients) {
            client.receive(message);
        }
    }


}
