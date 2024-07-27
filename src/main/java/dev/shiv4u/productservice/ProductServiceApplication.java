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
       /* // create,setVale,save into DB using Hibernate:-
      Category category=new Category();
      category.setName("electronics");
      Category savedCategory=categoryRepository.save(category);

      Price price=new Price("Rupee",10.0);
      //Price savePrice=priceRepository.save(price);
      //add product details in our local database:-
        Product product=new Product();
        product.setTitle("iPhone6");
        product.setImage("image url");
        product.setDescription("Best phone ever");
        product.setCategory(savedCategory);
        product.setPrice(price);
        productRepository.save(product);

        Product product1=productRepository.findByTitle("iphone5");
        System.out.println(product1);

        Optional<Category> categoryOptional = categoryRepository.
				findById(UUID.fromString("52c62741-46bc-4dcd-90f6-d4e068be5bce"));
        if(!categoryOptional.isEmpty()) {
			Category category1 = categoryOptional.get();
			System.out.println(category1.getProduct());
		}
*/
        //productRepository.deleteById(UUID.fromString("c4be258a-1735-4af8-a81e-eb6017a14279"));

    }
}
