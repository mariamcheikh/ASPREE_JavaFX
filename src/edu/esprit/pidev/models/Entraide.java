/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Noor
 */
public class Entraide {
    private int id;
    private String description;
    private String nom;
    private Date date;
    private String image;

    public Entraide() {
    }

    public Entraide(String description, String nom, Date date, String image) {
        this.description = description;
        this.nom = nom;
        this.date = date;
        this.image = image;
    }

    public Entraide(int id, String description, String nom, Date date, String image) {
        this.id = id;
        this.description = description;
        this.nom = nom;
        this.date = date;
        this.image = image;
    }
   public Entraide(int id, String description, String image, String nom, Date date) {
        this.id = id;
        this.description = description;
        this.nom = nom;
        this.date = date;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Entraide{" + "id=" + id + ", description=" + description + ", nom=" + nom + ", date=" + date + ", image=" + image + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.nom);
        hash = 29 * hash + Objects.hashCode(this.date);
        hash = 29 * hash + Objects.hashCode(this.image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entraide other = (Entraide) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
    
    
}
