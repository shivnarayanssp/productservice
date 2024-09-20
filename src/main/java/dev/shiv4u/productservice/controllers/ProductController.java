package dev.shiv4u.productservice.controllers;

import dev.shiv4u.productservice.dtos.ExceptionDto;
import dev.shiv4u.productservice.dtos.GenericProductDto;
import dev.shiv4u.productservice.exceptions.NotFoundException;
import dev.shiv4u.productservice.models.Category;
import dev.shiv4u.productservice.security.JwtData;
import dev.shiv4u.productservice.security.TokenValidator;
import dev.shiv4u.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private TokenValidator tokenValidator;
    //2:-Constructor injection is right  way to go.
    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService
                             ,TokenValidator tokenValidator) {
        this.productService = productService;
        this.tokenValidator=tokenValidator;
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public GenericProductDto getProductById(
            @Nullable @RequestHeader(HttpHeaders.AUTHORIZATION)String authToken,
            @PathVariable("id") Long id) throws NotFoundException {
        Optional<JwtData> jwtDataOptional=tokenValidator.validateToken(authToken);
        if(jwtDataOptional.isPresent()){
            //Do whatever needs to be done according to the business logic
        }
        GenericProductDto genericProductDto=productService.getProductById(id);
        if(genericProductDto == null){
            return new GenericProductDto();
        }
        return productService.getProductById(id);
    }
  @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return productService.getAllCategories();
    }
    @GetMapping("/category/{name}")
    public List<GenericProductDto> getAllProductsInCategory(@PathVariable("name") String name){
        return productService.getAllProductsInCategory(name);
    }
    @PatchMapping("/{id}")
    public GenericProductDto updateProductByid(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) throws ExceptionDto {
        return productService.updateProductByid(id,genericProductDto);
    }
    @PostMapping
    public GenericProductDto createproduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProduct(@PathVariable("id") Long id) throws ExceptionDto{
        return productService.deleteProduct(id);
    }
    // This is specific to this controller
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException) {
//        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
//                HttpStatus.NOT_FOUND);
//    }
}
