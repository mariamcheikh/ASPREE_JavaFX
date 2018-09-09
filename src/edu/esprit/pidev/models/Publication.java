/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

import edu.esprit.pidev.services.impl.UserService;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author aymen
 */
public class Publication {

    private int likes;

    private int dislikes;

    private int id;

    private List<Fichier> fichiers;

    private List<Reaction> reactions;

    private User user;

    private String description;

    private int nbrSignal;

    private Timestamp dateCreation;

    public Publication(int id, int user_id, String description, Timestamp dateCreation, int nbrSignal, int likes, int dislikes) {
        UserService S=new UserService();
        this.id=id;
        this.user=S.findById(user_id);
        this.description=description;
        this.dateCreation=dateCreation;
        this.nbrSignal=nbrSignal;
        this.likes=likes;
        this.dislikes=dislikes;
    }

    @Override
    public String toString() {
        return "Publication{" + "likes=" + likes + ", dislikes=" + dislikes + ", id=" + id + ", fichiers=" + fichiers + ", reactions=" + reactions + ", user=" + user + ", description=" + description + ", nbrSignal=" + nbrSignal + ", dateCreation=" + dateCreation + '}';
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Fichier> getFichiers() {
        return fichiers;
    }

    public void setFichiers(List<Fichier> fichiers) {
        this.fichiers = fichiers;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbrSignal() {
        return nbrSignal;
    }

    public void setNbrSignal(int nbrSignal) {
        this.nbrSignal = nbrSignal;
    }

    public Timestamp getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * @return mixed
     */
}
