package com.cms.controller;

import java.io.IOException;

import com.cms.application.AlertHelper;
import com.cms.dao.AdminLoginDAO;
import com.cms.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AdminLoginController extends AdminLoginDAO {

	@FXML
	private TextField adminIdField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button submitButton, backButton;

	@FXML
	public void handleBackButtonAction(ActionEvent event) throws IOException {
		backButton.getScene().getWindow().hide();
	}

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		Window owner = submitButton.getScene().getWindow();

		if (adminIdField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter Admin ID!");
			return;
		}
		if (passwordField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter password!");

			return;
		}

		String adminID = adminIdField.getText();
		String password = passwordField.getText();

		User s;
		s = adminFinder(adminID, password);
		System.out.println(s);
		if (s == null) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
					"Admin ID and Password not matching ! Please try again later.");
		} else {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("Admin Login Successful!");
			a.showAndWait();
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/fxml/AdminOperations.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(root, 620, 500));
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}