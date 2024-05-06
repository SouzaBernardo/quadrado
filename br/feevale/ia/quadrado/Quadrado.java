package br.feevale.ia.quadrado;

public class Quadrado {

    private final String entrada;
    private final String saida;

    private final String[][] quadrado = new String[3][3];
    private Vazio vazio;

    public Quadrado(String entrada, String saida) {
        this.entrada = entrada;
        this.saida = saida;
    }

    public void montarQuadrado() {
        int contador = 0;
        String[] entradas = entrada.split("");
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                quadrado[linha][coluna] = entradas[contador];
                if (entradas[contador].equals("0")) {
                    vazio = new Vazio(linha, coluna);
                }
                contador++;
            }
        }
    }

    public String mostrarQuadrado() {
        StringBuilder montador = new StringBuilder("[");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                montador.append(quadrado[i][j]);
                montador.append(", ");
            }
        }
        montador.deleteCharAt(montador.length() - 2);
        montador.append("]");
        return montador.toString();
    }


    public Estado[] proximosEstados() {
        int contador = 0;
        Estado[] estados = new Estado[4];
        int colunaAtual = vazio.getColuna();
        int linhaAtual = vazio.getLinha();

        int linhaAcima = linhaAtual - 1;
        int linhaAbaixo = linhaAtual + 1;
        int colunaParaDireita = colunaAtual + 1;
        int colunaParaEsquerda = colunaAtual - 1;

        if (linhaAcima >= 0) {
            estados[contador++] = new Estado(new Vazio(linhaAcima, colunaAtual));
        }
        if (linhaAbaixo < 3) {
            estados[contador++] = new Estado(new Vazio(linhaAcima, colunaAtual));
        }
        if (colunaParaEsquerda >= 0) {
            estados[contador++] = new Estado(new Vazio(linhaAtual, colunaParaEsquerda));
        }
        if (colunaParaDireita < 3) {
            estados[contador] = new Estado(new Vazio(linhaAtual, colunaParaDireita));
        }

        return estados;
    }

    private boolean validarMovimentacao(int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3;
    }

    public void movimentarQuadrado(int linha, int coluna) {
        if (validarMovimentacao(linha, coluna)) {
            int linhaAntiga = vazio.getLinha();
            int colunaAntiga = vazio.getColuna();

            String elementoMovimentado = quadrado[linha][coluna];
            quadrado[linha][coluna] = quadrado[linhaAntiga][colunaAntiga];
            quadrado[linhaAntiga][colunaAntiga] = elementoMovimentado;

            vazio.mover(linha, coluna);
        }
    }

}
