/**
 * Created by aluno on 29/09/17.
 */

import java.lang.StringBuilder;

public class Roupa extends Produto{

    Tamanho tamanho;
    Cor cor;

    public Roupa(String nome, Tamanho tamanho, Cor cor, float preco, int codigoDeBarras) {
        super(nome, preco, codigoDeBarras);
        this.tamanho = tamanho;
        this.cor = cor;
    }

    @Override
    public String toString() {
        StringBuilder construtor =  new StringBuilder();
        construtor.append("Nome: ");
        construtor.append(super.getNome());
        construtor.append("\n");
        construtor.append("Tamanho: ");
        construtor.append(tamanho.getLetra());
        construtor.append("\n");
        construtor.append("Cor: ");
        construtor.append(cor.getNome());
        construtor.append("\n");
        construtor.append("Preço: ");
        construtor.append(super.getPreco());
        construtor.append(" R$\n");
        return construtor.toString();
    }
}
