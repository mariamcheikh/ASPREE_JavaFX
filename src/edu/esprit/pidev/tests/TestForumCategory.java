/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.Df_category;
import edu.esprit.pidev.services.impl.ForumCategorieSercice;
import edu.esprit.pidev.services.interfaces.IService;

/**
 *
 * @author Jean
 */
public class TestForumCategory {
     public static void main(String[] args) {
     
      Df_category c = new Df_category("Nature");
      Df_category cc = new Df_category("Faune&Flore");
      IService ga = new ForumCategorieSercice();
  
   ga.add(c);
   //ga.update(cc);
        //ga.delete(6);
      ga.getAll();
  
     }
    
}
