package br.feevale.ia.estados;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static br.feevale.ia.estados.Posicoes.BAIXO;
import static br.feevale.ia.estados.Posicoes.CIMA;
import static br.feevale.ia.estados.Posicoes.DIREITA;
import static br.feevale.ia.estados.Posicoes.ESQUERDA;

public class QuebraCabeca {

    private final String entrada;
    private final String objetivo;
    private Estado estadoAtual;
    private final Queue<Estado> proximos = new LinkedList<>();
    private final Set<Estado> explorados = new HashSet<>();

    public QuebraCabeca(String entrada, String objetivo) {
        this.entrada = entrada;
        this.objetivo = objetivo;
    }

    public void iniciar() {
        int contador = 0;
        String[] entradas = entrada.split("");
        String[][] quadrado = new String[3][3];
        Vazio vazio = null;
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                quadrado[linha][coluna] = entradas[contador];
                if (entradas[contador].equals("0")) {
                    vazio = new Vazio(linha, coluna);
                }
                contador++;
            }
        }
        estadoAtual = new Estado(quadrado, vazio);
    }

    public String[][] montarPosibilidade(int linha, int coluna) {
        return null;
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

        int linhaAcima = CIMA.calcular(linhaAtual);
        int linhaAbaixo = BAIXO.calcular(linhaAtual);
        int colunaParaDireita = DIREITA.calcular(colunaAtual);
        int colunaParaEsquerda = ESQUERDA.calcular(colunaAtual);

        if (CIMA.valido(linhaAcima)) {
            String[][] possibilidade = montarPosibilidade(linhaAcima, colunaAtual);
            estados.add(new Estado(possibilidade, new Vazio(linhaAcima, colunaAtual)));
        }
        if (BAIXO.valido(linhaAbaixo)) {
            String[][] possibilidade = montarPosibilidade(linhaAbaixo, colunaAtual);
            estados.add(new Estado(possibilidade, new Vazio(linhaAbaixo, colunaAtual)));
        }
        if (ESQUERDA.valido(colunaParaEsquerda)) {
            String[][] possibilidade = montarPosibilidade(linhaAtual, colunaParaEsquerda);
            estados.add(new Estado(possibilidade, new Vazio(linhaAtual, colunaParaEsquerda)));
        }
        if (DIREITA.valido(colunaParaDireita)) {
            String[][] possibilidade = montarPosibilidade(linhaAtual, colunaParaDireita);
            estados.add(new Estado(possibilidade, new Vazio(linhaAtual, colunaParaDireita)));
        }

        this.proximos.addAll(estados);
    }

    private boolean validarMovimentacao(int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3;
    }

    public void buscarQuadrado(Estado estado) {
    }

    public void porLargura() {
        do {
            iniciar();
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
