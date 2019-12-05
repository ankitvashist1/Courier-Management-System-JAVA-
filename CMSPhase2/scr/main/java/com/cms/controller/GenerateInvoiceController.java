package com.cms.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cms.application.DBConnect;
import com.cms.dao.InvoiceDao;
import com.cms.models.Address;
import com.cms.models.Order;
import com.cms.models.PackageDetail;
import com.cms.models.RateChart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GenerateInvoiceController extends InvoiceDao {

	@FXML
	private TextField invoiceField;

	@FXML
	private Button genInv, back;

	static String name, pwd;

	public void handleBackButtonAction(ActionEvent event) throws Exception {
		back.getScene().getWindow().hide();
	}

	public void setText(String name, String password) {
		this.name = name;
		this.pwd = password;
		System.out.println(name);
	}

	@FXML
	public void genInv() {
		String invorderID = invoiceField.getText();
		System.out.println("GenerateInvoiceController.getInv :" + invorderID + " name:" + name);
		Order order = null;
		Integer invoiceFlag = getinvoiceGeneratedFlag(invorderID, name);
		System.out.println("Invoice generated flag : " + invoiceFlag);
		if (invoiceFlag == 1) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("Invoice Already Generated!");
			a.showAndWait();
			return;
		} else {
			order = getOrderForInvoice(invorderID, name);
		}

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/Invoice.fxml"));
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InvoiceController invc = loader.getController();
		System.out.println("Order details..generateInvoiceController :: " + order);
		invc.setText(order);

		Parent p = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.show();
	}
}
