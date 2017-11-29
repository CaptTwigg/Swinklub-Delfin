import java.io.*;
import java.util.*;

public class Main {


  public static void main(String[] args) throws Exception {
    ArrayList<Employee> employees = new ArrayList<>();
    employees.add(new Formand("Bob", "Olsen", "Bob@delfin.dk", "Bigboss", "1234", 5, 5));
    employees.add(new Kasserer("Bob", "Olsen", "Bob@delfin.dk", "MoneyMan", "1234"));

    ArrayList<ArrayList<String>> test = new ArrayList<>();
    ArrayList<String> disciplin = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    disciplin.add("Crawl");
    disciplin.add("Butterfly");
    test.add(disciplin);
    result.add("11,56");
    result.add("11,55");
    test.add(result);


    String[][] array = new String[2][5];
    array[1][1] = "sdf";

   FileWriter file = new FileWriter(new File("result"),true);
   PrintStream stream = new PrintStream(new File("result"));
  for( int i = 0; i < 4; i++){
    //stream.printf("%s \n", test);
    file.write(String.format("%s \n",test ));
  }
  file.close();



    for (String a : test.get(0)) {
      System.out.printf("%s \t", a);

    }
    System.out.println();
    for (String a : test.get(1)) {
      System.out.println(a);
    }

//    for (ArrayList row : test) {
//      for (int j = 0; j < 2; j++){
//        System.out.printf("%s \t1",row);
//      }
//      for(int i = 2; i < row.size(); i++){
//        System.out.printf("%s \t2", row.get(1));
//      }
//      System.out.println();
//    }

    // mainMenu(employees);
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
