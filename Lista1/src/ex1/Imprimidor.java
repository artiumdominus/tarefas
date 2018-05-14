package ex1;

import org.w3c.dom.DOMImplementationList;

/**
 * Created by aluno on 24/04/18.
 */
public class Imprimidor extends Thread {

    private boolean isPar;

    public Imprimidor(boolean isPar) {
        this.isPar = isPar;
    }

    @Override
    public void run() {
        for (int i = isPar ? 2 : 1; i <= 2000; i += 2) {

            while (DoisMil.getVez() != isPar) {
                try {
                    wait();
                } catch (Exception e) {}
            }

            System.out.println(i);

            try {
                DoisMil.setVez();
                notify();
            } catch (Exception e){}
        }
    }
}
