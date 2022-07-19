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
      int id = this.getNextId();

      mealsDB.append(String.join(",", String.valueOf(id), meal.getName(), meal.getPrice(), meal.getDescription()));
      mealsDB.append("\n");

      mealsDB.flush();
      mealsDB.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void list(DataOutputStream outStream) {
    try {
      String line = "";
			File file = new File("meals.csv");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
			
      while((line = bufferedReader.readLine()) != null) {
        String[] values = line.split(",");
        Meal meal = new Meal(values[0], values[1], values[2], values[3]);
        System.out.println(meal.toString());
        
        outStream.writeUTF(meal.toString());
        outStream.flush();
			}

      outStream.writeUTF("exit");
      outStream.flush();

			bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  } 
  
  private int getNextId() {
    try {
      int count = 0;
      File file = new File("meals.csv");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
			
      while(bufferedReader.readLine() != null) {
				count++;
			}

      bufferedReader.close();
			return count + 1;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return 0;
  } 
}
