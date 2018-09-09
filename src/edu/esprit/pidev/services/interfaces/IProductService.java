/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.interfaces;

import edu.esprit.pidev.models.Product;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public interface IProductService {

    void add(Product product);

    void update(Product product);

    void delete(int id);

    List<Product> getAll();
}
