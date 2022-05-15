package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import conn.DBConnect;

public class PersonalModel {
	
	private int success;

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}
	
	//Add request
	public void addPersonal(String name,String nic,String address,String phone,String email,String area,Integer quantity,String solar_panel) {
		Connection con;
		PreparedStatement preparedStatement;
		
		try {
			con = DBConnect.getDatabase();
			
			preparedStatement = con.prepareStatement("insert into personal (name,nic,address,phone,email,area,quantity,solar_panel) values (?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, nic);
			preparedStatement.setString(3, address);
			preparedStatement.setString(4, phone);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, area);
			preparedStatement.setInt(7, quantity);
			preparedStatement.setString(8, solar_panel);
			preparedStatement.execute();
			preparedStatement.close();
			con.close();
			setSuccess(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
			System.out.println(e.getMessage());
		}
	}
	
	//Edit request
	public void editPersonal(int id,String name,String nic,String address,String phone,String email,String area,Integer quantity,String solar_panel) {
		Connection con;
		PreparedStatement preparedStatement;
		
		try {
			con = DBConnect.getDatabase();
			
				preparedStatement = con.prepareStatement("UPDATE personal SET name=?,nic=?,address=?,phone=?,email=?,area=?,quantity=?,solar_panel=? where id=?");
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, nic);
				preparedStatement.setString(3, address);
				preparedStatement.setString(4, phone);
				preparedStatement.setString(5, email);
				preparedStatement.setString(6, area);
				preparedStatement.setInt(7, quantity);
				preparedStatement.setString(8, solar_panel);
				preparedStatement.setInt(9,id);
				preparedStatement.execute();
				preparedStatement.close();
				con.close();
				setSuccess(1);
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
			System.out.println(e.getMessage());
		}
	}

	//Delete request
	public void deletePersonal(int id) {
		Connection con;
		PreparedStatement preparedStatement;
		
		try {
			con = DBConnect.getDatabase();
			
			preparedStatement = con.prepareStatement("DELETE FROM personal WHERE id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			
			setSuccess(1);
		
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
	}

	//View request
	public String getPersonal() {
		
		Connection con;
		PreparedStatement preparedStatement;
		String data="";
		
		try {
			
			con = DBConnect.getDatabase();
			preparedStatement = con.prepareStatement("SELECT * FROM personal");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			data = "<table style='border: 1px solid black;'>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>Name</th>"
	                +"<th style='border: 1px solid black;'>NIC</th>"
	                +"<th style='border: 1px solid black;'>Address</th>"
	                +"<th style='border: 1px solid black;'>Phone</th>"
	                +"<th style='border: 1px solid black;'>Email</th>"
	                +"<th style='border: 1px solid black;'>Area</th>"
	                +"<th style='border: 1px solid black;'>Quantity</th>"
	                +"<th style='border: 1px solid black;'>Solar Panel</th>"
	                +"<th style='border: 1px solid black;'>Action</th>"
	                +"</tr>";
			
			while (resultSet.next()) {

				String button = "<button type='button' onclick='edit("+resultSet.getString(1)+")' class='btn btn-primary'>Edit</button><br><button type='button' onclick='deletes("+resultSet.getString(1)+")' class='btn btn-warning'>Delete</button>";

				
				data = data+"<tr><td style='border: 1px solid black;'>"+resultSet.getInt(1)+"</td>"//getString(1) --> getInt(1)
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(2)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(3)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(4)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(5)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(6)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(7)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getInt(8)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(9)+"</td>"
						+ "<td style='border: 1px solid black;'>"+button+"</td>"
					  + "</tr>";
				
			}
			
			preparedStatement.close();
			con.close();
			
		}catch (ClassNotFoundException | SQLException  e) {

			System.out.println(e.getMessage());
		}
		
		return data+"</table>";
	}
	
	//View One request
	public JSONObject getOneRequest(int id) throws JSONException {
		Connection connection;
		PreparedStatement preparedStatement;
		JSONObject json = new JSONObject();
		
		try {
			connection = DBConnect.getDatabase();
			
			preparedStatement = connection.prepareStatement("SELECT * FROM personal where id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				json.put("id", rs.getInt(1));
				json.put("name", rs.getString(2));
				json.put("nic", rs.getString(3));
				json.put("address", rs.getString(4));
				json.put("phone", rs.getString(5));
				json.put("email", rs.getString(6));
				json.put("area", rs.getString(7));
				json.put("quantity", rs.getInt(8));
				json.put("solar_panel", rs.getString(9));
			}
			
		}catch (ClassNotFoundException | SQLException  e) {
			setSuccess(0);
		}
		return json;
	}


}
