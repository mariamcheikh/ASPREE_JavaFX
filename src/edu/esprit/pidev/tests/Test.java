package edu.esprit.pidev.tests;


import edu.esprit.pidev.technique.DataSource;
import edu.esprit.pidev.models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mehdi
 */
public class Test {

    public static void main(String[] args) throws SQLException {

        User user = new User("khaled", "khaled@esprit.tn");
        Connection conn = DataSource.getInstance().getConnection();
      /*  String req = "insert into user (name,email) values ('"+user.getName()+"','"+user.getEmail()+"')";
        Statement st = conn.createStatement();
        st.executeUpdate(req);*/
        
        String req = "insert into user(name, email) values (?,?)";
        PreparedStatement  ps = conn.prepareStatement(req);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.executeUpdate();
    }
}
