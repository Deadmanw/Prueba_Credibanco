package banck.inc.prueba.repository;

import banck.inc.prueba.entity.ProductCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Product card repository.
 */
@Repository
public interface ProductCardRepository extends JpaRepository<ProductCard, Long> {
}

