package multiserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import multiserver.services.CreateMealService;
import multiserver.services.CreateUserService;

class ClientHandler extends Thread {
  private int id;
  private Socket clientSocket;

  public ClientHandler(Socket socket, int id) {
    this.id = id;
    this.clientSocket = socket;
  }

  @Override
  public void run() {
    try {
      DataInputStream inStream = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());

      String clientMessage;

      clientMessage = inStream.readUTF();
      CreateUserService.execute(this.id, clientMessage);

      outStream.writeUTF(String.valueOf(this.id));  

      while ((clientMessage = inStream.readUTF()) != null) {
        String[] splitClientMessage = clientMessage.split("/");
        String clientAction = splitClientMessage[0];

        switch (clientAction) {
          case "create":
            CreateMealService.execute(splitClientMessage[1]);
          break;
          default:
          break;
        }

        outStream.writeUTF("mano você me enviu isso? " + clientMessage);
        outStream.flush();
      }

      inStream.close();
      outStream.close();
      clientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}