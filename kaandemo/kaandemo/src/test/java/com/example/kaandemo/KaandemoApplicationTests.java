package com.example.kaandemo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)
class TestingWebApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CustomerRepository customerRepository;
	
	@Test
	void shouldReturnCorrectCustomer() throws Exception {
		Customer RECORD_1 = new Customer("Kaan", "Kaynar");
		RECORD_1.setId("iddd11");//id can be set to any value
		Mockito.when(customerRepository.findById(RECORD_1.getId())).thenReturn(Optional.of(RECORD_1));

		this.mockMvc.perform(get("/customers/" + RECORD_1.getId())).andExpect(status().isOk())
		.andExpect(jsonPath("$", notNullValue()))
		.andExpect(jsonPath("$.firstName", is("Kaan")))
		.andExpect(jsonPath("$.lastName", is("Kaynar")));
	}

	@Test
	public void shouldReturnExceptionGetCustomer() throws Exception {
    	Mockito.when(customerRepository.findById("51")).thenReturn(Optional.empty());

    	mockMvc.perform(get("/customers/51")).andExpect(status().isNotFound())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomerNotFoundException));

	}
}