package hw.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import hw.dao.ProductDao;
import hw.dao.impl.ProductDaoImpl;
import hw.domain.Product;
import hw.service.ProductService;

public class ProductServiceImpl implements ProductService{
	
	private static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);
	private static ProductService productServiceImpl;
	private ProductDao productDao;
	
	public ProductServiceImpl() {
		try {
			productDao = new ProductDaoImpl();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}
	
	public static ProductService getProductService() {
		if(productServiceImpl == null) {
			productServiceImpl = new ProductServiceImpl();
		}
		return productServiceImpl;
	}
	@Override
	public Product creat(Product product) {
		
		return productDao.creat(product);
	}

	@Override
	public Product read(Integer id) {
		return productDao.read(id);
	}

	@Override
	public Product update(Product product) {
		return productDao.update(product);
	}

	@Override
	public void delete(Integer id) {
		productDao.delete(id);
	}

	@Override
	public List<Product> readAll() {
		return productDao.readAll();
	}

}
