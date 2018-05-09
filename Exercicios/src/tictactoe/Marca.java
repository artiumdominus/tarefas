package tictactoe;

import java.awt.*;

public enum Marca {
    VAZIO(" ", Color.LIGHT_GRAY),
    X("X", Color.GREEN),
    O("O", Color.RED);

    private String representacao;
    private Color color;

    Marca(String representacao, Color color) {
        this.representacao = representacao;
        this.color = color;
    }

    public String getRepresentacao() {
        return representacao;
    }

    public Color getColor() {
        return color;
    }
}
