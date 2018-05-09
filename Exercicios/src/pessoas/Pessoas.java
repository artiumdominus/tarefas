/**
 * Created by Basilio on 15/03/18.
 */

package pessoas;

import java.util.*;
import java.io.*;

public class Pessoas {

    private static List<Pessoa> pessoas = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello world!");

        lerArquivo();

        pessoas.sort((pessoa, t1) -> pessoa.getNome().compareTo(t1.getNome()));

        System.out.println("Pessoas ordenadas por nome:");
        imprimeLista();

        pessoas.sort((pessoa, t1) -> pessoa.getIdade() - t1.getIdade());

        System.out.println("Pessoas ordenadas por idade:");

        imprimeLista();

        pessoas.sort((pessoa, t1) -> {
            if (pessoa.getAltura() - t1.getAltura() > 0) {
                return 1;
            } else if (pessoa.getAltura() - t1.getAltura() < 0) {
                return -1;
            } else {
                return 0;
            }
        });

        System.out.println("Pessoas ordenadas por altura:");

        imprimeLista();
    }

    private static void lerArquivo() {
        File arquivo = new File("pessoas.txt");
        String[] dados;

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado.");
            return;
        }

        pessoas.clear();

        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            while (br.ready()) {
                dados = br.readLine().split(";");
                pessoas.add(new Pessoa(dados[0], Integer.parseInt(dados[1]), Double.parseDouble(dados[2])));
            }

            br.close();
            fr.close();

            System.out.println("Dados lidos com sucesso.");
        } catch (IOException e) {
            System.out.println("Houve um erro na leitura do arquivo.");
        }
    }

    private static void imprimeLista() {
        for (Pessoa pessoa : pessoas) {
            System.out.println("•");
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Idade: " + pessoa.getIdade());
            System.out.println("Altura: " + pessoa.getAltura());
        }
        System.out.println();
    }
}
