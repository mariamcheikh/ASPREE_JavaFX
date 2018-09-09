/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pidev.models.df_forum;
import edu.esprit.pidev.services.impl.ForumForumService;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class ForumController implements Initializable {
    @FXML
    private JFXTextField IDForum;
    @FXML
    private JFXComboBox<Integer> ID_CategoryForum;
    @FXML
    private JFXTextField NomForum;
    @FXML
    private JFXTextField SlugForum;
    @FXML
    private JFXTextArea DescForum;
    @FXML
    private JFXButton ajouterForum;

    
     private Connection connection;
    @FXML
    private TableView<df_forum> ForumTable;
    @FXML
    private TableColumn<?, ?> ColumnCategForum;
    @FXML
    private TableColumn<?, ?> ColumnNomForum;
    @FXML
    private TableColumn<?, ?> ColumnDescForum;
    @FXML
    private TableColumn<?, ?> ColumnSlugForum;
    @FXML
    private TableColumn<?, ?> ColumnIdForum;
    
    private ObservableList<df_forum> data=FXCollections.observableArrayList();
    @FXML
    private JFXButton SupprimerForum;
    @FXML
    private JFXTextField serachField;
    @FXML
    private JFXButton BTModif;
    @FXML
    private JFXButton Refresh;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ForumForumService ps= new ForumForumService();
            for(int i=1; i<6;i++)
            {
          ID_CategoryForum.getItems().add(i);
        }
       connection = DataSource.getInstance().getConnection();
       affichage();
        // TODO
   
    }    
    
     private boolean validateFields () {
         if (ID_CategoryForum.getSelectionModel().isEmpty()|  NomForum.getText().isEmpty() |DescForum.getText().isEmpty()|SlugForum.getText().isEmpty() ){
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
    private void ajouterForum(ActionEvent event) {
            ForumForumService ps= new ForumForumService();
       
          if (validateFields()){
      Alert alert =new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Forum ajouté avec succés!");
        alert.showAndWait();
       
         System.out.println("********************\n"+ID_CategoryForum.getSelectionModel().getSelectedIndex());
        System.out.println("********************\n"+NomForum.getText());
         System.out.println("********************\n"+DescForum.getText());
          System.out.println("********************\n"+SlugForum.getText());
    
              df_forum p= new df_forum (ID_CategoryForum.getSelectionModel().getSelectedIndex(),NomForum.getText(),DescForum.getText(),SlugForum.getText());
        ps.add(p);
//        
        System.out.println("ajout effectué");
        final TextInputDialog dialog = new TextInputDialog();  
        System.out.println("You clicked me!");
       data.clear();
        getAll();
         TrayNotification tray = new TrayNotification("Notification !", "Forum Ajouté avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
        
        }
    } 
       
public void affichage(){
        ColumnCategForum.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        ColumnNomForum.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnDescForum.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColumnSlugForum.setCellValueFactory(new PropertyValueFactory<>("slug"));
        ColumnIdForum.setCellValueFactory(new PropertyValueFactory<>("id"));
     data.clear();
        getAll();
      
      
    }
            
            
             public List<df_forum> getAll() {
           
 List<df_forum> forums = new ArrayList<>();
       try {
            String req = "SELECT `id`,`category_id`, `name`,`description`,`slug` FROM `df_forum`";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                data.add(new df_forum(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                
               ForumTable.setItems(data);
            
            }
            
            
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return forums;
         
    }

    @FXML
    private void SupprimerForum(ActionEvent event) {
         Alert alert =new Alert (AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer cet Forum?");
        Optional <ButtonType> action=alert.showAndWait();
        if (action.get()==ButtonType.OK){
        ForumForumService ps= new ForumForumService();
      
       df_forum prod=(df_forum )ForumTable.getSelectionModel().getSelectedItem();
        
        ps.delete(prod.getId());
        System.out.println(prod.getId());
        data.clear();
        getAll();
           TrayNotification tray = new TrayNotification("Notification !", "Forum Supprimé avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
    }
    }

    FilteredList<df_forum>filetredData=new FilteredList<>(data,e->true);
     
    @FXML
     public void serachForum(){
//       
//         serachField.textProperty().addListener((observableValue,oldValue,newValue)->{
//             filetredData.setPredicate((Predicate<? super df_forum>)df_forum->{
//                 if(newValue==null||newValue.isEmpty()){
//                     return true;
//                 }
//      
//                 
//                 String  lowerCaseFilter = newValue.toLowerCase();
//                
//                 if (df_forum.getName().toLowerCase().contains(lowerCaseFilter)){
//                     
//                     return true;
//                    
//                 }
//                
//                 return false;
//             });
//             
//         });
//        
//            SortedList<df_forum>sortedData=new SortedList<>(filetredData);
//            sortedData.comparatorProperty().bind(ForumTable.comparatorProperty());
//            ForumTable.setItems(sortedData);
//            
              ForumForumService ps= new ForumForumService();
              
              ForumTable.setItems((ObservableList<df_forum>) ps.findByNomCateg(serachField.getText()));
              ForumTable.refresh();
         
           
     }

      @FXML
    public void ShowOnClick (){
        df_forum prod=(df_forum )ForumTable.getSelectionModel().getSelectedItem();
        getAll();
        
        IDForum.setText (String.valueOf(prod.getId()));
        ID_CategoryForum.setValue(prod.getCategory_id());
        NomForum.setText (String.valueOf(prod.getName()));
        DescForum.setText(String.valueOf(prod.getDescription()));
        SlugForum.setText (String.valueOf(prod.getSlug()));
   }
    @FXML
    private void UpdateButtonAction(ActionEvent event) {
        
          
       Alert alert =new Alert (AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment Modifier ce Forum?");
        Optional <ButtonType> action=alert.showAndWait();
        if (action.get()==ButtonType.OK){
        ForumForumService ps= new ForumForumService();
        df_forum forum=(df_forum)ForumTable.getSelectionModel().getSelectedItem();
        
       try {
             String req = "UPDATE `df_forum` SET `category_id`=?,`name`=?,`description`=?,`slug`=? WHERE id= '"+IDForum.getText()+"' ";
            PreparedStatement pss = connection.prepareStatement(req);
            pss.setObject(1, ID_CategoryForum.getValue());
            pss.setString(2, NomForum.getText());
            pss.setString(3, DescForum.getText());
            pss.setString(4, SlugForum.getText());
           
            pss.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        data.clear();
        getAll();
           TrayNotification tray = new TrayNotification("Notification !", "Forum Modifié avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
        }
    }

    @FXML
    private void RefreshButton(ActionEvent event) {
       ForumForumService ps= new ForumForumService();
             ForumTable.setItems((ObservableList<df_forum>) ps.getAll());
              ForumTable.refresh();
    }
        
    
    
}
