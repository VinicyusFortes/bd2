package aor;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try {
      App app = new App();
      Menu menu = new Menu();
      menu.menu(app, scanner);
    } catch(Exception e) {
      System.out.println("Erro:" + e.getMessage());
    }
    scanner.close();
  }
}
