package client;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

import client.types.AdminEmployee;
import client.types.Customer;
import client.types.PassiveEmployee;

public class Client {
  public static void main(String[] args) {
    String clientMessage;
    Console clientMessageReceiver = System.console();
    List<String> allowedOptions = Arrays.asList("adm adm","pass pass", ""); 
    
    do {
      System.out.println("_____________________________\n\n");
      System.out.println("Bem vindo à Cyber Lanchonete!\n");
      System.out.println("_____________________________\n");
      System.out.println("Se você é um funcionário 'administrador', digite: seuusuario suasenha");
      System.out.println("Se você é um funcionário 'passivo', digite: seuusuario suasenha");
      System.out.println("Se você é um cliente, apenas pressione a tecla ENTER.\n");

      clientMessage =  clientMessageReceiver.readLine("Digite a opção aqui: ");
    } while(!allowedOptions.contains(clientMessage));

    switch(clientMessage) {
      case "adm adm":
        AdminEmployee adm = new AdminEmployee();
        adm.startConnection("127.0.0.1", 59091);
        break;
      case "pass pass":
        PassiveEmployee pass = new PassiveEmployee();
        pass.startConnection("127.0.0.1", 59091);
        break;
      case "":
        Customer customer = new Customer();
        customer.startConnection("127.0.0.1", 59091);
        break;
      default:
        break;
    }
  }
}
