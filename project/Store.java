package project;

import java.util.ArrayList;
import java.util.Scanner;

public class Store {
	private ArrayList<Item> items;
	private static ArrayList<Receipt> receipts;

	private Stack<Item> orders;

	public ArrayList<Item> getItems() {
		return items;
	}

	public static ArrayList<Receipt> getReceipts() {
		return receipts;
	}

	public Store() {
		items = new ArrayList<>();
		receipts = new ArrayList<Receipt>();
		items.add(new Item(1, "French Bulldog (2 yrs)", 2699));
		items.add(new Item(2, "Golden Retriever(5 mnths)", 1049));
		items.add(new Item(3, "Chihuauah(1 yr)", 999));
		items.add(new Item(4, "Pug(5 yrs)", 299));
		items.add(new Item(5, "20KG EnergyPet", 40));
		items.add(new Item(6, "7KG HappyDog", 22));
		items.add(new Item(7, "1KG Royale Canine", 6));
	}

	public void displayOptions(Scanner scan) {

		System.out.println("1...Available Dogs/Products");
		System.out.println("2...Buy");

		while (true) {
			System.out.println("Select a Number:");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					displayItems();
					displayOptionsAvailableItems(scan);
					return;
				case 2:
					displayOptionsBuyDog(scan);
					generateReceipt(scan);
					return;

				default:
					System.out.println("Pick a valid option please!");
			}
		}
	}

	public void displayOptionsAvailableItems(Scanner scan) {
		System.out.println("1...Back");
		System.out.println("2...Main Menu");
		while (true) {
			System.out.println("Select a Number:");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					displayOptions(scan);
					return;

				case 2:
					return;

				default:
					System.out.println("Pick a valid option please!");
			}
		}
	}

	public void displayOptionsBuyDog(Scanner scan) {
		orders = new Stack<Item>();
		boolean found = false;
		boolean another = true;
		while (!found || another) {
			System.out.println("Please Enter the Number of the Item you want to buy:");
			int itemId = scan.nextInt();
			for (Item item : items) {
				if (item.getId() == itemId) {
					orders.push(item);
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println("Item not found, Please try another ID!");
			} else {
				another = displayOptionsChooseNewItem(scan);
			}
		}
	}

	public boolean displayOptionsChooseNewItem(Scanner scan) {
		System.out.println("Would you like to buy another item?");
		System.out.println("1...Yes");
		System.out.println("2...No");

		while (true) {
			System.out.println("Select a Number:");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					return true;

				case 2:
					return false;

				default:
					System.out.println("Pick a valid option please!");
			}
		}
	}

	public void generateReceipt(Scanner scan) {
		System.out.println("Please Enter Your First and Last Name: ");
		String fname = scan.next();
		String lname = scan.next();

		Receipt receipt = new Receipt(fname + " " + lname);
		System.out.println("--------------------------------------------");
		System.out.println("-----------------DOG RECEIPT----------------");
		System.out.println("--------------------------------------------");
		System.out.println("Customer Name: " + fname + " " + lname);
		float total = 0;
		while (!orders.isEmpty()) {
			Item item = orders.pop();
			receipt.getOrders().add(item);
			total += item.getPrice();
			System.out.printf("%-35s %.2f%n", item.getName(), item.getPrice());
		}
		System.out.println("--------------------------------------------");
		System.out.println("Subtotal: " + total + "$");
		System.out.println("--------------------------------------------");
		System.out.println("--------------------------------------------");
		System.out.println();
		receipts.add(receipt);
	}

	public void displayItems() {
		System.out.printf("%-10s %-35s %-10s%n", "Number", "Item", "Price");
		for (Item item : items) {
			System.out.printf("%-10d %-35s %.2f%n", item.getId(), item.getName(), item.getPrice());
		}
	}
}
