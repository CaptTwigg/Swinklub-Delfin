import java.io.*;
import java.util.*;


public class MemberUtil {

  private static final String file = "src/members.dat";
  private static ArrayList<Member> members = new ArrayList<>();

  /*
  Overview of methods:
    memberMenu
    addMember
    alreadyExist
    addMemberElse
    disciplineArray
    changeMember
    showMember
    saveToFile
    loadFromFile
    loadArray
    stringToBoolean
   */

  public static void memberMenu() throws Exception {

    Scanner scanner = new Scanner(System.in);
    boolean keepGoing = true;
    loadFromFile();

    do {
      System.out.println("\n1: Add Member 2: Change member info 3: Show members");

      switch (scanner.nextInt()) {
        case -1:
          keepGoing = false;
          break;
        case 1:
          addMember();
          break;
        case 2:
          changeMember();
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
    String memberType = stringToBoolean("Enter member type active/passive: ", "active", "passive", "Not valid input");


    // Check if new member is passive and then use short or long constructor
    if (alreadyExist(firstName, lastName)) System.out.println("Member already exsist");
    else if (memberType.toLowerCase().equals("passive")) {
      members.add(new Member(firstName, lastName, memberType));
      members.get(members.size() - 1).setPayment();
    } else {
      addMemberElse(firstName, lastName, memberType);
    }
  }

  // Check if member already exsit
  private static boolean alreadyExist(String firstName, String lastName) {
    String email = firstName + lastName + "@delfin.dk";
    for (Member m : members) {
      if (email.toLowerCase().equals(m.getEmail().toLowerCase())) return true;
    }
    return false;
  }

  // method for long constructor member creating
  private static void addMemberElse(String firstName, String lastName, String memberType) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter discipline: ");
    ArrayList<String> discipline = disciplineArray();

    String team = stringToBoolean("Exerciser or Competitive: ", "exerciser", "competitive", "Not valid input");
    System.out.print("Enter age: ");
    int age = input.nextInt();

    // create object member and set payment
    members.add(new Member(firstName, lastName, discipline, memberType, team, age));
    members.get(members.size() - 1).setPayment();

    // Ask if new member want to pay now
    System.out.printf("Wanna pay now? \t price: %s\n", members.get(members.size() - 1).getPayment());
    String payed = stringToBoolean("Enter yes or no: ", "yes", "no", "Not valid input");
    members.get(members.size() - 1).setPayed(payed);

    // Adds Empty arrays for each discipline
    for (String s : discipline) {
      members.get(members.size() - 1).addArrayToResults(new ArrayList());
    }
  }

  // Choose disciplines for member when new member added
  private static ArrayList<String> disciplineArray() {
    Scanner input = new Scanner(System.in);
    String[] disciplines = {"Crawl", "Rygcrawl", "Butterfly", "Brystsvømning", "Hundesvømning"};
    ArrayList<String> discipline = new ArrayList<>();

    System.out.println("1: Crawl, 2: Rygcrawl 3: Butterfly, 4: Brystsvømning, 5: Hundesvømning");
    System.out.println("Enter the number of the discipline fx. 2 or 1 2 3 for multiply disciplines");
    String chose = input.next();
    String[] split = chose.split("");

    for (int i = 0; i < split.length; i++) {
      int get = Integer.parseInt(split[i]);
      discipline.add(disciplines[get - 1]);
    }

    return discipline;
  }

  private static void changeMember() {
    Scanner input = new Scanner(System.in);
    int memberIndex;
    do {
      System.out.print("Enter full name: ");
      String firstName = input.next();
      String lastName = input.next();
      memberIndex = searchIndex(firstName, lastName);
    } while (memberIndex == -1);
    System.out.println(members.get(memberIndex));


    boolean keepGoing = true;
    do {
      System.out.println("1: First name, 2: Last name, 3: Email 4: Member type, 5: Age");
      switch (input.nextInt()) {
        case -1:
          keepGoing = false;
          break;
        case 1:
          System.out.print("Enter new first name: ");
          members.get(memberIndex).setFirstName(input.next());
          break;
        case 2:
          System.out.print("Enter new last name: ");
          members.get(memberIndex).setLastName(input.next());
          break;
        case 3:
          System.out.print("Enter new Email: ");
          members.get(memberIndex).setEmail(input.next());
          break;
        case 4:
          members.get(memberIndex).setMemberType(stringToBoolean("Enter activityform Passive or Active: ", "passiv", "active", "Not valid input"));
          break;
        case 5:
          System.out.print("Enter new age: ");
          members.get(memberIndex).setAge(input.nextInt());
          break;
        default:
          System.out.println("Not valid menu number");
      }
      members.get(memberIndex).setPayment();
    } while (keepGoing);
  }

  private void showSearch(String name) {
    ArrayList<Member> foundArray = new ArrayList<>();
    for (Member member : members) {
      if (name
              .toLowerCase()
              .equals(member.getFirstName())
              || name
              .toLowerCase()
              .equals(member.getLastName())) {
        foundArray.add(member);
      }
    }
    for (int index = 0; index < members.size(); index++)
      System.out.println(index + " " + members.get(index));
  }

  private static int searchIndex(String first, String last) {
    for (int index = 0; index < members.size(); index++)
      if (first.equalsIgnoreCase(members.get(index).getFirstName()) && last.equalsIgnoreCase(members.get(index).getLastName()))
        return index;
    return -1;
  }

  private static void showMembers() {
    for (Member member : members) System.out.println(member);
  }

  private static void saveToFile() throws IOException {
    new PrintStream(new File(file));
    for (Member member : members) member.saveToFile(file);
  }

  // Creates new Member with every token on a line in a file and adds Member to arrayList "members"
  private static void loadFromFile() throws FileNotFoundException {
    Scanner readFile = new Scanner(new File(file));
    while (readFile.hasNextLine()) {
      Scanner line = new Scanner(readFile.nextLine());
      members.add(new Member(line.next(), line.next(), line.next(), line.next(), line.next(), line.next(), line.next(), line.nextInt(), line.nextInt(), loadArray(line.nextLine())));
    }
    for (Member setDiscipline : members) {
      setDiscipline.setDiscipline(setDiscipline.getResults().get(0));
    }
  }

  // method to clean up array from file. Returns new arrayList.
  private static ArrayList loadArray(String raw) {
    ArrayList<ArrayList<String>> loadedArray = new ArrayList<>();
    String[] array = raw.trim().split("](.*?)\\[");

    for (String index : array) {
      String cleanString = index.replaceAll("[\\[\\]]", "");
      loadedArray.add(new ArrayList<>(Arrays.asList(cleanString))); // adds new arrayList with cleanString to loadedArray arrayList
    }
    return loadedArray;
  }

  private static String stringToBoolean(String message, String first, String second, String error) {
    Scanner input = new Scanner(System.in);
    System.out.print(message);
    String inputString = input.next();
    String returnVal;

    if (inputString.toLowerCase().equals(first)) returnVal = first;
    else if (inputString.toLowerCase().equals(second)) returnVal = second;
    else {
      System.out.println(error);
      returnVal = stringToBoolean(message, first, second, error);
    }

    return returnVal;
  }

  //Test main for memberUtil
  public static void main(String[] args) throws Exception {
    memberMenu();
  }

  // Emil method
  //
  public static int addResult() {
    Scanner input = new Scanner(System.in);

    System.out.println("Write the name of the member! Write \"back\" to go back");
    String firstName = input.next();
    if (firstName.equalsIgnoreCase("back")) {
      return -1;
    }
    String lastName = input.next();
    for (int i = 0; i < members.size(); i++) {
      if (firstName.equalsIgnoreCase(members.get(i).getFirstName()) && lastName.equalsIgnoreCase(members.get(i).getLastName())) {
        return i;
      }
    }

    System.out.println("Member does not exist");
    return addResult();
  }

  public static void discResult(int i) {
    Scanner chose = new Scanner(System.in);
    Scanner time = new Scanner(System.in);
    if (i >= 0) {
      System.out.println("Enter the number of the discipline");
      System.out.println(members.get(i).getDiscipline());

      int temp = chose.nextInt();
      System.out.println("Enter recorded time");
      members.get(i).getResults().get(temp).add(time.next());
      System.out.println(members.get(i).getResults());
    }
  }
}
