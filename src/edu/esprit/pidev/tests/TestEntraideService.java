/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.Entraide;
import edu.esprit.pidev.models.Product;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.EntraideService;
import edu.esprit.pidev.services.impl.ProductService;
import edu.esprit.pidev.services.interfaces.IService;
import java.sql.Date;


/**
 *
 * @author Noor
 */
public class TestEntraideService {

    public static void main(String[] args) {
        Date d;
        d = new Date(2017,03,12);

        Entraide entraide = new Entraide("java8", "helpp",d, "mmm");
        IService entraideService = new EntraideService();
        //entraideService.add(entraide);
        //entraideService.delete(12);
//        entraideService.update(entraide);
        //System.out.println(entraideService.findById(16));
        entraideService.getAll().forEach(System.out::println);
    }
}
