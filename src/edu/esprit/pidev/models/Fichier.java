/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

import edu.esprit.pidev.services.impl.PublicationService;

/**
 *
 * @author aymen
 */
public class Fichier {
    private int id;
    private Publication publication;
    private String fichier;
    private String lien;

    
    public Fichier(int id, int publication_id, String fichier, String lien) {
        PublicationService S=new PublicationService();
        this.id = id;
        Publication Tpublication=S.findById(publication_id);
        this.publication = Tpublication;
        this.fichier = fichier;
        this.lien = lien;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    
    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }
    
    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
    
    
    
}
