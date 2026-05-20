package model;

import java.time.LocalDate;

public class Receita extends Transacao {

    public Receita(String descricao, double valor, LocalDate data) {
        super(descricao, valor, data);
    }

    @Override
    public double getValorParaSaldo() {
        return getValor();
    }
}