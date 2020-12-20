package hw.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import hw.dao.BasketDao;
import hw.dao.impl.BasketDaoImpl;
import hw.domain.Basket;
import hw.service.BasketService;

public class BasketServiceImpl implements BasketService {

	private static Logger LOGGER = Logger.getLogger(BasketServiceImpl.class);
	private static BasketService basketServiceImpl;
	private BasketDao basketDao;

	public BasketServiceImpl() {
		try {
			basketDao = new BasketDaoImpl();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	public static BasketService getBasketService() {
		if(basketServiceImpl == null) {
			basketServiceImpl = new BasketServiceImpl();
		}
		return basketServiceImpl;
	}
	
	
	@Override
	public Basket creat(Basket basket) {
		return basketDao.creat(basket);
	}

	@Override
	public Basket read(Integer id) {
		return basketDao.read(id);
	}

	@Override
	public Basket update(Basket basket) {
		return basketDao.update(basket);
	}

	@Override
	public void delete(Integer id) {
		basketDao.delete(id);
	}

	@Override
	public List<Basket> readAll() {
		return basketDao.readAll();
	}

}
