package com.assignment1.retailstore.bean;

import java.util.List;

public class InvoiceDetails {
	
	private Long InvoiceId;
	
	private List<ProductDetailsForInvoice> addedProducts;
	
	private List<ProductDetailsForInvoice> removedProducts;
	
	public Long getInvoiceId() {
		return InvoiceId;
	}
	public void setInvoiceId(Long InvoiceId) {
		this.InvoiceId = InvoiceId;
	}
	public InvoiceDetails() {
		super();
	}
	public List<ProductDetailsForInvoice> getAddedProducts() {
		return addedProducts;
	}
	public void setAddedProducts(List<ProductDetailsForInvoice> addedProducts) {
		this.addedProducts = addedProducts;
	}
	public List<ProductDetailsForInvoice> getRemovedProducts() {
		return removedProducts;
	}
	public void setRemovedProducts(List<ProductDetailsForInvoice> removedProducts) {
		this.removedProducts = removedProducts;
	}

}
