package com.revature.repository;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.revature.Utils.ConnectionUtil;

public class DaoImp implements Dao {

	@Override
	public void addUser(String username, String passHash, int accountType) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO users(user_name, password_hash, user_role) VALUES (?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, passHash);
			statement.setInt(3, accountType);
			statement.execute();	
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getPassHash(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT password_hash FROM users WHERE user_name = '"+username+"';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			result.next();
			if(!result.isAfterLast()) { 
				return result.getString("password_hash");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUserRole(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users u LEFT JOIN user_roles ur ON u.user_role = ur.role_id WHERE u.user_name = '"+username+"';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			result.next();
			if(!result.isAfterLast()) { 
				return result.getString("role_name");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void sendMessage(String username, String message) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO messages(sender_id, sent, message) VALUES (?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setTimestamp(2,new java.sql.Timestamp(System.currentTimeMillis()));
			statement.setString(3, message);
			statement.execute();	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean isBanned(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ban_list bl LEFT JOIN ban_state bs ON bl.ban_state = bs.state_id WHERE bl.banned_user_name = '"+username+"';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				if(result.getString("state_name").equals("timeout")) {
					if(result.getTimestamp("ban_release").before(new Date(System.currentTimeMillis()))) {
						//released
					}else {
						return true;
					}
				}else if(result.getString("state_name").equals("permanent")) {
					return true;
				}
			}
			return false;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getMessages() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM messages ORDER BY sent;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			StringBuilder output = new StringBuilder();
			while(result.next()) {
				output.append(result.getString("sender_id") + ": " + result.getString("message") + "\n");
			}
			
			return output.toString();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean userExists(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT COUNT(*) FROM users WHERE user_name = '"+username+"';";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			result.next();
			if(result.getInt("count") == 1) { 
				return true;
			}else {
				return false;
			}	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void addBan(String bannedUser, String banningMod, String banReason, int banState, Date releaseDate) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO ban_list(banned_user_name, banning_mod, ban_reason, ban_state, ban_release) VALUES (?,?,?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, bannedUser);
			statement.setString(2, banningMod);
			statement.setString(3, banReason);
			statement.setInt(4, banState);
			statement.setTimestamp(5,new java.sql.Timestamp(releaseDate.getTime()));
			statement.execute();	
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void makeMod(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE users SET user_role = '1' WHERE user_name = '"+username+"';";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
