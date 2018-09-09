/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.view;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.esprit.pidev.models.Amicale;
import edu.esprit.pidev.services.impl.AmicaleService;
import edu.esprit.pidev.technique.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import pi_dev_aspree.FTTS;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author Jean
 */
public class AmicaleController implements Initializable {
    @FXML
    private JFXTextField TFNom;
    @FXML
    private JFXButton Btajout;
    @FXML
    private JFXTextField TFTYPE;
    @FXML
    private JFXTextField TFMontant;
    @FXML
    private JFXTextField TFDescription;
    
    @FXML
    private TableView<Amicale> AmicaleTable;
    @FXML
    private TableColumn<?, ?> columnNom;
    @FXML
    private TableColumn<?, ?> columnType;
    @FXML
    private TableColumn<?, ?> columnMontant;
    @FXML
    private TableColumn<?, ?> columnDescript;
   
    private ObservableList<Amicale> data=FXCollections.observableArrayList();
    private Connection connection;
    
    @FXML
    private JFXButton DeleteAmicale;
    @FXML
    private TableColumn<?, ?> columnId;
    @FXML
    private JFXTextField serachField;
    @FXML
    private JFXButton BTModif;
    @FXML
    private JFXTextField TNID;
    @FXML
    private TableColumn<?, ?> columnUser;
    @FXML
    private TableColumn<?, ?> columnVisi;
    @FXML
    private JFXComboBox<Integer> TFUserID;
    @FXML
    private JFXComboBox<Integer> TFVisi;
    @FXML
    private JFXButton Speak;
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AmicaleService ps= new AmicaleService();
        for(int i=0; i<2;i++){
           //TFUserID.getItems().add(ps.getAll().size(), i);
           TFVisi.getItems().add(i);
          
           //categ.getItems().add(cs.getAll().get(i));
           
        }
        
          for(int i=1; i<3;i++){
           //TFUserID.getItems().add(ps.getAll().size(), i);
            TFUserID.getItems().addAll(i);
          
           //categ.getItems().add(cs.getAll().get(i));
           
        }
        
         
//          for(int i=0; i<ps.getAll().size();i++){
//           //TFUserID.getItems().add(ps.getAll().size(), i);
//           TFVisi.getItems().add(i);
//           TFUserID.getItems().addAll(i);
//           //categ.getItems().add(cs.getAll().get(i));
//           
//        }


