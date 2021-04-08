package model;

import java.sql.*;

public class AuthenticationModel {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_user_management", "paf_user",
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
			String query = " SELECT count(*) as 'count' FROM users where username = ? and password = ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, usename);
			preparedStmt.setString(2, password);

// execute the statement

			ResultSet rs = preparedStmt.executeQuery();
			int result = 0;
			if (rs.next()) {
				result = rs.getInt(1);

			}
			if (result == 1) {
				output = "valid";
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

}
