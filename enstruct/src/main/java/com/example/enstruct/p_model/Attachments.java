package com.example.enstruct.p_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "Attachments")
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long file_id;
    private String filename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"Attachments", "hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "user_id")
    private User owner_id;

    public Attachments() {
    }

    public Attachments(Long file_id, String filename, User owner_id) {
        this.file_id = file_id;
        this.filename = filename;
        this.owner_id = owner_id;
    }

    public Long getFile_id() {
        return file_id;
    }

    public void setFile_id(Long file_id) {
        this.file_id = file_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(User owner_id) {
        this.owner_id = owner_id;
    }
}