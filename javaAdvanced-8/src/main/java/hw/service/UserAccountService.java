package hw.service;

import hw.domain.UserAccount;
import hw.shared.AbstractCRUD;

public interface UserAccountService extends AbstractCRUD<UserAccount>{
	UserAccount getUserAccountByEmail(String email);
}
