/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.product.business.transactions;

import com.paymentchain.product.entities.Product;
import com.paymentchain.product.exception.BussinesRuleException;
import com.paymentchain.product.respository.ProductRepository;
import java.net.UnknownHostException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 *
 * @author DELL
 */
@Service
public class BussinnesTransaction {

    @Autowired
    ProductRepository productRepository;

    //Method post
    public Product post(Product input) throws BussinesRuleException {
        // Verificar si el objeto 'input' es nulo
        //if (input == null) {
        //   throw new BussinesRuleException("1024", "El objeto input no puede ser null", HttpStatus.PRECONDITION_FAILED);
        //}

        // Validar si 'code' o 'name' son nulos
        if (input.getCode() == null || input.getName() == null || "".equals(input.getCode()) || "".equals(input.getName())) {
            BussinesRuleException bussinesRuleException = new BussinesRuleException("1025", "Los valores code y name son requisito", HttpStatus.PRECONDITION_FAILED);
            throw bussinesRuleException;
        }

        // Guardar el producto si todas las validaciones han pasado
        Product save = productRepository.save(input);
        return save;
    }

    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) throws BussinesRuleException {

        Optional<Product> findById = productRepository.findById(id);
        if (!findById.isPresent()) {
            BussinesRuleException bussinesRuleException = new BussinesRuleException("1025", "Error de validación, product con id " + id + " no existe", HttpStatus.PRECONDITION_FAILED);
            throw bussinesRuleException;

        }
        productRepository.delete(findById.get());
        return ResponseEntity.ok().build();

    }
}
