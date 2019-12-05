package com.cms.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;

import com.cms.application.DBConnect;
import com.cms.dao.InvoiceDao;
import com.cms.models.Order;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

public class InvoiceController extends InvoiceDao {

	@FXML
	private Label showPackageName, sourceAddressLine, destAddressLine, SourceCity, SourceState, destCity, destState,
			invoiceID, sPIN, dPIN, orderID, invoiceDate, totaltobepaid, pkgcost, amountpaid, due;

	@FXML
	private Pane anchorPane;

	@FXML
	private Button backButton;

	@FXML
	Button saveInvoice;

	public void handleBackButtonAction(ActionEvent event) throws Exception {
		backButton.getScene().getWindow().hide();
	}

	
	public void setText(Order order) {
		System.out.println("InvoiceController.setText:" + order);
		this.showPackageName.setText(order.getPackageDetail().getPackagename());
		this.sourceAddressLine.setText(order.getSourceAddress().getAddressLine());
		this.SourceCity.setText(order.getSourceAddress().getCity());
		this.SourceState.setText(order.getSourceAddress().getState());
		this.sPIN.setText(order.getSourceAddress().getPin());
		this.destAddressLine.setText(order.getDestinationAddress().getAddressLine());
		this.destCity.setText(order.getDestinationAddress().getCity());
		this.destState.setText(order.getDestinationAddress().getState());
		this.dPIN.setText(order.getDestinationAddress().getPin());
		this.orderID.setText(order.getOrderid());

		this.invoiceDate.setText(order.getOrderPlacedDate().toLocaleString());
		this.totaltobepaid.setText(order.getPackageDetail().getFinalCost().toString());
		this.pkgcost.setText(order.getPackageDetail().getFinalCost().toString());
		this.due.setText(order.getPackageDetail().getFinalCost().toString());
		this.amountpaid.setText("0.00");

		try {
			System.out.println("Hunny" + orderID);
			updateInvoiceFlag(order.getOrderid());
			this.invoiceID.setText(order.getOrderid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void saveOrderInformation(ActionEvent action) {
		WritableImage image = anchorPane.snapshot(new SnapshotParameters(), null);
		System.out.println("saveOrderInformation;" + orderID.getText());
		String location = "C:\\CMS\\" + orderID.getText() + ".png";

		File file = new File(location);

		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Alert a = new Alert(AlertType.INFORMATION);
		a.setContentText("Invoice Successfully Saved at :" + location);
		a.showAndWait();
		return;

	}

}
