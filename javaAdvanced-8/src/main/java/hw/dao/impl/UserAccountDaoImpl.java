package hw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import hw.dao.UserAccountDao;
import hw.domain.UserAccount;
import hw.utils.ConnectionUtils;

public class UserAccountDaoImpl implements UserAccountDao{
	
	private static String CREATE = "insert into user(`email`,`first_name`,`last_name`,`password`,`role`) values (?,?,?,?,?)";
	private static String READ_BY_ID = "select * from user where id=?";
	private static String READ_ALL = "select * from user";
	private static String DELETE_BY_ID = "delete from user where id=?";
	private static String UPDATE_BY_ID = "update user set name=?, description = ?, price = ? where id = ?";
	private static String READ_BY_EMAIL = "select * from user where email=?";
	
	private static Logger LOGGER = Logger.getLogger(UserAccountDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	public UserAccountDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.connectToDB();
	}
	
	
	
	@Override
	public UserAccount creat(UserAccount user) {
		
		try {
			preparedStatement = connection.prepareStatement(CREATE);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			user.setId(rs.getInt(1));
			
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return user;
	}

	@Override
	public UserAccount read(Integer id) {
		UserAccount user = null;
		
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			
			Integer userId = result.getInt("id");
			String email = result.getString("email");
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			String role = result.getString("role");
			String password = result.getString("password");
			
			user = new UserAccount(userId,email,firstName,lastName,role,password);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return user;
	}

	@Override
	public UserAccount update(UserAccount user) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeQuery();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@Override
	public List<UserAccount> readAll() {
		List<UserAccount> users = new ArrayList<UserAccount>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) {
				Integer userId = result.getInt("id");
				String email = result.getString("email");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String role = result.getString("role");
				String password = result.getString("password");
				
				users.add(new UserAccount(userId, firstName, lastName, email, password, role));
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return users;
	}



	@Override
	public UserAccount getUserAccountByEmail(String email) {
		UserAccount user = null;
		
				try {
					preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
					preparedStatement.setString(1, email);
					ResultSet result = preparedStatement.executeQuery();
					result.next();
					
					Integer userId = result.getInt("id");
					String firstName = result.getString("first_name");
					String lastName = result.getString("last_name");
					String role = result.getString("role");
					String password = result.getString("password");
					
					user = new UserAccount(userId, firstName, lastName, email, password, role); 
					
				} catch (Exception e) {
					LOGGER.error(e);
				}
				
		return user;
	}

}
