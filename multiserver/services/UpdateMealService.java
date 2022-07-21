package multiserver.services;

import java.io.DataOutputStream;

import multiserver.entities.Meal;
import multiserver.repositories.MealRepository;

public class UpdateMealService {
  public static void execute(DataOutputStream outStream, String clientMessage) {
    String[] mealValues = clientMessage.split(",");

    MealRepository mealRepository = new MealRepository(outStream);
    Meal meal = new Meal(mealValues[0], mealValues[1], mealValues[2], mealValues[3]);

    mealRepository.update(meal);
  }
}
