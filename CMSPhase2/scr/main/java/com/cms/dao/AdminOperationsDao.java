package com.cms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cms.application.DBConnect;

public class AdminOperationsDao {
	
	public ResultSet getAllOrderDetails() {
		ResultSet rs = null;
		Connection c;
		try {
			c = DBConnect.getConnection();
			String SQL = "select ot.order_id,ot.placed_by,ot.order_date,ot.status,pkg.PACKAGE_NAME, "
					+ "pkg.PACKAGE_TYPE,pkg.FRAGILE_COST,pkg.DELIVERY_TYPE,pkg.EXRESS_COST,pkg.FINAL_COST, "
					+ "concat (concat (concat (concat(ad.SOURCE_ADDRESS_LINE,', '),concat(ad.SOURCE_CITY,', ')) ,concat (ad.SOURCE_STATE,', ') ),ad.SOURCE_PIN) as SOUCE_ADDRESS, "
					+ "concat (concat (concat (concat(ad.DEST_ADDRESS_LINE,', '),concat(ad.DEST_CITY,', ')) ,concat (ad.DEST_STATE,', ') ),ad.DEST_PIN) as DESTINATION_ADDRESS "
					+ "from order_table ot " + "inner join package pkg on pkg.order_id  = ot.ORDER_ID "
					+ "inner join address ad on ad.ORDER_ID = ot.order_id order by ot.order_date";

			// ResultSet
			rs = c.createStatement().executeQuery(SQL);
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	public String getOrderStatus(String orderId) {
		String query = "SELECT STATUS FROM ORDER_TABLE WHERE ORDER_ID ='" + orderId + "'";
		Statement st = null;
		String status = null;
		try {
			st = DBConnect.getConnection().createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(query);
			if (rs.next()) {
				status = rs.getString("status");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
		
	}
	
	public void updateOrderStatus(String orderId,String updatedStatus) {
		String queryUpd = "UPDATE ORDER_TABLE SET status = ? WHERE ORDER_ID ='" + orderId + "'";
		java.sql.PreparedStatement preparedStmt;
		try {
			preparedStmt = DBConnect.getConnection().prepareStatement(queryUpd);
			preparedStmt.setString(1, updatedStatus);
			// execute the java preparedstatement
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
