/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.Don;
import edu.esprit.pidev.models.Entraide;
import edu.esprit.pidev.services.impl.DonService;
import edu.esprit.pidev.services.impl.EntraideService;
import edu.esprit.pidev.services.interfaces.IService;
import java.sql.Date;

/**
 *
 * @author Noor
 */
public class TestDon {
    public static void main(String[] args) {
//        Date d = new Date(2017,03,12);
//
//        Don don = new Don(1200f,"ee",d,1,1);
        IService donService = new DonService();
//        donService.add(don);
        donService.getAll().forEach(System.out::println);
    }
}
