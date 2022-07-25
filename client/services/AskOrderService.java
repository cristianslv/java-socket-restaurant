package client.services;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AskOrderService {
  public static void execute(DataInputStream inStream, DataOutputStream outStream, String clientId) 
  {
    String message;
    Console messageReceiver = System.console();

    System.out.println("___________________\n\n");
    System.out.println("Realizar um pedido!\n");
    System.out.println("___________________\n");
    System.out.println("Para realizar um pedido, digite exatamente assim:\n\n");
    System.out.println("id_item_1,id_item_2,id_item_3,id_item_3...");
    System.out.println("Cada id de um item no cardápio deve estar separado por vírgula, como indicado.");
    System.out.println("Caso haja uma barra na sentença, o processo será repetido.\n\n");

    do {
      message = messageReceiver.readLine("Digite a sentença de forma correta aqui: ");
    } while (message.contains("/"));

    try {
      outStream.writeUTF("createorder/"+message+"/"+clientId);
      outStream.flush();
      
      String serverMessage = "";

      while(true) {
        serverMessage = inStream.readUTF();
        
        if (serverMessage.contains("refuse")
          || serverMessage.contains("finish")) {
          break;
        } else {
          System.out.println(serverMessage);
        }
      } 
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
