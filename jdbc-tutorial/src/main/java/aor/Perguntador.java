package aor;

import java.util.Scanner;
import java.sql.Date;

public class Perguntador {

  public String perguntaTitulo(Scanner scanner) {
    System.out.print("\nInforme o título da música: ");
    String titulo = scanner.nextLine();

    return titulo;
  }

  public Date perguntaData(Scanner scanner) {
    System.out.print("\nInforme a data da música: ");
    Date data = Date.valueOf(scanner.nextLine());

    return data;
  }

  public String perguntaGenero(Scanner scanner) {
    System.out.print("\nInforme o genero da música: ");
    String genero = scanner.nextLine();

    return genero;
  }

  public String perguntaSimNao(Scanner scanner) {
    System.out.print("\n1. Sim");
    System.out.print("\n2. Não");

    return scanner.nextLine();
  }

  public String perguntaInserirAlbum(Scanner scanner) {
    System.out.print("\nDeseja inserir a música num álbum?");
    String resposta = perguntaSimNao(scanner);
    System.out.print("\nResposta: ");

    return resposta;
  }
}
