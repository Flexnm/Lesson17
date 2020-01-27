package testing;

import java.sql.SQLException;

import beans.Company;
import projectDAO.CompaniesDBDAO;
import projectDAO.ConnectionPool;

public class Test2 {
	public static void main(String[] args) {
		
		CompaniesDBDAO compDB = new CompaniesDBDAO();
		
		try {
			//compDB.addCompany(new Company("Monies", "cash@money.com", "$$$WeMake$$$"));
			System.out.println(compDB.getAllCompanies());
			
			for (Company comp : compDB.getAllCompanies()) {
				System.out.println(comp);
			}
			
		} catch (SQLException e) {
			System.out.println("Addition of company failed: " + e.getMessage());
		}
		
		ConnectionPool.getInstance().closeAllConnections();
	}
}