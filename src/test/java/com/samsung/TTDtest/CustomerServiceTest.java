package com.samsung.TTDtest;

import com.samsung.TTDtest.repositories.CustomerRepository;
import com.samsung.TTDtest.repositories.model.Customer;
import com.samsung.TTDtest.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {
    @InjectMocks
    CustomerService service;

    @Mock
    CustomerRepository repository;

    @Test
    public void should_return_error_when_email_is_already_existed() {
        Customer fakeCustomer = new Customer("Nguyễn Trọng Hùng", "C062", "Hung.bn@gmail.com");
        when(repository.getCustomerByEmail(fakeCustomer.Email)).thenReturn(fakeCustomer);

        String expected = "Email is already existed";
        String result = service.createCustomer(fakeCustomer);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void should_return_error_when_email_is_invalid() {
        Customer fakeCustomer = new Customer("Hung trong", "C062", "hung.bn@gmail.com");

        fakeCustomer.setEmail("tuananh1612");

        String expected = "Email is invalid";
        String result = service.createCustomer(fakeCustomer);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void should_return_successful_message_when_successful_create() {
        Customer fakeCustomer = new Customer("Trong Hung", "C062", "Tronghung.bn@gmail.com");
        when(repository.getCustomerByEmail(fakeCustomer.Email)).thenReturn(fakeCustomer);
        fakeCustomer.setEmail("hung@gmail.com");
        when(repository.createcustomer(fakeCustomer)).thenReturn(true);

        String expected = "Created successfully";
        String result = service.createCustomer(fakeCustomer);

        assertThat(result).isEqualTo(expected);
    }
}