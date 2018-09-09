/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Fichier;
import edu.esprit.pidev.models.Publication;
import edu.esprit.pidev.models.Reaction;
import edu.esprit.pidev.services.interfaces.IReactionService;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * SELECT COUNT(*) as "nbr employes", job_id as "ID"FROM employees GROUP BY
 * job_id;
 *
 * @author aymen
 */
public class ReactionService implements IReactionService {

    private Connection connection;

    public ReactionService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void updateComment(Reaction r, String contenu) {
        try {
            String req = "update reaction set Contenu = ?, date = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, contenu);
            ps.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            ps.setInt(3, r.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void add(Reaction r) {
        try {

            String req = "insert into reaction(user_id,contenu,avis,date,publication_id,nbr_signal) values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, r.getUser().getId());
            ps.setString(2, r.getContenu());
            ps.setInt(3, r.getAvis());
            ps.setTimestamp(4, r.getDate());
            ps.setInt(5, r.getPublication().getId());
            ps.setInt(6, r.getNbrSignal());
            ps.executeUpdate();
            if (r.getAvis() == 1) {
                try {
                    String req1 = "update publication set likes = likes+1 where id = ?";
                    PreparedStatement ps1 = connection.prepareStatement(req1);

                    ps1.setInt(1, r.getPublication().getId());
                    ps1.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (r.getAvis() == 2) {
                try {
                    String req2 = "update publication set dislikes = dislikes+1 where id = ?";
                    PreparedStatement ps2 = connection.prepareStatement(req2);
                    ps2.setInt(1, r.getPublication().getId());
                    ps2.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Reaction r) {
        try {
            String req = "UPDATE reaction SET user_id = ?, contenu = ? , avis = ? , date = ? , publication_id = ? ,nbr_signal= ? WHERE id = ? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, r.getUser().getId());
            ps.setString(2, r.getContenu());
            ps.setInt(3, r.getAvis());
            ps.setTimestamp(4, r.getDate());
            ps.setInt(5, r.getPublication().getId());
            ps.setInt(6, r.getNbrSignal());
            ps.setInt(7, r.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from reaction where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Reaction> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reaction findById(Integer id) {
        Reaction reaction = null;
        try {
            String req = "select * from fichier where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reaction = new Reaction(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getTimestamp(6), rs.getInt(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reaction;
    }
}
