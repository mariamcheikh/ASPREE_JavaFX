/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.df_forum;
import edu.esprit.pidev.services.impl.ForumForumService;
import edu.esprit.pidev.services.interfaces.IService;

/**
 *
 * @author Jean
 */
public class TestForumForum {
     public static void main(String[] args) {
     
      df_forum f = new df_forum(3,"hajra","medecine","haa");
      df_forum fff = new df_forum(1,"hajra","test","test");
     
      IService ff = new ForumForumService();
  
   //ff.add(f);
   //ff.update(fff);
   //ff.delete(60);
    
    ff.getAll();
  
     }
}
