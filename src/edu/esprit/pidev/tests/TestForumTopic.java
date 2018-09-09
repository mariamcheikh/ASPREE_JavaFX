/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.df_topic;
import edu.esprit.pidev.services.impl.ForumTopicService;
import edu.esprit.pidev.services.interfaces.IService;

/**
 *
 * @author Jean
 */
public class TestForumTopic {
     public static void main(String[] args) {
     
         df_topic t = new df_topic(3,1,"medecine","mm");
       df_topic ttt = new df_topic(1,1,"morale","modi");
     
      IService tt = new ForumTopicService();
  
   //tt.add(t);
   //tt.update(ttt);
   //tt.delete(12);
    tt.getAll();
  
     }
    
}
