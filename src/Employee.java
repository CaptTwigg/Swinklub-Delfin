public class Employee {

  protected String name;
  protected String lastName;
  protected String email;
  protected String username;
  protected String password;

  public Employee(String name, String lastName, String email, String username, String password) {
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
  }
  public boolean checkUsername(String username){
    return this.username.toLowerCase().equals(username.toLowerCase());
  }
  public boolean checkPassword(String password){
    return this.password.equals(password);
  }
}
