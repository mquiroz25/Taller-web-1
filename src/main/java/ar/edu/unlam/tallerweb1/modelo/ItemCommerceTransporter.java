package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

public class ItemCommerceTransporter {

	private static List<ItemCommerce> itemscommerces;

	public static List<ItemCommerce> getItemsCommerces() {
		return itemscommerces;
	}

	public static void setItemsCommerces(List<ItemCommerce> itemscommerces) {
		ItemCommerceTransporter.itemscommerces = itemscommerces;
	}
}