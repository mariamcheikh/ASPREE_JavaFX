/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Don;
import edu.esprit.pidev.models.Entraide;
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
public class DonService implements IService<Don, Integer> {

    private Connection connection;

    public DonService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Don don) {
        try {
            String req = "insert into don(montant,description, date,entraide_id,user_id) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setDouble(1, don.getMontant());
            ps.setString(2, don.getDescription());
            ps.setDate(3, don.getDate());
            ps.setInt(4, don.getEntraide().getId());
            ps.setInt(5, don.getUser().getId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Don don) {
        try {
            String req = "update don set (montant,description, date,entraide_id,user_id) values (?,?,?,?,?) where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setDouble(1, don.getMontant());
            ps.setString(2, don.getDescription());
            ps.setDate(3, don.getDate());
            ps.setInt(4, don.getEntraide().getId());
            ps.setInt(5, 10);

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from don where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Don> getAll() {
        List<Don> dons = new ArrayList<>();
        try {
            String req = "select * from don";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Don don = new Don(rs.getInt(1), new UserService().findById(rs.getInt(2)), new EntraideService().findById(rs.getInt(3)), rs.getDouble(4), rs.getString(5), rs.getDate(6));
                dons.add(don);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dons;
    }

    @Override
    public Don findById(Integer id) {
        Don don = null;
        try {
            String req = "select * from don where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                don = new Don(rs.getInt(1), new UserService().findById(rs.getInt(2)), new EntraideService().findById(rs.getInt(3)), rs.getDouble(4), rs.getString(5), rs.getDate(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return don;
    }

}
