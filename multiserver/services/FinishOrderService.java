package multiserver.services;

import java.io.DataOutputStream;

import multiserver.repositories.OrderRepository;

public class FinishOrderService {
  public static void execute(DataOutputStream outStream, String id) {
    OrderRepository orderRepository = new OrderRepository(outStream);
    
    orderRepository.finish(id);
  }
}
