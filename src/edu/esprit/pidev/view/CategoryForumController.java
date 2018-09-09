/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.view;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;

import edu.esprit.pidev.models.Df_category;
import edu.esprit.pidev.services.impl.ForumCategorieSercice;
import edu.esprit.pidev.technique.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Jean
 */
public class CategoryForumController implements Initializable {
    @FXML
    private JFXTextField IDCategory;
    @FXML
    private JFXTextField NomCategory;
    @FXML
    private JFXButton ajouterCategory;
    @FXML
    private TableView<Df_category> CategoryTable;
    @FXML
    private TableColumn<?, ?> columCategory;
    @FXML
    private TableColumn<?, ?> columidcategory;
   
    
 private ObservableList<Df_category> data=FXCollections.observableArrayList();
    private Connection connection;
    
    @FXML
    private JFXButton SupprimerCategory;
    @FXML
    private JFXTextField serachField;
    @FXML
    private JFXButton BTModif;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ForumCategorieSercice ps= new ForumCategorieSercice();
       connection = DataSource.getInstance().getConnection();
       affichage();
    }    

    
    private boolean validateFields () {
         if (NomCategory.getText().isEmpty()){
        Alert alert =new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Validation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vérifiez les champs !");
        alert.showAndWait();
        return false;
        }
        return true;

    }    
    
    @FXML
    private void ajouterCategory(ActionEvent event) {
          ForumCategorieSercice ps= new ForumCategorieSercice();
       
          if (validateFields()){
      Alert alert =new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Categorie ajouté avec succés!");
        alert.showAndWait();
       
        System.out.println("********************\n"+NomCategory.getText());
    
        Df_category p= new Df_category(NomCategory.getText());
        ps.add(p);
//        
        System.out.println("ajout effectué");
        final TextInputDialog dialog = new TextInputDialog();  
        System.out.println("You clicked me!");
       data.clear();
        getAll();
          TrayNotification tray = new TrayNotification("Notification !", "Categorie de forum Ajouté avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
        
        }
    }
    
     public void affichage(){
        
        columidcategory.setCellValueFactory(new PropertyValueFactory<>("id"));
        columCategory.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        
        data.clear();
        getAll();
      
      
    }
    public List<Df_category> getAll() {
           
 List<Df_category> categories = new ArrayList<>();
       try {
            String req = "SELECT `id`,`name` FROM `df_category`";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                data.add(new Df_category(rs.getInt(1),rs.getString(2)));
                
                CategoryTable.setItems(data);
          }
       } catch (Exception ex) {
            ex.printStackTrace();
        }

        return categories;
         
    }

    @FXML
    private void SupprimerCategory(ActionEvent event) {
         Alert alert =new Alert (AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer cette Category ?");
        Optional <ButtonType> action=alert.showAndWait();
        if (action.get()==ButtonType.OK){
         ForumCategorieSercice ps= new ForumCategorieSercice();
      
       Df_category prod=(Df_category)CategoryTable.getSelectionModel().getSelectedItem();
        
        ps.delete(prod.getId());
        System.out.println(prod.getId());
        data.clear();
        getAll();
       TrayNotification tray = new TrayNotification("Notification !", "Categorie de forum Supprimé avec succée", NotificationType.SUCCESS);
       tray.showAndDismiss(javafx.util.Duration.seconds(2));
    }
    }

     FilteredList<Df_category>filetredData=new FilteredList<>(data,e->true);
     
    @FXML
     public void serachCategory(){
       
         serachField.textProperty().addListener((observableValue,oldValue,newValue)->{
             filetredData.setPredicate((Predicate<? super Df_category>)Df_category->{
                 if(newValue==null||newValue.isEmpty()){
                     return true;
                 }
      
                 
                 String  lowerCaseFilter = newValue.toLowerCase();
                
                 if (Df_category.getName().toLowerCase().contains(lowerCaseFilter)){
                     
                     return true;
                    
                 }
                
                 return false;
             });
             
         });
        
            SortedList<Df_category>sortedData=new SortedList<>(filetredData);
            sortedData.comparatorProperty().bind(CategoryTable.comparatorProperty());
            CategoryTable.setItems(sortedData);
            
             
           
     }

    @FXML
    public void ShowOnClick (){
        Df_category prod=(Df_category)CategoryTable.getSelectionModel().getSelectedItem();
        getAll();
        IDCategory.setText (String.valueOf(prod.getId()));
        NomCategory.setText(prod.getName());
       
        
        
    }

    @FXML
    private void UpdateButtonAction(ActionEvent event) {
        Alert alert =new Alert (AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment Modifier cette categorie ?");
        Optional <ButtonType> action=alert.showAndWait();
        if (action.get()==ButtonType.OK){
          ForumCategorieSercice ps= new ForumCategorieSercice();
        Df_category categories=(Df_category)CategoryTable.getSelectionModel().getSelectedItem();
        
       try {
             String req = "UPDATE `df_category` SET `name`=? WHERE id= '"+IDCategory.getText()+"' ";
            PreparedStatement pss = connection.prepareStatement(req);
            pss.setString(1, NomCategory.getText());
           
            pss.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        data.clear();
        getAll();
         TrayNotification tray = new TrayNotification("Notification !", "Categorie de forum Modifié avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
        }
    }
    }
      
    
