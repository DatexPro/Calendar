package net.proselyte.securetyapp.model;

import jdk.nashorn.internal.objects.annotations.Constructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "note")
public class Note {
    public Note(Date day, String context, Client client) {
        this.day = day;
        this.context = context;
        this.client = client;
    }

    public Note(){};

    @Id
    private Long id;

    @Column(name = "day")
    private Date day;

    @Column(name = "context")
    private String context;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
