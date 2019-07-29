package com.shandeep.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection 
{
	private final String url = "jdbc:postgresql://ec2-54-247-170-5.eu-west-1.compute.amazonaws.com:5432/dbut4b3f8gp5mp?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	private final String username = "fuymmypxwuljuc";
	private final String password = "dd77f5c64339b37804192ff8718b6af1a3b6feb9a6fa97344d356fffad412810";
	public Branch getBranchIFSC(String ifsc)
	{
		Branch branch = new Branch();
		try (Connection connection = DriverManager.getConnection(url,username,password)) {

			PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM branches where ifsc = ?");
			statement1.setString(1,ifsc);
			ResultSet resultSet1 = statement1.executeQuery();
			while (resultSet1.next()) {
				branch.setAddress(resultSet1.getString("address"));
				branch.setBank_id(resultSet1.getInt("bank_id"));
				branch.setBranch(resultSet1.getString("branch"));
				branch.setCity(resultSet1.getString("city"));
				branch.setDistrict(resultSet1.getString("district"));
				branch.setIfsc(resultSet1.getString("ifsc"));
				branch.setState(resultSet1.getString("state"));
			}
			PreparedStatement statement2 = connection.prepareStatement("SELECT name FROM banks where id = ?");
			statement2.setInt(1,branch.getBank_id());
			ResultSet resultSet2 = statement2.executeQuery();
			while(resultSet2.next()) {
				branch.setBank_name(resultSet2.getString("name"));
			}
			
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return branch;
	}
	public List<Branch> getBranchNameandCity(String name,String city,int limit,int offset)
	{
		

		List<Branch> branchlist = new ArrayList();
		try (Connection connection = DriverManager.getConnection(url,username,password)) {
			
			PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM branches where bank_id IN (SELECT id from banks where name = ?) AND city = ? OFFSET ? LIMIT ?");
			statement1.setString(1,name);
			statement1.setString(2,city);
			statement1.setInt(3,offset);
			statement1.setInt(4,limit);
			ResultSet resultSet1 = statement1.executeQuery();
			while (resultSet1.next()) {
				Branch branch = new Branch();
				branch.setAddress(resultSet1.getString("address"));
				branch.setBank_id(resultSet1.getInt("bank_id"));
				branch.setBank_name(name);
				branch.setBranch(resultSet1.getString("branch"));
				branch.setCity(city);
				branch.setDistrict(resultSet1.getString("district"));
				branch.setIfsc(resultSet1.getString("ifsc"));
				branch.setState(resultSet1.getString("state"));
				branchlist.add(branch);
			}
		} 
		catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return branchlist;
	}
}
