public class Formand extends Employee {
  String username;
  String password;
  int vacation;
  int salary;

  public Formand(String name, String lastName, String email, String username, String password, int vacation, int salary) {
    super(name, lastName, email);
    this.username = username;
    this.password = password;
    this.vacation = vacation;
    this.salary = salary;
  }
}
