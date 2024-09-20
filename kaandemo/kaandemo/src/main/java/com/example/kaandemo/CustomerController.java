package com.example.kaandemo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController {

  private final CustomerRepository repository;

  CustomerController(CustomerRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/customers")
  List<Customer> all() {
    return repository.findAll();
  }

  @GetMapping("/customers/{id}")
  Customer one(@PathVariable String id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new CustomerNotFoundException(id));
  }

  @GetMapping("/customers/lastname/{name}")
  List<Customer> byLastName(@PathVariable String name) {
    
    return repository.findByLastName(name);
  }

  @GetMapping("/customers/firstname/{name}")
  List<Customer> byFirstName(@PathVariable String name) {
    
    return repository.findByFirstName(name);
  }

  @PostMapping("/customers")
  Customer newCustomer(@RequestBody Customer newCustomer) {
    return repository.save(newCustomer);
  }

  @PutMapping("/customers/{id}")
  Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable String id) {
    
    return repository.findById(id)
      .map(customer -> {
        customer.setFirstName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());
        return repository.save(customer);
      })
      .orElseGet(() -> {
        return repository.save(newCustomer);
      });
  }

  @DeleteMapping("/customers/{id}")
  void deleteEmployee(@PathVariable String id) {
    repository.deleteById(id);
  }
}