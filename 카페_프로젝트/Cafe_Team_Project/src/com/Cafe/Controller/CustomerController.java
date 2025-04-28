package com.Cafe.Controller;

import java.util.List;
import java.util.Scanner;
import com.Cafe.dao.CustomerDAO;
import com.Cafe.model.Customer;

public class CustomerController {

	private final CustomerDAO dao = new CustomerDAO();
	private final Scanner sc = new Scanner(System.in);

	// 고객 관리
	public void run() {
		while (true) {
			System.out.println("\n=== 고객 관리 ===");
			System.out.println("1. 전체 조회 | 2. 등록 | 3. 수정 | 4. 삭제 | 0. 뒤로가기");
			System.out.print("선택: ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1 -> showAllCustomers();
			case 2 -> addCustomer();
			case 3 -> updateCustomer();
			case 4 -> deleteCustomer();
			case 0 -> {
				return;
			}
			default -> System.out.println("잘못된 선택입니다.");
			}
		}
	}

	// 고객 조회
	private void showAllCustomers() {
		List<Customer> customers = dao.getAllCustomers();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}

	//고객 정보
	private void addCustomer() {
		System.out.print("고객 ID: ");
		int customerId = sc.nextInt();
		sc.nextLine();
		System.out.print("이름: ");
		String name = sc.nextLine();
		System.out.print("전화번호: ");
		String phone = sc.nextLine();
		System.out.print("이메일: ");
		String email = sc.nextLine();
		System.out.print("주소: ");
		String address = sc.nextLine();
		System.out.print("가입일 (YYYY-MM-DD): ");
		String signupDate = sc.nextLine();

		Customer customer = new Customer(customerId, name, phone, email, address, signupDate);
		dao.insertCustomer(customer);
	}

	// 고객 수정 
	private void updateCustomer() {
		System.out.print("수정할 고객 ID: ");
		int customerId = sc.nextInt();
		sc.nextLine();
		System.out.print("새 이름: ");
		String name = sc.nextLine();
		System.out.print("새 전화번호: ");
		String phone = sc.nextLine();
		System.out.print("새 이메일: ");
		String email = sc.nextLine();
		System.out.print("새 주소: ");
		String address = sc.nextLine();

		Customer customer = new Customer(customerId, name, phone, email, address, null);
		dao.updateCustomer(customer);
	}

	// 고객 삭제
	private void deleteCustomer() {
		System.out.print("삭제할 고객 ID: ");
		int customerId = sc.nextInt();
		dao.deleteCustomer(customerId);
	}
}
