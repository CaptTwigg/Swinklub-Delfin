import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {

    mainMenu();

  }

  private static void mainMenu() throws Exception{
    Scanner input = new Scanner(System.in);
    System.out.println("Choose a menu.");
    do {
      System.out.println("1: Member, 2: Kontigent ");
      switch (input.nextInt()) {
        case 1:
          MemberUtil.memberMenu();
          System.out.println(MemberUtil.getMembers());

          break;
        case 2:
//          kontigentMenu();
          break;
        default:
          System.out.println("Not valid menu number");
      }
    } while (false);
  }
}
