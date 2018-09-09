/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.df_forum;
import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jean
 */
public class ForumForumService implements IService<df_forum, Integer> {
     private Connection connection;
     public ForumForumService()
      {
      connection = DataSource.getInstance().getConnection();
      }

    @Override
    public void add(df_forum F) {
        try {
            String req1 = "INSERT INTO df_forum(category_id,name,description,slug)  VALUES (?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(req1);
            st.setInt(1, F.getCategory_id());
            st.setString(2, F.getName());
            st.setString(3, F.getDescription());
            st.setString(4, F.getSlug());
            st.executeUpdate(); // valable pour insert update w delete // lecture et ecriture mel basee
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
            Logger.getLogger(ForumForumService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(df_forum F) {
        try {
            String req3 = "UPDATE `df_forum` SET `category_id`=?,`name`=?,`description`=?,`slug`=? WHERE id=6 ";
            PreparedStatement st = connection.prepareStatement(req3);
            st.setInt(1, F.getCategory_id());
            st.setString(2, F.getName());
            st.setString(3, F.getDescription());
            st.setString(4, F.getSlug());
            st.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String req3 = "DELETE FROM `df_forum` WHERE id=? ";
        try {
           PreparedStatement st = connection.prepareStatement(req3);
           st.setInt(1, id);
           st.executeUpdate();
           System.out.println("Forum  supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<df_forum> getAll() {
        
        ObservableList<df_forum> listForum = FXCollections.observableArrayList();
        String req4 = "SELECT * FROM `df_forum`";
        try {
            Statement st = connection.createStatement();
            st.executeQuery(req4);
            ResultSet rs = st.executeQuery(req4);
            while (rs.next()) {
                df_forum A = new df_forum(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
                listForum.add(A);
            }
            listForum.forEach(System.out::println);
            return listForum;

        } catch (SQLException ex) {
            Logger.getLogger(ForumForumService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public df_forum findById(Integer id) {
         df_forum forum = null;
        try {
            String req = "select * from df_forum where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                forum= new df_forum(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return forum;
    }

     
    public List<df_forum> findByNomCateg(String nom) {
         ObservableList<df_forum> listForum = FXCollections.observableArrayList();
         df_forum forum = null;
        
        try {
            String req = "select f.id,f.category_id, f.name,f.description,f.slug from df_forum f , df_category c where f.category_id = c.id and c.name=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                forum= new df_forum(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
                listForum.add(forum);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listForum;
    }
    
}
