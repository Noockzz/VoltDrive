package com.example.aula;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private Label texto;
    @FXML
    private TextField nome;
    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
    @FXML
    private PasswordField confsenha;
    @FXML
    private CheckBox termos;
    @FXML
    private Button cadastrar;
    @FXML
    private Hyperlink link;

    @FXML
    private void botaoCadastrar() {
        if (termos.isSelected()) {
            String name = nome.getText();
            String mail = email.getText();
            String pass = senha.getText();
            String confpass = confsenha.getText();


            if (conferesenha(pass, confpass) == true) {
                salvarNoBanco(name,mail,pass);

                texto.setText("Cadatrado com sucesso!");

            } else {
                texto.setText("As senhas não conferem!");
            }
        } else {
            texto.setText("Obrigatorio aceitar os termos");
        }
    }

    private boolean conferesenha(String senha, String confsenha) {
        if (senha.equals(confsenha)) {
            return true;

        } else {
            return false;
        }

    }
    @FXML
    private void telaLogin() throws IOException {
        Stage stage = (Stage) email.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "login-view.fxml");
    }

    private void salvarNoBanco(String name, String mail, String pass){
        String url = "jdbc:mysql://localhost:3308/aplicacao";
        String user = "root";
        String pwd = "";

        String query = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, pwd);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, mail);
            preparedStatement.setString(3, pass);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário salvo com sucesso!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}


