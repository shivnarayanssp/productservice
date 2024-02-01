package dev.shiv4u.productservice.repositories;

import dev.shiv4u.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByTitleAndPrice_Currency(String title,String currency);
    Product findByTitle(String title);
}
