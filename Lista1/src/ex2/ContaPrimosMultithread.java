package ex2;

import java.util.Scanner;

public class ContaPrimosMultithread {

    public static void main(String[] args) {
        int begin, end, nThreads;
        Scanner leitor = new Scanner(System.in);

        System.out.print("Entre com o valor inicial: ");
        begin = leitor.nextInt();
        System.out.print("Entre com o valor final: ");
        end = leitor.nextInt();
        System.out.print("Entre com o numero de threads: ");
        nThreads = leitor.nextInt();

        long start = System.currentTimeMillis();

        ContaPrimosThread threads[] = new ContaPrimosThread[nThreads];
        int range_size = (end - begin + 1) / nThreads;
        int range_start, range_end;
        for (int i = 0; i < nThreads; ++i) {
            range_start = range_size * i + begin;
            if (i == nThreads - 1) {
                range_end = end;
            } else {
                range_end = range_start + range_size - 1;
            }
            threads[i] = new ContaPrimosThread(range_start, range_end);
            threads[i].start();
        }

        int nPrimos = 0;

        for (int i = 0; i < nThreads; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Erro, thread interrompida.");
            }
            nPrimos += threads[i].getnPrimos();
        }


        long elapsed = System.currentTimeMillis() - start;

        System.out.println("numero de primos entre " + begin + " e " + end + ": " + nPrimos);
        System.out.println("calculado em " +  elapsed / 1000d + " segundos");
    }


}
