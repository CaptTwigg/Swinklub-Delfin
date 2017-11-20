public class Kontigent extends Member{
//  private int payment = payment();

  public Kontigent(String firstName, String lastName, boolean pay, boolean memberType) {
    super(firstName, lastName, pay, memberType);
  }

  public Kontigent(String firstName, String lastName, String discipline, boolean pay, boolean memberType, boolean team, int age) {
    super(firstName, lastName, discipline, pay, memberType, team, age);
  }

  private int payment(){
    int price = 0;
    if(isPay()) price = 500;
//    if(getDiscipline().equals("butterfly"))price = 100;
    return price;
  }
  public String toString(){
    return "payment: " + payment();
  }

}
