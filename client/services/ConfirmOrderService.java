package client.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConfirmOrderService {
  public static void execute(DataInputStream inStream, DataOutputStream outStream, String adminMessage) 
  {
    System.out.println("______________________\n\n");
    System.out.println("Confirmação de pedido!\n");
    System.out.println("______________________\n");
    System.out.println("Caso o id do pedido exista, uma mensagem de sucesso será informada.\n");
    System.out.println("Caso o id do pedido não exista, uma mensagem de erro será informada.\n");

    try {
      String[] confirmedValues = adminMessage.split(",");

      if (confirmedValues.length == 2) {
        outStream.writeUTF("confirm/" + confirmedValues[1]);
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
