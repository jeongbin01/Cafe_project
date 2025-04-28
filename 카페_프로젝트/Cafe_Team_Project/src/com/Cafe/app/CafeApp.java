package com.Cafe.app;

import java.util.Scanner;

import com.Cafe.Controller.CoffeeBeansController;
import com.Cafe.Controller.CustomerController;
import com.Cafe.Controller.DeliveryController;
import com.Cafe.Controller.OrdersController;
import com.Cafe.Controller.ProductController;

public class CafeApp {

	// Scanner와 Controller 객체들을 클래스 레벨에서 선언
	private static final Scanner scanner = new Scanner(System.in);
	private static final CustomerController customer = new CustomerController();
	private static final OrdersController orders = new OrdersController();
	private static final CoffeeBeansController coffeeBeans = new CoffeeBeansController();
	private static final ProductController product = new ProductController();
	private static final DeliveryController deliveryController = new DeliveryController();

	public static void main(String[] args) {
		// 무한 루프 시작
		while (true) {
			printMainMenu();
			int choice = readInt("선택 ▶ ");
			switch (choice) {
			case 1:
				// 고객 관리
				customer.run();
				break;
			case 2:
				// 주문 관리
				orders.run();
				break;
			case 3:
				// 원두 관리
				coffeeBeans.run();
				break;
			case 4:
				// 제품 관리
				product.run();
				break;
			case 5:
				// 배송 관리
				deliveryController.run();
				break;
			case 0:
				// 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				scanner.close();
				return; // 종료
			default:
				System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
				break;
			}
		}
	}

	// 메인 메뉴 출력
	private static void printMainMenu() {
		System.out.println("\n=== 카페 관리 시스템 ===");
		System.out.println("1. 고객 관리");
		System.out.println("2. 주문 관리");
		System.out.println("3. 원두 관리");
		System.out.println("4. 제품 관리");
		System.out.println("5. 배송 관리");
		System.out.println("0. 종료");
	}

	// 사용자로부터 정수 입력 받기
	private static int readInt(String prompt) {
		int input = 0;
		boolean valid = false;
		while (!valid) {
			System.out.print(prompt);
			try {
				input = Integer.parseInt(scanner.nextLine());
				valid = true;
			} catch (NumberFormatException e) {
				System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
			}
		}
		return input;
	}
}
