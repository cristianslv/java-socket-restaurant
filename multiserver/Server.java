package multiserver;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static int serverPort = 59091;
    public static Map<String, Socket> sockets = new HashMap<>(); 
    
    public static void main(String[] args) {      
        try {
            new FileWriter("orders.csv");
            new FileWriter("clients.csv");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        try {
            int socketCounter = 0;
            ServerSocket serverSocket = new ServerSocket(serverPort);
            
            while (true) {
                socketCounter++;
                Socket socket = serverSocket.accept();

                sockets.put(String.valueOf(socketCounter), socket);

                new ClientHandler(socket, socketCounter).start();
            }
        } catch (IOException e) {
            if(e.getMessage().contains("Connection reset")) {
                System.out.println("Cliente desconectado!");
            } 
        }
    }
}