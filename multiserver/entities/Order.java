package multiserver.entities;

public class Order {
  private String id;
  private String mealId;
  private String clientId;

  public Order(String id, String mealId, String clientId) {
    this.id = id;
    this.mealId = mealId;
    this.clientId = clientId;
  }

  public String getId() {
    return this.id;
  }

  public String getMealId() {
    return this.mealId;
  }

  public String getClientId() {
    return this.clientId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMealId(String mealId) {
    this.mealId = mealId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }
}
