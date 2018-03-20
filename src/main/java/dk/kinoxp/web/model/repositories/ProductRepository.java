package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>  {

    Product findByName(String name);
    Product findById(int id);
}
