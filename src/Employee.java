import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Employee {

  protected String name;
  protected String lastName;
  protected String username;
  protected String password;
  private String type;

  public Employee( String name, String lastName, String username, String password) {
    this.name = name;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
  }

  public boolean checkUsername(String username) {
    return this.username.toLowerCase().equals(username.toLowerCase());
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }

  public void saveToFile(String file) throws IOException{
    FileWriter writer = new FileWriter(new File(file), true);
    String format = String.format("%s %s %s %s %s \n", type, name, lastName, username, password);
    writer.write(format);
    writer.close();
  }

  public String toString() {
    return String.format("Name: %s %s, Username: %s, Password: %s", name, lastName, username, password);
  }

  public void setType(String type){
    this.type = type;
  }
}
