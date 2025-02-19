package aor;

import java.sql.*;

public class App implements AutoCloseable {
  //caminho do filipe(caso faca algum pull terei que trocar para a porta 5432
  //private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
  private final static String URL = "jdbc:postgresql://localhost:5433/postgres";

  private final static String USER = "postgres";
  private final static String PASSWORD = "postgres";
  private Connection conn;

  public App() throws SQLException {
    this.conn = DriverManager.getConnection(App.URL, App.USER, App.PASSWORD);
  }

  //1 - INSERIR MUSICA
  public void inserirMusica(String tituloMusica, Date dataCriacao, String autor, String genero) throws SQLException{
    String query = "insert into musica (titulo_musica, data_criacao, titulo_genero, nome_autor) values (?, ?, ?, ?)";

    try (PreparedStatement stm = conn.prepareStatement(query)){
      stm.setString(1, tituloMusica);
      stm.setDate(2, dataCriacao);
      stm.setString(3, autor);
      stm.setString(4, genero);

      int rowsAffected = stm.executeUpdate();
      if (rowsAffected > 0) {
        System.out.printf("Música '%s' foi inserida na tabela.%n", tituloMusica);
      } else {
        System.out.println("Erro ao inserir música.");
      }
    }
  }

  //retorna true caso o autor já exista
  public boolean procuraAutor(String autor){
    String queryAutor = "select nome_autor from autor where nome_autor = ?";

    try(PreparedStatement stmAutor = conn.prepareStatement(queryAutor)) {
      stmAutor.setString(1, autor);
      try (ResultSet rs = stmAutor.executeQuery()) {
        return rs.next();
      }
    } catch(Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
    return false;
  }

  public void criarAutor(String autor)  {
    String queryCriarAutor = "insert into autor (nome_autor) values(?)";

    try (PreparedStatement stmCriarAutor = conn.prepareStatement(queryCriarAutor)) {
      stmCriarAutor.setString(1, autor);
      stmCriarAutor.executeUpdate();
      System.out.println("\nAutor: " + autor + " foi criado");
    } catch(Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }

  //2 - CORRIGIR TITULO DE UMA MUSICA
  public void alterarTitulo(int id) {
    System.out.println("CORRIGIR TITULO DE MUSICA");
  }

  //3 - APAGAR MUSICA
  public void apagarMusica(int id) throws SQLException{
    String queryTitulo = "select titulo_musica from musica where id = ?";
    String queryDelete = "Delete from musica where id = ?";
    String nomeMusica = "";

    try (PreparedStatement stmTitulo = conn.prepareStatement(queryTitulo)) {
      stmTitulo.setInt(1, id);

      // Executa a consulta e obtém o resultado
      try (ResultSet rs = stmTitulo.executeQuery()) {
        if (rs.next()) {
          // Armazena o título da música retornado pela query
          nomeMusica = rs.getString("titulo_musica");
        }
      }
    }

    if(nomeMusica != null){
      try(PreparedStatement stm = conn.prepareStatement(queryDelete)) {
        stm.setInt(1, id);
        int rowsAffected = stm.executeUpdate();
        if(rowsAffected > 0) {
          System.out.printf("Música '%s' foi apagada a tabela.%n", nomeMusica);
        }
      }
    } else {
      System.out.print("Música não registada.");
    }
  }

  //4 - VER PLAYLIST
  public void verPlaylist() {
    System.out.println("VER PLAYLIST");
  }


  //5 - CONSULTAR MUSICAS REGISTADAS
  public void consultaMusica() throws SQLException {
    String query = "SELECT * FROM musica";
    try (PreparedStatement stm = conn.prepareStatement(query)) {
      try (ResultSet rs1 = stm.executeQuery()) {
        System.out.print("\nAs músicas atualmente registadas são: ");
        System.out.print("\nID - TÍTULO - DATA - GENERO MUSICAL\n");
        while (rs1.next()) {
          System.out.println("ID: " + rs1.getString("id")
                  + " | Titulo: " + rs1.getString("titulo_musica")
                  + " | Data: " + rs1.getString("data_criacao")
                  + " | Genero: " + rs1.getString("genero"));
        }
      }
    }
  }

  @Override
  public void close() throws SQLException {
    if (this.conn != null) {
      this.conn.close();
    }
  }

  public static void main(String[] args) {
    Main m = new Main();

    try (App app = new App()) {
      //app.queryEmployees();
      //app.consultaMusica();


    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}