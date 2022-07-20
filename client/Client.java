package client;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

import client.types.AdminEmployee;
import client.types.Customer;
import client.types.PassiveEmployee;

public class Client {
  private static int serverPort = 59091;
  private static String ipv4Ethernet = "127.0.0.1";

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
        adm.startConnection(ipv4Ethernet, serverPort);
        break;
      case "pass pass":
        PassiveEmployee pass = new PassiveEmployee();
        pass.startConnection(ipv4Ethernet, serverPort);
        break;
      case "":
        Customer customer = new Customer();
        customer.startConnection(ipv4Ethernet, serverPort);
        break;
      default:
        break;
    }
  }
}
