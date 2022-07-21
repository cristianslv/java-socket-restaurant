package client.services;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class UpdateMealService {
  public static void execute(DataInputStream inStream, DataOutputStream outStream) 
  {
    String message;
    Console messageReceiver = System.console();

    System.out.println("________________________________\n\n");
    System.out.println("Atualização de item no cardápio!\n");
    System.out.println("________________________________\n");
    System.out.println("Para atualizar um item, digite exatamente assim:\n\n");
    System.out.println("id_item,novo_nome_item,preco_item,descricao_item\n\n");
    System.out.println("Cada informação deve estar separada por vírgula, como indicado.");
    System.out.println("A quantidade de vírgulas deve ser três, do contrário, o processo será repetido.\n");

    do {
      message = messageReceiver.readLine("Digite a sentença de forma correta aqui: ");

      String[] mealInformations = message.split(",");
      
      if (mealInformations.length == 4) {
        try {
          outStream.writeUTF("update/"+message);
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
