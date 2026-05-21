package model;

import java.time.LocalDate;

public class TransacaoFactory {

    public static Transacao criarTransacao(
            String tipo,
            String descricao,
            double valor,
            LocalDate data,
            Categoria categoria
    ) {
        if (tipo.equalsIgnoreCase("Receita")) {
            return new Receita(descricao, valor, data, categoria);
        } else if (tipo.equalsIgnoreCase("Despesa")) {
            return new Despesa(descricao, valor, data, categoria);
        } else {
            throw new IllegalArgumentException("Tipo de transação inválido.");
        }
    }
}