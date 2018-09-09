/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.Publication;
import edu.esprit.pidev.models.Reaction;
import edu.esprit.pidev.services.impl.PublicationService;
import edu.esprit.pidev.services.impl.ReactionService;
import edu.esprit.pidev.services.impl.UserService;
import java.util.Calendar;
import java.sql.SQLException;
/**
 *
 * @author aymen
 */
public class testGPublication {
    
   public static void main(String[] args) throws SQLException {
     PublicationService P=new PublicationService();

     
        
        System.out.println(P.getAll());
       
    }
}
