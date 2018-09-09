/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Df_category;
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
public class ForumCategorieSercice implements IService<Df_category, Integer>{
      private Connection connection;
      
      public ForumCategorieSercice ()
      {
      connection = DataSource.getInstance().getConnection();
      }

    @Override
    public void add(Df_category C) {
        try {
            String req1 = "INSERT INTO df_category(name)  VALUES (?)";
            PreparedStatement st = connection.prepareStatement(req1);
            st.setString(1, C.getName());
         st.executeUpdate(); // valable pour insert update w delete // lecture et ecriture mel basee
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
            Logger.getLogger(ForumCategorieSercice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Df_category C) {
        try {
            String req3 = "UPDATE `df_category` SET `name`=? WHERE id=6 ";
            PreparedStatement st = connection.prepareStatement(req3);
            st.setString(1, C.getName());
            st.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String req3 = "DELETE FROM `df_category` WHERE id=? ";
        try {
           PreparedStatement st = connection.prepareStatement(req3);
           st.setInt(1, id);
           st.executeUpdate();
           System.out.println("Categorie de forum  supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Df_category> getAll() {
        ObservableList<Df_category> listDf_category = FXCollections.observableArrayList();
        String req4 = "SELECT * FROM `df_category`";
        try {
            Statement st = connection.createStatement();
            st.executeQuery(req4);
            ResultSet rs = st.executeQuery(req4);
            while (rs.next()) {
                Df_category A = new Df_category(rs.getInt(1),rs.getString(2));
                listDf_category.add(A);
            }
            listDf_category.forEach(System.out::println);
            return listDf_category;

        } catch (SQLException ex) {
            Logger.getLogger(ForumCategorieSercice.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Df_category findById(Integer id) {
        Df_category categorie_forum = null;
        try {
            String req = "select * from df_category where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categorie_forum = new Df_category( rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categorie_forum;
    }
    
}
