/**
 * Created by aluno on 29/09/17.
 */

import java.lang.Comparable;

public class Produto implements Comparable{
    private String nome;
    private float preco;
    private int codigoDeBarras;

    public Produto(String nome, float preco, int codigoDeBarras) {
        this.nome = nome;
        this.preco = preco;
        this.codigoDeBarras = codigoDeBarras;
    }

    public Produto() {}

    public String getNome() {
        return this.nome;
    }

    public float getPreco() {
        return this.preco;
    }

    public int getCodigoDeBarras() { return this.codigoDeBarras; }

    public boolean equals(Produto produto) { return this.codigoDeBarras == produto.codigoDeBarras; }


    @Override
    public int compareTo(Object o) {
        Produto outro = (Produto) o;
        //return this.nome.compareTo(outro.getNome());
        if (this.preco > outro.getPreco()) {
            return 1;
        } else if (this.preco < outro.getPreco()) {
            return -1;
        } else {
            return 0;
        }
    }
}
