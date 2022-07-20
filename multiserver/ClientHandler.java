package multiserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import multiserver.services.ListMealsService;
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
            CreateMealService.execute(outStream, splitClientMessage[1]);
          break;
          case "list":
            ListMealsService.execute(outStream);
          break;
          // case "delete":
          //   DeleteMealsService.execute(outStream, splitClientMessage[1]);
          // break;
          default:
          break;
        }

        outStream.writeUTF("Já passou pela action " + clientAction);
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