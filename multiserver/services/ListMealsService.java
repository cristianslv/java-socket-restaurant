package multiserver.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import multiserver.repositories.MealRepository;

public class ListMealsService {
  public static void execute(
    DataInputStream inStream,
    DataOutputStream outStream  
  ) {
    MealRepository mealRepository = new MealRepository();
    
    mealRepository.list(inStream, outStream);
  }
}
