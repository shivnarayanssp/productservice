package dev.shiv4u.productservice;

import dev.shiv4u.productservice.models.Category;
import dev.shiv4u.productservice.models.Price;
import dev.shiv4u.productservice.models.Product;
import dev.shiv4u.productservice.repositories.CategoryRepository;
import dev.shiv4u.productservice.repositories.PriceRepository;
import dev.shiv4u.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;
    public ProductServiceApplication(ProductRepository productRepository,
                                     CategoryRepository categoryRepository,
                                     PriceRepository priceRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
        this.priceRepository=priceRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception{
        Category category=new Category();
        category.setName("electronics");
        Category savedCategory=categoryRepository.save(category);
        Price price=new Price("Rupee",10.0);
        Price savePrice=priceRepository.save(price);

        Product product=new Product();
        product.setTitle("iPhone4");
        product.setImage("image url");
        product.setDescription("Best phone ever");
        product.setCategory(savedCategory);
        product.setPrice(savePrice);

        //productRepository.save(product);
//        Product product1=productRepository.findByTitle("iphone4");
//        System.out.println(product1);
//        Optional<Category> categoryOptional = categoryRepository.
//				findById(UUID.fromString("02600b88-43f3-4341-8bff-ec665a17c21c"));
//        if(!categoryOptional.isEmpty()) {
//			Category category = categoryOptional.get();
//			System.out.println(category.getProduct());
//		}
//        productRepository.deleteById(UUID.fromString("8ffdd9b4-acd1-48a3-9c44-ec92c2b4e5b1"));
    }
}
