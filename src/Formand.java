public class Formand extends Employee {
  private String username;
  private String password;
  private int vacation;
  private int salary;

  public Formand(String name, String lastName, String email, String username, String password, int vacation, int salary) {
    super(name, lastName, email, username,password);
    this.vacation = vacation;
    this.salary = salary;
  }



  @Override
  public String toString() {
    return "Formand{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", vacation=" + vacation +
            ", salary=" + salary +
            '}';
  }
}
