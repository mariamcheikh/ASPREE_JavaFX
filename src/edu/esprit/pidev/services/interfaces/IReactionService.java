/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.interfaces;

import edu.esprit.pidev.models.Publication;
import edu.esprit.pidev.models.Reaction;
import edu.esprit.pidev.models.User;

/**
 *
 * @author aymen
 */
public interface IReactionService extends IService<Reaction, Integer>  {
    

    public void updateComment(Reaction r,String contenu);



}
