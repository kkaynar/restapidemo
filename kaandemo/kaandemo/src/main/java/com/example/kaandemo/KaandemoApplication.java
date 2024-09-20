package com.example.kaandemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KaandemoApplication implements CommandLineRunner {

  	@Autowired
  	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(KaandemoApplication.class, args);
	}

	@Override
  	public void run(String... args) throws Exception {

    		repository.deleteAll();

    		repository.save(new Customer("Kaan", "Kaynar"));
    		repository.save(new Customer("Hasan", "Saydam"));
		repository.save(new Customer("Kaan", "Saydam"));

    		System.out.println("Customers found with findAll():");
    		System.out.println("-------------------------------");
    		for (Customer customer : repository.findAll()) {
      			System.out.println(customer);
    		}
    		System.out.println();

    		System.out.println("Customer found with findByFirstName('Kaan'):");
    		System.out.println("--------------------------------");
		for (Customer customer : repository.findByFirstName("Kaan")) {
      			System.out.println(customer);
    		}
    		System.out.println();

    		System.out.println("Customers found with findByLastName('Kaynar'):");
    		System.out.println("--------------------------------");
    		for (Customer customer : repository.findByLastName("Kaynar")) {
      			System.out.println(customer);
    		}
	}
}
