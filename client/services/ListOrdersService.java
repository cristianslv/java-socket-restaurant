package client.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ListOrdersService {
  public static void execute(DataInputStream inStream, DataOutputStream outStream) 
  {
    System.out.println("_____________________________\n\n");
    System.out.println("Todos os pedidos de clientes!\n");
    System.out.println("_____________________________\n");
    
    try {
      String message = "";

      outStream.writeUTF("listorders/");
      outStream.flush();

      while(true) {
        message = inStream.readUTF();

        if (message.equals("exit")) {
          break;
        }

        System.out.println(message + "\n");
      } 
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
