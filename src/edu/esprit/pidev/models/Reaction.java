/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

import edu.esprit.pidev.services.impl.PublicationService;
import edu.esprit.pidev.services.impl.UserService;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author aymen
 */
public class Reaction {
    private int id;
    private User user;
    private Publication publication;
    private String contenu;
    private int avis;
    private Timestamp date;
    private int nbrSignal; 

    public Reaction(int id, int user_id, int publication_id, String contenu, int avis, Timestamp date, int nbrSignal) {
        PublicationService PS=new PublicationService();
        UserService US=new UserService();
        User user= US.findById(user_id);
        Publication publication= PS.findById(publication_id);
        this.id = id;
        this.user = user;
        this.publication = publication;
        this.contenu = contenu;
        this.avis = avis;
        this.date = date;
        this.nbrSignal = nbrSignal;
    }
    
    
    public Reaction() {
        this.date=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        this.nbrSignal=0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getAvis() {
        return avis;
    }

    public void setAvis(int avis) {
        this.avis = avis;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getNbrSignal() {
        return nbrSignal;
    }

    public void setNbrSignal(int nbrSignal) {
        this.nbrSignal = nbrSignal;
    }
    
}
