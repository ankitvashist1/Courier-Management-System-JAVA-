package com.cms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cms.application.DBConnect;
import com.cms.models.Customer;

public class CustomerActionsDao extends AdminOperationsDao{
	
	private Statement statement;
	
	public String getOrderStatus(String orderId,String username) {
		String query = "SELECT STATUS FROM ORDER_TABLE WHERE ORDER_ID ='" + orderId + "' and PLACED_BY = '" + username + "'" ;
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

	public ResultSet getCustomerOrders(String username) {
		Connection c;
		ResultSet rs = null;
		try {
			c = DBConnect.getConnection();
			String sql = "select ot.order_id,ot.placed_by,ot.order_date,ot.status,pkg.PACKAGE_NAME, "
					+ "pkg.PACKAGE_TYPE,pkg.FRAGILE_COST,pkg.DELIVERY_TYPE,pkg.EXRESS_COST,pkg.FINAL_COST, "
					+ "concat (concat (concat (concat(ad.SOURCE_ADDRESS_LINE,', '),concat(ad.SOURCE_CITY,', ')) ,concat (ad.SOURCE_STATE,', ') ),ad.SOURCE_PIN) as SOUCE_ADDRESS, "
					+ "concat (concat (concat (concat(ad.DEST_ADDRESS_LINE,', '),concat(ad.DEST_CITY,', ')) ,concat (ad.DEST_STATE,', ') ),ad.DEST_PIN) as DESTINATION_ADDRESS "
					+ " from order_table ot " + "inner join package pkg on pkg.order_id  = ot.ORDER_ID "
					+ "inner join address ad on ad.ORDER_ID = ot.order_id " + "where ot.placed_by= '" + username + "'";
			System.out.println(sql);
			 rs = c.createStatement().executeQuery(sql);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	public String insertTable(Customer customer) throws SQLException {
		try {
			//Statement statement = null;
			System.out.println("Registering User! " + customer);
			statement = DBConnect.getConnection().createStatement();
			System.out.println("here before insert");
			String sql = "INSERT INTO Customer(USER_ID,FIRST_NAME,LAST_NAME,ADDRESS,EMAIL_ID,PASSWORD,CONTACT_NUMBER)"
					+ " VALUES ('" + customer.getUsername() + "','" + customer.getFirstname() + "','"
					+ customer.getLastname() + "','" + customer.getAddress() + "','" + customer.getEmail() + "','"
					+ customer.getPassword() + "','" + customer.getMobilenumber() + "')";
			statement.executeUpdate(sql);

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

		return "Data inserted into table !!!";
	}
}
