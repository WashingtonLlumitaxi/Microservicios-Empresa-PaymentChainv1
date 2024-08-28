/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.product.controller;

import com.paymentchain.product.business.transactions.BussinnesTransaction;
import com.paymentchain.product.entities.Product;
import com.paymentchain.product.exception.BussinesRuleException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.paymentchain.product.respository.ProductRepository;
import java.net.UnknownHostException;
import java.util.Optional;
import org.springframework.http.HttpStatus;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/product/V1")
public class ProductRestController {

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    BussinnesTransaction bt;

    @GetMapping()
    public ResponseEntity<List<Product>> list() {
        List<Product> findAllProd = productRepository.findAll();
        if(findAllProd.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAllProd);
        }
    }

    /*
    @GetMapping("/{id}")
    public Product get(@PathVariable(name = "id") long id) {
        return productRepository.findById(id).get();
    }*/
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> get(@PathVariable(name = "id") long id) {
         Optional<Product> findById = productRepository.findById(id);
        if(findById.isPresent()){
            return ResponseEntity.ok(findById);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") long id, @RequestBody Product input) {
        Product find = productRepository.findById(id).get();
        if (find != null) {
            find.setCode(input.getCode());
            find.setName(input.getName());
        }
        Product save = productRepository.save(find);
        return ResponseEntity.ok(save);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Product input) throws BussinesRuleException {
        //Product save = productRepository.save(input);
        //return ResponseEntity.ok(save);
        Product post = bt.post(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) throws BussinesRuleException {
        /*Optional<Product> findById = productRepository.findById(id);
        if (findById.get() != null) {
            productRepository.delete(findById.get());
        }
        return ResponseEntity.ok().build();*/
        
        bt.delete(id);
        return ResponseEntity.ok().build();
        
    }

}
