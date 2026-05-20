package model;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorFinancas {

    private List<Transacao> transacoes;

    public GerenciadorFinancas() {
        transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void removerTransacao(Transacao transacao) {
        transacoes.remove(transacao);
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public double calcularSaldo() {
        double saldo = 0;

        for (Transacao t : transacoes) {
            saldo += t.getValorParaSaldo();
        }

        return saldo;
    }
}
