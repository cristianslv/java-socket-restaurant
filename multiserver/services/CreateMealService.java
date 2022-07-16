package multiserver.services;

import multiserver.entities.Meal;
import multiserver.repositories.MealRepository;

public class CreateMealService {
  public static void execute(String clientMessage) {
    String[] values = clientMessage.split(",");

    MealRepository mealRepository = new MealRepository();
    
    Meal meal = new Meal(String.valueOf(0), values[0], values[1], values[2]);
    
    mealRepository.save(meal);
  }
}
