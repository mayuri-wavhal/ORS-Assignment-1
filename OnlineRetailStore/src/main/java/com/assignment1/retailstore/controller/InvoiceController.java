package com.assignment1.retailstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment1.retailstore.bean.InvoiceDetails;
import com.assignment1.retailstore.entity.Invoice;
import com.assignment1.retailstore.service.InvoiceService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@ApiOperation(value = "Get all invoices.")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Invoice>> getAllInvoices() {
		logger.info("InvoiceController :: Entered in getAllInvoices");
		return new ResponseEntity<>(invoiceService.getAllInvoices(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get an invoice details")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
		logger.info("InvoiceController :: Entered in getInvoiceById");
		return new ResponseEntity<>(invoiceService.getInvoiceById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Creates an invoice")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceDetails invoiceDetails) {
		logger.info("InvoiceController :: Entered in createInvoice");
		Invoice invoice = invoiceService.createInvoice(invoiceDetails);
		logger.info("InvoiceController :: Exit from createInvoice");
		return new ResponseEntity<>(invoice, HttpStatus.CREATED);
	}
	
}
