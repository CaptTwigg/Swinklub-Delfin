import java.io.*;
import java.util.*;

public class Main {


  public static void main(String[] args) throws Exception {
    ArrayList<Employee> employees = new ArrayList<>();
    employees.add(new Formand("Bob", "Olsen", "Bob@delfin.dk", "Bigboss", "1234", 5, 5));
    employees.add(new Kasserer("Bob", "Olsen", "Bob@delfin.dk", "MoneyMan", "1234"));

    mainMenu(employees);
  }

  private static void mainMenu(ArrayList<Employee> employees) throws Exception {
    int menuNumber = 0;
    int employeeType = login(employees);

    if (employees.get(employeeType) instanceof Formand) menuNumber = 1;
    if (employees.get(employeeType) instanceof Kasserer) menuNumber = 2;


    do {
      switch (menuNumber) {
        case 1:
          MemberUtil.memberMenu();
          System.out.println("Formand menu");

          break;
        case 2:
//          kontigentMenu();
          System.out.println("Kasserer menu");
          break;
      }
    } while (false);
  }

  public static int login(ArrayList<Employee> employees) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter username:");
    String user = input.next();
    System.out.println("Enter password");
    String pass = input.next();
    int index = -1;
    for (int i = 0; i < employees.size(); i++) {
      if (employees.get(i).checkUsername(user) && employees.get(i).checkPassword(pass)) {
        index = i;
      }
    }
    if (index == -1) {
      System.out.println("Username or Password wrong");
      index = login(employees);

    }
    return index;
  }

}
