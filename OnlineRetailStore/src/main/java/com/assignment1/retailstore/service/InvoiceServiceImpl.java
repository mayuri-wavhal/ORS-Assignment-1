package com.assignment1.retailstore.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.assignment1.retailstore.bean.InvoiceDetails;
import com.assignment1.retailstore.bean.ProductDetailsForInvoice;
import com.assignment1.retailstore.entity.Invoice;
import com.assignment1.retailstore.entity.InvoiceItem;
import com.assignment1.retailstore.entity.Product;
import com.assignment1.retailstore.exception.CustomNotFoundException;
import com.assignment1.retailstore.repository.InvoiceRepository;
import com.assignment1.retailstore.repository.ProductRepository;
import com.assignment1.retailstore.util.Category;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private ProductRepository productRepository;

	final Logger logger = LoggerFactory.getLogger(getClass());

	public Iterable<Invoice> getAllInvoices() {
		logger.info("Returning all invoices");
		return invoiceRepository.findAll();
	}

	public Invoice getInvoiceById(Long id) {
		logger.info("Returning an invoice by id");
		return invoiceRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Invoice not found!"));
	}

	@Override
	public Invoice createInvoice(InvoiceDetails invoiceDetails) {
		logger.info("Creating a new invoice");
		if (null == invoiceDetails || CollectionUtils.isEmpty(invoiceDetails.getAddedProducts())) {
			throw new CustomNotFoundException("No information to create new invoice.");
		}
		List<ProductDetailsForInvoice> products = invoiceDetails.getAddedProducts();
		Invoice invoice = new Invoice();
		List<InvoiceItem> items = new ArrayList<>();
		for (ProductDetailsForInvoice p : products) {
			Product selectedProduct = isProductExists(p.getBarCode());
			InvoiceItem item = new InvoiceItem(selectedProduct, p.getQuantity());
			items.add(item);
		}
		invoice.setItems(items);
		invoice.setDate(new Date());
		calculateTotalAmount(invoice);
		invoice = invoiceRepository.save(invoice);
		logger.info("Successfully created an invoice" + invoice.getId());
		return invoice;
	}

	private Invoice calculateTotalAmount(Invoice invoice) {
		logger.info("Calculating total amount");
		double totalCost = 0.0;
		double totalAmount = 0.0;
		if (!CollectionUtils.isEmpty(invoice.getItems())) {
			List<InvoiceItem> items = invoice.getItems();
			for (InvoiceItem item : items) {
				double saleValue = calculateValueWithSalesTaxForItem(item);
				totalCost += item.getQuantity() * item.getProduct().getPrice();
				totalAmount += saleValue;
			}
		}
		invoice.setTotalCost(totalCost);
		invoice.setTotalAmount(totalAmount);
		invoice.setTotalSalesTax(totalAmount - totalCost);
		return invoice;
	}

	private double calculateValueWithSalesTaxForItem(InvoiceItem item) {
		logger.info("Calculating sale value for each item");
		long quantity = item.getQuantity();
		Category category = item.getProduct().getCategory();
		double price = item.getProduct().getPrice();
		double saleValue = 0;
		switch (category) {
		case A:
			saleValue = quantity * price * 1.1;
			break;
		case B:
			saleValue = quantity * price * 1.2;
			break;
		case C:
			saleValue = quantity * price;
			break;
		default:
			break;
		}
		return saleValue;
	}

	private Product isProductExists(String barCode) {
		List<Product> products = productRepository.findByBarCode(barCode);
		if (CollectionUtils.isEmpty(products)) {
			logger.info("BarCode does not exist!");
			throw new CustomNotFoundException("BarCode does not exist!");
		}
		return products.get(0);
	}

}
