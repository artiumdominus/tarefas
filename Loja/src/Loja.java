/**
 * Created by aluno on 29/09/17.
 */

public class Loja {
    public static void main(String args[]) {
        Produto produtos[] = new Produto[5];
        produtos[0] = new Decoracao("Abajour", 30, 500, 20.0f);
        produtos[1] = new Roupa("Moletom Berserk", Tamanho.P, Cor.PRETA, 35.0f);
        produtos[2] = new Livro("Neuromancer", "William Gibson", 1984, Estado.SEMINOVO, 40.0f);
        produtos[3] = new Livro("Brave New World", "Aldous Huxley", 1932, Estado.CONSERVADO, 35.0f);
        produtos[4] = new Livro("1984", "George Orwell", 1948, Estado.RAZOÁVEL, 30.0f);

        int i = 1;
        for(Produto produto : produtos) {
            System.out.println("produto " + i++);
            System.out.println(produto.toString());
        }
    }
}
