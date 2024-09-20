package dev.shiv4u.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String descr;
    private String image;

    @ManyToOne(cascade = CascadeType.PERSIST) // Adjust cascade type as necessary
    @JoinColumn(name = "category_id") // Use a more explicit naming convention
    @JsonIgnore
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id") // Explicit join column name for clarity
    private Price price;
}
