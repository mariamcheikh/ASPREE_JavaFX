/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.services.interfaces.IUserService;

/**
 *
 * @author Mehdi
 */
public class TestUserService {

    public static void main(String[] args) {
        User user = new User("Ahmed", "ahmed@gmail.com");
        IUserService userService = new UserService();
        userService.add(user);
    }
}
