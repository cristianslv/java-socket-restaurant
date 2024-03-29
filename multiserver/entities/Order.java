package multiserver.entities;

public class Order {
  private String id;
  private String meals;
  private String status;
  private String clientId;

  public Order(String id, String meals, String clientId, String status) {
    this.id = id;
    this.meals = meals;
    this.status = status;
    this.clientId = clientId;
  }

  public String getId() {
    return this.id;
  }

  public String getMeals() {
    return this.meals;
  }

  public String getStatus() {
    return this.status;
  }

  public String getClientId() {
    return this.clientId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMeals(String meals) {
    this.meals = meals;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  @Override
  public String toString() {
    String trueStatus = "";

    switch (this.status) {
      case "0":
        trueStatus = "Aguardando confirmação";
      break;
      case "1":
        trueStatus = "Pedido confirmado";
      break;
      case "2":
        trueStatus = "Pedido finalizado";
      break;
      default:
      break;
    }

    return "[ID: " + this.id + ", IDs dos Items: " + this.meals + ", Status: " + trueStatus + ", ID do cliente: " + this.clientId + "]";
  }
}
