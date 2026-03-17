package com.learnJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor 
{
	private Connection connection;
	public Doctor(Connection connection)
	{
		this.connection=connection;
	}
	public void viewDoctors()
	{
		String query="select * from doctors";
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			System.out.println("Doctors:");
			System.out.println("+------------+--------------+-----------------+");
			System.out.println("| Doctor Id  | Name         | specilization   |");
			System.out.println("+------------+--------------+-----------------+");
			while(resultSet.next())
			{
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				String specilization=resultSet.getString("specilization");
				System.out.printf("|%-12s|%-14s|%-16s|\n",id,name,specilization);
				System.out.println("+------------+--------------+-----------------+");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public boolean getDoctorByID(int id)
	{
		String query="select * from doctors where id=?";
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
