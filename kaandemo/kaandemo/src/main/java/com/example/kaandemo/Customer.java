package com.example.kaandemo;

import java.util.*;

import org.springframework.data.annotation.Id;


public class Customer {

  @Id
  public String id;

  public String firstName;
  public String lastName;

  public Customer() {}

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Customer))
      return false;
    Customer customer = (Customer) o;
    return Objects.equals(this.id, customer.id) && Objects.equals(this.firstName, customer.firstName)
        && Objects.equals(this.lastName, customer.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.firstName, this.lastName);
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id='%s', firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

}