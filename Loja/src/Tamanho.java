/**
 * Created by aluno on 29/09/17.
 */
public enum Tamanho {
    P("P"), M("M"), G("G");

    private String letra;

    Tamanho(String letra) {
        this.letra = letra;
    }

    public String getLetra() {
        return this.letra;
    }
}
