package dev.shiv4u.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Price extends BaseModel{
    private String currency;
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private double price;
}
