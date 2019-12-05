package com.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cms.application.DBConnect;
import com.cms.models.Address;
import com.cms.models.Order;
import com.cms.models.PackageDetail;
import com.cms.models.RateChart;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvoiceDao {

	public Integer getinvoiceGeneratedFlag(String orderId,String username) {
		String query = "SELECT INVOICE_GENERATED FROM order_table WHERE PLACED_BY = '"+username+"' AND order_id ='"+orderId+"'";
		System.out.println(query);
	    Statement st = null;
	    ResultSet rs = null;
	    Integer invoiceFlag = null;
	    try {
			st = DBConnect.getConnection().createStatement();
	        // execute the query, and get a java resultset
	 
			rs = st.executeQuery(query);
			while( rs.next() ) {
				 invoiceFlag = rs.getInt("INVOICE_GENERATED");
			}
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return invoiceFlag;
	}
	
	public Order getOrderForInvoice(String orderId,String username ) {
		Order order = new Order();
		String querySelect  = "select ot.*,pkg.PACKAGE_NAME, " + 
           		"pkg.PACKAGE_TYPE,pkg.FRAGILE_COST,pkg.DELIVERY_TYPE,pkg.EXRESS_COST,pkg.FINAL_COST, " + 
           		"ad.SOURCE_ADDRESS_LINE as SOUCE_ADDRESS,ad.SOURCE_CITY,ad.SOURCE_STATE,ad.SOURCE_PIN , "
           		+ "ad.DEST_ADDRESS_LINE DESTINATION_ADDRESS,ad.DEST_CITY,ad.DEST_STATE,ad.DEST_PIN, "
           		+ "rc.cost as base_delivery_cost,rc.id as rate_chart_id " + 
           		" from order_table ot " + 
           		"inner join package pkg on pkg.order_id  = ot.ORDER_ID " + 
           		"inner join address ad on ad.ORDER_ID = ot.order_id "
           		+"inner join rate_chart rc on pkg.rate_chart_id = rc.id " + 
           		"where ot.placed_by= '"+username+"' and ot.order_id ='"+orderId+"'";	
		
		System.out.println(querySelect);
		Statement st = null;
		ResultSet rs = null;
		try 
		{

			System.out.println("4");
			st = DBConnect.getConnection().createStatement();
	        rs = st.executeQuery(querySelect);
	       // Order order = new Order();
			while( rs.next() ) {
				System.out.println("5");
				order.setOrderid(rs.getString("ORDER_ID"));
				order.setOrderPlacedDate(rs.getTimestamp("ORDER_DATE"));
				order.setPlacedBy(rs.getString("PLACED_BY"));
				order.setStatus(rs.getString("STATUS"));
				
				PackageDetail pkg = new PackageDetail();
				pkg.setDeliveryType(rs.getString("DELIVERY_TYPE"));//
			//	pkg.setExpressDelivery(rs.getBoolean("IS_EXPRESS"));
				pkg.setExpressDeliveryCost(rs.getDouble("EXRESS_COST"));
				pkg.setFinalCost(rs.getDouble("FINAL_COST")); //
				//pkg.setFragile(rs.getBoolean("IS_FRAGILE"));
				pkg.setFragilePackageCost(rs.getDouble("FRAGILE_COST")); //
				pkg.setPackagename(rs.getString("PACKAGE_NAME")); //
				pkg.setPackageType(rs.getString("PACKAGE_TYPE"));//
				
				RateChart rateChart = new RateChart();
				rateChart.setId(rs.getInt("rate_chart_id"));
				rateChart.setCost(rs.getDouble("base_delivery_cost"));
				pkg.setRateChart(rateChart);
			//	pkg.setWeight(rs.getDouble("EXRESS_COST"));
				
				order.setPackageDetail(pkg);
				
				Address sourceAddress = new Address();
				sourceAddress.setAddressLine(rs.getString("SOUCE_ADDRESS"));
				sourceAddress.setCity(rs.getString("SOURCE_CITY"));
				sourceAddress.setState(rs.getString("SOURCE_STATE"));
				sourceAddress.setPin(rs.getString("PACKAGE_NAME"));
				
				Address destinationAddress = new Address();
				destinationAddress.setAddressLine(rs.getString("DESTINATION_ADDRESS"));
				destinationAddress.setCity(rs.getString("DEST_CITY"));
				destinationAddress.setState(rs.getString("DEST_STATE"));
				destinationAddress.setPin(rs.getString("DEST_PIN"));
				
				System.out.println("sourceaddress :"+sourceAddress);
				System.out.println("destinationAddress:"+destinationAddress);
				order.setDestinationAddress(destinationAddress);
				order.setSourceAddress(sourceAddress);
				
				//name          = rs.getString("PLACED_BY");
				System.out.println("Fetched Order:"+order);
				
			}
		}
		catch  (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	protected String updateInvoiceFlag(String orderID) throws SQLException {
		// TODO Auto-generated method stub
		Statement statement;
		try {
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			System.out.println("Attempting to connect!");
			statement = DBConnect.getConnection().createStatement();
			System.out.println("orderID" + orderID);
			System.out.println("insertInvoice - here before insert");
			String sql = " update order_table set invoice_generated = 1 and invoice_date = '" + date + "'"
					+ " where order_id ='" + orderID + "'";

			statement.executeUpdate(sql);
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("Invoice generated!");
			a.showAndWait();

		} catch (SQLException e) {
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
