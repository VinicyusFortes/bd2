package aor;

import java.util.Scanner;
import java.sql.Date;

public class Perguntador {
  private final Validador validador = new Validador();

  public String perguntaTitulo(Scanner scanner) {
    boolean continuar = false;
    String titulo = "";
    System.out.print("\nInforme o título da música: ");
    while(!continuar) {
      titulo = scanner.nextLine().trim();
      if(!validador.validarTituloMusica(titulo)) {
        System.out.print("\n\tParametro errado. Informe novamente: ");
      }else {
        continuar = true;
      }
    }

    return titulo;
  }

  public Date perguntaData(Scanner scanner) {
    System.out.print("\nInforme a data da música (AAAA-MM-DD): ");
    Date data = Date.valueOf(scanner.nextLine().trim());

    return data;
  }

  public String perguntaGenero(Scanner scanner) {
    System.out.print("\nInforme o genero da música: ");
    String genero = scanner.nextLine().trim();

    return genero;
  }

  public String perguntaSimNao(Scanner scanner) {
    boolean continuar = false;
    String simNao = "";
    System.out.print("\nEscolha: ");
    System.out.print("\n1. Sim");
    System.out.print("\n2. Não");
    System.out.print("\nResposta: ");
    while(!continuar) {
      simNao = scanner.nextLine().trim();
      if(!validador.validarSimNao(simNao)) {
        System.out.print("\n\tParametro errado. Informe novamente: ");
      }else {
        continuar = true;
      }
    }






    return scanner.nextLine().trim();
  }

  public String perguntaInserirAlbum(Scanner scanner) {
    System.out.print("\nDeseja inserir a música num álbum?");
    String resposta = perguntaSimNao(scanner);
//    System.out.print("\nResposta: ");

    return resposta;
  }

  public String perguntaAutor(Scanner scanner) {
    System.out.print("\nInforme o nome do autor: ");
    return scanner.nextLine().trim().toLowerCase();
  }
}
