package dev.shiv4u.productservice.services;

import dev.shiv4u.productservice.dtos.ExceptionDto;
import dev.shiv4u.productservice.dtos.GenericProductDto;
import dev.shiv4u.productservice.models.Category;
import dev.shiv4u.productservice.models.Price;
import dev.shiv4u.productservice.models.Product;
import dev.shiv4u.productservice.repositories.CategoryRepository;
import dev.shiv4u.productservice.repositories.PriceRepository;
import dev.shiv4u.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Primary
@Service("selfProductService")
@Transactional
public class selfProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    @Autowired
    public selfProductServiceImpl(ProductRepository productRepository,
                                  CategoryRepository categoryRepository,
                                  PriceRepository priceRepository){
        this.productRepository=productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
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
        genericProductDto.setDescription(product.getDescr());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setCurrency(product.getPrice().getCurrency());
        return genericProductDto;
    }

    @Override
    public List<Category> getAllCategories() {
      List<Category> categories=categoryRepository.findAll();
      return categories;
    }
    @Override
    public List<GenericProductDto> getAllProductsInCategory(String name) {
        Category category=categoryRepository.findByName(name);
        List<GenericProductDto> genericProductDtos=new ArrayList<>();
        for(Product products:category.getProducts()){
            GenericProductDto genericProductDto=new GenericProductDto();
            genericProductDto.setId(products.getId());
            genericProductDto.setTitle(products.getTitle());
            genericProductDto.setImage(products.getImage());
            genericProductDto.setCategory(products.getCategory().getName());
            genericProductDto.setDescription(products.getDescr());
            genericProductDto.setPrice(products.getPrice().getPrice());
            genericProductDto.setCurrency(products.getPrice().getCurrency());
            genericProductDtos.add(genericProductDto);
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = new Product();
        product.setTitle(genericProductDto.getTitle());
        product.setImage(genericProductDto.getImage());
        product.setDescr(genericProductDto.getDescription());
        Category category = categoryRepository.findByName(genericProductDto.getCategory());
        if(category==null){
            Category category1=new Category();
            category1.setName(genericProductDto.getCategory());
            Category savedCategory = categoryRepository.save(category1);
            product.setCategory(savedCategory);
        }else{
            product.setCategory(category);
        }
        Price price=new Price();
        price.setCurrency(genericProductDto.getCurrency());
        price.setPrice(genericProductDto.getPrice());
        Price savePrice=priceRepository.save(price);
        product.setPrice(savePrice);
        Product product1 = productRepository.save(product);
        GenericProductDto genericProductDto1 = new GenericProductDto();
        genericProductDto1.setCategory(product1.getCategory().getName());
        genericProductDto1.setDescription(product1.getDescr());
        genericProductDto1.setTitle(product1.getTitle());
        genericProductDto1.setCurrency(product1.getPrice().getCurrency());
        genericProductDto1.setPrice(product1.getPrice().getPrice());
        return genericProductDto1;
    }

    @Override
    public GenericProductDto updateProductByid(Long id,GenericProductDto genericProductDto) throws ExceptionDto {
        Product product=productRepository.findById(id);
        if(product==null){
           throw new ExceptionDto(HttpStatus.NOT_FOUND,"Not_Found");
        }else{
            product.setTitle(genericProductDto.getTitle());
            product.getCategory().setName(genericProductDto.getCategory());
            product.getPrice().setCurrency(genericProductDto.getCurrency());
            product.getPrice().setPrice(genericProductDto.getPrice());
            product.setDescr(genericProductDto.getDescription());
            product.setImage(genericProductDto.getImage());
            productRepository.save(product);
            GenericProductDto genericProductDto1=new GenericProductDto();
            genericProductDto1.setId(product.getId());
            genericProductDto1.setTitle(product.getTitle());
            genericProductDto1.setCategory(product.getCategory().getName());
            genericProductDto1.setDescription(product.getDescr());
            genericProductDto1.setImage(product.getImage());
            genericProductDto1.setPrice(product.getPrice().getPrice());
            genericProductDto1.setCurrency(product.getPrice().getCurrency());
            return genericProductDto1;
        }
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products=productRepository.findAll();
        List<GenericProductDto> genericProductDtos=new ArrayList<>();
        GenericProductDto genericProductDto=new GenericProductDto();
        for (Product product:products){
            genericProductDto.setId(product.getId());
            genericProductDto.setTitle(product.getTitle());
            genericProductDto.setPrice(product.getPrice().getPrice());
            genericProductDto.setCurrency(product.getPrice().getCurrency());
            genericProductDto.setCategory(product.getCategory().getName());
            genericProductDto.setDescription(product.getDescr());
            genericProductDto.setImage(product.getImage());
        }
        genericProductDtos.add(genericProductDto);
        return genericProductDtos;
    }
    @Override
    public GenericProductDto deleteProduct(Long id) throws ExceptionDto{
        Product product=productRepository.findById(id);
        if(product==null){
            throw new ExceptionDto(HttpStatus.NOT_FOUND,"NOT_FOUND");
        }
        productRepository.deleteById(id);
        GenericProductDto genericProductDto=new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setCurrency(product.getPrice().getCurrency());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setDescription(product.getDescr());
        genericProductDto.setImage(product.getImage());
        return genericProductDto;
    }
}
