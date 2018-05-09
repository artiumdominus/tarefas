package ex4;

import java.util.Scanner;

public class Soma {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int ini, fim, nThreads;

        System.out.print("Entre com o valor de ini: ");
        ini = leitor.nextInt();
        System.out.print("Entre com o valor de fim: ");
        fim = leitor.nextInt();
        System.out.print("Entre com o numero de threads: ");
        nThreads = leitor.nextInt();
        for (int i = 0; i < nThreads; ++i) {
            new Thread().run();
        }

        int soma = (ini + fim) * (fim - --ini) / 2;
        System.out.println("Soma: " + soma);
    }

}
