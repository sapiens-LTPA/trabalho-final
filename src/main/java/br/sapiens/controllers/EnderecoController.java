package br.sapiens.controllers;

import br.sapiens.Main;
import br.sapiens.daos.EnderecoDao;
import br.sapiens.models.Endereco;
import br.sapiens.models.LogradouroEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class EnderecoController {

    public Label id;
    EnderecoDao dao = new EnderecoDao();
    @FXML
    TextField descricao;
    @FXML
    ChoiceBox logradouro;

    @FXML
    TableView table;

    @FXML
    Pane painelVinculo;

    @FXML
    DatePicker dataJf;

    public EnderecoController() throws SQLException {
    }

    @FXML
    public void initialize() throws IOException, SQLException {
        if(logradouro != null){ // estou na tela de cadastro
            ObservableList<LogradouroEnum> list = FXCollections.observableArrayList();
            list.addAll(LogradouroEnum.values());
            logradouro.setItems(list);
        }
        if(table != null){
            TableColumn<Endereco, String> idC = new TableColumn("Id");
            idC.setCellValueFactory(new PropertyValueFactory("id"));
            TableColumn<Endereco, String> logradouroC = new TableColumn("Logradouro");
            logradouroC.setCellValueFactory(new PropertyValueFactory("logradouro"));
            TableColumn<Endereco, String> descricaoC = new TableColumn("Descricao");
            descricaoC.setCellValueFactory(new PropertyValueFactory("descricao"));
            TableColumn action = new TableColumn("Ação");
            action.setCellFactory(criaAcao());
            table.getColumns().addAll(List.of(idC,logradouroC,descricaoC,action));
            table.getItems().addAll(dao.findAll());
        }

    }

    private Callback<TableColumn<Endereco, String>, TableCell<Endereco, String>> criaAcao() {
       return
                new Callback<TableColumn<Endereco, String>, TableCell<Endereco, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Endereco, String> param) {
                        final TableCell<Endereco, String> cell = new TableCell<Endereco, String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                setGraphic(null);
                                setText(null);
                                if (!empty) {
                                    Button btn = new Button("Vincular");
                                    btn.setOnAction(event -> {
                                        FXMLLoader fxmlLoader =
                                                new FXMLLoader(Main.class.getResource("/endereco/vinculo.fxml"));
                                        try {
                                            painelVinculo.getChildren().add(fxmlLoader.load());
                                            VinculaEnderecoController controller = fxmlLoader.getController();
                                            Endereco endereco = this.getTableRow().getItem();
                                            controller.recebeEndereco(endereco);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                    setGraphic(btn);
                                }
                            }
                        };
                        return cell;
                    }
                };
    }

    public void salvar() throws SQLException {
        String id = this.id.getText();
        Integer idInt = null;
        if(!id.isEmpty())
            idInt = Integer.valueOf(id);
        LocalDate localDate = dataJf.getValue();
        Endereco retorno = dao.save(new Endereco(idInt, descricao.getText(), (LogradouroEnum) logradouro.getValue(), new Date()));
        this.id.setText(retorno.getId().toString());
    }
}