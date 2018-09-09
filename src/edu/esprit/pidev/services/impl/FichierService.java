/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Fichier;
import edu.esprit.pidev.models.Publication;
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
 * @author aymen
 */
public class FichierService implements IService<Fichier, Integer> {

     private Connection connection;

    public FichierService() {
        connection = DataSource.getInstance().getConnection();
    }
    @Override
    public void add(Fichier f) {
 
        try {
            
            String req = "insert into publication(publication_id, fichier,lien) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, f.getPublication().getId());
            ps.setString(2, f.getFichier());
            ps.setString(3, f.getLien());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
    }

    @Override
    public void update(Fichier f) {
        try {
            String req = "update fichier set (publication_id, fichier,lien) values (?,?,?) where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, f.getPublication().getId());
            ps.setString(2, f.getFichier());
            ps.setString(3, f.getLien());
            ps.setInt(4, f.getId());            
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }

    @Override
    public void delete(Integer id) {
         try {
            String req = "delete from fichier where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Fichier> getAll() {
        List<Fichier> fichiers = new ArrayList<>();
        try {
            String req = "select * from fichier";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fichier fichier = new Fichier(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4));
            fichiers.add(fichier);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fichiers;    
    }

    @Override
    public Fichier findById(Integer id) {
        Fichier fichier = null;
        try {
            String req = "select * from fichier where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fichier = new Fichier(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return fichier;
    }
    
}
