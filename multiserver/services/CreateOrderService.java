package multiserver.services;

import java.io.DataOutputStream;

import multiserver.repositories.OrderRepository;

public class CreateOrderService {
  public static void execute(DataOutputStream outStream, String clientMessage, String clientId) {
    OrderRepository orderRepository = new OrderRepository(outStream);
    
    orderRepository.save(clientMessage, clientId);
  }
}
