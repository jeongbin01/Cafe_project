package com.Cafe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Cafe.model.Customer;

public class CustomerDAO {
	private final String url = "jdbc:oracle:thin:@localhost:10521:xe";
	private final String user = "C##Cafe_Website";
	private final String password = "1234";

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	// 고객 등록      
	public boolean insertCustomer(Customer customer) {
		String sql = "INSERT INTO CUSTOMER (customer_id, name, phone, email, address, signup_date) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, customer.getCustomerId());
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getPhone());
			pstmt.setString(4, customer.getEmail());
			pstmt.setString(5, customer.getAddress());
			pstmt.setString(6, customer.getSignupDate());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 모든 고객 조회
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new ArrayList<>();
		String sql = "SELECT * FROM CUSTOMER";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getString("phone"),
						rs.getString("email"), rs.getString("address"), rs.getString("signup_date"));
				customerList.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}

	// 고객 수정
	public boolean updateCustomer(Customer customer) {
		String sql = "UPDATE CUSTOMER SET name = ?, phone = ?, email = ?, address = ? WHERE customer_id = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getPhone());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getAddress());
			pstmt.setInt(5, customer.getCustomerId());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 고객 삭제
	public boolean deleteCustomer(int customerId) {
		String sql = "DELETE FROM CUSTOMER WHERE customer_id = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