       connection = DataSource.getInstance().getConnection();
       affichage();
       
      
       
    }    
    
    private boolean validateFields () {
         if (TFNom.getText().isEmpty() | TFTYPE.getText().isEmpty() |  TFMontant.getText().isEmpty() |TFDescription.getText().isEmpty() |  TFUserID.getSelectionModel().isEmpty() | TFVisi.getSelectionModel().isEmpty() ){
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
    private void ajouterAmicale(ActionEvent event) {
        
        AmicaleService ps= new AmicaleService();
        

        if (validateFields()){
        TrayNotification tray = new TrayNotification("Notification !", "Amicale Crée avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
            
//      Alert alert =new Alert (Alert.AlertType.INFORMATION);
//        alert.setTitle("Confirmation Dialog");
//        alert.setHeaderText(null);
//        alert.setContentText("Amicale ajouté avec succés!");
//        alert.showAndWait();
       
        System.out.println("********************\n"+TFNom.getText());
        System.out.println("********************\n"+TFTYPE.getText());
        System.out.println("********************\n"+Integer.parseInt(TFMontant.getText()));
        System.out.println("********************\n"+TFDescription.getText());
        System.out.println("*******************\n"+TFUserID.getSelectionModel().getSelectedIndex());
        System.out.println("*******************\n"+TFVisi.getSelectionModel().getSelectedIndex());
        
        Amicale p= new Amicale(TFNom.getText(),TFTYPE.getText(),Integer.parseInt(TFMontant.getText()),TFDescription.getText(),TFUserID.getSelectionModel().getSelectedIndex(),TFVisi.getSelectionModel().getSelectedIndex());
        ps.add(p);

     
        
        
//        System.out.println("ajout effectué");
//        final TextInputDialog dialog = new TextInputDialog();  
       
       data.clear();
        getAll();
        
        
        }
        
        
    }
    
    public void affichage(){
        
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom_Amicale"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("Type_Amicale"));
        columnMontant.setCellValueFactory(new PropertyValueFactory<>("montant_Amicale"));
        columnDescript.setCellValueFactory(new PropertyValueFactory<>("description_Amicale"));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnUser.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        columnVisi.setCellValueFactory(new PropertyValueFactory<>("visi"));
        
        data.clear();
        getAll();
      
      
    }
    


   
    public List<Amicale> getAll() {
           
        List<Amicale> Amicales = new ArrayList<>();
       try {
            String req = "SELECT `id`,`nom_Amicale`, `Type_Amicale`, `montant_Amicale`, `description_Amicale`,`user_id`,`visi` FROM `amicale`";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                data.add(new Amicale(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
                
                AmicaleTable.setItems(data);
            
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return Amicales;
         
    }

    @FXML
    private void DeleteAmicale(ActionEvent event) {
         Alert alert =new Alert (AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer cet Amicale ?");
        Optional <ButtonType> action=alert.showAndWait();
        if (action.get()==ButtonType.OK){
       AmicaleService ps= new AmicaleService();
      
       Amicale prod=(Amicale)AmicaleTable.getSelectionModel().getSelectedItem();
        
        ps.delete(prod.getId());
        System.out.println(prod.getId());
        data.clear();
        getAll();
          TrayNotification tray = new TrayNotification("Notification !", "Amicale Supprimé avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
    }
 
       
    }

    FilteredList<Amicale>filetredData=new FilteredList<>(data,e->true);
    
    
    
    @FXML
     public void serachAmicale (){
       
         serachField.textProperty().addListener((observableValue,oldValue,newValue)->{
             filetredData.setPredicate((Predicate<? super Amicale>)Amicale->{
                 if(newValue==null||newValue.isEmpty()){
                     return true;
                 }
      
                 
                 String  lowerCaseFilter = newValue.toLowerCase();
                
                 if (Amicale.getNom_Amicale().toLowerCase().contains(lowerCaseFilter)){
                     
                     return true;
                    
                 }
                
                 return false;
             });
             
         });
        
            SortedList<Amicale>sortedData=new SortedList<>(filetredData);
            sortedData.comparatorProperty().bind(AmicaleTable.comparatorProperty());
            AmicaleTable.setItems(sortedData);
            
             
           
     }
     
    
    @FXML
    public void ShowOnClick (){
        Amicale prod=(Amicale)AmicaleTable.getSelectionModel().getSelectedItem();
        getAll();
        TNID.setText (String.valueOf(prod.getId()));
        TFNom.setText (String.valueOf(prod.getNom_Amicale()));
        TFTYPE.setText(prod.getType_Amicale());
        TFMontant.setText (String.valueOf(prod.getMontant_Amicale()));
        TFDescription.setText(prod.getDescription_Amicale());
        TFUserID.setValue(prod.getUser_id());
        TFVisi.setValue(prod.getVisi());
        
        
    }

    @FXML
    private void UpdateButtonAction(ActionEvent event) {
         
       Alert alert =new Alert (AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment Modifier cet Amicale?");
        Optional <ButtonType> action=alert.showAndWait();
        if (action.get()==ButtonType.OK){
         AmicaleService ps= new AmicaleService();
        Amicale amicale=(Amicale)AmicaleTable.getSelectionModel().getSelectedItem();
        
       try {
             String req = "UPDATE `amicale` SET `nom_Amicale`=?,`Type_Amicale`=?,`montant_Amicale`=?,`description_Amicale`=?,`user_id`=?,`visi`=? WHERE id= '"+TNID.getText()+"' ";
            PreparedStatement pss = connection.prepareStatement(req);
            pss.setString(1, TFNom.getText());
            pss.setString(2, TFTYPE.getText());
            pss.setInt(3, Integer.parseInt(TFMontant.getText()));
            pss.setString(4, TFDescription.getText());
            pss.setObject(5, TFUserID.getValue());
            pss.setObject(6, TFVisi.getValue());
            pss.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        data.clear();
        getAll();
          TrayNotification tray = new TrayNotification("Notification !", "Amicale Modifié avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
        }
    }

    @FXML
    private void Speak(ActionEvent event) {
       String desc = TFDescription.getText();
       Amicale a = new Amicale(desc);
         AmicaleService ps= new AmicaleService();
        // ps.add(a);
        FTTS freeTTS = new FTTS(a.getDescription_Amicale());
        freeTTS.speak();
       
    }
    }

   

   

    
