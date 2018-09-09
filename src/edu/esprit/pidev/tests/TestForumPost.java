/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.df_post;
import edu.esprit.pidev.services.impl.ForumPostService;
import edu.esprit.pidev.services.interfaces.IService;

/**
 *
 * @author Jean
 */
public class TestForumPost {
     public static void main(String[] args) {
     
      df_post p = new df_post(3,2,"haa");
      df_post ppp = new df_post(4,2,"test");
     
      IService pp = new ForumPostService();
  
  // pp.add(p);
  //pp.update(ppp);
   //pp.delete(15);
    
    pp.getAll();
  
     }
}
