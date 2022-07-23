package multiserver.services;

import java.io.DataOutputStream;

import multiserver.repositories.OrderRepository;

public class ListOrdersService {
  public static void execute(DataOutputStream outStream) {
    OrderRepository orderRepository = new OrderRepository(outStream);
    
    orderRepository.list();
  }
}
