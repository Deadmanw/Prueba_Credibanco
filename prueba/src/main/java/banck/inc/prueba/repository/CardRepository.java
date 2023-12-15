package banck.inc.prueba.repository;

import banck.inc.prueba.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Card repository.
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    /**
     * Find by card number card.
     *
     * @param cardNumber the card number
     * @return the card
     */
    Card findByCardNumber(String cardNumber);
}
