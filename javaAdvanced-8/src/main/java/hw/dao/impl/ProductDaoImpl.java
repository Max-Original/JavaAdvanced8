package hw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import hw.dao.ProductDao;
import hw.domain.Product;
import hw.utils.ConnectionUtils;

public class ProductDaoImpl implements ProductDao {

	private static String CREAT = "insert into product(`name`, `description`,`price`) values (?,?,?)";
	private static String READ_BY_ID = "select * from product where id=?";
	private static String READ_ALL = "select * from product";
	private static String DELETE_BY_ID = "delete from product where id=?";
	private static String UPDATE_BY_ID = "update product set name=?, description = ?, price = ? where id = ?";

	private static Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;

	public ProductDaoImpl()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.connectToDB();
	}

	@Override
	public Product creat(Product product) {
		try {
			preparedStatement = connection.prepareStatement(CREAT);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			product.setId(rs.getInt(1));
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return product;
	}

	@Override
	public Product read(Integer id) {
		Product product = null;

		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();

			result.next();

			Integer productId = result.getInt("id");
			String name = result.getString("name");
			String description = result.getString("description");
			Double price = result.getDouble("price");

			product = new Product(productId,name,description,price);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return product;
	}

	@Override
	public Product update(Product product) {
		
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setDouble(4, product.getPrice());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		
		
		return product;
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
	public List<Product> readAll() {
		List<Product> products = new ArrayList<Product>();
		
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) {
				Integer productId = result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				Double price = result.getDouble("price");
				
				products.add(new Product(productId,name, description, price));
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return products;
	}

}
