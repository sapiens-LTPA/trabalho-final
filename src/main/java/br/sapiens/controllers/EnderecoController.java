package br.sapiens.controllers;

import br.sapiens.Main;
import br.sapiens.daos.EnderecoDao;
import br.sapiens.models.Endereco;
import br.sapiens.models.LogradouroEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.SQLException;

public class EnderecoController {

    public Label id;
    EnderecoDao dao = new EnderecoDao();
    @FXML
    TextField descricao;

    @FXML
    ChoiceBox logradouro;

    public EnderecoController() throws SQLException {
    }

    @FXML
    public void initialize() throws IOException {
        ObservableList<LogradouroEnum> list = FXCollections.observableArrayList();
        list.addAll(LogradouroEnum.values());
        logradouro.setItems(list);
    }
    public void salvar() throws SQLException {
        String id = this.id.getText();
        Integer idInt = null;
        if(!id.isEmpty())
            idInt = Integer.valueOf(id);
        Endereco retorno = dao.save(new Endereco(idInt, descricao.getText(), (LogradouroEnum) logradouro.getValue()));
        this.id.setText(retorno.getId().toString());
    }
}