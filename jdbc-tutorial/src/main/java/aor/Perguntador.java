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
    System.out.print("\nOs generos disponíveis sao:");
    System.out.print("\n1 - Clássico");
    System.out.print("\n2 - Pop");
    System.out.print("\n3 - Rock");
    System.out.print("\n4 - Hip-hop");
    System.out.print("\n5 - Heavy Metal \\m/");
    System.out.print("\n6 - Outros");
    System.out.print("\nInforme o número do genero da música: ");
    String g = scanner.nextLine().trim();
    String genero = "";

    switch(g) {
      case "1": genero = "Clássico"; break;
      case "2": genero = "Pop"; break;
      case "3": genero = "Rock"; break;
      case "4": genero = "Hip-hop"; break;
      case "5": genero = "Heavy Metal"; break;
      case "6": genero = "Outros"; break;
      default: System.out.println("Valor errado."); break;
    }
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
    return simNao;
  }

  public String perguntaInserirAlbum(Scanner scanner) {
    System.out.print("\nDeseja inserir a música num álbum?");
    String resposta = perguntaSimNao(scanner);
    return resposta;
  }

  public String perguntaAutor(Scanner scanner) {
    System.out.print("\nInforme o nome do autor: ");
    return scanner.nextLine().trim().toLowerCase();
  }

  public String perguntaIdMusica(Scanner scanner){
    System.out.println("\nInforme o id da música: ");
    return scanner.nextLine().trim().toLowerCase();
  }

  public String perguntaTituloAlbum(Scanner scanner) {
    boolean continuar = false;
    String titulo = "";
    System.out.print("\nInforme o título do álbum: ");
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
}