package com.shoppingCart.adminSystem;

public class AdminAuthenticator {
	private ThreadLocal<Boolean> isAdminLoggedIn ;
	
	public AdminAuthenticator() {
		isAdminLoggedIn = ThreadLocal.withInitial(()->false);
	}
	
	public void authenticateAdmin(String userName, String password) {
		Admin admin = Admin.getAdmin();
		if(admin.getUserName().equals(userName) && admin.getPassword().equals(password)) {
			isAdminLoggedIn.set(true);
		}
	}
	
	public boolean isAdminLoggedIn() {
		return isAdminLoggedIn.get();
	}
	
	public void logoutAdmin() {
		isAdminLoggedIn.set(false);
	}
}
