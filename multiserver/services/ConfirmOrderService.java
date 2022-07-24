package multiserver.services;

import java.io.DataOutputStream;

import multiserver.repositories.OrderRepository;

public class ConfirmOrderService {
  public static void execute(DataOutputStream outStream, String id) {
    OrderRepository orderRepository = new OrderRepository(outStream);
    
    orderRepository.confirm(id);
  }
}
