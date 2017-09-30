/**
 * Created by aluno on 29/09/17.
 */

import java.lang.StringBuilder;

public class Decoracao extends Produto{
    private float tamanhoCm;
    private float pesoGramas;

    public Decoracao(String nome, float tamanho, float pesoGramas, float preco) {
        super(nome, preco);
        this.tamanhoCm = tamanho;
        this.pesoGramas = pesoGramas;
    }

    @Override
    public String toString(){
        StringBuilder construtor = new StringBuilder();
        construtor.append("Nome: ");
        construtor.append(super.getNome());
        construtor.append("\n");
        construtor.append("Tamanho: ");
        construtor.append(tamanhoCm);
        construtor.append(" cm\n");
        construtor.append("Peso: ");
        construtor.append(pesoGramas);
        construtor.append(" g\n");
        construtor.append("Preço: ");
        construtor.append(super.getPreco());
        construtor.append(" R$\n");
        return construtor.toString();
    }
}
