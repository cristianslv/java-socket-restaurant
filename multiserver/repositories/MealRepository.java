package multiserver.repositories;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

  public void list(DataInputStream inStream, DataOutputStream outStream) {
    try {
      Scanner scanner = new Scanner(new File("meals.csv"));
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
			scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }  
}
