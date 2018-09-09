/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.df_topic;
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
public class ForumTopicService implements IService<df_topic, Integer>{
      private Connection connection;
     public ForumTopicService()
      {
      connection = DataSource.getInstance().getConnection();
      }

    @Override
    public void add(df_topic T) {
         try {
            String req1 = "INSERT INTO df_topic(forum_id,user_id,title,slug)  VALUES (?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(req1);
            st.setInt(1, T.getForum_id());
            st.setInt(2, T.getUser_id());
            st.setString(3, T.getTitle());
            st.setString(4, T.getSlug());
            st.executeUpdate(); // valable pour insert update w delete // lecture et ecriture mel basee
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
            Logger.getLogger(ForumTopicService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(df_topic T) {
         try {
            String req3 = "UPDATE `df_topic` SET `forum_id`=?,`user_id`=?,`title`=?,`slug`=? WHERE id=6 ";
            PreparedStatement st = connection.prepareStatement(req3);
            st.setInt(1, T.getForum_id());
            st.setInt(2, T.getUser_id());
            st.setString(3, T.getTitle());
            st.setString(4, T.getSlug());
            st.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String req3 = "DELETE FROM `df_topic` WHERE id=? ";
        try {
           PreparedStatement st = connection.prepareStatement(req3);
           st.setInt(1, id);
           st.executeUpdate();
           System.out.println("Topic  supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<df_topic> getAll() {
        ObservableList<df_topic> listTopic = FXCollections.observableArrayList();
        String req4 = "SELECT * FROM `df_topic`";
        try {
            Statement st = connection.createStatement();
            st.executeQuery(req4);
            ResultSet rs = st.executeQuery(req4);
            while (rs.next()) {
                df_topic A = new df_topic(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                listTopic.add(A);
            }
            listTopic.forEach(System.out::println);
            return listTopic;

        } catch (SQLException ex) {
            Logger.getLogger(ForumTopicService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public df_topic findById(Integer id) {
       df_topic topic = null;
        try {
            String req = "select * from df_topic where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                topic= new df_topic(rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return topic;
    }
    
    public List<df_topic> findByNomForum(String nom) {
         ObservableList<df_topic> listTopic = FXCollections.observableArrayList();
         df_topic topic = null;
        
        try {
            String req = "select t.id,t.forum_id, t.user_id,t.title,t.slug from df_topic t , df_forum f where t.forum_id = f.id and f.name=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                topic= new df_topic(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                listTopic.add(topic);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTopic;
    }

}
