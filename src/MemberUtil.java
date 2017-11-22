import java.io.*;
import java.util.*;


public class MemberUtil {

  private static final String file = "src/members.dat";
  private static ArrayList<Member> members = new ArrayList<>();

  public static ArrayList<Member> getMembers() {
    return members;
  }

  public static void memberMenu() throws Exception {


    Scanner scanner = new Scanner(System.in);
    boolean keepGoing = true;
    do {
      System.out.println("\n1: Add Member 2: Change member info 3: Show members");
      loadFromFile();

      switch (scanner.nextInt()) {
        case -1:
          keepGoing = false;
          break;
        case 1:
          addMember();
          break;
        case 2:
//          changeMember();
          break;
        case 3:
          showMembers();
          break;
        default:
          System.out.println("Not valid menu number.");
      }
    } while (keepGoing);
    saveToFile();
  }

  private static void addMember() {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter first name: ");
    String firstName = input.next();
    System.out.print("Enter last name: ");
    String lastName = input.next();
    String memberType = stringInput("Enter member type active/passive: ", "active", "passive", "Not valid input");

    // Check if new member is passive and then use short or long constructor
    if (memberType.toLowerCase().equals("passive")) {
      members.add(new Member(firstName, lastName, memberType));
      members.get(members.size() - 1).autoPayment();
    } else {
      addMemberElse(firstName, lastName, memberType);
    }
    System.out.println("Member created.");
  }

  // method for long constructor member creating
  private static void addMemberElse(String firstName, String lastName, String memberType) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter discipline: ");
    String discipline = input.next();
    String team = stringInput("Exerciser or Competitive: ", "exerciser", "competitive", "Not valid input");
    System.out.print("Enter age: ");
    int age = input.nextInt();

    // create object member and set payment
    members.add(new Member(firstName, lastName, discipline, memberType, team, age));
    members.get(members.size() - 1).autoPayment();

    // Ask if new member want to pay now
    System.out.printf("Wanna pay now? \t price: %s\n", members.get(members.size() - 1).getPayment());
    String payed = stringInput("Enter yes or no: ", "yes", "no", "Not valid input");
    members.get(members.size() - 1).setPayed(payed);
  }

  private static void showMembers() {
    for (int i = 0; i < members.size(); i++) {
      System.out.println(members.get(i));
    }
  }

  private static void saveToFile() throws IOException {
    new PrintStream(new File(file));
    for (int index = 0; index < members.size(); index++) {
      members.get(index).saveToFile(file);
    }
  }

  private static void loadFromFile() throws FileNotFoundException {
    Scanner readFile = new Scanner(new File(file));
    while (readFile.hasNextLine()) {
      Scanner line = new Scanner(readFile.nextLine());
      members.add(new Member(line.next(), line.next(), line.next(), line.next(), line.next(), line.next(), line.next(), line.next(), line.nextInt(), line.nextInt()));
    }
  }

  private static String stringInput(String message, String first, String second, String error) {
    Scanner input = new Scanner(System.in);
    System.out.print(message);
    String inputString = input.next();
    String returnVal = "";

    if (inputString.toLowerCase().equals(first) || inputString.toLowerCase().equals(second)) {
      if (inputString.toLowerCase().equals(first)) returnVal = first;
      if (inputString.toLowerCase().equals(second)) returnVal = second;
    } else {
      System.out.println(error);
      returnVal = stringInput(message, first, second, error);
    }

    return returnVal;
  }

  public static void main(String[] args) throws Exception {
    memberMenu();
  }
}
