package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

import model.*;

import java.time.LocalDate;
import java.util.List;

public class MainController {

    @FXML
    private TextField descricaoField;

    @FXML
    private TextField valorField;

    @FXML
    private ComboBox<String> tipoComboBox;

    @FXML
    private ComboBox<Categoria> categoriaComboBox;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TableView<Transacao> tabelaTransacoes;

    @FXML
    private TableColumn<Transacao, String> colDescricao;

    @FXML
    private TableColumn<Transacao, Double> colValor;

    @FXML
    private TableColumn<Transacao, LocalDate> colData;

    @FXML
    private TableColumn<Transacao, Categoria> colCategoria;

    @FXML
    private TableColumn<Transacao, String> colTipo;

    @FXML
    private Label saldoLabel;

    private ObservableList<Transacao> listaTransacoes =
            FXCollections.observableArrayList();

    private double saldo = 0;

    @FXML
    public void initialize() {

        colDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));

        colValor.setCellValueFactory(
                new PropertyValueFactory<>("valor"));

        colData.setCellValueFactory(
                new PropertyValueFactory<>("data"));

        colCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria"));

        colTipo.setCellValueFactory(cellData -> {
            Transacao t = cellData.getValue();

            if (t instanceof Receita) {
                return new SimpleStringProperty("Receita");
            } else {
                return new SimpleStringProperty("Despesa");
            }
        });

        tabelaTransacoes.setItems(listaTransacoes);

        tipoComboBox.getItems().addAll("Receita", "Despesa");
        categoriaComboBox.getItems().addAll(Categoria.values());

        tipoComboBox.setValue("Receita");
        categoriaComboBox.setValue(Categoria.OUTROS);
        dataPicker.setValue(LocalDate.now());

        carregarTransacoes();
    }

    @FXML
    public void adicionarTransacao() {

        try {

            String descricao = descricaoField.getText();
            double valor = Double.parseDouble(valorField.getText());
            String tipo = tipoComboBox.getValue();
            Categoria categoria = categoriaComboBox.getValue();
            LocalDate data = dataPicker.getValue();

            if (descricao.isBlank() || tipo == null || categoria == null || data == null) {
                saldoLabel.setText("Preencha todos os campos!");
                return;
            }

            Transacao transacao = TransacaoFactory.criarTransacao(
                    tipo,
                    descricao,
                    valor,
                    data,
                    categoria
            );

            listaTransacoes.add(transacao);

            saldo += transacao.getValorParaSaldo();

            atualizarSaldo();

            PersistenciaTransacoes.salvar(listaTransacoes);

            descricaoField.clear();
            valorField.clear();
            tipoComboBox.setValue("Receita");
            categoriaComboBox.setValue(Categoria.OUTROS);
            dataPicker.setValue(LocalDate.now());

        } catch (NumberFormatException e) {
            saldoLabel.setText("Digite um valor válido!");
        } catch (Exception e) {
            saldoLabel.setText("Erro ao adicionar transação!");
        }
    }

    private void carregarTransacoes() {

        List<Transacao> transacoes = PersistenciaTransacoes.carregar();

        listaTransacoes.addAll(transacoes);

        for (Transacao t : transacoes) {
            saldo += t.getValorParaSaldo();
        }

        atualizarSaldo();
    }

    private void atualizarSaldo() {
        saldoLabel.setText("Saldo Total: R$ " + saldo);
    }
}