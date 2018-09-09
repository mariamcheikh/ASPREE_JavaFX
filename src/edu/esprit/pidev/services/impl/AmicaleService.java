/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Amicale;
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
public class AmicaleService implements IService<Amicale, Integer> {

        private Connection connection;

    

    public AmicaleService() {
          connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Amicale A) {
        try {
            String req1 = "INSERT INTO amicale( nom_Amicale,Type_Amicale,montant_Amicale,description_Amicale,user_id,image,visi)  VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(req1);
            st.setString(1, A.getNom_Amicale());
            st.setString(2, A.getType_Amicale());
            st.setInt(3, A.getMontant_Amicale());
            st.setString(4, A.getDescription_Amicale());
            st.setInt(5, A.getUser_id());
            st.setString(6, A.getImage());
            st.setInt(7, A.getVisi());
            
            st.executeUpdate(); // valable pour insert update w delete // lecture et ecriture mel basee
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
            Logger.getLogger(AmicaleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Amicale A) {
        try {
            String req3 = "UPDATE `amicale` SET `nom_Amicale`=?,`Type_Amicale`=?,`montant_Amicale`=?,`description_Amicale`=?,`user_id`=?,`image`=?,`visi`=? WHERE id=5 ";

            PreparedStatement st = connection.prepareStatement(req3);

            st.setString(1, A.getNom_Amicale());
            st.setString(2, A.getType_Amicale());
            st.setInt(3, A.getMontant_Amicale());
            st.setString(4, A.getDescription_Amicale());
            st.setInt(5, A.getUser_id());
            st.setString(6, A.getImage());
            st.setInt(7, A.getVisi());
            st.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String req3 = "DELETE FROM `amicale` WHERE id=? ";
        try {

            PreparedStatement st = connection.prepareStatement(req3);
            st.setInt(1, id);
            st.executeUpdate();

            System.out.println("amicale supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Amicale> getAll() {
        ObservableList<Amicale> listAmicale = FXCollections.observableArrayList();
        String req4 = "SELECT * FROM `amicale`";
        try {
            Statement st = connection.createStatement();
            st.executeQuery(req4);
            ResultSet rs = st.executeQuery(req4);

            while (rs.next()) {
                Amicale A = new Amicale(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8));
                listAmicale.add(A);
            }
            listAmicale.forEach(System.out::println);
            return listAmicale;

        } catch (SQLException ex) {
            Logger.getLogger(AmicaleService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Amicale findById(Integer id) {
        Amicale amicale = null;
        try {
            String req = "select * from amicale where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                amicale = new Amicale(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getInt(6),rs.getString(7),rs.getInt(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return amicale;
    }

}
