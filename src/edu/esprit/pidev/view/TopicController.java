/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import edu.esprit.pidev.models.df_topic;
import edu.esprit.pidev.services.impl.ForumTopicService;
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
public class TopicController implements Initializable {
    @FXML
    private JFXTextField IDTopic;
    @FXML
    private JFXComboBox<Integer> IDForumTopic;
    @FXML
    private JFXComboBox<Integer> IDUserTopic;
    @FXML
    private JFXTextField TitleTopic;
    @FXML
    private JFXTextField SlugTopic;
    @FXML
    private JFXButton ajouterTopic;

     private ObservableList<df_topic> data=FXCollections.observableArrayList();
     private Connection connection;
    @FXML
    private TableView<df_topic> TopicTable;
    @FXML
    private TableColumn<?, ?> ColumnForumTopic;
    @FXML
    private TableColumn<?, ?> ColumnUserTopic;
    @FXML
    private TableColumn<?, ?> ColumnTitleTopic;
    @FXML
    private TableColumn<?, ?> ColumnSlugTopic;
    @FXML
    private TableColumn<?, ?> ColumnIDTopic;
    @FXML
    private JFXButton SupprimerTopic;
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
            ForumTopicService ps= new ForumTopicService();
            for(int i=1; i<16;i++)
            {
          IDForumTopic.getItems().add(i);
           }
             for(int i=1; i<3;i++)
            {
          IDUserTopic.getItems().add(i);
           }
       connection = DataSource.getInstance().getConnection();
         affichage();
        // TODO
    }   

    
    
         private boolean validateFields () {
         if (IDForumTopic.getSelectionModel().isEmpty()| IDUserTopic.getSelectionModel().isEmpty()|TitleTopic.getText().isEmpty()|SlugTopic.getText().isEmpty() ){
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
    private void ajouterTopic(ActionEvent event) {
        ForumTopicService ps= new ForumTopicService();
       
          if (validateFields()){
      Alert alert =new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Topic ajouté avec succés!");
        alert.showAndWait();
       
         System.out.println("********************\n"+IDForumTopic.getSelectionModel().getSelectedIndex());
        System.out.println("********************\n"+IDUserTopic.getSelectionModel().getSelectedIndex());
         System.out.println("********************\n"+TitleTopic.getText());
          System.out.println("********************\n"+SlugTopic.getText());
    
              df_topic p= new df_topic (IDForumTopic.getSelectionModel().getSelectedIndex(),IDUserTopic.getSelectionModel().getSelectedIndex(),TitleTopic.getText(),SlugTopic.getText());
        ps.add(p);
//        
        System.out.println("ajout effectué");
        final TextInputDialog dialog = new TextInputDialog();  
        System.out.println("You clicked me!");
       data.clear();
        getAll();
           TrayNotification tray = new TrayNotification("Notification !", "Topic de forum Ajouté avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
        
        }
    }
    
    
    public void affichage(){
        ColumnForumTopic.setCellValueFactory(new PropertyValueFactory<>("forum_id"));
        ColumnUserTopic.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        ColumnTitleTopic.setCellValueFactory(new PropertyValueFactory<>("title"));
        ColumnSlugTopic.setCellValueFactory(new PropertyValueFactory<>("slug"));
        ColumnIDTopic.setCellValueFactory(new PropertyValueFactory<>("id"));
     data.clear();
        getAll();
      
      
    }
    
    
    public List<df_topic> getAll() {
           
 List<df_topic> topics = new ArrayList<>();
       try {
            String req = "SELECT `id`,`forum_id`, `user_id`,`title`,`slug` FROM `df_topic`";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                data.add(new df_topic(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5)));
                
               TopicTable.setItems(data);
            
            }
            
            
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return topics;
         
    }

    @FXML
    private void SupprimerTopic(ActionEvent event) {
        
         Alert alert =new Alert (AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer ce Topic?");
        Optional <ButtonType> action=alert.showAndWait();
        if (action.get()==ButtonType.OK){
       ForumTopicService ps= new ForumTopicService();
      
       df_topic prod=(df_topic )TopicTable.getSelectionModel().getSelectedItem();
        
        ps.delete(prod.getId());
        System.out.println(prod.getId());
        data.clear();
        getAll();
            TrayNotification tray = new TrayNotification("Notification !", "Topic de forum Supprimé avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
    }
    }
    
    
    FilteredList<df_topic>filetredData=new FilteredList<>(data,e->true);

    
    
@FXML
     public void serachTopic(){
       
//         serachField.textProperty().addListener((observableValue,oldValue,newValue)->{
//             filetredData.setPredicate((Predicate<? super df_topic>)df_topic->{
//                 if(newValue==null||newValue.isEmpty()){
//                     return true;
//                 }
//      
//                 
//                 String  lowerCaseFilter = newValue.toLowerCase();
//                
//                 if (df_topic.getTitle().toLowerCase().contains(lowerCaseFilter)){
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
//            SortedList<df_topic>sortedData=new SortedList<>(filetredData);
//            sortedData.comparatorProperty().bind(TopicTable.comparatorProperty());
//            TopicTable.setItems(sortedData);
//            
//             
//           
          
             ForumTopicService ps= new ForumTopicService();
             TopicTable.setItems((ObservableList<df_topic>) ps.findByNomForum(serachField.getText()));
              TopicTable.refresh();
         //data.clear();
        //getAll();
     }

    @FXML
    private void ShowOnClick() {
         df_topic prod=(df_topic )TopicTable.getSelectionModel().getSelectedItem();
        getAll();
        
        IDTopic.setText (String.valueOf(prod.getId()));
        IDForumTopic.setValue(prod.getForum_id());
        IDUserTopic.setValue(prod.getUser_id());
        TitleTopic.setText(String.valueOf(prod.getTitle()));
        SlugTopic.setText (String.valueOf(prod.getSlug()));
    }

    @FXML
    private void UpdateButtonAction(ActionEvent event) {
        
           
       Alert alert =new Alert (AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment Modifier ce Forum?");
        Optional <ButtonType> action=alert.showAndWait();
        if (action.get()==ButtonType.OK){
       ForumTopicService ps= new ForumTopicService();
        df_topic topics =(df_topic)TopicTable.getSelectionModel().getSelectedItem();
        
       try {
             String req = "UPDATE `df_topic` SET `forum_id`=?,`user_id`=?,`title`=?,`slug`=? WHERE id= '"+IDTopic.getText()+"' ";
            PreparedStatement pss = connection.prepareStatement(req);
            pss.setObject(1, IDForumTopic.getValue());
            pss.setObject(2, IDUserTopic.getValue());
            pss.setString(3, TitleTopic.getText());
            pss.setString(4, SlugTopic.getText());
           
            pss.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        data.clear();
        getAll();
       TrayNotification tray = new TrayNotification("Notification !", "Topic de forum Modifié avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
        }
    }

    @FXML
    private void RefreshButton(ActionEvent event) {
         ForumTopicService ps= new ForumTopicService();
             TopicTable.setItems((ObservableList<df_topic>) ps.getAll());
              TopicTable.refresh();
    }

    
}
