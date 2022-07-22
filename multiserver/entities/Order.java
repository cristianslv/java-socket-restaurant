package multiserver.entities;

public class Order {
  private String id;
  private String meals;
  private String clientId;

  public Order(String id, String meals, String clientId) {
    this.id = id;
    this.meals = meals;
    this.clientId = clientId;
  }

  public String getId() {
    return this.id;
  }

  public String getMeals() {
    return this.meals;
  }

  public String getClientId() {
    return this.clientId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMealId(String mealId) {
    this.meals = mealId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }
}
