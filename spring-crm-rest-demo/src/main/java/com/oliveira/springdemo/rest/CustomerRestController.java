package com.oliveira.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.springdemo.entity.Customer;
import com.oliveira.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController
{

	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;

	// add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers()
	{
		return customerService.getCustomers();
	}

	// add mapping for GET /customers/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId)
	{
		Customer theCustomer = customerService.getCustomer(customerId);

		if (theCustomer == null)
		{
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}

		return theCustomer;
	}

	// add mapping for POST /customers -- add new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer)
	{
		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update
		theCustomer.setId(0); // add 0 make DAO insert a new item instead update an existent one

		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	// add mapping for PUT /customers -- update an existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer)
	{

		// because id is not null or 0, it means that is an update
		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	// add mapping for DELETE /customers/{customerId} -- delete customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId)
	{
		Customer tempCustomer = customerService.getCustomer(customerId);

		if (tempCustomer == null)
		{
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}

		customerService.deleteCustomer(customerId);

		return "Deleted customer id - " + customerId;

	}

}
