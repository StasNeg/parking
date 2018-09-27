package com.example.demo.model.car;

import com.example.demo.model.AbstractBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "producer", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "producer_unique_name_idx")})
public class Producer extends AbstractBaseEntity {


    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producer")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<Model> models;

    public Producer() {
    }

    public Producer(String name) {
        this.name = name;
    }

    public Producer(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Producer(String name, List<Model> models) {
        this.name = name;
        this.models = models;

    }

    public Producer(Long id, String name, List<Model> models) {
        super(id);
        this.name = name;
        this.models = models;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
