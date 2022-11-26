package br.sapiens.controllers;

import br.sapiens.models.Endereco;
import br.sapiens.models.LogradouroEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VinculaEnderecoController {

    private Endereco endereco;

    @FXML
    Label idLabel;

    @FXML
    Label descricaoLabel;

    @FXML
    ChoiceBox vinculo;

    public void recebeEndereco(Endereco endereco){
        this.endereco = endereco;
        idLabel.setText(endereco.getId().toString());
        descricaoLabel.setText(endereco.getDescricao());
        carregaMatriculas();
    }

    @FXML
    public void initialize() throws IOException, SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(List.of("opcao1","opcao2"));
        vinculo.setItems(list);
    }

    public void salvar(){
        //Salvar matricula no banco de dados
        System.out.println("salvando");
        vinculo.valueProperty().set(null);
        carregaMatriculas();
    }


    public void carregaMatriculas(){

    }

}