package dev.shiv4u.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    @ManyToOne(cascade ={CascadeType.REMOVE,CascadeType.PERSIST})
    @JoinColumn(name = "category")
    private Category category;
    //private double price;
    @OneToOne(cascade ={CascadeType.REMOVE,CascadeType.PERSIST})
    private Price price;
}
