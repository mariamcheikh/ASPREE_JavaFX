/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Entraide;
import edu.esprit.pidev.models.Product;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noor
 */
public class EntraideService implements IService<Entraide, Integer> {

    private Connection connection;

    public EntraideService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Entraide entraide) {
        try {
            String req = "insert into entraide(description, nom, date, image) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, entraide.getDescription());
            ps.setString(2, entraide.getNom());
            ps.setDate(3, entraide.getDate());
            ps.setString(4, entraide.getImage());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Entraide entraide) {
        try {
       String req = "UPDATE `entraide` SET `description`=?,`image`=?,`nom`=?,`date`=? WHERE id=3 ";

            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, entraide.getDescription());
            ps.setString(2, entraide.getNom());
            ps.setString(3, entraide.getImage());
            ps.setDate(4, entraide.getDate());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from entraide where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Entraide> getAll() {
        List<Entraide> entraides = new ArrayList<>();
        try {
            String req = "select * from entraide";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Entraide entraide = new Entraide(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getDate(4),rs.getString(5) );
                entraides.add(entraide);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entraides;
    }

    @Override
    public Entraide findById(Integer id) {
        Entraide entraide = null;
        try {
            String req = "select * from entraide where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                entraide = new Entraide(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entraide;
    }

}
