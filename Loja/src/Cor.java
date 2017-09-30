/**
 * Created by aluno on 29/09/17.
 */
public enum Cor {
    PRETA("preta"),
    BRANCA("branca"),
    VERMELHO("vermelho"),
    AMARELO("amarelo"),
    VERDE("verde"),
    CIANO("ciano"),
    AZUL("azul"),
    MAGENTA("magenta");

    private String nome;

    Cor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
