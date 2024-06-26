package com.example.aula;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.sql.*;
import javafx.scene.image.Image;


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

    private String url = "jdbc:mysql://localhost:3308/aplicacao";
    private String user = "root";
    private String psw = "";

    @FXML
    public void initialize(){
        loadComboBoxes();

        MarcaCarro.setOnAction(event -> updateCarInfo());
        Modelo.setOnAction(event -> updateCarInfo());
    }
    private void loadComboBoxes(){
        loadMarca();
        loadModelo();
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
    private void loadModelo(){
        String query = "SELECT DISTINCT modelo FROM carros ORDER BY modelo";

        try (Connection connection = DriverManager.getConnection(url, user, psw);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

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
