/*
  Created by Basilio on 13/03/18.
 */

package paises;

import java.text.NumberFormat;
import java.util.List;
import java.util.ArrayList;

public class Pais {

    private String codigo;
    private String nome;
    private String capital;
    private long populacao;
    private long dimensao;
    private List<String> vizinhos;

    public Pais(String codigo, String nome, String capital, long populacao, long dimensao) {
        this.codigo = codigo.toUpperCase();
        this.nome = nome;
        this.capital = capital;
        this.populacao = populacao;
        this.dimensao = dimensao;
        this.vizinhos = new ArrayList<>();
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo.toUpperCase();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setPopulacao(long populacao) {
        this.populacao = populacao;
    }

    public void setDimensao(long dimensao) {
        this.dimensao = dimensao;
    }

    public void setVizinhos(List<String> vizinhos) {
        this.vizinhos = vizinhos;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCapital() {
        return capital;
    }

    public long getPopulacao() {
        return populacao;
    }

    public long getDimensao() {
        return dimensao;
    }

    public List<String> getVizinhos() {
        return vizinhos;
    }

    public void addVizinho(String cod) {
        if (!vizinhos.contains(cod.toUpperCase())) {
            vizinhos.add(cod.toUpperCase());
        }
    }

    public boolean equals(Pais pais) {
        return this.codigo.equals(pais.getCodigo());
    }

    public boolean isVizinho(Pais pais) {
        return vizinhos.contains(pais.getCodigo());
    }

    public boolean possuiVizinhosEmComum(Pais pais) {
        for (String x : this.vizinhos) {
            if (pais.getVizinhos().contains(x)) {
                return true;
            }
        }

        return false;
    }

    public double densidadePopulacional() {
        if (dimensao == 0) {
            return 0;
        } else {
            return (double) populacao / (double) dimensao;
        }
    }

    public List<String> vizinhosEmComum(Pais pais) {
        List<String> vizinhosEmComum = new ArrayList<>();
        for (String x : vizinhos) {
            if (pais.getVizinhos().contains(x)) {
                vizinhosEmComum.add(x);
            }
        }
        return vizinhosEmComum;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(codigo)
                .append(" - ")
                .append(nome)
                .append("\n   Capital: ")
                .append(capital)
                .append("\n   Populacao: ")
                .append(NumberFormat.getNumberInstance().format(populacao))
                .append(" hab.\n   Area: ")
                .append(NumberFormat.getNumberInstance().format(dimensao))
                .append(" km²\n")
                .append("   Densidade populacional: ")
                .append(densidadePopulacional())
                .append(" hab./km²");
        if (!vizinhos.isEmpty()) {
            builder.append("\n   Vizinhos:");
            for (String cod : vizinhos) {
                builder.append(" ").append(cod);
            }
        }
        return builder.toString();
    }
}
