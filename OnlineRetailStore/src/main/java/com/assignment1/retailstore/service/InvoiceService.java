package com.assignment1.retailstore.service;

import com.assignment1.retailstore.bean.InvoiceDetails;
import com.assignment1.retailstore.entity.Invoice;

public interface InvoiceService {

	public Iterable<Invoice> getAllInvoices();
	
	public Invoice getInvoiceById(Long id);
	
	public Invoice createInvoice(InvoiceDetails invoiceDetails);

}
