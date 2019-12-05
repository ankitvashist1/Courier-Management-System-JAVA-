package com.cms.dao;


import java.sql.SQLException;
import java.sql.Statement;

import com.cms.application.DBConnect;
import com.cms.models.Order;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConfirmOrderDao {
	
	private Statement statement;
	protected String insertOrderTable(Order order) throws SQLException { {
		// TODO Auto-generated method stub
		try {
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			System.out.println("Attempting to save Order !"+order);
			statement = DBConnect.getConnection().createStatement();
		    System.out.println("here before insert");
		    String ordertableSql = "INSERT INTO order_table(ORDER_ID,PLACED_BY,STATUS,INVOICE_GENERATED)" + 
				    " VALUES ('"+order.getOrderid()+"','"+order.getPlacedBy()+"','"+order.getStatus()+"',0)";
		    statement.executeUpdate(ordertableSql);
		    
		    String addresstableSql = "INSERT INTO address(ORDER_ID,SOURCE_ADDRESS_LINE,SOURCE_CITY,SOURCE_STATE,SOURCE_PIN,DEST_ADDRESS_LINE,DEST_CITY,DEST_STATE,DEST_PIN)" + 
				    " VALUES ('"+order.getOrderid()+"','"+order.getSourceAddress().getAddressLine()+"','"+order.getSourceAddress().getCity()+"','"+order.getSourceAddress().getState()+"','"+order.getSourceAddress().getPin()+"','"+order.getDestinationAddress().getAddressLine()+"','"+order.getDestinationAddress().getCity()+"','"+order.getDestinationAddress().getState()+"','"+order.getDestinationAddress().getPin()+"')";
		    statement.executeUpdate(addresstableSql);
		    
		    
		    int is_fragile = order.getPackageDetail().isFragile() == true ? 1:0;
		    int is_express = order.getPackageDetail().isExpressDelivery() == true ? 1 : 0;
		    String packageTableSql = "INSERT INTO package(ORDER_ID,PACKAGE_NAME,IS_FRAGILE,FRAGILE_COST,IS_EXPRESS,EXRESS_COST,FINAL_COST,DELIVERY_TYPE,PACKAGE_TYPE,RATE_CHART_ID)" + 
				    " VALUES ('"+order.getOrderid()+"','"+order.getPackageDetail().getPackagename()+"','"+is_fragile+"','"+order.getPackageDetail().getFragilePackageCost()+"','"+is_express+"','"+order.getPackageDetail().getExpressDeliveryCost()+"','"+order.getPackageDetail().getFinalCost()+"','"+order.getPackageDetail().getDeliveryType()+"','"+order.getPackageDetail().getPackageType()+"','"+order.getPackageDetail().getRateChart().getId()+"')";
		    statement.executeUpdate(packageTableSql);	
		    
     	    Alert a = new Alert(AlertType.INFORMATION);
		    a.setContentText("Order Placed Successfully! \nORDER ID : "+order.getOrderid());
		    a.showAndWait();

		    
	     	}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new SQLException();

			}
		
			try {
				statement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return "Your Order has been successfully placed";
	}
	

	}
}
