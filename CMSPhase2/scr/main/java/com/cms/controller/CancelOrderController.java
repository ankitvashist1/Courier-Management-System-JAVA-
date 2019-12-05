package com.cms.controller;

import java.sql.SQLException;

import com.cms.application.AlertHelper;
import com.cms.dao.CustomerActionsDao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class CancelOrderController extends CustomerActionsDao {

	@FXML
	private Button cancelOrder, backButton;

	@FXML
	private TextField cancelOrderID;

	static String name, pwd;

	public void handleBackButtonAction(ActionEvent event) throws SQLException {
		backButton.getScene().getWindow().hide();
	}

	public void setText(String name, String pwd) {
		// TODO Auto-generated method stub
		this.name = name;
		this.pwd = pwd;
	}

	public void findOrderAndCancel(ActionEvent event) {
		String  orderID = cancelOrderID.getText();
		System.out.println("Cancel Order ! order_id: " + orderID + " Username : " + name);
		
		Window owner = cancelOrder.getScene().getWindow();
		String status = getOrderStatus(orderID, name);

		if(status.equalsIgnoreCase("Cancelled")) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
					"Your order is already cancelled!");
			cancelOrder.getScene().getWindow().hide();
			return;
		}
		if (status.equalsIgnoreCase("Confirmed") || status.equalsIgnoreCase("Placed")) {
			updateOrderStatus(orderID, "Cancelled");
			
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Your order has been cancelled!");
		} else {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
					"Your order has been Shipped, Sorry can not be cancelled!");
		}
		cancelOrder.getScene().getWindow().hide();
		return;
	}

}
