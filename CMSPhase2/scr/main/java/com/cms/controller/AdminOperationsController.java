package com.cms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import com.cms.application.DBConnect;
import com.cms.dao.AdminOperationsDao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

public class AdminOperationsController extends AdminOperationsDao {
	@FXML
	private Button viewOrdersButton, updateCostButton;

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		Window owner = viewOrdersButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/AdminOrderActions.fxml"));
		try {
			loader.load();
			Parent p = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateCostButtonAction(ActionEvent event) {
		@SuppressWarnings("unused")
		Window owner = updateCostButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/ViewOrderCost.fxml"));
		try {
			loader.load();
			Parent p = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void viewAllOrder(ActionEvent event) {
		@SuppressWarnings("rawtypes")
		TableView tableview = new TableView();
		@SuppressWarnings("rawtypes")
		ObservableList<ObservableList> data;

		// CONNECTION DATABASE
		//Connection c;
		data = FXCollections.observableArrayList();
		try {
			// ResultSet
			ResultSet rs = getAllOrderDetails();

			/**********************************
			 * TABLE COLUMN ADDED DYNAMICALLY *
			 **********************************/
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				@SuppressWarnings("rawtypes")
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});

				tableview.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}

			/********************************
			 * DATA ADDED TO OBSERVABLELIST*
			 ********************************/
			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data.add(row);
			}

			// FINALLY ADD TO TableView
			tableview.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}

		// Create Main Scene (pop up)
		tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		Scene scene = new Scene(tableview);
		Stage stage = new Stage();
		stage.setWidth(500);
		stage.setScene(scene);
		stage.show();
	}

	public void addCustomer(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginDemoProj.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root, 450, 450));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
