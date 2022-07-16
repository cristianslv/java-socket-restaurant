package multiserver.repositories;

import java.io.FileWriter;
import java.io.IOException;

import multiserver.entities.Meal;

public class MealRepository {

  public void save(Meal meal) {
    try {
      FileWriter mealsDB = new FileWriter("meals.csv", true);

      mealsDB.append(String.join(",", meal.getId(), meal.getName(), meal.getPrice(), meal.getDescription()));
      mealsDB.append("\n");

      mealsDB.flush();
      mealsDB.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
