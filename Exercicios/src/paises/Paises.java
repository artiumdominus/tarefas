/*
  Created by Basilio on 15/03/18.
 */

package paises;

import java.util.*;
import java.io.*;

public class Paises {

    private static Map<String, Continente> continentes = new HashMap<>();
    private static Scanner leitor = new Scanner(System.in);
    final static private String NOME_ARQUIVO = "paises.txt";

    public static void main(String[] args) {
        System.out.println("Bem vindo ao programa de gerenciamento de países!");
        lerDados();
        int opcao;
        do {
            opcao = menu();
            switch (opcao) {
                case 1:
                    criarContinente();
                    break;
                case 2:
                    destruirContinente();
                    break;
                case 3:
                    detalharContinente();
                    break;
                case 4:
                    listarContinentes();
                    break;
                case 5:
                    incluirPais();
                    break;
                case 6:
                    editarPais();
                    break;
                case 7:
                    detalharPais();
                    break;
                case 8:
                    listarPaises();
                    break;
                case 9:
                    saoVizinhos();
                    break;
                case 10:
                    vizinhosEmComum();
                    break;
                case 0:
                    gravarDados();
                    System.out.println("Até a próxima!");
            }
        } while (opcao != 0);
    }

    private static int menu() {
        System.out.println();
        System.out.println("┌────────────────────────┐");
        System.out.println("│         M E N U        │");
        System.out.println("├────────────────────────┤");
        System.out.println("│ 1. Criar Continente    │");
        System.out.println("│ 2. Destruir Continente │");
        System.out.println("│ 3. Detalhar Continente │");
        System.out.println("│ 4. Listar Continentes  │");
        System.out.println("│ 5. Incluir País        │");
        System.out.println("│ 6. Editar País         │");
        System.out.println("│ 7. Detalhar País       │");
        System.out.println("│ 8. Listar Países       │");
        System.out.println("│ 9. São Vizinhos?       │");
        System.out.println("│10. Vizinhos em Comum   │");
        System.out.println("│ 0. Sair do Programa    │");
        System.out.println("└────────────────────────┘");

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
            if (opcao < 0 || opcao > 10) {
                System.out.println("Opção inexistente.");
            }
        } while (opcao < 0 || opcao > 10);

