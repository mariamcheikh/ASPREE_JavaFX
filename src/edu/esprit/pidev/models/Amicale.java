/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

import java.util.Objects;

/**
 *
 * @author Jean
 */
 public class Amicale {
    
    private int id;
    private String nom_Amicale;
    private String Type_Amicale;
    private int montant_Amicale;
    private String description_Amicale;
    private int user_id;
    private String image;
    private int visi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_Amicale() {
        return nom_Amicale;
    }

    public void setNom_Amicale(String nom_Amicale) {
        this.nom_Amicale = nom_Amicale;
    }

    public String getType_Amicale() {
        return Type_Amicale;
    }

    public void setType_Amicale(String Type_Amicale) {
        this.Type_Amicale = Type_Amicale;
    }

    public int getMontant_Amicale() {
        return montant_Amicale;
    }

    public void setMontant_Amicale(int montant_Amicale) {
        this.montant_Amicale = montant_Amicale;
    }

    public String getDescription_Amicale() {
        return description_Amicale;
    }

    public void setDescription_Amicale(String description_Amicale) {
        this.description_Amicale = description_Amicale;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getVisi() {
        return visi;
    }

    public void setVisi(int visi) {
        this.visi = visi;
    }

    public Amicale(String description_Amicale) {
        this.description_Amicale = description_Amicale;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.nom_Amicale);
        hash = 67 * hash + Objects.hashCode(this.Type_Amicale);
        hash = 67 * hash + this.montant_Amicale;
        hash = 67 * hash + Objects.hashCode(this.description_Amicale);
        hash = 67 * hash + this.user_id;
        hash = 67 * hash + Objects.hashCode(this.image);
        hash = 67 * hash + this.visi;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Amicale other = (Amicale) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom_Amicale, other.nom_Amicale)) {
            return false;
        }
        if (!Objects.equals(this.Type_Amicale, other.Type_Amicale)) {
            return false;
        }
        if (this.montant_Amicale != other.montant_Amicale) {
            return false;
        }
        if (!Objects.equals(this.description_Amicale, other.description_Amicale)) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (this.visi != other.visi) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Amicale{" + "id=" + id + ", nom_Amicale=" + nom_Amicale + ", Type_Amicale=" + Type_Amicale + ", montant_Amicale=" + montant_Amicale + ", description_Amicale=" + description_Amicale + ", user_id=" + user_id + ", image=" + image + ", visi=" + visi + '}';
    }
    
    public Amicale(){}
    
    public Amicale(int id) {
        this.id = id;
        
    }
    public Amicale(int id, String nom_Amicale) {
        this.id = id;
        this.nom_Amicale = nom_Amicale;
     
    }

    public Amicale(String nom_Amicale, String Type_Amicale, int montant_Amicale, String description_Amicale, int user_id, int visi) {
        this.nom_Amicale = nom_Amicale;
        this.Type_Amicale = Type_Amicale;
        this.montant_Amicale = montant_Amicale;
        this.description_Amicale = description_Amicale;
        this.user_id = user_id;
        this.visi = visi;
    }

    
    public Amicale(String nom_Amicale, String Type_Amicale, int montant_Amicale, String description_Amicale) {
        this.nom_Amicale = nom_Amicale;
        this.Type_Amicale = Type_Amicale;
        this.montant_Amicale = montant_Amicale;
        this.description_Amicale = description_Amicale;
    }

    public Amicale(int id, String nom_Amicale, String Type_Amicale, int montant_Amicale, String description_Amicale, int user_id, int visi) {
        this.id = id;
        this.nom_Amicale = nom_Amicale;
        this.Type_Amicale = Type_Amicale;
        this.montant_Amicale = montant_Amicale;
        this.description_Amicale = description_Amicale;
        this.user_id = user_id;
        this.visi = visi;
    }

  
  
        public Amicale(int id, String nom_Amicale, String Type_Amicale) {
        this.id = id;
        this.nom_Amicale = nom_Amicale;
        this.Type_Amicale = Type_Amicale;
       
    }
      public Amicale(int id, String nom_Amicale, String Type_Amicale, int montant_Amicale) {
        this.id = id;
        this.nom_Amicale = nom_Amicale;
        this.Type_Amicale = Type_Amicale;
        this.montant_Amicale = montant_Amicale;
  
    }
      public Amicale(int id, String nom_Amicale, String Type_Amicale, int montant_Amicale, String description_Amicale) {
        this.id = id;
        this.nom_Amicale = nom_Amicale;
        this.Type_Amicale = Type_Amicale;
        this.montant_Amicale = montant_Amicale;
        this.description_Amicale = description_Amicale;
     
    }
      public Amicale(int id, String nom_Amicale, String Type_Amicale, int montant_Amicale, String description_Amicale, int user_id) {
        this.id = id;
        this.nom_Amicale = nom_Amicale;
        this.Type_Amicale = Type_Amicale;
        this.montant_Amicale = montant_Amicale;
        this.description_Amicale = description_Amicale;
        this.user_id = user_id;
      
    }
      public Amicale(int id, String nom_Amicale, String Type_Amicale, int montant_Amicale, String description_Amicale, int user_id, String image) {
        this.id = id;
        this.nom_Amicale = nom_Amicale;
        this.Type_Amicale = Type_Amicale;
        this.montant_Amicale = montant_Amicale;
        this.description_Amicale = description_Amicale;
        this.user_id = user_id;
        this.image = image;
     
    }
    
        public Amicale(int id, String nom_Amicale, String Type_Amicale, int montant_Amicale, String description_Amicale, int user_id, String image, int visi) {
        this.id = id;
        this.nom_Amicale = nom_Amicale;
        this.Type_Amicale = Type_Amicale;
        this.montant_Amicale = montant_Amicale;
        this.description_Amicale = description_Amicale;
        this.user_id = user_id;
        this.image = image;
        this.visi = visi;
    }
        public Amicale( String nom_Amicale, String Type_Amicale, int montant_Amicale, String description_Amicale,int user_id, String image, int visi) {
     
        this.nom_Amicale = nom_Amicale;
        this.Type_Amicale = Type_Amicale;
        this.montant_Amicale = montant_Amicale;
        this.description_Amicale = description_Amicale;
          this.user_id = user_id;
        this.image = image;
        this.visi = visi;
      
    }
    
    
    
}
