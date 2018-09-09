/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.df_post;
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
public class ForumPostService implements IService<df_post, Integer>  {
    
    private Connection connection;
    
     public ForumPostService()
      {
      connection = DataSource.getInstance().getConnection();
      }

    @Override
    public void add(df_post P) {
         try {
            String req1 = "INSERT INTO df_post(topic_id,poster_id,content)  VALUES (?,?,?)";
            PreparedStatement st = connection.prepareStatement(req1);
            st.setInt(1, P.getTopic_id());
            st.setInt(2, P.getPoster_id());
            st.setString(3, P.getContent());
          
            st.executeUpdate(); // valable pour insert update w delete // lecture et ecriture mel basee
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
            Logger.getLogger(ForumPostService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void update(df_post P) {
         try {
            String req3 = "UPDATE `df_post` SET `topic_id`=?,`poster_id`=?,`content`=? WHERE id=6 ";
            PreparedStatement st = connection.prepareStatement(req3);
             st.setInt(1, P.getTopic_id());
            st.setInt(2, P.getPoster_id());
            st.setString(3, P.getContent());
            st.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String req3 = "DELETE FROM `df_post` WHERE id=? ";
        try {
           PreparedStatement st = connection.prepareStatement(req3);
           st.setInt(1, id);
           st.executeUpdate();
           System.out.println("Post  supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<df_post> getAll() {
        
        ObservableList<df_post> listPost = FXCollections.observableArrayList();
        String req4 = "SELECT * FROM `df_post`";
        try {
            Statement st = connection.createStatement();
            st.executeQuery(req4);
            ResultSet rs = st.executeQuery(req4);
            while (rs.next()) {
                df_post A = new df_post(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5));
                listPost.add(A);
            }
            listPost.forEach(System.out::println);
            return listPost;

        } catch (SQLException ex) {
            Logger.getLogger(ForumPostService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public df_post findById(Integer id) {
         df_post post = null;
        try {
            String req = "select * from df_forum where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                post= new df_post(rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return post;
    }
}
