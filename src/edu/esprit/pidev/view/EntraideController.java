/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.view;

import edu.esprit.pidev.models.Entraide;
import edu.esprit.pidev.services.impl.EntraideService;
import edu.esprit.pidev.technique.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Noor
 */
public class EntraideController implements Initializable {

    @FXML
    private TableView<Entraide> table;
    @FXML
    private TableColumn<Entraide, Integer> id;
    @FXML
    private TableColumn<Entraide, String> description;
    @FXML
    private TableColumn<Entraide, String> nom;
    @FXML
    private TableColumn<Entraide, Date> date;
    @FXML
    private TableColumn<Entraide, String> image;
    private Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//             connection = DataSource.getInstance().getConnection();
//
//     EntraideService entraide = new EntraideService();
//        ResultSet result = (ResultSet) entraide.getAll();
//        try {
//            while (result.next()) {
//                data.addAll(new Entraide(result.getInt("id"), result.getString("description"), result.getString("nom"), result.getDate("date"), result.getString("image")));
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(EntraideController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        description.setCellValueFactory(new PropertyValueFactory<>("description"));
//        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        date.setCellValueFactory(new PropertyValueFactory<>("date"));
//        image.setCellValueFactory(new PropertyValueFactory<>("image"));
//
//        table.setItems(data);
    }
}
    