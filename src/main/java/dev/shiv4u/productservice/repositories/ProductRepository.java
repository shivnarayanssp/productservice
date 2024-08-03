package dev.shiv4u.productservice.repositories;

import dev.shiv4u.productservice.dtos.GenericProductDto;
import dev.shiv4u.productservice.models.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Id> {
    /*List<Product> findByTitleAndPrice_Currency(String title, String currency);
    Product findByTitle(String title);*/
    Product findById(Long id);
    List<Product> findAll();
    @Transactional
    void deleteById(Long id);
}
