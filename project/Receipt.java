package project;

import java.util.ArrayList;

public class Receipt {
	private String fullName;
	private ArrayList<Item> orders;

	public ArrayList<Item> getOrders() {
		return orders;
	}

	public Receipt(String fullName) {
		this.fullName = fullName;
		orders = new ArrayList<>();
	}

	public String getFullName() {
		return fullName;
	}
}