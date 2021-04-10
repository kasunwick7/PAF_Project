package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthenticationModel {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_security_management", "paf_user",
					"^paf_user_pw_000");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String uservalidation(String usename, String password) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " SELECT * FROM user_credentials where username = ? and password = ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, usename);
			preparedStmt.setString(2, password);

			// execute the statement

			ResultSet rs = preparedStmt.executeQuery();
			int userID = 0;

			int result = 0;
			if (rs.next()) {
				result++;
				userID = rs.getInt(1);

			}
			if (result == 1) {
				output = "valid";
				this.addUserToCurrentyLoggedInTable(userID);
			} else {
				output = "invalid";
			}
			con.close();

		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public boolean addUserToCurrentyLoggedInTable(int userID) {

		try {

			Connection con = connect();
			if (con == null) {
				return false;
			}
			// create a prepared statement
			String query = " insert into logged_in_users values(? , ?) ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			preparedStmt.setInt(1, userID);
			preparedStmt.setString(2, formatter.format(date));
			preparedStmt.executeUpdate();

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return false;

		}

		return true;
	}

}
