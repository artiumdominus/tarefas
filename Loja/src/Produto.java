/**
 * Created by aluno on 29/09/17.
 */
public class Produto {
    private String nome;
    private float preco;

    public Produto(String nome, float preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Produto() {}

    public String getNome() {
        return this.nome;
    }

    public float getPreco() {
        return this.preco;
    }
}
