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
    private CheckBox loginadm;

    @FXML
    private void botaoentar() throws IOException {
        String mail = email.getText();
        String pass = senha.getText();
        boolean isAdminCheckboxChecked = loginadm.isSelected();

        if (isAdminCheckboxChecked) {
            if (validarLogin(mail, pass, true)) {
                telaCadastro();
            } else {
                texto.setText("Apenas os administradores podem acessar!");
            }
        } else {
            if (validarLogin(mail, pass, false)) {
                telaPrincipal();
            } else {
                texto.setText("O Email e/ou Senha estão incorretos!");
            }
        }

    }
    @FXML
    private void telaCadastro() throws IOException {
        Stage stage = (Stage) email.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "TelaAdm-view.fxml");
    }

    @FXML
    private void telaPrincipal() throws IOException {
        Stage stage = (Stage) email.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "telaPrincipal.fxml");
    }

    @FXML
    private void buttonCadastrar() throws IOException {
        Stage stage = (Stage) link.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "hello-view.fxml");
    }

    private boolean validarLogin(String mail, String password, boolean isAdmin) {
        String url = "jdbc:mysql://localhost:3308/VoltDrive";
        String user = "root";
        String pwd = "";

        String query = "SELECT * FROM usuarios WHERE email = ? AND senha= ? AND administrador = ?";

        try (Connection connection = DriverManager.getConnection(url, user, pwd);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, password);
            preparedStatement.setBoolean(3, isAdmin);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
