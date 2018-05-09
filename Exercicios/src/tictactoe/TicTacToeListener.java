package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeListener implements ActionListener {

    private int i;

    TicTacToeListener(int i) {
        this.i = i;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        //TicTacToe.btn[i].setLabel("X");
        //TicTacToe.btn[i].setBackground(Color.GREEN);

        TicTacToe.jogar(i);
    }
}
