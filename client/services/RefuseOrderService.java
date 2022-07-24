package client.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RefuseOrderService {
  public static void execute(DataInputStream inStream, DataOutputStream outStream, String adminMessage) 
  {
    System.out.println("_______________\n\n");
    System.out.println("Recusar pedido!\n");
    System.out.println("_______________\n");
    System.out.println("Caso o id do pedido exista, uma mensagem de sucesso será informada.\n");
    System.out.println("Caso o id do pedido não exista, uma mensagem de erro será informada.\n");

    try {
      String[] refusedValues = adminMessage.split(",");

      if (refusedValues.length == 2) {
        outStream.writeUTF("refuse/" + refusedValues[1]);
        outStream.flush();
        
        System.out.println("\n\n" + inStream.readUTF() + "\n\n");
      } else {
        System.out.println("\n\nExistem informações faltando.\n\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
