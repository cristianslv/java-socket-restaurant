package multiserver.services;

import java.io.DataOutputStream;

import multiserver.entities.Meal;
import multiserver.repositories.MealRepository;

public class CreateMealService {
  public static void execute(DataOutputStream outStream, String clientMessage) {
    String[] values = clientMessage.split(",");

    MealRepository mealRepository = new MealRepository(outStream);
    
    Meal meal = new Meal(String.valueOf(0), values[0], values[1], values[2]);
    
    mealRepository.save(meal);
  }
}
