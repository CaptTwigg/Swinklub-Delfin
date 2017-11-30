import java.io.*;
import java.util.*;

public class Member {

  private String firstName;
  private String lastName;
  private String email;       // firstName + lastName@delfin.dk
  private ArrayList<String> discipline = new ArrayList<>();  // butterfly, crawl, rygcrawl, brystsvømning og hundesvømning
  private String payed;        // true yes - false no
  private String memberType; // true active - false passive
  private String group;       // true junior - false senior
  private String team;       // true motionist - false konkurrencesvømmer
  private int age;
  private int payment;
  private ArrayList<ArrayList> results = new ArrayList<>();

  // passive member constructor
  public Member(String firstName, String lastName, String memberType) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = firstName + lastName + "@delfin.dk";
    this.memberType = memberType;
  }

  //Active member constructor
  public Member(String firstName, String lastName, ArrayList<String> discipline, String memberType, String team, int age){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = firstName + lastName + "@delfin.dk";
    this.discipline = discipline;
    this.results.add(discipline);
    this.memberType = memberType;
    this.group = age < 18 ? "Junior": "Senior";
    this.team = team;
    this.age = age;
  }

  // load file constructor
  public Member(String firstName, String lastName, String email , String payed, String memberType, String group, String team, int age, int payment, ArrayList results) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.discipline = discipline;
    this.payed = payed;
    this.memberType = memberType;
    this.group = group;
    this.team = team;
    this.age = age;
    this.payment = payment;
    this.results = results;
  }

  public void setPayment() {
    int payment;
    int senior = 1600;
    Double old = senior - senior * 0.25;
    payment = 1000;
    if (age >= 18) payment = senior;
    if (age >= 60) payment =  old.intValue();
    if(memberType.toLowerCase().equals("passive")) payment = 500;
    this.payment = payment;
  }

  public void saveToFile(String file) throws IOException{
    FileWriter writer = new FileWriter(new File(file), true);
    String format = String.format("%s %s %s %s %s %s %s %s %s %s \n"
            , this.firstName, this.lastName, this.email, this.payed, this.memberType, this.group, this.team, this.age, this.payment, this.results);
    writer.write(format);
    writer.close();

  }

  @Override
  public String toString() {
    return String.format("Name: %s %-10s \t Email: %-20s \t Payed: %-4s \t MemberType: %-4s \t Group: %-4s \t Team: %-8s \t Age: %-4s \t Payment: %-4s \t Discipline: %s"
            , this.firstName, this.lastName, this.email, this.payed, this.memberType, this.group, this.team, this.age, this.payment, this.discipline);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ArrayList<String> getDiscipline() {
    return discipline;
  }

  public void setDiscipline(ArrayList discipline) {
    this.discipline = discipline;
  }

  public void addToDiscipline(String discipline){this.discipline.add(discipline);}

  public String getPayed() {
    return payed;
  }

  public void setPayed(String payed) {
    this.payed = payed;
  }

  public String getMemberType() {
    return memberType;
  }

  public void setMemberType(String memberType) {
    this.memberType = memberType;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getPayment() {
    return payment;
  }

  public void setPayment(int payment) {
    this.payment = payment;
  }

  public ArrayList<ArrayList> getResults() {
    return results;
  }

  public void setResults(ArrayList<ArrayList> results) {
    this.results = results;
  }

  public void addArrayToResults(ArrayList array){
    this.results.add(array);
  }
}
