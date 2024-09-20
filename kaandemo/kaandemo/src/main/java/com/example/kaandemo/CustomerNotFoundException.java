package com.example.kaandemo;

class CustomerNotFoundException extends RuntimeException {

  CustomerNotFoundException(String id) {
    super("Could not find employee " + id);
  }
}