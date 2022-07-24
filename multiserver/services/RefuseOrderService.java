package multiserver.services;

import java.io.DataOutputStream;

import multiserver.repositories.OrderRepository;

public class RefuseOrderService {
  public static void execute(DataOutputStream outStream, String id) {
    OrderRepository orderRepository = new OrderRepository(outStream);
    
    orderRepository.refuse(id);
  }
}
