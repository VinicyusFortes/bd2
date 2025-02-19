package aor;

import java.util.Scanner;
import java.sql.Date;


public class Main {
  private final Perguntador perguntador = new Perguntador();

  public void menu(App app, Scanner scanner) {
    boolean continuar = true;
    while(continuar) {
      System.out.print("\nEscolha uma das opcoes: ");
      System.out.print("\n1. Adicionar música");
      System.out.print("\n2. Alterar título de uma música");
      System.out.print("\n3. Apagar uma música");
      System.out.print("\n4. Ver playlist");
      System.out.print("\n5. Consultar todas as músicas");
      System.out.print("\n6. fechar programa");
      System.out.print("\nEscolha: ");
      String escolha = scanner.nextLine();

      switch(escolha) {
        case "1":

          String titulo = perguntador.perguntaTitulo(scanner);
          Date data = perguntador.perguntaData(scanner);
          String genero = perguntador.perguntaGenero(scanner);
          String inserirAlbum = perguntador.perguntaInserirAlbum(scanner);
          System.out.println(inserirAlbum);

          //todo inserir logica de adicionar a musica a um album
          /*if(inserirAlbum.equalsIgnoreCase("sim")) {

          }*/

          try {
            app.inserirMusica(titulo, data);
          } catch(Exception e){
            System.out.println("[Erro]" + e.getMessage());
          }
          break;
        case "2":
          app.alterarTitulo(2);
          break;
        case "3":
          try {
            System.out.print("\nInforme o id da música: ");
            String ids = scanner.nextLine();
            int id = Integer.parseInt(ids);
            app.apagarMusica(id);
          } catch(Exception e) {
            System.out.println("[Erro]" + e.getMessage());
          }
          System.out.println("apagar musica");
          break;
        case "4":
          app.verPlaylist();
          break;
        case "5":
          try {
            app.consultaMusica();
          } catch(Exception e) {
            System.out.println("[Erro]" + e.getMessage());
          }
          break;
        case "6":
          continuar = false;
          System.out.println("Obrigado e até a proxima");
          break;
        default:
          System.out.println("dados errados, informe novamente");
          break;
      }
    }
  }


  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      App app = new App();
      Main mainMenu = new Main();
      mainMenu.menu(app, scanner);
    } catch(Exception e) {
      System.out.println("Erro:" + e.getMessage());
    }



  }
}
