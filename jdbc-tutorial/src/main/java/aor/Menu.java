package aor;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
  Perguntador perguntador = new Perguntador();

  public void menu(App app, Scanner scanner) throws SQLException {
    boolean continuar = true;
    while(continuar) {
      System.out.print("\nEscolha uma das opcoes: ");
      System.out.print("\n1. Adicionar música");
      System.out.print("\n2. Alterar título de uma música");
      System.out.print("\n3. Apagar uma música");
      System.out.print("\n4. Ver playlist");
      System.out.print("\n5. Consulta detalhada de todas as músicas");
      System.out.print("\n6. fechar programa");
      System.out.print("\nEscolha: ");
      String escolha = scanner.nextLine();

      switch(escolha) {
        case "1":
          /*String titulo = perguntador.perguntaTitulo(scanner);
          Date data = perguntador.perguntaData(scanner);
          String genero ="N/A";

          // Pergunta se quer inserir género na música
          boolean opcaoGenero = perguntador.perguntaGeneroInserir(scanner);

          if (opcaoGenero){       // Se escolher inserir género
          genero = perguntador.perguntaGenero(scanner);
          } // Senão, continua pra baixo

          String inserirAlbum = perguntador.perguntaInserirAlbum(scanner);
          String nomeAlbum = " ";
          if(inserirAlbum.equalsIgnoreCase("sim") || inserirAlbum.equalsIgnoreCase("1")) {
            nomeAlbum = perguntador.perguntaTituloAlbum(scanner);

            //caso o album nao exista é criado
            if(!app.procuraAlbum(nomeAlbum)) {
              app.criarAlbum(nomeAlbum);
            }
            String posicaoAlbum = perguntador.posicaoAlbum(scanner);

            //insercao na tabela album
          }

          String autor = perguntador.perguntaAutor(scanner);

          if(!app.procuraAutor(autor)) {
            System.out.print("\nO autor nao existe. Será criado");
            app.criarAutor(autor);
          }

          try {
            app.inserirMusica(titulo, data, autor, genero);
          } catch(Exception e){
            System.out.println("[Erro]" + e.getMessage());
          }*/

          String titulo = perguntador.perguntaTitulo(scanner);
          Date data = perguntador.perguntaData(scanner);
          String genero = "N/A";

// Pergunta se quer inserir gênero na música
          boolean opcaoGenero = perguntador.perguntaGeneroInserir(scanner);
          if (opcaoGenero) { // Se escolher inserir gênero
            genero = perguntador.perguntaGenero(scanner);

            /*// Verifica se o gênero existe, se não, insere
            if (!app.procuraGenero(genero)) {
              app.criarGenero(genero); // Método para inserir o gênero na tabela
            }*/
          }

          String inserirAlbum = perguntador.perguntaInserirAlbum(scanner);
          String nomeAlbum = "";
          long posicaoAlbum = -1; // Inicializa a posição como -1 para verificação posterior

          if (inserirAlbum.equalsIgnoreCase("sim") || inserirAlbum.equalsIgnoreCase("1")) {
            nomeAlbum = perguntador.perguntaTituloAlbum(scanner);

            // Caso o álbum não exista, é criado
            if (!app.procuraAlbum(nomeAlbum)) {
              app.criarAlbum(nomeAlbum);
            }
            posicaoAlbum = perguntador.posicaoAlbum(scanner); // Captura a posição do álbum
          }

          String autor = perguntador.perguntaAutor(scanner);

          if (!app.procuraAutor(autor)) {
            System.out.print("\nO autor não existe. Será criado");
            app.criarAutor(autor);
          }

          try {
            // Insere a música e captura o ID da música inserida
             app.inserirMusica(titulo, data, autor, genero);

            long musicaId = app.obterUltimoIdMusica(); // Busca o último ID inserido
            if (musicaId != -1) {
              if (inserirAlbum.equalsIgnoreCase("sim") || inserirAlbum.equalsIgnoreCase("1")) {
                long albumId = app.procuraAlbumId(nomeAlbum);
                app.inserirMusicaAlbum(musicaId, albumId, posicaoAlbum);
              }
            } else {
              System.out.println("Não foi possível obter o ID da música.");
            }
          } catch (Exception e) {
            System.out.println("[Erro] " + e.getMessage());
          }
          break;
        case "2":
          try {
            app.consultaMusica();
          } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
          }
          String idTemp = perguntador.perguntaIdMusica(scanner);
          int idMusicaAlterar = Integer.parseInt(idTemp);
          String tituloMusica = perguntador.perguntaTitulo(scanner);
          app.alterarTitulo(idMusicaAlterar, tituloMusica);
          break;
        case "3":
          try {
            System.out.print("\nInforme o id da música: ");
            String ids = scanner.nextLine();
            int idMusicaApagar = Integer.parseInt(ids);
            app.apagarMusica(idMusicaApagar);
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
}
