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
public class df_post {
    
    private int id;
    private int topic_id;
    private int poster_id;
    private int updated_by;
    private String content;

    public df_post(int id, int topic_id, int poster_id, String content) {
        this.id = id;
        this.topic_id = topic_id;
        this.poster_id = poster_id;
        this.content = content;
    }

    public df_post(int topic_id, int poster_id, String content) {
        this.topic_id = topic_id;
        this.poster_id = poster_id;
        this.content = content;
    }

    public df_post(int id, int topic_id, int poster_id, int updated_by, String content) {
        this.id = id;
        this.topic_id = topic_id;
        this.poster_id = poster_id;
        this.updated_by = updated_by;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getPoster_id() {
        return poster_id;
    }

    public void setPoster_id(int poster_id) {
        this.poster_id = poster_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.id;
        hash = 19 * hash + this.topic_id;
        hash = 19 * hash + this.poster_id;
        hash = 19 * hash + Objects.hashCode(this.content);
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
        final df_post other = (df_post) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.topic_id != other.topic_id) {
            return false;
        }
        if (this.poster_id != other.poster_id) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "df_post{" + "id=" + id + ", topic_id=" + topic_id + ", poster_id=" + poster_id + ", content=" + content + '}';
    }
    
   
    
}
