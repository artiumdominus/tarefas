package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// An AWT GUI program inherits the top-level container java.awt.Frame
public class TicTacToe extends Frame implements WindowListener {
    private static Button[] btn;
    private static Label lblTurn, lblXPoints, lblOpoints;

    private static Modo modo;
    private static Marca[][] grade;
    private static Marca turno;
    private static Marca comeca;
    private static AI Joi;
    private static int XPoints, Opoints;

    // Constructor to setup GUI components and event handlers
    private TicTacToe() {

        Panel panelTurn = new Panel(new FlowLayout());
        lblTurn = new Label();
        panelTurn.add(lblTurn);

        String[] opcoes = {"Humano x Humano","Humano x Máquina"};
        int opcao = JOptionPane.showOptionDialog(null,"Escolha o modo de jogo:","TicTacToe",
                JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,opcoes,opcoes[0]);
        if (opcao == 0) {
            modo = Modo.HUMANO_VS_HUMANO;
            lblTurn.setText("Vez de X");
        } else if (opcao == 1) {
            modo =  Modo.HUMANO_VS_MAQUINA;
            lblTurn.setText("Try me, you fleshbag.");
            Joi = new AI();
        } else {
            System.exit(0);
        }
        comeca = turno = Marca.X;
        XPoints = Opoints = 0;

        grade = new Marca[3][3];

        Panel panelButtons = new Panel(new GridLayout(3, 2, 3, 3));

        btn = new Button[9];
        // The components are added from left-to-right, top-to-bottom
        for (int i = 0; i < 9; ++i) {
            btn[i] = new Button();
            btn[i].addActionListener(new TicTacToeListener(i));
            panelButtons.add(btn[i]);
        }
        limpaGrade();

        lblXPoints = new Label("X : 0         ");
        lblOpoints = new Label ("O : 0");
        Panel panelPoints = new Panel(new FlowLayout());
        panelPoints.add(lblXPoints);
        panelPoints.add(lblOpoints);

        setLayout(new BorderLayout());
        add(panelTurn, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);
        add(panelPoints, BorderLayout.SOUTH);

        addWindowListener(this);

        setTitle("Jogo da Velha");
        setLocation(650,350);
        setSize(280, 360);
        setVisible(true);
    }

    // The entry main() method
    public static void main(String[] args) {
        new TicTacToe();  // Let the constructor do the job
    }

    private static void limpaGrade() {
        for (int linha = 0; linha < 3; ++linha) {
            for (int coluna = 0; coluna < 3; ++coluna) {
                grade[linha][coluna] = Marca.VAZIO;
                btn[linha * 3 + coluna].setBackground(Marca.VAZIO.getColor());
                btn[linha * 3 + coluna].setLabel(Marca.VAZIO.getRepresentacao());
                btn[linha * 3 + coluna].setEnabled(true);
            }
        }
    }

    static void jogar(int posicao) {
        if (modo == Modo.HUMANO_VS_HUMANO) {
            marcar(turno, posicao);
            if (verificaGanhador(turno)) {
                gameOver("Jogador " + turno.getRepresentacao() + " venceu!", true);
            } else if (verificaVelha()) {
                gameOver("Deu velha!", false);
            } else {
                if (turno == Marca.X) {
                    turno = Marca.O;
                    lblTurn.setText("Vez de O");
                } else {
                    turno = Marca.X;
                    lblTurn.setText("Vez de X");
                }
            }
        } else if (modo == Modo.HUMANO_VS_MAQUINA) {
            marcar(Marca.X, posicao);

            if (verificaGanhador(Marca.X)) {
                gameOver("Você venceu!", true);
            } else if (verificaVelha()) {
                gameOver("Deu velha!", false);
            } else {

                marcar(Marca.O, Joi.move(grade));

                if (verificaGanhador(Marca.O)) {
                    gameOver("A máquina venceu!", true);
                } else if (verificaVelha()) {
                    gameOver("Deu velha!", false);
                }

            }
        }
    }

    private static void marcar(Marca marca, int posicao) {
        grade[posicao / 3][posicao % 3] = marca;
        btn[posicao].setLabel(marca.getRepresentacao());
        btn[posicao].setBackground(marca.getColor());
        btn[posicao].setEnabled(false);
    }

    private static boolean verificaGanhador(Marca jogador) {
        // checagem tosca for força bruta.
        return grade[0][0] == jogador && grade[0][1] == jogador && grade[0][2] == jogador ||
                grade[0][0] == jogador && grade[1][1] == jogador && grade[2][2] == jogador ||
                grade[0][0] == jogador && grade[1][0] == jogador && grade[2][0] == jogador ||
                grade[0][1] == jogador && grade[1][1] == jogador && grade[2][1] == jogador ||
                grade[1][0] == jogador && grade[1][1] == jogador && grade[1][2] == jogador ||
                grade[0][2] == jogador && grade[1][2] == jogador && grade[2][2] == jogador ||
                grade[2][0] == jogador && grade[2][1] == jogador && grade[2][2] == jogador ||
                grade[0][2] == jogador && grade[1][1] == jogador && grade[2][0] == jogador;
    }

    private static boolean verificaVelha() {
        for (int linha = 0; linha < 3; ++linha) {
            for (int coluna = 0; coluna < 3; ++coluna) {
                if (grade[linha][coluna] == Marca.VAZIO) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void gameOver(String mensagem, boolean vitoria) {
        String[] opcoes = {"Sim","Não"};
        int opcao = JOptionPane.showOptionDialog(null,mensagem + "\nJogar novamente?","TicTacToe",
                JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,opcoes,opcoes[0]);
        if (opcao == 0) {

            if (vitoria) {
                if (turno == Marca.X) {
                    XPoints++;
                    lblXPoints.setText("X : " + XPoints);
                } else {
                    Opoints++;
                    lblOpoints.setText("O : " + Opoints);
                }
            }

            if (comeca == Marca.X) {
                comeca = turno = Marca.O;
                if (modo == Modo.HUMANO_VS_HUMANO) {
                    lblTurn.setText("Vez de O");
                }
            } else {
                comeca = turno = Marca.X;
                if (modo == Modo.HUMANO_VS_HUMANO) {
                    lblTurn.setText("Vez de X");
                }
            }
            limpaGrade();

            if (modo == Modo.HUMANO_VS_MAQUINA && comeca == Marca.O) {
                marcar(Marca.O, Joi.move(grade));
            }

        } else {
            System.exit(0);
        }
    }


    /* WindowEvent handlers */
    // Called back upon clicking close-window button
    @Override
    public void windowClosing(WindowEvent evt) {
        System.exit(0);  // Terminate the program
    }

    // Not Used, but need to provide an empty body to compile.
    @Override public void windowOpened(WindowEvent evt) { }
    @Override public void windowClosed(WindowEvent evt) { }
    @Override public void windowIconified(WindowEvent evt) { }
    @Override public void windowDeiconified(WindowEvent evt) { }
    @Override public void windowActivated(WindowEvent evt) { }
    @Override public void windowDeactivated(WindowEvent evt) { }
}