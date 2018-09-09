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
public class df_topic {
    private int id;
    private int forum_id;
    private int user_id;
    private String title;
    private String slug;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getForum_id() {
        return forum_id;
    }

    public void setForum_id(int forum_id) {
        this.forum_id = forum_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public df_topic(int id, int forum_id, int user_id, String title, String slug) {
        this.id = id;
        this.forum_id = forum_id;
        this.user_id = user_id;
        this.title = title;
        this.slug = slug;
    }

    public df_topic(int forum_id, int user_id, String title, String slug) {
        this.forum_id = forum_id;
        this.user_id = user_id;
        this.title = title;
        this.slug = slug;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + this.forum_id;
        hash = 23 * hash + this.user_id;
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.slug);
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
        final df_topic other = (df_topic) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.forum_id != other.forum_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.slug, other.slug)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "df_topic{" + "id=" + id + ", forum_id=" + forum_id + ", user_id=" + user_id + ", title=" + title + ", slug=" + slug + '}';
    }
    
    
    
    
}
