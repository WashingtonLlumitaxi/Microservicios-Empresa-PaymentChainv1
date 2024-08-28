/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.customer.controller;

import com.paymentchain.customer.business.transactions.BussinesTransaction;
import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.exception.BussinesRuleException;
import com.paymentchain.customer.respository.CustomerRepository;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/customer/V1")
public class CustomerRestController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BussinesTransaction bt;

    //Infraestructure
    // @Value("${custom.activeprofile}")
    // private String profile;
    @Autowired
    private Environment env;

    @GetMapping("/check") //check the rol (local, develop, pruduction)
    public String check() {
        return "Hello your proerty value is: " + env.getProperty("custom.activeprofileName");
    }

    /*@GetMapping()
    public List<Customer> list() {
        return customerRepository.findAll();
    }*/
    @GetMapping
    public ResponseEntity<List<Customer>> list() {
        List<Customer> findAll = customerRepository.findAll();
        if (findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") long id) {
        Optional<Customer> findById = customerRepository.findById(id);
        if (findById.isPresent()) {
            return ResponseEntity.ok(findById);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") long id, @RequestBody Customer input) {
        Customer find = customerRepository.findById(id).get();
        if (find != null) {
            find.setCode(input.getCode());
            find.setName(input.getName());
            find.setIban(input.getIban());
            find.setPhone(input.getPhone());
            find.setSurname(input.getSurname());
        }
        Customer save = customerRepository.save(find);
        return ResponseEntity.ok(save);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Customer input) throws BussinesRuleException, UnknownHostException {
        /*input.getProducts().forEach(x -> x.setCustomer(input));
        Customer save = customerRepository.save(input);
        //return ResponseEntity.ok(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);*/
        Customer post = bt.post(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        Optional<Customer> findById = customerRepository.findById(id);
        if (findById.get() != null) {
            customerRepository.delete(findById.get());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/full")
    public Customer getByCode(@RequestParam(name = "code") String code) {
        Customer customer = bt.getByCode(code);
        return customer;
    }

}
