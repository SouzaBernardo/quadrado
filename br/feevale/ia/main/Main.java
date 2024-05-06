package br.feevale.ia.main;

import br.feevale.ia.quadrado.Quadrado;

public class Main {
    public static void main(String[] args) {
        Quadrado quadrado = new Quadrado("123405678", "123456708");
        quadrado.montarQuadrado();
        System.out.println(quadrado.mostrarQuadrado());

        quadrado.movimentarQuadrado(2, 2);
        System.out.println(quadrado.mostrarQuadrado());
    }
}
