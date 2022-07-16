package multiserver;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private static int serverPort = 59091;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {      
        try {
            FileWriter mealsDB = new FileWriter("meals.csv");
            FileWriter ordersDB = new FileWriter("orders.csv");
            FileWriter clientsDB = new FileWriter("clients.csv");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            int socketCounter = 0;
            serverSocket = new ServerSocket(serverPort);
            
            while (true) {
                socketCounter++;

                new ClientHandler(serverSocket.accept(), socketCounter).start();
            }
        } catch (IOException e) {
            if(e.getMessage().contains("asd")) {
                System.out.println("Cliente desconectado!");
            }

            e.printStackTrace();
        }
    }

    public static void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}