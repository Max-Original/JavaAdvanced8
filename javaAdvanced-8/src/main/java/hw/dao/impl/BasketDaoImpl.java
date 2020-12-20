package hw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import hw.dao.BasketDao;
import hw.domain.Basket;
import hw.utils.ConnectionUtils;

public class BasketDaoImpl implements BasketDao {

	private static String CREAT = "insert into basket(`user_id`, `product_id`,`date_purhase`) values (?,?,?)";
	private static String READ_BY_ID = "select * from basket where id=?";
	private static String READ_ALL = "select * from basket";
	private static String DELETE_BY_ID = "delete from basket where id=?";
	
	private static Logger LOGGER = Logger.getLogger(BasketDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	public BasketDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.connectToDB();
	}
	
	@Override
	public Basket creat(Basket basket) {
		
		try {
			preparedStatement = connection.prepareStatement(CREAT);
			preparedStatement.setInt(1, basket.getId());
			preparedStatement.setInt(2, basket.getProduct_id());
			preparedStatement.setDate(3, (java.sql.Date) new Date(basket.getDate_purchase().getTime()));
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			basket.setId(rs.getInt(1));
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return basket;
	}

	@Override
	public Basket read(Integer id) {
		Basket basket = null;
		
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			
			result.next();
			Integer basketId = result.getInt("id");
			Integer userId = result.getInt("user_id");
			Integer productId = result.getInt("product_id");
			Date purhaseDate = result.getDate("purhaseDate");
			
			basket = new Basket(basketId,userId, productId, purhaseDate);
			
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return basket;
	}

	@Override
	public Basket update(Basket t) {
		throw new IllegalStateException("we dont use this");
	}

	@Override
	public void delete(Integer id) {
		try {
			connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@Override
	public List<Basket> readAll() {
		List<Basket> basketObjects = new ArrayList<Basket>();
		try {
			connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) {
				Integer basketId = result.getInt("id");
				Integer userId = result.getInt("user_id");
				Integer productId = result.getInt("product_id");
				Date purhaseDate = result.getDate("purhaseDate");
				
				 basketObjects.add(new Basket(basketId,userId, productId, purhaseDate));
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return basketObjects;
	}

}
