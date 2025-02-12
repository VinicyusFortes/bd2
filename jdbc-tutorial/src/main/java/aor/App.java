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

    private void consultaMusica() throws SQLException {
        String sql = "SELECT * FROM musica";
        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            try (ResultSet rs1 = stm.executeQuery()) {
                while (rs1.next()) {
                  /*  System.out.println("Nome: " + rs1.getString("nome") + " Dep:"
                            + rs1.getString("ndep"));*/

                    System.out.println("id: " + rs1.getString("id") + " titulo:"
                            + rs1.getString("titulo_musica") + " data" + rs1.getString("data_criacao") + " genero" + rs1.getString("genero"));
                }
            }
        }
    }


    private void inserirMusica() throws SQLException{
        String tituloMusica = "nothing else matters";
        Date dataCriacao = Date.valueOf("2025-01-01");
        String genero = "rock";

        String sql = "insert into musica (titulo_musica, data_criacao, genero) values (?, ?, ?)";

        try (PreparedStatement stm = conn.prepareStatement(sql)){
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

    @Override
    public void close() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
    }

    public static void main(String[] args) {
        try (App app = new App()) {
            //app.queryEmployees();
            //app.consultaMusica();
            app.inserirMusica();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}