package com.example.aula;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    private Label texto;
    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
    @FXML
    private Button entrar;
    @FXML
    private Hyperlink link;



    @FXML
    private void botaoentar() throws IOException {
        String mail = email.getText();
        String pass = senha.getText();

        if (validarLogin(mail,pass)== true){
            telaCadastro();
        }
        else{
            texto.setText("usuario e senha não existe!");

        }

    }
    @FXML
    private void telaCadastro() throws IOException {
        Stage stage = (Stage) email.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "TelaPrincipal-view.fxml");
    }
    private boolean validarLogin(String mail, String password) {
        String url = "jdbc:mysql://localhost:3308/aplicacao";
        String user = "root";
        String pwd = "";

        String query = "SELECT * FROM usuarios WHERE email = ? AND senha= ?";

        try (Connection connection = DriverManager.getConnection(url, user, pwd);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
