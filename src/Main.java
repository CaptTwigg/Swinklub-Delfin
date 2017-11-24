import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    ArrayList<Employee> emps = new ArrayList<>();
    emps.add(new Formand("Bob", "Olsen", "Bob@delfin.dk", "Bigboss", "1234", 5, 5));
    emps.add(new Kasserer("Bob", "Olsen", "Bob@delfin.dk", "MoneyMan", "1234"));


    //login(emps);
    mainMenu(emps);

  }

  private static void mainMenu(ArrayList<Employee> emps) throws Exception {
    Scanner input = new Scanner(System.in);
    int menuNumber = 0;
    int temp = login(emps);

    if (emps.get(temp) instanceof Formand) menuNumber = 1;
    if (emps.get(temp) instanceof Kasserer) menuNumber = 2;

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

  public static int login(ArrayList<Employee> emps) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter username:");
    String user = input.next();
    System.out.println("Enter password");
    String pass = input.next();
    int index = -1;
    for (int i = 0; i < emps.size(); i++) {
      if (emps.get(i).checkUsername(user) && emps.get(i).checkPassword(pass)) {
        index = i;
      }
    }
    if(index == -1 ) {
      System.out.println("Username or Password wrong");
      index = login(emps);

    }
    return index;
  }

}
