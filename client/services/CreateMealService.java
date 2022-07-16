package client.services;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CreateMealService {
  public CreateMealService(DataInputStream inStream, DataOutputStream outStream) 
  {
    String message;
    Console messageReceiver = System.console();

    System.out.println("____________________________\n\n");
    System.out.println("Criação de item no cardápio!\n");
    System.out.println("____________________________\n");
    System.out.println("Para criar um item, digite exatamente assim:\n\n");
    System.out.println("nomeitem,precoitem,descricaoitem\n\n");
    System.out.println("Cada informação deve estar separada por vírgula, como indicado.");
    System.out.println("Caso haja mais que duas vírgulas, o processo será repetido.\n");

    do {
      message = messageReceiver.readLine("Digite a sentença de forma correta aqui: ");

      String[] mealInformations = message.split(",");

      if (mealInformations.length == 3) {
        try {
          outStream.writeUTF("create/"+message);
          outStream.flush();
          
          System.out.println(inStream.readUTF() + "\n\n");
          
          break;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    } while(!message.equals("exit"));
  }
}
