package model;

import java.time.LocalDate;

public class Despesa extends Transacao {

    public Despesa(String descricao, double valor, LocalDate data) {
        super(descricao, valor, data);
    }

    @Override
    public double getValorParaSaldo() {
        return -getValor();
    }
}