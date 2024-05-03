package dev.shiv4u.productservice.controllers;

import dev.shiv4u.productservice.dtos.GenericProductDto;
import dev.shiv4u.productservice.exceptions.NotFoundException;
import dev.shiv4u.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    //2:-Constructor injection is right  way to go.
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }
    @PatchMapping
    public GenericProductDto updateProductByid(@PathVariable("id") Long id,@RequestBody GenericProductDto genericProductDto){
        return productService.updateProduct(genericProductDto);
    }

    @PostMapping
    public GenericProductDto createproduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }
    // This is specific to this controller
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException) {
//        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
//                HttpStatus.NOT_FOUND);
//    }
}
