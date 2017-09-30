/**
 * Created by aluno on 29/09/17.
 */

import java.util.Arrays;

public class Loja {
    public static void main(String args[]) {
        Produto produtos[] = new Produto[5];
        produtos[0] = new Decoracao("Abajour", 30, 500, 20.0f, 1);
        produtos[1] = new Roupa("Moletom Berserk", Tamanho.P, Cor.PRETA, 35.0f, 2);
        produtos[2] = new Livro("Neuromancer", "William Gibson", 1984, Estado.SEMINOVO, 40.0f, 3);
        produtos[3] = new Livro("Brave New World", "Aldous Huxley", 1932, Estado.CONSERVADO, 35.0f, 4);
        produtos[4] = new Livro("1984", "George Orwell", 1948, Estado.RAZOÁVEL, 30.0f, 5);

        int i = 1;
        for(Produto produto : produtos) {
            System.out.println("produto " + i++);
            System.out.println(produto.toString());
        }

        Produto clone = new Livro("Neuromancer", "William Gibson", 1984, Estado.SEMINOVO, 40.0f, 3);
        Produto fake = new Livro("Neuromancer", "William Gibson", 1984, Estado.SEMINOVO, 40.0f, 6);

        findProduto(clone, produtos);
        findProduto(fake, produtos);
        System.out.println("\n");


        Arrays.sort(produtos);
        i = 1;
        for(Produto produto : produtos) {
            System.out.println("produto " + i++);
            System.out.println(produto.toString());
        }
    }

    public static void findProduto(Produto produto, Produto produtos[]) {
        for(int i = 0; i < produtos.length; ++i) {
            if(produto.equals(produtos[i])) {
                System.out.println("Produto encontrado na posição " + i + " do vetor.");
                return;
            }
        }
        System.out.println("O Produto não foi encontrado no vetor");
    }
}
