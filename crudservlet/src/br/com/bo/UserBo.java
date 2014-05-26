package br.com.bo;

import br.com.dao.UserDao;
import br.com.model.User;

public class UserBo {

	 private UserDao dao;
	 
	    public UserBo() {
	        super();
	        dao = new UserDao();
	    }

	public void deleteUser(String userId) {
		dao.deleteUser(userId);
	}

	public Object getAllUsers() {
		return dao.getAllUsers();
	}

	public User getUserById(String userId) {
		return dao.getUserById(userId);
	}

	public void checkUser(User user) {
		dao.checkUser(user);
	}

}
