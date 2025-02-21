package aor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.Date;
import java.time.format.DateTimeParseException;


public class Perguntador {
  private final Validador validador = new Validador();
  //titulo musica
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

  //data musica
  public Date perguntaData(Scanner scanner) {
    Date data = null;
    boolean dataValida = false;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    while (!dataValida) {
      System.out.print("\nInforme a data da música (AAAA-MM-DD): ");
      String dataString = scanner.nextLine().trim();

      // Valida e tenta converter a string em LocalDate
      try {
        LocalDate localDate = LocalDate.parse(dataString, formatter); // Validação da data

        // A data é válida, então converte para java.sql.Date
        data = Date.valueOf(localDate); // Converte LocalDate para java.sql.Date
        dataValida = true; // Data válida, sai do loop
      } catch (DateTimeParseException e) {
        System.out.println("Formato inválido! Use o formato AAAA-MM-DD.");
      } catch (IllegalArgumentException e) {
        System.out.println("Data inválida! A data deve existir no calendário.");
      }
    }

    return data; // Retorna a data como java.sql.Date
  }


  //genero musica
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

  //sim ou nao
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

  //inserir album
  public String perguntaInserirAlbum(Scanner scanner) {
    System.out.print("\nDeseja inserir a música num álbum?");
    String resposta = perguntaSimNao(scanner);
    return resposta;
  }

  //nome autor
  public String perguntaAutor(Scanner scanner) {
    System.out.print("\nInforme o nome do autor: ");
    return scanner.nextLine().trim().toLowerCase();
  }

  //id musica
  public String perguntaIdMusica(Scanner scanner){
    System.out.println("\nInforme o id da música: ");
    return scanner.nextLine().trim().toLowerCase();
  }

  //titulo album
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