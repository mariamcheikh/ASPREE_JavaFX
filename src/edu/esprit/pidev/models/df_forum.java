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
public class df_forum {
    private int id;
    private int category_id;
    private String name;
    private String description;
    private String slug;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public df_forum(int id, int category_id, String name, String description, String slug) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.slug = slug;
    }

    public df_forum(int category_id, String name, String description, String slug) {
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.slug = slug;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + this.category_id;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.slug);
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
        final df_forum other = (df_forum) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.category_id != other.category_id) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.slug, other.slug)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "df_forum{" + "id=" + id + ", category_id=" + category_id + ", name=" + name + ", description=" + description + ", slug=" + slug + '}';
    }
    
    
    
}
