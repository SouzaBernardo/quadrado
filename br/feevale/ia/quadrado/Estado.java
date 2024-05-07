package br.feevale.ia.quadrado;

public class Estado {
    private final String[][] quadrado;

    private final Vazio vazio;

    public Estado(String[][] quadrado, Vazio vazio) {
        this.quadrado = quadrado;
        this.vazio = vazio;
    }

    public Vazio getVazio() {
        return vazio;
    }

    public String[][] getQuadrado() {
        return quadrado;
    }
}
