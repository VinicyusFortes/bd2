package aor;

public class Validador {
  public boolean validarTituloMusica(String titulo) {
    String requisitoTituloMusica = "^[\\wÀ-ÖØ-öø-ÿ\\s\\p{P}]{2,}$";
    return titulo.matches(requisitoTituloMusica);
  }

  public boolean validarNomeAutor(String nome) {
    String requisitoNomeAutor = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?: [A-Za-zÀ-ÖØ-öø-ÿ]+)*$";
    return nome.matches(requisitoNomeAutor);
  }

  public boolean validarSimNao(String simNao) {
    String requisitoSimNao = "^(sim|n[ãa]o|[12])$";
    return simNao.matches(requisitoSimNao);
  }

  public boolean validaGenero(String genero) {
  String requisitoGenero = "^[1-6]$";
  return genero.matches(requisitoGenero);
  }
}
