package dev.shiv4u.productservice.services;

import dev.shiv4u.productservice.dtos.GenericProductDto;
import dev.shiv4u.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto genericProductDto);
    GenericProductDto updateProduct(GenericProductDto genericProductDto);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(Long id);

}
