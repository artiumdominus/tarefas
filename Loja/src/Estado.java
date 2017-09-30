/**
 * Created by aluno on 29/09/17.
 */
public enum Estado {
    SEMINOVO("Seminovo"),
    CONSERVADO("Conservado"),
    RAZOÁVEL("Razoável"),
    DEGRADADO("Degradado");

    private String descricao;

    Estado(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
