package ex1;

/**
 * Created by aluno on 24/04/18.
 */
public class DoisMil {

    private static boolean vezDoPar = false;

    public static synchronized boolean getVez() {
        return vezDoPar;
    }

    public static synchronized void setVez() {
        vezDoPar = vezDoPar ? false : true;
    }

    public static void main(String[] args) {
        Imprimidor pares = new Imprimidor(true);
        Imprimidor impares = new Imprimidor(false);

        impares.start();
        pares.start();

        try {
            impares.join();
            pares.join();
        } catch (InterruptedException e) {
            System.out.println("Ocorreu uma interrupção.");
        }
    }
}
