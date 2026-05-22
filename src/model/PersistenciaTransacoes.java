package model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTransacoes {

    private static final String ARQUIVO =
            "data/transacoes.txt";

    public static void salvar(List<Transacao> transacoes) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(ARQUIVO))) {

            for (Transacao t : transacoes) {

                writer.write(
                        t.getDescricao() + ";" +
                        t.getValor() + ";" +
                        t.getData() + ";" +
                        t.getCategoria()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Transacao> carregar() {

        List<Transacao> lista =
                new ArrayList<>();

        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return lista;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(ARQUIVO))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] dados =
                        linha.split(";");

                String descricao = dados[0];

                double valor =
                        Double.parseDouble(dados[1]);

                LocalDate data =
                        LocalDate.parse(dados[2]);

                Categoria categoria =
                        Categoria.valueOf(dados[3]);

                Transacao transacao =
                        new Receita(
                                descricao,
                                valor,
                                data,
                                categoria
                        );

                lista.add(transacao);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}