package multiserver.services;

import java.io.DataOutputStream;

import multiserver.repositories.MealRepository;

public class DeleteMealService {
  public static void execute(DataOutputStream outStream, String id) {
    MealRepository mealRepository = new MealRepository(outStream);
    
    mealRepository.delete(id);
  }
}
