package banck.inc.prueba.repository;
import banck.inc.prueba.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Card transaction repository.
 */
@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {
}
