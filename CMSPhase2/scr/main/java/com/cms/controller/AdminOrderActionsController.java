package com.cms.controller;

import java.sql.SQLException;

import com.cms.application.AlertHelper;
import com.cms.dao.AdminOperationsDao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class AdminOrderActionsController  extends AdminOperationsDao  {

	@FXML
	private Button confirmOrder, backButton, transitButton;

	@FXML
	private TextField confirmOrderID;

	public void handleBackButtonAction(ActionEvent event) throws SQLException {
		backButton.getScene().getWindow().hide();
	}

	public void findOrderAndConfirm(ActionEvent event) {
		System.out.println("AdminOrderActions.findOrderAndConfirm !" + confirmOrderID.getText());

		if (confirmOrderID.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please enter Order ID!");

			return;
		}
		
		
		String orderID = confirmOrderID.getText();
		Window owner = confirmOrder.getScene().getWindow();
		String status = getOrderStatus(orderID);
		
		if (status.equalsIgnoreCase("Placed")) {
			updateOrderStatus(orderID, "Confirmed");
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Successful!",
					"Your have just updated the status of order " + orderID + " to Confirmed");
		} else if (status.equalsIgnoreCase("In Transit")) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Successful!",
					"Your order is already in Transit");
		} else if (status.equalsIgnoreCase("Confirmed")) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Successful!", "Your order is already Confirmed");
		} else if (status.equalsIgnoreCase("Cancelled")) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Successful!",
					"Status can't be changed as already cancelled");
		} else {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
					"Something went wrong! Please try again later");
		}
	}

	public void findOrderAndInTransit(ActionEvent event) {
		System.out.println("AdminOrderActions.findOrderAndInTransit");
		if (confirmOrderID.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please enter Order ID!");
			return;
		}
		String orderID = confirmOrderID.getText();
		String status = getOrderStatus(orderID);
		Window owner = transitButton.getScene().getWindow();

		if (status.equalsIgnoreCase("Confirmed")) {
			updateOrderStatus(orderID, "In Transit");

			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Successful!",
					"Your have just updated the status of order " + orderID + " to In Transit");
		} else if (status.equalsIgnoreCase("Placed")) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Successful!",
					"First set your status to confirm");
		} else if (status.equalsIgnoreCase("In Transit")) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Successful!",
					"Your order is already in Transit");
		} else if (status.equalsIgnoreCase("Cancelled")) {
			AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Successful!",
					"Status can't be changed as already cancelled");
		} else {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
					"Something went wrong! Please try again later");
		}
	}

}