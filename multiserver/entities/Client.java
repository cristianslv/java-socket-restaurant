package multiserver.entities;

public class Client {
  private String id;
  private String type;

  public Client(String id, String type) {
    this.id = id;
    this.type = type;
  }

  public String getId() {
    return this.id;
  }

  public String getType() {
    return this.type;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }
}
