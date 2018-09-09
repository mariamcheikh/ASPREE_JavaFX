/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.Amicale;
import edu.esprit.pidev.services.impl.AmicaleService;
import edu.esprit.pidev.services.interfaces.IService;

/**
 *
 * @author Jean
 */
public class TestAmicaleService {
        public static void main(String[] args) {
        Amicale a = new Amicale("nooor", "nouha",555,"foyer",1,"",1);
        Amicale aa = new Amicale("ssss", "ffff", 555, "foyer",2,"",1);
        IService ga = new AmicaleService();
    IService hh =new AmicaleService();
   hh.add(a);
   hh.delete(4);
    hh.update(aa);
    hh.getAll();
    
    
        }
    
}
