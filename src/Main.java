public class Main {

  public static void main(String[] args) {

  }

  private static void mainMenu() {
    System.out.println("1: Member, 2: Kontigent ");
    do {
      switch (input) {
        case 1:
          memberMenu();
        case 2:
          kontigentMenu();
        default:
          System.out.println("Not valid menu number");
      }
    } while (true);
  }
}
