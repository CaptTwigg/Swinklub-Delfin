import java.io.*;
import java.util.*;


public class MemberUtil {

  private static final String file = "src/members.dat";
  private static ArrayList<Member> members = new ArrayList<>();

  //Test main for memberUtil
  public static void main(String[] args) throws Exception {
    loadFromFile();
    top5("Crawl");

    //memberMenu();
    //kassererMenu();
    //trænerMenu();
  }

  /*
  Overview of methods:
    memberMenu
    addMember
    alreadyExist
    addMemberElse
    disciplineArray
    changeMember
    searchIndex
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

    // Ask if new member want to pay now
    System.out.printf("Wanna pay now? \t price: %s\n", members.get(members.size() - 1).getPayment());
    String payed = stringToBoolean("Enter yes or no: ", "yes", "no", "Not valid input");
    members.get(members.size() - 1).setPayed(payed);
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

    //Check if entered member exist or is found
    int memberIndex;
    do {
      System.out.print("Enter full name: ");
      String firstName = input.next();
      String lastName = input.next();
      memberIndex = searchIndex(firstName, lastName);
    } while (memberIndex == -1);
    System.out.println(members.get(memberIndex));

    // switch submenu for member
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
      loadedArray.add(new ArrayList<>(Arrays.asList(cleanString.split(",")))); // adds new arrayList with cleanString to loadedArray arrayList
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



   /*
 Kasserer methods and menu

Methods:
  kassererMenu
  showRestance - can sorts

  */

  public static void kassererMenu() throws IOException {
    loadFromFile();
    Scanner menuInput = new Scanner(System.in);
    boolean again = true;
    do {
      System.out.println("1: Sorted by have payed, 2: Sorted by haven't payed, -1 Logout");
      int menu = menuInput.nextInt();

      switch (menu) {
        case -1:
          again = false;
          break;
        case 1:
          showRestance("yes");
          break;
        case 2:
          showRestance("no");
          break;
      }

    } while (again);
    saveToFile();
  }


  private static void showRestance(String sort) {
    ArrayList<String> sorted = new ArrayList<>();
    for (Member m : members) {
      String format = String.format("Name: %s %-10s \t Membertype: %s \t Payment: %s \t Payed:  %s "
              , m.getFirstName(), m.getLastName(), m.getMemberType(), m.getPayment(), m.getPayed());
      if (sort.equalsIgnoreCase(m.getPayed()))
        sorted.add(0, format);
      else
        sorted.add(format);
    }
    for (String s : sorted)
      System.out.println(s);
  }

 /*
 Træner methods and menu

Methods:
  TrænerMenu
  findMemberIndex

  */

  public static void trænerMenu() throws IOException {
    loadFromFile();
    Scanner menuInput = new Scanner(System.in);
    boolean again = true;
    do {
      System.out.println("1: Add result, 2: show results, 3: Show top 5, -1 Logout");

      switch (menuInput.nextInt()) {
        case -1:
          again = false;
          break;
        case 1:
          discResult(findMemberIndex());
          break;
        case 2:
          System.out.println("Show");
          break;
        case 3:
          System.out.println("top 5");
          break;
        default:
          System.out.println("Not valid menu number");
      }

    } while (again);
    saveToFile();
  }

  private static int findMemberIndex() {
    Scanner input = new Scanner(System.in);

    System.out.println("Write the full name of the member! Write \"back\" to go back");
    String firstName = input.next();
    if (firstName.equalsIgnoreCase("back"))
      return -1;

    String lastName = input.next();
    for (int index = 0; index < members.size(); index++)
      if (firstName.equalsIgnoreCase(members.get(index).getFirstName()) && lastName.equalsIgnoreCase(members.get(index).getLastName()))
        return index;

    System.out.println("Member does not exist");
    return findMemberIndex();
  }

