/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Product;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.interfaces.IProductService;
import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.services.interfaces.IUserService;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mehdi
 */
public class ProductService implements IService<Product, Integer> {

    private Connection connection;

    public ProductService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Product product) {
        try {
            String req = "insert into product(name, user_fk) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getOwner().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        try {
            String req = "update product set (name, user_fk) values (?,?) where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getOwner().getId());
            ps.setInt(3, product.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from product where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            String req = "select * from product";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1),rs.getString(2),new UserService().findById(rs.getInt(3)));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
