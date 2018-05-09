package matrizes;

import java.io.IOException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Matrizes {

    private static Map<String, Matriz> matrizes = new HashMap<>();
    private static Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bem vindo ao programa de matrizes!");
        int opcao;
        do {
            opcao = menu();
            switch (opcao) {
                case 1:
                    criarMatriz();
                    break;
                case 2:
                    exibirMatriz();
                    break;
                case 3:
                    classificarMatriz();
                    break;
                case 4:
                    editarMatriz();
                    break;
                case 5:
                    limparMatriz();
                    break;
                case 6:
                    deletarMatriz();
                    break;
                case 7:
                    listarMatrizes();
                    break;
                case 8:
                    clonar();
                    break;
                case 9:
                    transposta();
                    break;
                case 10:
                    oposta();
                    break;
                case 11:
                    igualdade();
                    break;
                case 12:
                    soma();
                    break;
                case 13:
                    subtracao();
                    break;
                case 14:
                    multiplicacao();
                    break;
                case 0:
                    System.out.println("Tchau!");
            }
        } while (opcao != 0);
    }

    private static int menu() {
        System.out.println();
        System.out.println("┌─────────────────────────┐");
        System.out.println("│         M E N U         │");
        System.out.println("├─────────────────────────┤");
        System.out.println("│ 1. Criar Matriz         │");
        System.out.println("│ 2. Exibir Matriz        │");
        System.out.println("│ 3. Classificar Matriz   │");
        System.out.println("│ 4. Editar Matriz        │");
        System.out.println("│ 5. Limpar Matriz        │");
        System.out.println("│ 6. Deletar Matriz       │");
        System.out.println("│ 7. Listar Matrizes      │");
        System.out.println("│ 8. Y <- X               │");
        System.out.println("│ 9. Y <- transposta de X │");
        System.out.println("│10. Y <- oposta de X     │");
        System.out.println("│11. X = Y ?              │");
        System.out.println("│12. X <- X + Y           │");
        System.out.println("│13. X <- X - Y           │");
        System.out.println("│14. X <- X * Y           │");
        System.out.println("│ 0. Sair do Programa     │");
        System.out.println("└─────────────────────────┘");

        int opcao;
        do {
            try {
                System.out.print("Entre com a opção desejada: ");
                opcao = Integer.parseInt(leitor.nextLine());
            } catch (Exception e) {
                System.out.println("Você deve entrar com o número da opção.");
                opcao = -1;
                continue;
            }
            if (opcao < 0 || opcao > 14) {
                System.out.println("Opção inexistente.");
            }
        } while (opcao < 0 || opcao > 14);

        return opcao;
    }

    private static void criarMatriz() {
        String key;
        int nLinhas, nColunas;

        boolean ok = false;
        do {
            System.out.print("Entre com o nome da matriz: ");
            key = leitor.nextLine();
            if (matrizes.containsKey(key)) {
                System.out.print("Já existe uma matriz com este nome, deseja sobrescrever? (S/N): ");
                if (leitor.nextLine().equalsIgnoreCase("S")) {
                    ok = true;
                } else {
                    System.out.print("Cancelar a operação? (S/N): ");
                    if (leitor.nextLine().equalsIgnoreCase(("S"))) {
                        return;
                    }
                }
            } else if (key.isEmpty()) {
                System.out.println("O nome da matriz não pode ser vazio.");
            } else {
                ok = true;
            }
        } while (!ok);

        nLinhas = obterInteiroPositivo("Entre com o número de linhas: ");
        nColunas = obterInteiroPositivo("Entre com o número de colunas: ");

        Matriz matriz = new Matriz(nLinhas, nColunas);
        matrizes.put(key, matriz);

        do {
            ok = lerMatriz(matriz);
        } while (!ok);

        System.out.println("Matriz " + key + " criada:");
        System.out.println(matriz);
    }

    private static boolean lerMatriz(Matriz matriz) {
        System.out.println("Entre com os valores da matriz: ");
        for (int i = 1; i <= matriz.getNLinhas(); ++i) {
            for (int j = 1; j <= matriz.getNColunas(); ++j) {
                try {
                    matriz.setE(i, j, leitor.nextDouble());
                } catch (Exception e) {
                    System.out.println("Falha na leitura dos dados.");
                    leitor.nextLine();
                    return false;
                }
            }
        }
        return true;
    }

    private static void exibirMatriz() {
        Matriz matriz = obterMatriz("Entre com o nome da matriz: ");
        if (matriz != null) {
            System.out.println(matriz);
        }
    }

    private static void classificarMatriz() {
        Matriz matriz = obterMatriz("Entre com o nome da matriz: ");
        if (matriz != null) {
            System.out.println(matriz);
            if (matriz.isQuadrada()) {
                System.out.println("• Quadrada");
            }
            if (matriz.isDiagonal()) {
                System.out.println("• Diagonal");
            }
            if (matriz.isSingular()) {
                System.out.println("• Singular");
            }
            if (matriz.isSimetrica()) {
                System.out.println("• Simétrica");
            }
            if (matriz.isAntissimetrica()) {
                System.out.println("• Antissimétrica");
            }
            if (matriz.isIdentidade()) {
                System.out.println("• Identidade");
            }
        }
    }

    private static void editarMatriz() {
        Matriz matriz = obterMatriz("Entre com o nome da matriz: ");
        if (matriz == null) {
            return;
        }
        boolean ok = false;
        do {
            System.out.println(matriz);
            int linha = obterInteiroPositivo("Entre com a linha do elemento: ");
            int coluna = obterInteiroPositivo("Entre com a coluna do elemento: ");
            double valor = 0;
            do {
                try {
                    System.out.print("Entre com o novo valor do elemento: ");
                    valor = Double.parseDouble(leitor.nextLine());
                    ok = true;
                } catch (Exception e) {
                    System.out.println("Você deve entrar com um valor real.");
                }
            } while (!ok);
            try {
                matriz.setE(linha, coluna, valor);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            System.out.print("Deseja alterar outro elemento? (S/N): ");
        } while (leitor.nextLine().equalsIgnoreCase("S"));
    }

    private static void limparMatriz() {
        Matriz matriz = obterMatriz("Entre com o nome da matriz: ");
        if (matriz == null) {
            return;
        }
        matriz.limpar();
        System.out.println(matriz);
    }

    private static void deletarMatriz() {
        String nome = obterChave("Entre com o nome da matriz: ");
        matrizes.remove(nome);
    }

    //done
    private static void listarMatrizes() {
        for (Map.Entry<String, Matriz> entry : matrizes.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().getNLinhas() + " X "
                    + entry.getValue().getNColunas());
        }
    }

    private static void clonar() {
        Matriz matrizX = obterMatriz("Entre com o nome da matriz \"X\": ");
        if (matrizX == null) {
            return;
        }
        String matrizY = obterChave("Entre com o nome da matriz \"Y\": ");
        matrizes.put(matrizY, matrizX.clonar());
    }

    private static void transposta() {
        Matriz matrizX = obterMatriz("Entre com o nome da matriz \"X\": ");
        if (matrizX == null) {
            return;
        }
        String matrizY = obterChave("Entre com o nome da matriz \"Y\": ");
        matrizes.put(matrizY, matrizX.transposta());
        System.out.println("Resultado:");
        System.out.println(matrizes.get(matrizY));
    }

    private static void oposta() {
        Matriz matrizX = obterMatriz("Entre com o nome da matriz \"X\": ");
        if (matrizX == null) {
            return;
        }
        String matrizY = obterChave("Entre com o nome da matriz \"Y\": ");
        matrizes.put(matrizY, matrizX.oposta());
        System.out.println("Resultado:");
        System.out.println(matrizes.get(matrizY));
    }

    private static void igualdade() {
        Matriz matrizX = obterMatriz("Entre com o nome da matriz \"X\": ");
        if (matrizX == null) {
            return;
        }
        Matriz matrizY = obterMatriz("Entre com o nome da matriz \"Y\": ");
        if (matrizY == null) {
            return;
        }
        if (matrizX.equals(matrizY)) {
            System.out.println("As matrizes são iguais");
        } else {
            System.out.println("As matrizes são diferentes");
        }
    }

    private static void soma() {
        Matriz matrizX = obterMatriz("Entre com o nome da matriz \"X\": ");
        if (matrizX == null) {
            return;
        }
        Matriz matrizY = obterMatriz("Entre com o nome da matriz \"Y\": ");
        if (matrizY == null) {
            return;
        }
        matrizX.somar(matrizY);
        System.out.println("Resultado:");
        System.out.println(matrizX);
    }

    private static void subtracao() {
        Matriz matrizX = obterMatriz("Entre com o nome da matriz \"X\": ");
        if (matrizX == null) {
            return;
        }
        Matriz matrizY = obterMatriz("Entre com o nome da matriz \"Y\": ");
        if (matrizY == null) {
            return;
        }
        matrizX.subtrair(matrizY);
        System.out.println("Resultado:");
        System.out.println(matrizX);
    }

    private static void multiplicacao() {
        Matriz matrizX = obterMatriz("Entre com o nome da matriz \"X\": ");
        if (matrizX == null) {
            return;
        }
        Matriz matrizY = obterMatriz("Entre com o nome da matriz \"Y\": ");
        if (matrizY == null) {
            return;
        }
        matrizX.multiplicar(matrizY);
        System.out.println("Resultado:");
        System.out.println(matrizX);
    }

    private static Matriz obterMatriz(String mensagem) {
        String key;
        while (true) {
            System.out.print(mensagem);
            key = leitor.nextLine();
            if (!matrizes.containsKey(key)) {
                System.out.println("Não há matriz com este nome.");
                System.out.print("Deseja cancelar a operação? (S/N):");
                if (leitor.nextLine().equalsIgnoreCase("S")) {
                    return null;
                }
            } else {
                return matrizes.get(key);
            }
        }
    }

    private static String obterChave(String mensagem) {
        String key;
        while (true) {
            System.out.print(mensagem);
            key = leitor.nextLine();
            if (key.isEmpty()) {
                System.out.println("A matriz não pode ter nome vazio.");
            } else {
                return key;
            }
        }
    }

    private static int obterInteiroPositivo(String mensagem) {
        int inteiro = 0;
        do {
            try {
                System.out.print(mensagem);
                inteiro = Integer.parseInt(leitor.nextLine());
            } catch (Exception e) {
                System.out.println("Você deve entrar com um valor inteiro positivo");
                continue;
            }
            if (inteiro <= 0) {
                System.out.println("Você deve entrar com um valor inteiro positivo.");
            }
        } while (inteiro <= 0);
        return inteiro;
    }

}