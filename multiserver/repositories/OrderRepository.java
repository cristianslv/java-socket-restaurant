package multiserver.repositories;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import multiserver.entities.Meal;
import multiserver.entities.Order;

public class OrderRepository {
  DataOutputStream outStream;

  public OrderRepository(DataOutputStream outStream) {
    this.outStream = outStream;
  }

  public void save(String meals, String clientId) {
    int id = this.getNextId();

    try {
      List<Meal> requiredMeals = this.getMealsByIds(meals);

      outStream.writeUTF("\n\n______________________________\n");
      outStream.flush();

      outStream.writeUTF("Você pediu os seguintes items:\n");
      outStream.flush();

      outStream.writeUTF("______________________________\n\n");
      outStream.flush();

      for (Meal requiredMeal : requiredMeals) {
        outStream.writeUTF(requiredMeal.toString());
        outStream.flush();
      }

      String[] mealsIds = meals.split(",");

      FileWriter mealsDB = new FileWriter("orders.csv", true);

      mealsDB.append(String.join(",", String.valueOf(id), String.join("|", mealsIds), clientId, "0"));
      mealsDB.append("\n");


      outStream.writeUTF("\n\n______________________________\n");
      outStream.flush();

      outStream.writeUTF("Sucesso! Um pedido foi criado e seu status é: AGUARDANDO.");
      outStream.flush();
      
      outStream.writeUTF("______________________________\n\n");
      outStream.flush();

      outStream.writeUTF("\n\n______________________________\n");
      outStream.flush();
      
      outStream.writeUTF("Aguarde até que um funcionário lhe responda.");
      outStream.flush();
      
      outStream.writeUTF("______________________________\n\n");
      outStream.flush();
      
      mealsDB.flush();
      mealsDB.close();
    } catch (IOException e) {  
      this.handleIOException(" Não foi possível realizar um pedido.");
      e.printStackTrace();
    }
  }

  // public void delete(String id) {
  //   List<Meal> meals = this.getOrders();

  //   Meal foundMeal = meals.stream().filter(meal -> meal.getId().equals(id)).findAny().orElse(null);
    
  //   if (foundMeal != null) {
  //     meals = meals.stream().filter(meal -> !meal.getId().equals(id)).collect(Collectors.toList());
      
  //     try {
  //       FileWriter mealsDB = new FileWriter("meals.csv");
  
  //       for (Meal meal : meals) {
  //         mealsDB.append(String.join(",", meal.getId(), meal.getName(), meal.getPrice(), meal.getDescription()));
  //         mealsDB.append("\n");
  //       }
  
  //       outStream.writeUTF("Sucesso! Um item deletado do cardápio.");
  //       outStream.flush();
  
  //       mealsDB.flush();
  //       mealsDB.close();
  //     } catch (IOException e) {  
  //       this.handleIOException(" Não foi possível deletar um item no cardápio.");
  //       e.printStackTrace();
  //     }
  //   } else {
  //     this.handleIOException(" Não foi possível encontrar este item no cardápio.");
  //   }
  // }

  // public void update(Meal updatedMeal) {
  //   List<Meal> meals = this.getOrders();

  //   Meal foundMeal = meals
  //     .stream()
  //     .filter(meal -> meal.getId().equals(updatedMeal.getId()))
  //     .findAny()
  //     .orElse(null);
    
  //   if (foundMeal != null) {
  //     for (Meal meal : meals) {
  //       if (meal.getId().equals(updatedMeal.getId())) {
  //         meal.setId(updatedMeal.getId());
  //         meal.setName(updatedMeal.getName());
  //         meal.setPrice(updatedMeal.getPrice());
  //         meal.setDescription(updatedMeal.getDescription());
  //       }
  //     }
      
  //     try {
  //       FileWriter mealsDB = new FileWriter("meals.csv");
  
  //       for (Meal meal : meals) {
  //         mealsDB.append(String.join(",", meal.getId(), meal.getName(), meal.getPrice(), meal.getDescription()));
  //         mealsDB.append("\n");
  //       }
  
  //       outStream.writeUTF("Sucesso! O item foi atualizado.");
  //       outStream.flush();
  
  //       mealsDB.flush();
  //       mealsDB.close();
  //     } catch (IOException e) {  
  //       this.handleIOException(" Não foi possível atualizar este item.");
  //       e.printStackTrace();
  //     }
  //   } else {
  //     this.handleIOException(" Não foi possível encontrar este item no cardápio.");
  //   }
  // }

  public void list() {
    List<Order> orders = this.getOrders();
    
    try {
      for (Order order : orders) {
        outStream.writeUTF(order.toString());
        outStream.flush();
      }

      outStream.writeUTF("exit");
      outStream.flush();
    } catch (IOException e) {
      this.handleIOException(" Não foi possível concluir a listagem de items do cardápio.");
      e.printStackTrace();
    }
  } 
  
  private int getNextId() {
    List<Order> orders = this.getOrders();
    Order lastOrder = orders.isEmpty() ? null : orders.get(orders.size() - 1);

    return lastOrder != null ? Integer.valueOf(lastOrder.getId()) + 1 : 1;
  } 

  private List<Order> getOrders() {
    String line = "";
    List<Order> orders = new ArrayList<>();
    
    try {
			File file = new File("orders.csv");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
			
      while((line = bufferedReader.readLine()) != null) {
        String[] orderRow = line.split(",");

        Order order = new Order(orderRow[0], orderRow[1], orderRow[2], orderRow[3]);

        orders.add(order);
			}

      bufferedReader.close();

      return orders;
    } catch (IOException e) {
      this.handleIOException(" Não foi possível resgatar a lista de pedidos dos clientes.");
      e.printStackTrace();
    }

    return Collections.emptyList();
  }

  private List<Meal> getMealsByIds(String meals) {
    MealRepository mealRepository = new MealRepository(outStream);

    String[] mealsIds = meals.split(",");
    List<Meal> mealsList = mealRepository.getMeals();
    
    return mealsList.stream().filter(meal -> Arrays.asList(mealsIds).contains(meal.getId())).collect(Collectors.toList());
  }

  private void handleIOException(String message) {
    try {
      outStream.writeUTF("Erro!" + message);
      outStream.flush();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }
}
