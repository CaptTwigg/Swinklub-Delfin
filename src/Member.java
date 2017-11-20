public class Member {

  private String firstName;
  private String lastName;
  private String email;       // firstName + lastName@delfin.dk
  private String discipline;  // butterfly, crawl, rygcrawl, brystsvømning og hundesvømning
  private boolean pay;        // true yes - false no
  private boolean memberType; // true active - false passive
  private boolean swim;       // true junior - false senior
  private boolean team;       // true motionist - false konkurrencesvømmer
  private int age;

  // passive member constructor
  public Member(String firstName, String lastName, boolean pay, boolean memberType) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = firstName + lastName + "@delfin.dk";
    this.pay = pay;
    this.memberType = memberType;

  }

  // active member constructor
  public Member(String firstName, String lastName, String discipline, boolean pay, boolean memberType, boolean team, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = firstName + lastName + "@delfin.dk";
    this.discipline = discipline;
    this.pay = pay;
    this.memberType = memberType;
    this.team = team;
    this.age = age;
    this.swim = age < 18;
  }

  public String getDiscipline() {
    return discipline;
  }

  public boolean isPay() {

    return pay;
  }
}