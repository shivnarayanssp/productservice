package dev.shiv4u.productservice.repositories;

import dev.shiv4u.productservice.models.Category;
import dev.shiv4u.productservice.models.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Id>{
        Category findByName(String name);
}
