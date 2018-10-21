package com.assignment1.retailstore;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.assignment1.retailstore.bean.InvoiceDetails;
import com.assignment1.retailstore.bean.ProductDetails;
import com.assignment1.retailstore.bean.ProductDetailsForInvoice;
import com.assignment1.retailstore.entity.Invoice;
import com.assignment1.retailstore.service.InvoiceService;
import com.assignment1.retailstore.service.ProductService;
import com.assignment1.retailstore.util.Category;

@Component
public class OnlineRetailStoreRunner implements CommandLineRunner {

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private ProductService productService;

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void run(String... arg0) throws Exception {
		addProductsInInventory();
		createInvoice();
	}

	public void createInvoice() {
		InvoiceDetails invoiceDetails = new InvoiceDetails();
		//Add new products in cart
		List<ProductDetailsForInvoice> productsList1 = new ArrayList<>(); 
		productsList1.add(new ProductDetailsForInvoice("1", 1));
		productsList1.add(new ProductDetailsForInvoice("3", 1));
		productsList1.add(new ProductDetailsForInvoice("4", 1));
		productsList1.add(new ProductDetailsForInvoice("7", 1));
		invoiceDetails.setAddedProducts(productsList1);
		Invoice invoice = invoiceService.createInvoice(invoiceDetails);
		logger.info("Invoice is created with id -  " + invoice.getId());
		logger.info("Total amount = " + invoice.getTotalAmount());
		logger.info("Total cost = " + invoice.getTotalCost());
		logger.info("Total sales tax = " + invoice.getTotalSalesTax());
	}

	private void addProductsInInventory() {
		productService.createProduct(new ProductDetails("1", 100.0, "Mango", Category.A));
		productService.createProduct(new ProductDetails("2", 120.0, "Apple", Category.A));
		productService.createProduct(new ProductDetails("3", 80.0, "Strawberry", Category.A));
		productService.createProduct(new ProductDetails("4", 90.0, "Kiwi", Category.B));
		productService.createProduct(new ProductDetails("5", 40.0, "Cherry", Category.B));
		productService.createProduct(new ProductDetails("6", 60.0, "Fig", Category.C));
		productService.createProduct(new ProductDetails("7", 80.0, "Orange", Category.C));
	}
}
