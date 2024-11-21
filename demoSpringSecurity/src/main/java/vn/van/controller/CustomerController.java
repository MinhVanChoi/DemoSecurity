package vn.van.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.van.entity.Customer;

@RestController
public class CustomerController {

    // Initialize a list of customers (You might load this from a database in a real app)
    final private List<Customer> customers = List.of(
        Customer.builder().id("001").name("Van").email("van123@gmail.com").build(),
        Customer.builder().id("002").name("Kiet").email("kiet456@gmail.com").build()
    );
    
    

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello is Guest");
    }

    // Endpoint to get all customers (accessible by users with 'ROLE_ADMIN')
    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(this.customers);
    }

    // Endpoint to get a specific customer by ID (accessible by users with 'ROLE_USER')
    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        Optional<Customer> customer = this.customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst(); // Returns the first match or empty if not found

        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            // Return 404 if the customer is not found
            return ResponseEntity.notFound().build();
        }
    }
}
