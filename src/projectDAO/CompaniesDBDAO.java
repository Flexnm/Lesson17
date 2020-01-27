package projectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Company;

public class CompaniesDBDAO implements CompaniesDAO {
	// This class can also be called CompaniesDAOimpl
	private ConnectionPool pool = ConnectionPool.getInstance();

	public boolean isCompanyExists(String email, String password) throws SQLException {
		Connection con = pool.getConnection();
		try {
			// checks if a company exists in the companies table according to login input.
			PreparedStatement ps = con.prepareStatement("SELECT company_email, company_password FROM companies");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equals(email) && rs.getString(2).equals(password)) {
					System.out.println("Whelcome");
					return true;
				}
			}
			System.out.println("Login Error");
			return false;
		} finally {
			pool.restoreConnection(con);
		}
	}

	public void addCompany(Company company) throws SQLException {
		Connection con = pool.getConnection();
		try {
			// add company to the companies table.
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO companies(company_name, company_email, password) values(? ,? ,?)");
			ps.setString(1, company.getName());
			ps.setString(2, company.getEmail());
			ps.setString(3, company.getPassword());
			ps.execute();
		} finally {
			pool.restoreConnection(con);
		}
	}

	public void updateCompany(Company company) throws SQLException {
		Connection con = pool.getConnection();
		try {
			// update an existing company data in the companies table according to input
			PreparedStatement ps = con
					.prepareStatement("UPDATE companies SET name = ?, email = ?, password = ? WHERE id = ?");
			ps.setString(1, company.getName());
			ps.setString(2, company.getEmail());
			ps.setString(3, company.getPassword());
			ps.setInt(4, company.getId());
			ps.execute();
		} finally {
			pool.restoreConnection(con);
		}
	}

	public void deleteCompany(int companyID) throws SQLException {
		Connection con = pool.getConnection();
		try {
			// remove a company from the companies table according to ID.
			PreparedStatement ps = con.prepareStatement("DELETE FROM companies WHERE id = " + companyID);
			ps.execute();
		} finally {
			pool.restoreConnection(con);
		}
	}

	public ArrayList<Company> getAllCompanies() throws SQLException {
		ArrayList<Company> companies = new ArrayList<Company>();
		Connection con = pool.getConnection();
		try {
			// return a list of company Objects that exist in the companies table.
			PreparedStatement ps = con.prepareStatement("SELECT * FROM companies");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				companies.add(new Company(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			return companies;
		} finally {
			pool.restoreConnection(con);
		}
	}

	public Company getOneCompany(int companyID) throws SQLException {
		Connection con = pool.getConnection();
		try {
			// return a company Object from the companies table according to ID.
			PreparedStatement ps = con.prepareStatement("SELECT * FROM companies WHERE id = " + companyID);
			ResultSet rs = ps.executeQuery();
			rs.first();
			Company company = new Company(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			return company;
		} finally {
			pool.restoreConnection(con);
		}
	}
}