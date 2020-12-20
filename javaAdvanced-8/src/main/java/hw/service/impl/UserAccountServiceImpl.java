package hw.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import hw.dao.UserAccountDao;
import hw.dao.impl.UserAccountDaoImpl;
import hw.domain.UserAccount;
import hw.service.ProductService;
import hw.service.UserAccountService;

public class UserAccountServiceImpl implements UserAccountService {
	
	private static Logger LOGGER = Logger.getLogger(UserAccountServiceImpl.class);
	private static UserAccountService userAccountServiceImpl;
	private UserAccountDao userAccountDao;
	
	public UserAccountServiceImpl() {
		try {
			userAccountDao = new UserAccountDaoImpl();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}
	
	public static UserAccountService getUserAccountService() {
		if(userAccountServiceImpl == null) {
			userAccountServiceImpl = new UserAccountServiceImpl();
		}
		return userAccountServiceImpl;
	} 
	
	@Override
	public UserAccount creat(UserAccount userAccount) {
		return userAccountDao.creat(userAccount);
	}

	@Override
	public UserAccount read(Integer id) {
		return userAccountDao.read(id);
	}

	@Override
	public UserAccount update(UserAccount userAccount) {
		return userAccountDao.update(userAccount);
	}

	@Override
	public void delete(Integer id) {
		userAccountDao.delete(id);
	}

	@Override
	public List<UserAccount> readAll() {
		return userAccountDao.readAll();
	}

	@Override
	public UserAccount getUserAccountByEmail(String email) {
		return userAccountDao.getUserAccountByEmail(email);
	}
	
	

}
