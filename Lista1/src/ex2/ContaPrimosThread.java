package ex2;

public class ContaPrimosThread extends Thread {
    private int nPrimos;
    private int begin;
    private int end;

    ContaPrimosThread(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        nPrimos = 0;
        for (int x = begin; x <= end; ++x) {
            if (isPrime(x)) {
                nPrimos++;
            }
        }
    }

    public int getnPrimos() {
        return nPrimos;
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }
}
