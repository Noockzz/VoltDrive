package com.example.aula;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class TelaPrincipalController {
    @FXML
    private ImageView imagemcarro;
    @FXML
    private Label textomarca;
    @FXML
    private Label espmodelo;
    @FXML
    private ComboBox<String> MarcaCarro;
    @FXML
    private ComboBox<String> Modelo;
    @FXML
    private Label fabricante;
    @FXML
    private Label carro;
    @FXML
    private Button voltar;

    @FXML
    private void buttonvoltar() {
        try {
            Stage stage = (Stage) voltar.getScene().getWindow();
            SceneSwitcher.switchScene(stage, "login-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String url = "jdbc:mysql://localhost:3308/VoltDrive";
    private String user = "root";
    private String psw = "";

    @FXML
    public void initialize(){
        loadMarca();

        MarcaCarro.setOnAction(event -> {
        String selectedMarca = MarcaCarro.getValue();
        if (selectedMarca != null) {
            loadModelo(selectedMarca);
            updateCarInfo();
        }
    });

        Modelo.setOnAction(event -> updateCarInfo());
}

    private void loadMarca(){
        String query = "SELECT DISTINCT marca FROM carros ORDER BY marca";

        try (Connection connection = DriverManager.getConnection(url, user, psw);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String value = resultSet.getString("marca");
                MarcaCarro.getItems().add(value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadModelo(String marca){
        Modelo.getItems().clear();
        String query = "SELECT DISTINCT modelo FROM carros WHERE marca = ? ORDER BY modelo";


        try (Connection connection = DriverManager.getConnection(url, user, psw);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, marca);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String value = resultSet.getString("modelo");
                Modelo.getItems().add(value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateCarInfo(){
        String selectedMarca = MarcaCarro.getValue();
        String selectedModelo = Modelo.getValue();

        if (selectedMarca != null && selectedModelo != null) {
            String query = String.format("SELECT imagem, descricao, marca FROM carros WHERE marca='%s' AND modelo='%s'",
                    selectedMarca, selectedModelo);

            try (Connection connection = DriverManager.getConnection(url, user, psw);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                if (resultSet.next()) {
                    String imagemPath = resultSet.getString("imagem");
                    String descricao = resultSet.getString("descricao");
                    String marcatexto = resultSet.getString("marca");

                    imagemcarro.setImage(new Image("file:" + imagemPath));
                    espmodelo.setText(descricao);
                    espmodelo.setWrapText(true);
                    textomarca.setText(marcatexto);

                    fabricante.setText(selectedMarca);
                    carro.setText(selectedModelo);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
