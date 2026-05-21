package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField descricaoField;

    @FXML
    private TextField valorField;

    @FXML
    private TableView<?> tabelaTransacoes;

    @FXML
    private Label saldoLabel;

}