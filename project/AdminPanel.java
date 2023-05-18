package project;

import java.util.Scanner;

public class AdminPanel {
	Queue<Credentials> credentials;

	public AdminPanel() {
		credentials = new Queue<Credentials>();
		credentials.enqueue(new Credentials("Mo", "0000"));
		credentials.enqueue(new Credentials("tr", "712"));
		credentials.enqueue(new Credentials("nada", "1111"));
	}

	public void displayAuthentication(Scanner scan) {
		boolean found = false;
		while (!found) {
			System.out.println("Admin Username: ");
			String username = scan.next();
			System.out.println("Admin Pin: ");
			String password = scan.next();
			for (int i = 0; i < credentials.getSize(); i++) {
				if (username.equals(credentials.peek().username) && password.equals(credentials.peek().password)) {
					System.out.println("Welcome " + username);
					found = true;
					break;
				}
				credentials.enqueue(credentials.dequeue());
			}
			if (!found)
				System.out.println("Credentials invalid, please try again!");
		}

	}

	public void displayOptions(Scanner scan, Store store) {

		System.out.println("1...Add New Item");
		System.out.println("2...Remove Existing Item");
		System.out.println("3...Orders List");
		System.out.println("4...Log Out");

		while (true) {
			System.out.println("Select a Number:");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					addNewItem(scan, store);
					return;
				case 2:
					removeItem(scan, store);
					return;
				case 3:
					displayReceipts();
					return;
				case 4:
					System.out.println("Successfully logged out.");
					return;
				default:
					System.out.println("Please select a valid number.");
			}
		}
	}

	private void addNewItem(Scanner scan, Store store) {
		boolean present;
		present = false;
		System.out.println("1....Name of item you want to add: ");
		scan.nextLine();
		String name = scan.nextLine();
		System.out.println("2....Price of item you want to add: ");
		float price = scan.nextFloat();
		System.out.println("3....Number of item you want to add: ");
		int id = scan.nextInt();

		for (Item item : store.getItems()) {
			if (item.getId() == id)
				present = true;
		}
		while (present) {
			present = false;
			System.out.println("This number already exists for another item, please choose another number:");
			id = scan.nextInt();
			for (Item item : store.getItems()) {
				if (item.getId() == id)
					present = true;
			}
		}
		store.getItems().add(new Item(id, name, price));
		System.out.println("--------------NEW ITEM ADDED SUCCESSFULLY--------------");

	}

	private void removeItem(Scanner scan, Store store) {
		boolean present;
		present = false;
		Item toRemove = new Item(0, null, 0);
		System.out.println("Enter the number of item you want to remove: ");
		int id = scan.nextInt();

		for (Item item : store.getItems()) {
			if (item.getId() == id) {
				present = true;
				toRemove = item;
				break;
			}
		}
		while (!present) {
			present = false;
			System.out.println("This item does not exist, please enter an existing item number:");
			id = scan.nextInt();
			for (Item item : store.getItems()) {
				if (item.getId() == id) {
					toRemove = item;
					present = true;
					break;
				}
			}
		}
		store.getItems().remove(toRemove);
		System.out.println("--------------ITEM REMOVED SUCCESSFULLY--------------");

	}

	private void displayReceipts() {
		for (Receipt receipt : Store.getReceipts()) {
			System.out.println("--------------------------------------------");
			System.out.println("-----------------DOG RECEIPT----------------");
			System.out.println("--------------------------------------------");
			System.out.println("Customer Name: " + receipt.getFullName());
			float total = 0;
			for (Item item : receipt.getOrders()) {
				total += item.getPrice();
				System.out.printf("%-35s %.2f%n", item.getName(), item.getPrice());
			}
			System.out.println("--------------------------------------------");
			System.out.println("Subtotal: " + total + "$");
			System.out.println("--------------------------------------------");
			System.out.println("--------------------------------------------");
			System.out.println();
		}
	}
}
