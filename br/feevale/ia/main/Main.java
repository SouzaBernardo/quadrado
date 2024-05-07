package br.feevale.ia.main;

import br.feevale.ia.estados.QuebraCabeca;

public class Main {
    public static void main(String[] args) {
        QuebraCabeca quebraCabeca = new QuebraCabeca("123405678", "123456708");
        quebraCabeca.porLargura();
    }
}
