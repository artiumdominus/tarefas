/*
  Created by Basilio on 13/03/18.
 */

package paises;

import java.util.List;
import java.util.ArrayList;

public class Continente {

    private String nome;
    private List<Pais> paises;

    Continente(String nome) {
        this.nome = nome;
        this.paises = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void add(Pais pais) {
        if (!paises.contains(pais)) {
            paises.add(pais);
        }
    }

    public double area() {
        double area = 0;
        for (Pais pais : paises) {
            area += pais.getDimensao();
        }
        return area;
    }

    public long populacao() {
        long populacao = 0;
        for (Pais pais : paises) {
            populacao += pais.getPopulacao();
        }
        return populacao;
    }

    public double densidadePopulacional() {
        if (area() == 0) {
            return 0;
        } else {
            return populacao() / area();
        }
    }

    public Pais paisMaisPopuloso() {
        Pais maisPopuloso;
        maisPopuloso = paises.get(0);
        for (Pais pais : paises) {
            if (pais.getPopulacao() > maisPopuloso.getPopulacao()) {
                maisPopuloso = pais;
            }
        }
        return maisPopuloso;
    }

    public Pais paisMenosPopuloso() {
        Pais menosPopuloso;
        menosPopuloso = paises.get(0);
        for (Pais pais : paises) {
            if (pais.getPopulacao() < menosPopuloso.getPopulacao()) {
                menosPopuloso = pais;
            }
        }
        return menosPopuloso;
    }

    public Pais maiorPais() {
        Pais maior;
        maior = paises.get(0);
        for (Pais pais : paises) {
            if (pais.getDimensao() > maior.getDimensao()) {
                maior = pais;
            }
        }
        return maior;
    }

    public Pais menorPais() {
        Pais menor;
        menor = paises.get(0);
        for (Pais pais : paises) {
            if (pais.getDimensao() < menor.getDimensao()) {
                menor = pais;
            }
        }
        return menor;
    }

    public double razaoTerritorialEntreMaiorEMenor() {
        return maiorPais().getDimensao() / menorPais().getDimensao();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ")
                .append(nome)
                .append(" ]\nPopulação: ")
                .append(populacao())
                .append("\nArea: ")
                .append(area())
                .append(" km²\nDensidade Populacional: ")
                .append(densidadePopulacional())
                .append(" hab/km²\n");
                if (paises.isEmpty()) {
                    builder.append("Ainda não há países neste continente.");
                } else {
                    builder.append("País mais populoso: ")
                            .append(paisMaisPopuloso().getNome())
                            .append("\nPaís menos populoso: ")
                            .append(paisMenosPopuloso().getNome())
                            .append("\nPaís com maior território: ")
                            .append(maiorPais().getNome())
                            .append("\nPaís com menor território: ")
                            .append(menorPais().getNome())
                            .append("\n");
                }
        return builder.toString();
    }
}
