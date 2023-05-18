package project;

import java.util.Scanner;

public class DogShop {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		Store store = new Store();
		AdminPanel adminPanel = new AdminPanel();
		while (loop) {
			System.out.println("1...Store");
			System.out.println("2...Admin Panel");
			System.out.println("3...Exit");
			System.out.println("Select a Number:");

			int option = scan.nextInt();

			switch (option) {
			case 1:
				store.displayOptions(scan);
				break;

			case 2:
				adminPanel.displayAuthentication(scan);
				adminPanel.displayOptions(scan, store);
				break;

			case 3:
				System.out.println("Thank you for shopping at our Dog Shop!");
				System.out.println("See you again soon.");
				loop = false;
				break;
			default:
				System.out.println("Pick a valid option please!");
			}
		}

		scan.close();

	}
}
