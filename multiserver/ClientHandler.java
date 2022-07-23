package multiserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import multiserver.services.DeleteMealService;
import multiserver.services.ListMealsService;
import multiserver.services.ListOrdersService;
import multiserver.services.UpdateMealService;
import multiserver.services.CreateMealService;
import multiserver.services.CreateOrderService;
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
          case "createorder":
            CreateOrderService.execute(outStream, splitClientMessage[1], splitClientMessage[2]);
          break;
          case "list":
            ListMealsService.execute(outStream);
          break;
          case "listorders":
            ListOrdersService.execute(outStream);
          break;
          case "delete":
            DeleteMealService.execute(outStream, splitClientMessage[1]);
          break;
          case "update":
            UpdateMealService.execute(outStream, splitClientMessage[1]);
          break;
          default:
          break;
        }
      }

      inStream.close();
      outStream.close();
      clientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}