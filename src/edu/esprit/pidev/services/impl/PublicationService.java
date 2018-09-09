/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Product;
import edu.esprit.pidev.models.Publication;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aymen
 */
public class PublicationService implements IService<Publication, Integer>{

        private Connection connection;

    public PublicationService() {
        connection = DataSource.getInstance().getConnection();
    }
    @Override
    public void add(Publication P) {
        try {
            String req = "insert into publication(user_id, description,date_creation,nbr_signal,likes,dislikes) values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, P.getUser().getId());
            ps.setString(2, P.getDescription());
            ps.setTimestamp(3, P.getDateCreation());
            ps.setInt(4, P.getNbrSignal());
            ps.setInt(5, P.getLikes());
            ps.setInt(6, P.getDislikes());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Publication P) {
        try {
            String req = "update publication set user_id = ? , description = ? ,date_creation = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
             ps.setInt(1, P.getUser().getId());
            ps.setString(2, P.getDescription());
            ps.setTimestamp(3, P.getDateCreation());
            ps.setInt(4, P.getId());            
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from publication where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Publication> getAll() {
    List<Publication> publications = new ArrayList<>();
        try {
            String req = "select * from publication";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Publication publication = new Publication(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getTimestamp(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
                publications.add(publication);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return publications;    
    }

    @Override
    public Publication findById(Integer id) {
        Publication publication = null;
        try {
            String req = "select * from publication where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                publication = new Publication(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getTimestamp(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return publication;
    }

    
}
