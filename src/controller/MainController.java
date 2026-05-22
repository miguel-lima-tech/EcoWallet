package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Categoria;
import model.Receita;
import model.Transacao;

import java.time.LocalDate;

public class MainController {

    @FXML
    private TextField descricaoField;

    @FXML
    private TextField valorField;

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

        tabelaTransacoes.setItems(listaTransacoes);
    }

    @FXML
    public void adicionarTransacao() {

        try {

            String descricao = descricaoField.getText();

            double valor = Double.parseDouble(
                    valorField.getText());

            Transacao transacao = new Receita(
                    descricao,
                    valor,
                    LocalDate.now(),
                    Categoria.OUTROS
            );

            listaTransacoes.add(transacao);

            saldo += transacao.getValorParaSaldo();

            saldoLabel.setText(
                    "Saldo Total: R$ " + saldo);

            descricaoField.clear();
            valorField.clear();

        } catch (NumberFormatException e) {

            saldoLabel.setText(
                    "Digite um valor válido!");
        }
    }
}