package dev.shiv4u.productservice.services;

import dev.shiv4u.productservice.dtos.GenericProductDto;
import dev.shiv4u.productservice.models.Category;
import dev.shiv4u.productservice.models.Price;
import dev.shiv4u.productservice.models.Product;
import dev.shiv4u.productservice.repositories.CategoryRepository;
import dev.shiv4u.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service("selfProductService")
public class selfProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public selfProductServiceImpl(ProductRepository productRepository,
                                  CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        Product product =productRepository.findById(id);
        //debug the code
        /*System.out.println(product.getPrice().getCurrency()+"|"+product.getCategory().getName()
                +"|"+product.getTitle());*/
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setPrice(product.getPrice().getPrice());
        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = new Product();
        product.setTitle(genericProductDto.getTitle());
        product.setImage(genericProductDto.getImage());
        product.setDescription(genericProductDto.getDescription());
        Category category = categoryRepository.findByName(genericProductDto.getCategory());
        Category savedCategory = null;
        if (category == null) {
            category.setName("Electronics");
            savedCategory = categoryRepository.save(category);
        }
        product.setCategory(savedCategory);
        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());
        product.setPrice(price);
        Product product1 = productRepository.save(product);
        GenericProductDto genericProductDto1 = new GenericProductDto();
        genericProductDto1.setCategory(product1.getCategory().getName());
        genericProductDto1.setDescription(product1.getDescription());
        genericProductDto1.setTitle(product1.getTitle());
        return genericProductDto1;
    }

    @Override
    public GenericProductDto updateProductByid(Long id,GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

}
