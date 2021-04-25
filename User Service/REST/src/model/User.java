package model;
import java.sql.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
public class User
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_user_management", "paf_user", "^paf_user_pw_000");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public boolean insertUser(int user_level, String email, String fname, String lname, String dob, String address, int tp_number)
 {
 try
 {
 Connection con = connect();
 if (con == null)
 {return false; }
 // create a prepared statement
 String query = " insert into users (`user_id`, `user_level`, `email`, `fname`, `lname`, `dob`, `address`, `tp_number`)"
 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setInt(2, user_level);
 preparedStmt.setString(3, email);
 preparedStmt.setString(4, fname);
 preparedStmt.setString(5, lname);
 preparedStmt.setString(6, dob);
 preparedStmt.setString(7, address);
 preparedStmt.setInt(8, tp_number);
// execute the statement
 preparedStmt.execute();
 con.close();
 return true;
 }
 catch (Exception e)
 {
 System.err.println(e.getMessage());
 return false;
 }
 }
public String readUsers(int user_id) {
	JsonObject userData = new JsonObject();

	try {

		Connection con = connect();
		if (con == null) {
			return userData.toString();
		}
		// create a prepared statement
		String query = " select * from users where user_id = ? ";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values

		preparedStmt.setInt(1, user_id);
		ResultSet rs = preparedStmt.executeQuery();

		JsonArray array = new JsonArray();
		JsonObject innerUsers = new JsonObject();
		while (rs.next()) {

			innerUsers.addProperty("user_level", rs.getInt(2));
			innerUsers.addProperty("email", rs.getString(3));
			innerUsers.addProperty("fname", rs.getString(4));
			innerUsers.addProperty("lname", rs.getString(5));
			innerUsers.addProperty("dob", rs.getString(6));
			innerUsers.addProperty("address", rs.getString(7));
			innerUsers.addProperty("tp_number", rs.getInt(8));
			array.add(innerUsers);

		}
		userData.add("users", array);

	} catch (Exception e) {

		e.printStackTrace();
		userData.addProperty("status", "error");
		return userData.toString();

	}

	return userData.toString();
}

public String updateUser(int user_id,int user_level, String email, String fname, String lname, String dob, String address, int tp_number)
{
	String output = "error";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database";
		}

		if (output.equals("invalid")) {
			return output;
		}

		// create a prepared statement for update
		String query = "UPDATE users SET user_level=?,email=?,fname=?,lname=?,dob=?,address=?,tp_number=? WHERE user_id=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values

		 preparedStmt.setInt(1, user_level);
		 preparedStmt.setString(2, email);
		 preparedStmt.setString(3, fname);
		 preparedStmt.setString(4, lname);
		 preparedStmt.setString(5, dob);
		 preparedStmt.setString(6, address);
		 preparedStmt.setInt(7, tp_number);
		 preparedStmt.setInt(8, user_id);
		// execute the statement

		int result = preparedStmt.executeUpdate();

		if (result >= 1) {
			output = "done";

		}
		con.close();

	} catch (Exception e) {
		output = "Error while prosessing the request.";
		System.err.println(e.getMessage());
	}
	return output;
}

public String deleteUser(int user_id) {
	String output = "error";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database";
		}
		// create a prepared statement
		String query = " delete from users where user_id = ? ";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values

		preparedStmt.setInt(1, user_id);

		// execute the statement

		int result = preparedStmt.executeUpdate();

		if (result >= 1) {
			output = "done";

		}
		con.close();

	} catch (Exception e) {
		output = "Exception";
		System.err.println(e.getMessage());
	}
	return output;
}
	
	public String returnUserLevel(int user_id) {
		JsonObject UserData = new JsonObject();

		try {

			Connection con = connect();
			if (con == null) {
				return UserData.toString();
			}
			// create a prepared statement
			String query = " select * from users where user_id = ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, user_id);
			ResultSet rs = preparedStmt.executeQuery();

			JsonArray array = new JsonArray();
			JsonObject innerUsers = new JsonObject();
			while (rs.next()) {

				innerUsers.addProperty("product_id", rs.getInt(1));
				innerUsers.addProperty("quantity", rs.getInt(2));
				array.add(innerUsers);

			}
			UserData.add("users", array);

		} catch (Exception e) {

			e.printStackTrace();
			UserData.addProperty("status", "error");
			return UserData.toString();

		}

		return UserData.toString();
	}	
}
