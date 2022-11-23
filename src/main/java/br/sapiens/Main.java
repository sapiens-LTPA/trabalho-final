package br.sapiens;


import br.sapiens.models.Endereco;
import br.sapiens.models.LogradouroEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("/layout/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Index");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //minha conecao
        launch();
    }

}