  public static void discResult(int memberIndex) {
    Scanner chose = new Scanner(System.in);

    if (memberIndex >= 0) {
      System.out.println("Enter the number of the discipline");
      System.out.println(members.get(memberIndex).getDiscipline());

      int disciplineNumber = chose.nextInt();
      System.out.println("Enter recorded time");
      double result = chose.nextDouble();
      members.get(memberIndex).getResults().get(disciplineNumber).add(result);
      System.out.println(members.get(memberIndex).getResults());
      switch (members.get(memberIndex).getResults().get(0).get(disciplineNumber - 1).toString()) {
        case "Crawl":
          disciplineNumber = 1;
          break;
        case "Rygcrawl":
          disciplineNumber = 2;
          break;
        case "Butterfly":
          disciplineNumber = 3;
          break;
        case "Brystsvømning":
          disciplineNumber = 4;
          break;
        case "Hundesvømning":
          disciplineNumber = 5;
          break;
        default:
          System.out.println("default");
          break;
      }
      int j = compareTop5(result, disciplineNumber);
      if (j != -1) {
        top5[disciplineNumber - 1].add(j, members.get(memberIndex).getFirstName() + members.get(memberIndex).getLastName() + " " + result);
        top5[disciplineNumber - 1].remove(5);
      }
    }
  }


  public static ArrayList[] loadTop5() throws Exception {
    ArrayList[] top5 = new ArrayList[5];
    ArrayList<String> crawl = new ArrayList<>();
    ArrayList<String> Rygcrawl = new ArrayList<>();
    ArrayList<String> Butterfly = new ArrayList<>();
    ArrayList<String> Brystsvømning = new ArrayList<>();
    ArrayList<String> Hundesvømning = new ArrayList<>();
    top5[0] = crawl;
    top5[1] = Rygcrawl;
    top5[2] = Butterfly;
    top5[3] = Brystsvømning;
    top5[4] = Hundesvømning;

    Scanner readFile = new Scanner(new File("Top5.dat"));
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        top5[i].add(j, (readFile.next() + " " + readFile.next()));
      }
    }

    return top5;
  }

  public static void printTop5(ArrayList[] grid) {
    Scanner input = new Scanner(System.in);
    System.out.println("What discipline do you want to see? Write the number \n1: Crawl\n2: Rygcrawl\n3: Butterfly\n4: Brystsvømning\n5: Hundesvømning");
    int i = input.nextInt();
    for (int j = 0; j < 5; j++) {
      System.out.printf("%20s \n", grid[i - 1].get(j));
    }
    System.out.println();

  }

  public static void saveTop5(ArrayList[] grid) throws Exception {
    PrintStream top5 = new PrintStream(new File("top5.dat"));
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        top5.print(grid[i].get(j) + " ");
      }
      top5.println();
    }
  }

  public static int compareTop5(double result, int discipline) {
    for (int i = 0; i < 5; i++) {
      String temp = top5[discipline - 1].get(i).toString();
      String[] parts = temp.split(" ");
      String temp2 = parts[1];
      double temp3 = Double.parseDouble(temp2);
      if (temp3 > result) {
        return (i);
      }
    }
    return (-1);

  }


  private static void top5(String dis) {
    ArrayList<Member> disciplinSort = new ArrayList<>();

    for (int i = 0; i < members.size(); i++) {
      for (int j = 0; j < members.get(i).getResults().get(0).size(); j++) {
        if (members.get(i).getResults().get(0).get(j).equals(dis))
          disciplinSort.add(members.get(i));
      }
    }

    for (int i = 0; i < disciplinSort.size(); i++) {
      for (int j = 0; j < disciplinSort.get(i).getResults().get(1).size(); j++) {
        System.out.println(Double.parseDouble(disciplinSort.get(i).getResults().get(1).get(j).toString()));
      }
    }
    ArrayList<ArrayList<String>> test = new ArrayList<>();
    ArrayList<Double> sortD = new ArrayList<>();
    ArrayList<String> sortS = new ArrayList<>();
    test.add(disciplinSort.get(1).getResults().get(0));
    for (int i = 0; i < disciplinSort.size(); i++) {
      for (int j = 0; j < disciplinSort.get(i).getResults().get(1).size(); j++) {
        sortD.add(Double.parseDouble(disciplinSort.get(1).getResults().get(1).get(i).toString()));
      }
    }
    Collections.sort(sortD);
    for (Double d : sortD)
      sortS.add(d.toString());
    test.add(sortS);
    System.out.println(test);
  }

  private static int disIndex(int member, String dis) {
    for (int i = 0; i < members.get(member).getResults().get(0).size(); i++) {
      if (members.get(member).getResults().get(0).get(i).equals(dis))
        return i;
    }
    return -1;
  }
}