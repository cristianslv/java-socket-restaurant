package client.types;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import client.actions.PassiveActions;

public class PassiveEmployee {
  public void startConnection(String ip, int port) {
    try {
      Socket clientSocket = new Socket(ip, port);
      
      DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());
      
      new PassiveActions(clientSocket, inStream, outStream); 
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}