package br.feevale.ia.quadrado;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Quadrado {

    private final String entrada;
    private final String objetivo;

    private final String[][] quadrado = new String[3][3];
    private Estado estadoAtual;
    private Queue<Estado> proximos = new LinkedList<>();
    private Set<Estado> explorados = new HashSet<>();

    public Quadrado(String entrada, String objetivo) {
        this.entrada = entrada;
        this.objetivo = objetivo;
    }

    public void montarQuadrado() {
        int contador = 0;
        String[] entradas = entrada.split("");
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                quadrado[linha][coluna] = entradas[contador];
                if (entradas[contador].equals("0")) {
                    estadoAtual = new Estado(quadrado, new Vazio(linha, coluna));
                }
                contador++;
            }
        }
    }

    public String[][] montarPosibilidade(int linha, int coluna) {

        String[][] posibilidade = new String[3][3];
        for (int linha2 = 0; linha2 < 3; linha2++) {
            System.arraycopy(quadrado[linha2], 0, posibilidade[linha2], 0, 3);
        }
        int linhaVazia = estadoAtual.getVazio().getLinha();
        int colunaVazia = estadoAtual.getVazio().getColuna();

        String antigo = posibilidade[linhaVazia][colunaVazia];
        posibilidade[linhaVazia][colunaVazia] = posibilidade[linha][coluna];
        posibilidade[linha][coluna] = antigo;

        return posibilidade;
    }

    public String mostrarQuadrado(Estado estado) {
        StringBuilder montador = new StringBuilder();
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                montador.append(estado.getQuadrado()[linha][coluna]);
            }
        }
        return montador.toString();
    }

    public void proximosEstados(Estado estado) {
        List<Estado> estados = new LinkedList<>();
        int colunaAtual = estado.getVazio().getColuna();
        int linhaAtual = estado.getVazio().getLinha();

        int linhaAcima = linhaAtual - 1;
        int linhaAbaixo = linhaAtual + 1;
        int colunaParaDireita = colunaAtual + 1;
        int colunaParaEsquerda = colunaAtual - 1;

        if (linhaAcima >= 0) {
            String[][] possibilidade = montarPosibilidade(linhaAcima, colunaAtual);
            estados.add(new Estado(possibilidade, new Vazio(linhaAcima, colunaAtual)));
        }
        if (linhaAbaixo < 3) {
            String[][] possibilidade = montarPosibilidade(linhaAbaixo, colunaAtual);
            estados.add(new Estado(possibilidade, new Vazio(linhaAbaixo, colunaAtual)));
        }
        if (colunaParaEsquerda >= 0) {
            String[][] possibilidade = montarPosibilidade(linhaAtual, colunaParaEsquerda);
            estados.add(new Estado(possibilidade, new Vazio(linhaAtual, colunaParaEsquerda)));
        }
        if (colunaParaDireita < 3) {
            String[][] possibilidade = montarPosibilidade(linhaAtual, colunaParaDireita);
            estados.add(new Estado(possibilidade, new Vazio(linhaAtual, colunaParaDireita)));
        }

        this.proximos.addAll(estados);
    }

    private boolean validarMovimentacao(int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3;
    }

    public void buscarQuadrado(Estado estado) {
        int linha = estado.getVazio().getLinha();
        int coluna = estado.getVazio().getColuna();
        if (validarMovimentacao(linha, coluna)) {
            int linhaAntiga = estadoAtual.getVazio().getLinha();
            int colunaAntiga = estadoAtual.getVazio().getColuna();

            String elementoMovimentado = quadrado[linha][coluna];
            quadrado[linha][coluna] = quadrado[linhaAntiga][colunaAntiga];
            quadrado[linhaAntiga][colunaAntiga] = elementoMovimentado;

            estadoAtual.getVazio().mover(linha, coluna);
        }
    }

    public void porLargura() {
        do {
            montarQuadrado();
            proximosEstados(estadoAtual);
            String atual = mostrarQuadrado(estadoAtual);
            System.out.println(atual);

            if (atual.equals(objetivo)) break;

            explorados.add(estadoAtual);
            Estado proximoEstado = proximos.remove();
            estadoAtual = proximoEstado;
            proximosEstados(proximoEstado);
            System.out.println();
        } while (true);
    }
}
