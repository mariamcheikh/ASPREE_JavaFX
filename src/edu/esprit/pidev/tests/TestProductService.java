/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.Product;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.ProductService;
import edu.esprit.pidev.services.interfaces.IProductService;
import edu.esprit.pidev.services.interfaces.IService;

/**
 *
 * @author Mehdi
 */
public class TestProductService {
    
    public static void main(String[] args) {
        Product product = new Product("PC", new User(2));
        IService productService = new ProductService();
      // productService.add(product);
        productService.getAll().forEach(System.out::println);
    }
}
