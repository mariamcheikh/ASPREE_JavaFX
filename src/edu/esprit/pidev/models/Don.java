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
public class Don {
    private int id;
    private double montant;
    private Date date;
    private String description;
    private User user;
    private Entraide entraide;

    public Don() {
    }

    public Don(double montant, Date date, String description) {
        this.montant = montant;
        this.date = date;
        this.description = description;
    }

    public Don(int id, User user, Entraide entraide, double montant, String description, Date date) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.user = user;
        this.entraide = entraide;
    }

    public Don(int id,double montant, String description, Date date, User user, Entraide entraide) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.user = user;
        this.entraide = entraide;
    }

    public Don(double montant, String description, Date date, User user, Entraide entraide) {
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.user = user;
        this.entraide = entraide;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Entraide getEntraide() {
        return entraide;
    }

    public void setEntraide(Entraide entraide) {
        this.entraide = entraide;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.montant) ^ (Double.doubleToLongBits(this.montant) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.user);
        hash = 47 * hash + Objects.hashCode(this.entraide);
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
        final Don other = (Don) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.montant) != Double.doubleToLongBits(other.montant)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.entraide, other.entraide)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Don{" + "id=" + id + ", montant=" + montant + ", date=" + date + ", description=" + description + ", user=" + user + ", entraide=" + entraide + '}';
    }

    
}
