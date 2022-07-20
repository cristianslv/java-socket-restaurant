package multiserver.repositories;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import multiserver.entities.Meal;

public class MealRepository {
  DataOutputStream outStream;

  public MealRepository(DataOutputStream outStream) {
    this.outStream = outStream;
  }

  public void save(Meal meal) {
    int id = this.getNextId();

    try {
      FileWriter mealsDB = new FileWriter("meals.csv", true);

      mealsDB.append(String.join(",", String.valueOf(id), meal.getName(), meal.getPrice(), meal.getDescription()));
      mealsDB.append("\n");

      outStream.writeUTF("Sucesso! Um item foi criado no cardápio.");
      outStream.flush();

      mealsDB.flush();
      mealsDB.close();
    } catch (IOException e) {  
      this.handleIOException(" Não foi possível criar um item no cardápio.");
      e.printStackTrace();
    }
  }

  public void delete(String id) {
    List<Meal> meals = this.getMeals();

    Meal foundMeal = meals.stream().filter(meal -> meal.getId().equals(id)).findAny().orElse(null);
    
    if (foundMeal != null) {
      meals = meals.stream().filter(meal -> !meal.getId().equals(id)).collect(Collectors.toList());
      
      try {
        FileWriter mealsDB = new FileWriter("meals.csv");
  
        for (Meal meal : meals) {
          mealsDB.append(String.join(",", meal.getId(), meal.getName(), meal.getPrice(), meal.getDescription()));
          mealsDB.append("\n");
        }
  
        outStream.writeUTF("Sucesso! Um item deletado do cardápio.");
        outStream.flush();
  
        mealsDB.flush();
        mealsDB.close();
      } catch (IOException e) {  
        this.handleIOException(" Não foi possível deletar um item no cardápio.");
        e.printStackTrace();
      }
    } else {
      this.handleIOException(" Não foi possível encontrar este item no cardápio.");
    }
  }

  public void list() {
    List<Meal> meals = this.getMeals();
    
    try {
      for (Meal meal : meals) {
        System.out.println(meal.toString());
        
        outStream.writeUTF(meal.toString());
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
    List<Meal> meals = this.getMeals();
    Meal lastMeal = meals.get(meals.size() - 1);

    return Integer.valueOf(lastMeal.getId()) + 1;
  } 

  private List<Meal> getMeals() {
    String line = "";
    List<Meal> meals = new ArrayList<>();
    
    try {
			File file = new File("meals.csv");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
			
      while((line = bufferedReader.readLine()) != null) {
        String[] mealRow = line.split(",");

        Meal meal = new Meal(mealRow[0], mealRow[1], mealRow[2], mealRow[3]);

        meals.add(meal);
			}

      bufferedReader.close();

      return meals;
    } catch (IOException e) {
      this.handleIOException(" Não foi possível resgatar a lista de items no cardápio.");
      e.printStackTrace();
    }

    return Collections.emptyList();
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
