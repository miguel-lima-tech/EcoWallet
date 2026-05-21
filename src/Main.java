import java.time.LocalDate;
import model.*;

public class Main {
    public static void main(String[] args) {

        GerenciadorFinancas gerenciador = new GerenciadorFinancas();

        Transacao salario = TransacaoFactory.criarTransacao(
                "Receita",
                "Salário",
                1500.00,
                LocalDate.now(),
                Categoria.SALARIO
        );

        Transacao mercado = TransacaoFactory.criarTransacao(
                "Despesa",
                "Compra no mercado",
                250.00,
                LocalDate.now(),
                Categoria.ALIMENTACAO
        );

        gerenciador.adicionarTransacao(salario);
        gerenciador.adicionarTransacao(mercado);

        System.out.println("Transações cadastradas:");
        for (Transacao t : gerenciador.getTransacoes()) {
            System.out.println(t.getDescricao() + " - R$ " + t.getValorParaSaldo());
        }

        System.out.println("Saldo total: R$ " + gerenciador.calcularSaldo());
    }
}