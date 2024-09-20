package com.example.kaandemo;

import java.util.*;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Optional<Customer> findById(String id);	
  	public List<Customer> findByFirstName(String firstName);
  	public List<Customer> findByLastName(String lastName);

}