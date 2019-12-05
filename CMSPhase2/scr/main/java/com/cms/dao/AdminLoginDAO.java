package com.cms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cms.application.DBConnect;
import com.cms.models.User;

public class AdminLoginDAO/* extends DataSource */ {

	public User adminFinder(String adminID, String password) {
		User user = null;
		String query = "SELECT * FROM ADMIN_USER WHERE ADMIN_ID = ? AND password = ?;";

		try (PreparedStatement statement = DBConnect.getConnection().prepareStatement(query)) {
			statement.setString(1, adminID);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = new User(

						resultSet.getString("ADMIN_ID"), resultSet.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println("Error Finding Admin by Admin ID: " + e);
		}
		return user;
	}
}