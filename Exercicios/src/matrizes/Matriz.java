/**
 * Created by Basilio on 15/03/18.
 */

package matrizes;

import java.text.NumberFormat;

public class Matriz {

    private double e[][];
    private int nLinhas;
    private int nColunas;

    Matriz(int nLinhas, int nColunas) {

        if (nLinhas <= 0) {
            throw new IllegalArgumentException("Número de linhas inválido");
        }

        if (nColunas <= 0) {
            throw new IllegalArgumentException("Número de colunas inválido");
        }

        this.nLinhas = nLinhas;
        this.nColunas = nColunas;
        e = new double[nLinhas][nColunas];
        for (int i = 0; i < nLinhas; ++i) {
            for (int j = 0; j < nColunas; ++j) {
                e[i][j] = 0;
            }
        }
    }

    public double getE(int linha, int coluna) {
        if (linha < 1 || linha > nLinhas) {
            throw new IllegalArgumentException("Linha não existe na matriz.");
        }

        if (coluna < 1 || coluna > nColunas) {
            throw new IllegalArgumentException("Coluna não existe na matriz.");
        }

        int i = linha - 1;
        int j = coluna - 1;
        return e[i][j];
    }

    public void setE(int linha, int coluna, double valor) {
        if (linha < 1 || linha > nLinhas) {
            throw new IllegalArgumentException("Linha não existe na matriz.");
        }

        if (coluna < 1 || coluna > nColunas) {
            throw new IllegalArgumentException("Coluna não existe na matriz.");
        }

        int i = linha - 1;
        int j = coluna - 1;
        e[i][j] = valor;
    }

    public int getNLinhas() {
        return nLinhas;
    }

    public int getNColunas() {
        return nColunas;
    }

    public boolean equals(Matriz m) {
        if (this.nLinhas != m.getNLinhas() || this.nColunas != m.getNColunas()) {
            return false;
        } else {
            for (int i = 1; i <= nLinhas; ++i) {
                for (int j = 1; j <= nColunas; ++j) {
                    if (this.getE(i, j) != m.getE(i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Matriz transposta() {
        Matriz transposta = new Matriz(nColunas, nLinhas);
        for (int i = 1; i <= nLinhas; ++i) {
            for (int j = 1; j <= nColunas; ++j) {
                transposta.setE(j, i, getE(i, j));
            }
        }
        return transposta;
    }

    public Matriz oposta() {
        Matriz oposta = new Matriz(nLinhas, nColunas);
        for (int i = 1; i <= nLinhas; ++i) {
            for (int j = 1; j <= nColunas; ++j) {
                oposta.setE(i, j, -getE(i, j));
            }
        }
        return oposta;
    }

    public Matriz clonar() {
        Matriz clone = new Matriz(nLinhas, nColunas);
        for (int i = 1; i <= nLinhas; ++i) {
            for (int j = 1; j <= nColunas; ++j) {
                clone.setE(i, j, getE(i, j));
            }
        }
        return clone;
    }

    /**
     * Transforma a matriz em nula.
     */
    public void limpar() {
        for (int i = 0; i < nLinhas; ++i) {
            for (int j = 0; j < nColunas; ++j) {
                e[i][j] = 0;
            }
        }
    }

    public boolean isQuadrada() {
        return nLinhas == nColunas;
    }

    public boolean isIdentidade() {
        if (!isQuadrada()) {
            return false;
        }
        for (int i = 0; i < nLinhas; ++i) {
            for (int j = 0; j < nColunas; ++j) {
                if (i == j) {
                    if (e[i][j] != 1) {
                        return false;
                    }
                } else if (e[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isDiagonal() {
        if (!isQuadrada()) {
            return false;
        }
        for (int i = 0; i < nLinhas; ++i) {
            for (int j = 0; j < nColunas; ++j) {
                if (i != j && e[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSingular() {
        if (!isDiagonal()) {
            return false;
        }
        double x = e[0][0];
        for (int i = 1; i < nLinhas; ++i) {
            if (e[i][i] != x) {
                return false;
            }
        }
        return true;
    }

    public boolean isSimetrica() {
        return equals(transposta());
    }

    public boolean isAntissimetrica() {
        return oposta().equals(transposta());
    }

    public void somar(Matriz m) {
        if (nLinhas != m.getNLinhas() || nColunas != m.getNColunas()) {
            throw new IllegalArgumentException("Matrizes incompatíveis para soma.");
        }

        for (int i = 0; i < nLinhas; ++i) {
            for (int j = 0; j < nColunas; ++j) {
                e[i][j] += m.getE(i+1,j+1);
            }
        }
    }

    public void subtrair(Matriz m) {
        if (nLinhas != m.getNLinhas() || nColunas != m.getNColunas()) {
            throw new IllegalArgumentException("Matrizes incompatíveis para soma.");
        }

        for (int i = 0; i < nLinhas; ++i) {
            for (int j = 0; j < nColunas; ++j) {
                e[i][j] -= m.getE(i+1,j+1);
            }
        }
    }

    public void multiplicar(Matriz m) {
        if (nColunas != m.getNLinhas()) {
            throw new IllegalArgumentException("Matrizes incompatíveis para multiplicação.");
        }

        double aux[][] = new double[nLinhas][m.getNColunas()];
        int nCamadas = nColunas;
        nColunas = m.getNColunas();
        for (int i = 0; i < nLinhas; ++i) {
            for (int j = 0; j < m.getNColunas(); ++j) {
                aux[i][j] = 0;
                for (int k = 0; k < nCamadas; ++k) {
                    aux[i][j] += e[i][k] * m.getE(k+1,j+1);
                }
            }
        }
        e = aux;
    }


    // Incluir formato de colchetes.
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nLinhas; ++i) {
            for (int j = 0; j < nColunas; j++) {
                builder.append(NumberFormat.getNumberInstance().format(e[i][j])).append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