        return opcao;
    }

    private static void criarContinente() {
        String nome;
        boolean ok = false;
        do {
            System.out.print("Entre com o nome do continente a ser criado: ");
            nome = leitor.nextLine();
            if (continentes.containsKey(nome)) {
                System.out.println("Já existe um continente com este nome.");
                System.out.println("Deseja cancelar a operação? (S/N)");
                if (leitor.nextLine().equalsIgnoreCase("S")) {
                    ok = true;
                }
            } else if (nome.isEmpty()) {
                System.out.println("O nome do continente não pode ser vazio.");
            } else {
                continentes.put(nome, new Continente(nome));
                System.out.println("Continente \"" + nome + "\" criado com sucesso.");
                ok = true;
            }
        } while (!ok);
    }

    private static void destruirContinente() {
        String nome;
        boolean ok = false;
        do {
            System.out.print("Entre com o nome do continente a ser destruido: ");
            nome = leitor.nextLine();
            if(!continentes.containsKey(nome)) {
                System.out.println("Não existe um continente com este nome.");
                System.out.println("Deseja cancelar a operação? (S/N)");
                if (leitor.nextLine().equalsIgnoreCase("S")) {
                    ok = true;
                }
            } else {
                continentes.remove(nome);
                System.out.println("Continente \"" + nome + "\" destruido com sucesso.");
                ok = true;
            }
        } while (!ok);
    }

    private static void detalharContinente() {
        String nome;
        boolean ok = false;
        do {
            System.out.print("Entre com o nome do continente a ser detalhado: ");
            nome = leitor.nextLine();
            if(!continentes.containsKey(nome)) {
                System.out.println("Não existe um continente com este nome.");
                System.out.println("Deseja cancelar a operação? (S/N)");
                if (leitor.nextLine().equalsIgnoreCase("S")) {
                    ok = true;
                }
            } else {
                Continente continente = continentes.get(nome);
                System.out.println(continente);
                List<Pais> paises = continente.getPaises();
                for (Pais pais : paises) {
                    System.out.println(pais.getCodigo() + " : " + pais.getNome());
                }
                ok = true;
            }
        } while (!ok);
    }

    private static void listarContinentes() {
        for (Map.Entry<String, Continente> entry : continentes.entrySet()) {
            System.out.println("• " + entry.getKey());
        }
    }

    private static Pais buscaPais(String codigo) {
        for (Map.Entry<String, Continente> entry : continentes.entrySet()) {
            for (Pais pais : entry.getValue().getPaises()) {
                if (pais.getCodigo().equalsIgnoreCase(codigo)) {
                    return pais;
                }
            }
        }
        return null;
    }

    private static void incluirPais() {
        String continente;
        String codigo;
        String nome;
        String capital;
        long populacao;
        long area;

        System.out.println("- Cadastro de País - ");

        boolean ok = false;
        do {
            System.out.print("Entre o código ISO do país: ");
            codigo = leitor.nextLine();
            if (buscaPais(codigo) != null) {
                System.out.println("Este país já está cadastrado.");
                System.out.println("Deseja cancelar a operação? (S/N)");
                if (leitor.nextLine().equalsIgnoreCase("S")) {
                    return;
                }
            } else if (codigo.isEmpty()) {
                System.out.println("O código do país não pode ser vazio");
            } else {
                ok = true;
            }
        } while (!ok);

        ok = false;
        do {
            System.out.print("Entre o nome do continente cujo o país pertence: ");
            continente = leitor.nextLine();
            if (!continentes.containsKey(continente)) {
                System.out.println("Não existe um continente com este nome.");
            } else {
                ok = true;
            }
        } while (!ok);


        ok = false;
        do {
            System.out.print("Entre o nome do país: ");
            nome = leitor.nextLine();
            if (nome.isEmpty()) {
                System.out.println("O nome do país não pode ser vazio");
            } else {
                ok = true;
            }
        } while (!ok);

        ok = false;
        do {
            System.out.print("Entre o nome da capital: ");
            capital = leitor.nextLine();
            if (capital.isEmpty()) {
                System.out.println("O nome da capital não pode ser vazio");
            } else {
                ok = true;
            }
        } while (!ok);

        ok = false;
        do {
            try {
                System.out.print("Entre com a população do pais: ");
                populacao = Integer.parseInt(leitor.nextLine());
            } catch (Exception e) {
                System.out.println("A população do país deve ser um número inteiro.");
                populacao = -1;
                continue;
            }
            if (populacao < 0) {
                System.out.println("A população não pode ter valor negativo.");
            } else {
                ok = true;
            }
        } while (!ok);

        ok = false;
        do {
            try {
                System.out.print("Entre com a área do pais: ");
                area = Integer.parseInt(leitor.nextLine());
            } catch (Exception e) {
                System.out.println("A área do país deve ser um número inteiro");
                area = -1;
                continue;
            }
            if (area < 0) {
                System.out.println("A área não pode ter valor negativo");
            } else {
                ok = true;
            }
        } while (!ok);

        continentes.get(continente).add(new Pais(codigo.toUpperCase(), nome, capital, populacao, area));
        System.out.println("Pais cadastrado com sucesso.");
    }

    private static void editarPais() {
        String codigo;
        Pais pais;
        boolean ok = false;
        do {
            System.out.print("Entre com o código ISO do país: ");
            codigo = leitor.nextLine();
            pais = buscaPais(codigo);
            if (pais == null) {
                System.out.println("Não existe um país com este código.");
                System.out.print("Deseja cancelar a operação? (S/N):");
                if (leitor.nextLine().equalsIgnoreCase("S")) {
                    return;
                }
            } else {
                ok = true;
            }
        } while (!ok);

        int opcao;
        do {
            System.out.println();
            System.out.println(pais);
            opcao = menuEditarPais();
            switch (opcao) {
                case 1:
                    editarCodigo(pais);
                    break;
                case 2:
                    editarNome(pais);
                    break;
                case 3:
                    editarCapital(pais);
                    break;
                case 4:
                    editarPopulacao(pais);
                    break;
                case 5:
                    editarArea(pais);
                    break;
                case 6:
                    adicionarVizinhos(pais);
                    break;
            }
        } while (opcao != 0);
    }

    private static int menuEditarPais() {
        System.out.println();
        System.out.println("┌────────────────────────┐");
        System.out.println("│ M E N U  D O  P A Í S  │");
        System.out.println("├────────────────────────┤");
        System.out.println("│ 1. Editar Código       │");
        System.out.println("│ 2. Editar Nome         │");
        System.out.println("│ 3. Editar Capital      │");
        System.out.println("│ 4. Editar População    │");
        System.out.println("│ 5. Editar Área         │");
        System.out.println("│ 6. Adicionar Vizinhos  │");
        System.out.println("│ 0. Voltar              │");
        System.out.println("└────────────────────────┘");

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
            if (opcao < 0 || opcao > 6) {
                System.out.println("Opção inexistente.");
            }
        } while (opcao < 0 || opcao > 6);

        return opcao;
    }

    private static void editarCodigo(Pais pais) {
        boolean ok = false;
        do {
            System.out.print("Entre o novo código ISO do país: ");
            String codigo = leitor.nextLine();
            if (buscaPais(codigo) != null && buscaPais(codigo) != pais) {
                System.out.println("Já há outro país com este código.");
                System.out.println("Deseja cancelar a operação? (S/N)");
                if (leitor.nextLine().equalsIgnoreCase("S")) {
                    return;
                }
            } else {
                pais.setCodigo(codigo);
                ok = true;
            }
        } while (!ok);
    }

    private static void editarNome(Pais pais) {
        boolean ok = false;
        do {
            System.out.print("Entre o novo nome do país: ");
            String nome = leitor.nextLine();
            if (nome.isEmpty()) {
                System.out.println("O nome do país não pode ser vazio");
            } else {
                pais.setNome(nome);
                ok = true;
            }
        } while (!ok);
    }

    private static void editarCapital(Pais pais) {
        boolean ok = false;
        do {
            System.out.print("Entre o novo nome da capital: ");
            String capital = leitor.nextLine();
            if (capital.isEmpty()) {
                System.out.println("O nome da capital não pode ser vazio");
            } else {
                pais.setCapital(capital);
                ok = true;
            }
        } while (!ok);
    }

    private static void editarPopulacao(Pais pais) {
        boolean ok = false;
        int populacao;
        do {
            try {
                System.out.print("Entre com a nova população do país: ");
                populacao = Integer.parseInt(leitor.nextLine());
            } catch (Exception e) {
                System.out.println("A população do país deve ser um número inteiro.");
                continue;
            }
            if (populacao < 0) {
                System.out.println("A população não pode ter valor negativo.");
            } else {
                pais.setPopulacao(populacao);
                ok = true;
            }
        } while (!ok);
    }

    private static void editarArea(Pais pais) {
        boolean ok = false;
        int area;
        do {
            try {
                System.out.print("Entre com a nova área do país: ");
                area = Integer.parseInt(leitor.nextLine());
            } catch (Exception e) {
                System.out.println("A área do país deve ser um número inteiro");
                continue;
            }
            if (area < 0) {
                System.out.println("A área não pode ter valor negativo");
            } else {
                pais.setDimensao(area);
                ok = true;
            }
        } while (!ok);
    }

    private static void adicionarVizinhos(Pais pais) {
        String codigo;
        do {
            boolean ok = false;
            do {
                System.out.print("Entre com o código ISO do vizinho: ");
                codigo = leitor.nextLine();
                if (codigo.isEmpty()) {
                    System.out.println("O código não pode ser vazio.");
                } else {
                    pais.addVizinho(codigo);
                    System.out.println(codigo.toUpperCase() + " adicionado a vizinhança de " + pais.getNome() + ".");
                    ok = true;
                }
            } while (!ok);
            System.out.println("Deseja adicionar outro vizinho? (S/N):");
        } while (leitor.nextLine().equalsIgnoreCase("S"));
    }

    private static void detalharPais() {
        String codigo;
        boolean ok = false;
        do {
            System.out.print("Entre com o código ISO do país: ");
            codigo = leitor.nextLine();
            Pais pais = buscaPais(codigo);
            if (pais == null) {
                System.out.println("Não existe um país com este código.");
                System.out.print("Deseja cancelar a operação? (S/N):");
                if (leitor.nextLine().equalsIgnoreCase("S")) {
                    return;
                }
            } else {
                System.out.println(pais);
                ok = true;
            }
        } while (!ok);
    }

    private static void listarPaises() {
        List<Pais> lista;
        for (Map.Entry<String, Continente> entry : continentes.entrySet()) {
            if (!entry.getValue().getPaises().isEmpty()) {
                System.out.println(entry.getKey() + ":");
                lista = entry.getValue().getPaises();
                for (Pais pais : lista) {
                    System.out.println("• " + pais.getNome());
                }
                System.out.println();
            }
        }
    }

    private static void saoVizinhos() {
        Pais pais1, pais2;
        boolean ok;
        String codigo;

        do {
            ok = false;
            do {
                System.out.print("Entre com o código ISO do primeiro país: ");
                codigo = leitor.nextLine();
                if (codigo.equalsIgnoreCase("fim")) {
                    return;
                }
                pais1 = buscaPais(codigo);
                if (pais1 == null) {
                    System.out.println("Pais não encontrado. (Entre \"fim\" para cancelar a operação)");
                } else {
                    ok = true;
                }
            } while (!ok);

            ok = false;
            do {
                System.out.print("Entre com o código ISO do segundo país: ");
                codigo = leitor.nextLine();
                if (codigo.equalsIgnoreCase("fim")) {
                    return;
                }
                pais2 = buscaPais(codigo);
                if (pais2 == null) {
                    System.out.println("Pais não encontrado. (Entre o código \"fim\" para cancelar a operação)");
                } else {
                    ok = true;
                }
            } while (!ok);

            System.out.print(pais1.getNome() + " e " + pais2.getNome() + " ");
            if(pais1.isVizinho(pais2)) {
                System.out.println("são vizinhos!");
            } else {
                System.out.println("não são vizinhos!");
            }

            System.out.print("Deseja outra consulta? (Y/N): ");
        } while (leitor.nextLine().equalsIgnoreCase("Y"));
    }

    private static void vizinhosEmComum() {
        Pais pais1, pais2;
        boolean ok;
        String codigo;

        do {
            ok = false;
            do {
                System.out.print("Entre com o código ISO do primeiro país: ");
                codigo = leitor.nextLine();
                if (codigo.equalsIgnoreCase("fim")) {
                    return;
                }
                pais1 = buscaPais(codigo);
                if (pais1 == null) {
                    System.out.println("Pais não encontrado. (Entre \"fim\" para cancelar a operação)");
                } else {
                    ok = true;
                }
            } while (!ok);

            ok = false;
            do {
                System.out.print("Entre com o código ISO do segundo país: ");
                codigo = leitor.nextLine();
                if (codigo.equalsIgnoreCase("fim")) {
                    return;
                }
                pais2 = buscaPais(codigo);
                if (pais2 == null) {
                    System.out.println("Pais não encontrado. (Entre o código \"fim\" para cancelar a operação)");
                } else {
                    ok = true;
                }
            } while (!ok);

            List<String> vizinhosEmComum = pais1.vizinhosEmComum(pais2);
            if (vizinhosEmComum.isEmpty()) {
                System.out.println(pais1.getNome() + " e " + pais2.getNome() + " não possuem vizinhos em comum.");
            } else {
                System.out.println("Vizinhos em comum entre " + pais1.getNome() + " e " + pais2.getNome() + ":");
                for (String cod : vizinhosEmComum) {
                    System.out.println("• " + cod);
                }
            }

            System.out.print("Deseja outra consulta? (Y/N): ");
        } while (leitor.nextLine().equalsIgnoreCase("Y"));
    }

    private static void lerDados() {
        File arquivo = new File(NOME_ARQUIVO);
        String[] dados;

        if (!arquivo.exists()) {
            System.out.println("Arquivo de dados não encontrado.");
            return;
        }

        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader buffer = new BufferedReader(fr);

            if (buffer.ready()) {
                dados = buffer.readLine().split(";");
                for (String continente : dados) {
                    continentes.put(continente, new Continente(continente));
                }
            }

            while (buffer.ready()) {
                dados = buffer.readLine().split(";");
                Pais pais = new Pais(dados[1],dados[2],dados[3],Long.parseLong(dados[4]),Long.parseLong(dados[5]));
                continentes.get(dados[0]).add(pais);
                for (int i = 6; i < dados.length; i++) {
                    pais.addVizinho(dados[i]);
                }
            }

            buffer.close();
            fr.close();
            System.out.println("Dados carregados.");

        } catch (IOException e) {
            System.out.println("Erro na leituro do arquivo de dados.");
        }
    }

    private static void gravarDados() {
        File arquivo = new File(NOME_ARQUIVO);
        StringBuilder linha =  new StringBuilder();

        try {
            //arquivo.createNewFile();
            FileWriter fw = new FileWriter(arquivo,false);
            BufferedWriter buffer = new BufferedWriter(fw);

            Iterator<String> iterator = continentes.keySet().iterator();
            while (iterator.hasNext()) {
                String continente = iterator.next();
                linha.append(continente);
                if (iterator.hasNext()) {
                    linha.append(";");
                }
            }
            buffer.write(linha.toString());
            buffer.newLine();

            for (Map.Entry<String, Continente> entry : continentes.entrySet()) {
                for (Pais pais : entry.getValue().getPaises()) {
                    linha = new StringBuilder();
                    linha.append(entry.getKey())
                            .append(";")
                            .append(pais.getCodigo())
                            .append(";")
                            .append(pais.getNome())
                            .append(";")
                            .append(pais.getCapital())
                            .append(";")
                            .append(Long.toString(pais.getPopulacao()))
                            .append(";")
                            .append(Long.toString(pais.getDimensao()));
                    for (String codigo : pais.getVizinhos()) {
                        linha.append(";").append(codigo);
                    }
                    buffer.write(linha.toString());
                    buffer.newLine();
                }
            }

            buffer.close();
            fw.close();

            System.out.println("Dados gravados com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro na gravação do arquivo.");
            System.out.println(e.getLocalizedMessage());
        }
    }
}