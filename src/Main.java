import java.io.*;
import java.util.*;

public class Main {

  private static final String file = "src/employees.dat";
  private static ArrayList<Employee> employees = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    loadFromFile();
    mainMenu();
    saveToFile();
  }

  private static void mainMenu() throws Exception {

    int menuNumber = 0;
    int employeeType = login();

    //Checks what menu the logged in user are
    if (employees.get(employeeType) instanceof Employee) menuNumber = 4;
    if (employees.get(employeeType) instanceof Træner) menuNumber = 3;
    if (employees.get(employeeType) instanceof Kasserer) menuNumber = 2;
    if (employees.get(employeeType) instanceof Formand) menuNumber = 1;

    switch (menuNumber) {
      case 1:
        MemberUtil.memberMenu();
        break;
      case 2:
//          kontigentMenu();
        System.out.println("Kasserer menu");
        break;
      case 4:
        createEmployee();
        break;
    }
  }

  private static int login() {
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
      index = login();
    }
    return index;
  }

  private static void createEmployee() {
    Scanner input = new Scanner(System.in);

    System.out.println("Enter employee type");
    String emp = input.next();
    System.out.println("Enter first name");
    String first = input.next();
    System.out.println("Enter last name");
    String last = input.next();
    System.out.println("Enter username");
    String user = input.next();
    System.out.println("Enter password");
    String pass = input.next();

    if (emp.equalsIgnoreCase("Formand")) employees.add(new Formand(first, last, user, pass));
    else if (emp.equalsIgnoreCase("Kasserer")) employees.add(new Kasserer(first, last, user, pass));
    else if (emp.equalsIgnoreCase("Træner")) employees.add(new Træner(first, last, user, pass));
    else System.out.println("Not right employee type");
  }

  private static void saveToFile() throws IOException {
    new PrintStream(new File(file)); //Clears file

    for (Employee e : employees) {
      e.setType(e.getClass().toString());
      e.saveToFile(file);
    }
  }

  private static void loadFromFile() throws FileNotFoundException {
    Scanner readFile = new Scanner(new File(file));

    while (readFile.hasNextLine()) {
      Scanner line = new Scanner(readFile.nextLine());
      line.next(); //used to skips "class" in file
      String emp = line.next();

      if (emp.equalsIgnoreCase("Formand"))
        employees.add(new Formand(line.next(), line.next(), line.next(), line.next()));
      else if (emp.equalsIgnoreCase("Kasserer"))
        employees.add(new Kasserer(line.next(), line.next(), line.next(), line.next()));
      else if (emp.equalsIgnoreCase("Træner"))
        employees.add(new Træner(line.next(), line.next(), line.next(), line.next()));
      else
        employees.add(new Employee(line.next(), line.next(), line.next(), line.next()));
    }
  }
}
