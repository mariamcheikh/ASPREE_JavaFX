/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.interfaces;

import edu.esprit.pidev.models.User;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public interface IUserService extends IService<User, Integer> {

    User authentication(String login, String password);
}
