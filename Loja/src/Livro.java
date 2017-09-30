/**
 * Created by aluno on 29/09/17.
 */

import java.lang.StringBuilder;

public class Livro extends Produto {
    private String autor;
    private int ano;
    private Estado estado;

    public Livro(String nome, String autor, int ano, Estado estado, float preco, int codigoDeBarras) {
        super(nome, preco, codigoDeBarras);
        this.autor = autor;
        this.ano = ano;
        this.estado = estado;
    }

    @Override
    public String toString() {
        StringBuilder construtor = new StringBuilder();
        construtor.append("Nome: ");
        construtor.append(super.getNome());
        construtor.append("\n");
        construtor.append("Autor: ");
        construtor.append(autor);
        construtor.append("\n");
        construtor.append("Ano: ");
        construtor.append(ano);
        construtor.append("\n");
        construtor.append("Estado: ");
        construtor.append(estado.getDescricao());
        construtor.append("\n");
        construtor.append("Preço: ");
        construtor.append(super.getPreco());
        construtor.append(" R$\n");
        return construtor.toString();
    }

}
