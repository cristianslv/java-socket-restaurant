package multiserver.services;

import java.io.DataOutputStream;

import multiserver.repositories.MealRepository;

public class ListMealsService {
  public static void execute(DataOutputStream outStream) {
    MealRepository mealRepository = new MealRepository();
    
    mealRepository.list(outStream);
  }
}
