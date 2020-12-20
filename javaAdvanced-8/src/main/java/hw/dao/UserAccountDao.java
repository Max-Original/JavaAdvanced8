package hw.dao;

import hw.domain.UserAccount;
import hw.shared.AbstractCRUD;

public interface UserAccountDao extends AbstractCRUD<UserAccount> {

	UserAccount getUserAccountByEmail(String email);
}
