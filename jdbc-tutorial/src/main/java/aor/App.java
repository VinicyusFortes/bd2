package aor;

import java.sql.*;

public class App implements AutoCloseable {
  private final static String URL = "jdbc:postgresql://localhost:5433/postgres";
  private final static String USER = "postgres";
  private final static String PASSWORD = "postgres";
  private Connection conn;

  public App() throws SQLException {
    this.conn = DriverManager.getConnection(App.URL, App.USER, App.PASSWORD);
  }

  private void queryEmployees() throws SQLException {
    String sql = "SELECT * FROM emp WHERE ndep = ? or ndep= ?";
    try (PreparedStatement stm = conn.prepareStatement(sql)) {
      stm.setInt(1, 10);
      stm.setInt(2, 20);
      try (ResultSet rs1 = stm.executeQuery()) {
        while (rs1.next()) {
          System.out.println("Nome: " + rs1.getString("nome") + " Dep:"
                  + rs1.getString("ndep"));
        }
      }
    }
  }


  //1 - INSERIR MUSICA
  public void inserirMusica(String tituloMusica, Date dataCriacao, String genero) throws SQLException{
    String query = "insert into musica (titulo_musica, data_criacao, genero) values (?, ?, ?)";

    try (PreparedStatement stm = conn.prepareStatement(query)){
      stm.setString(1, tituloMusica);
      stm.setDate(2, dataCriacao);
      stm.setString(3, genero);

      int rowsAffected = stm.executeUpdate();
      if (rowsAffected > 0) {
        System.out.printf("Música '%s' foi inserida na tabela.%n", tituloMusica);
      } else {
        System.out.println("Erro ao inserir música.");
      }
    }
  }

  //2 - APAGAR MUSICA
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


  //3 - CONSULTAR MUSICAS REGISTADAS
  public void consultaMusica() throws SQLException {
    String query = "SELECT * FROM musica";
    try (PreparedStatement stm = conn.prepareStatement(query)) {
      try (ResultSet rs1 = stm.executeQuery()) {
        System.out.print("\nAs músicas atualmente registadas são: ");
        System.out.print("\nTÍTULO - DATA - GENERO MUSICAL\n");
        while (rs1.next()) {
                  /*  System.out.println("Nome: " + rs1.getString("nome") + " Dep:"
                            + rs1.getString("ndep"));*/

          System.out.println("ID: " + rs1.getString("id") + " | Titulo: "
                  + rs1.getString("titulo_musica") + " | Data: " + rs1.getString("data_criacao") + " | Genero: " + rs1.getString("genero"));
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