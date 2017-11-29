import java.util.*;
import java.io.*;

public class test {

  public static void main(String[] args) throws Exception {
    Scanner scan = new Scanner(new File("result"));
    ArrayList<ArrayList<String>> arraylist = new ArrayList<>();

    String line = scan.nextLine();
    String[] array = line.split("],");

    for (String s : array) {
      String quick = s.replaceAll("[\\[\\]]", "");
      arraylist.add(new ArrayList<>(Arrays.asList(quick)));
      System.out.println(Arrays.asList(quick));
    }
    ArrayList<String> gi = new ArrayList<>(Arrays.asList("222", "333", "444"));
    arraylist.add(gi);
    System.out.println(arraylist);
  }
}
