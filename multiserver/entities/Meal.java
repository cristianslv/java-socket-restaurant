package multiserver.entities;

public class Meal {
  private String id;
  private String name;
  private String price;
  private String description;

  public Meal(String id, String name, String price, String description) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getPrice() {
    return this.price;
  }

  public String getDescription() {
    return this.description;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "[Nome: " + this.name + ", Preço: " + this.price + ", Descrição: " + this.description + "]";
  }
}
