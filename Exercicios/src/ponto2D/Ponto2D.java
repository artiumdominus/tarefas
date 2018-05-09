/**
 * Created by Basilio on 13/03/18.
 */

package ponto2D;

public class Ponto2D {

    private double ordenada;
    private double abscissa;

    public Ponto2D(){
        abscissa = 0;
        ordenada = 0;
    }

    public Ponto2D(double ordenada, double abscissa) {
        this.ordenada = ordenada;
        this.abscissa = abscissa;
    }


    public Ponto2D(Ponto2D ponto) {
        this.ordenada = ponto.getOrdenada();
        this.abscissa = ponto.getAbscissa();
    }

    public void setOrdenada(double ordenada) {
        this.ordenada = ordenada;

    }

    public void setAbscissa(double abscissa) {
        this.abscissa = abscissa;
    }

    public void setAbscissa() {
        abscissa = 0;
    }

    public void setOrdenada(Ponto2D ponto) {
        this.ordenada = ponto.getOrdenada();
    }

    public void setAbscissa(Ponto2D ponto) {
        this.abscissa = ponto.getAbscissa();
    }

    public double getAbscissa() {
        return abscissa;
    }

    public double getOrdenada() {
        return ordenada;
    }

    public void move(double deltaAbcissa, double deltaOrdenada) {
        this.abscissa = this.abscissa + deltaAbcissa;
        this.ordenada = this.ordenada + deltaOrdenada;
    }

    public void move(Ponto2D ponto) {
        setAbscissa(ponto);
        setOrdenada(ponto);
    }

    public boolean equals(Ponto2D ponto) {
        return this.abscissa == ponto.getAbscissa() && this.ordenada == ponto.getOrdenada();
    }

    public String toString() {
        return new StringBuilder().append("(").append(ordenada).append(";").append(abscissa).append(")").toString();
    }

    public double distancia(Ponto2D ponto) {
        return Math.sqrt(Math.pow(this.ordenada-ponto.getOrdenada(),2)+Math.pow(this.abscissa-ponto.getAbscissa(),2));
    }

    public Ponto2D clone() {
        return new Ponto2D(this);
    }
}
