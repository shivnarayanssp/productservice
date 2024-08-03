package dev.shiv4u.productservice.thirdpartyclients.fakestore.dtos;

import dev.shiv4u.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String category;
    private double price;
    private String description;
    private String image;
}
