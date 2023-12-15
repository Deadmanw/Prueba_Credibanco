package banck.inc.prueba.service;

import banck.inc.prueba.constants.Constants;
import banck.inc.prueba.entity.Card;
import banck.inc.prueba.entity.Product;
import banck.inc.prueba.entity.ProductCard;
import banck.inc.prueba.model.AddBalanceResponse;
import banck.inc.prueba.model.BlockCardResponse;
import banck.inc.prueba.model.CardEnrollmentResponse;
import banck.inc.prueba.model.CheckBalanceResponse;
import banck.inc.prueba.model.GenerateCardNumberResponse;
import banck.inc.prueba.repository.ProductRepository;
import banck.inc.prueba.repository.CardRepository;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Card service.
 */
@Service
public class CardService {
    /**
     * use for create a random number
     */
    Random random = new Random();
    /**
     * Repository of the products
     */
    @Autowired
    private ProductRepository productRepository;
    /**
     * Repository of the cards
     */
    @Autowired
    private CardRepository cardRepository;

    /**
     * method for create the card in the database
     *
     * @param productId      the product id
     * @param cardholderName name of the owner of the card
     * @param cardNumber     card number for create the card
     */
    public void createCard(Long productId, String cardholderName, String cardNumber) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException(Constants.PRODUCT_NOT_FOUND));
        Date currentDate = new Date();

        Calendar expiryDateCalendar = Calendar.getInstance();
        expiryDateCalendar.setTime(currentDate);
        expiryDateCalendar.add(Calendar.YEAR, 3);

        Card card = new Card();
        card.setProductId(productId);
        card.setCardNumber(cardNumber);
        card.setCardholderName(cardholderName);
        card.setCreationDate(currentDate);
        card.setExpiryDate(expiryDateCalendar.getTime());
        card.setBalance(BigDecimal.ZERO);
        card.setStatus(Constants.INACTIVE);

        ProductCard productCard = new ProductCard();
        productCard.setProduct(product);
        productCard.setCard(card);

        cardRepository.save(card);
    }

    /**
     * This method generates the card number from the product identification
     *
     * @param productId id for the product
     * @return Card number
     */
    public GenerateCardNumberResponse generateCardNumber(Long productId) {
        GenerateCardNumberResponse response = new GenerateCardNumberResponse();

        Optional<Product> product=productRepository.findById(productId);
        if (product.isEmpty()) {
            response.setMessage(Constants.GENERATE_CARD_NUMBER_ERROR);
            return  response;
        }
        String productIdString = String.format("%06d", productId);
        String randomDigits = String.format("%010d", random.nextInt(1000000000));
        String cardNumber = productIdString + randomDigits;
        createCard(productId, Constants.GENERIC_NAME, cardNumber);
        response.setCardId(cardNumber);

        return response;
    }

    /**
     * this method return the list of cards
     *
     * @return the list of cards
     */
    public List<Card> cardList() {
        return cardRepository.findAll();
    }

    /**
     * This method will activate the card from the card ID
     *
     * @param cardId the card id will is that be activated
     * @return a boolean state of the operation
     */
    public CardEnrollmentResponse enrollCard(String cardId) {
        CardEnrollmentResponse response = new CardEnrollmentResponse();
        Card card = cardRepository.findByCardNumber(cardId);
        card.setStatus(Constants.ACTIVE);
        cardRepository.save(card);
        response.setActivated(true);
        return response;
    }

    /**
     * This method will block the card from the card ID
     *
     * @param cardId this is the card id
     * @return a boolean state of the operation
     */
    public BlockCardResponse blockCard(String cardId) {
        BlockCardResponse response = new BlockCardResponse();
        Card card = cardRepository.findByCardNumber(cardId);
        card.setStatus(Constants.BLOCK);
        cardRepository.save(card);
        response.setBlocked(true);
        return response;
    }

    /**
     * this method add balance to the card
     *
     * @param cardId  the card id that be charge
     * @param balance the balance who be added to the card
     * @return the new balance and the status of the operation
     */
    public AddBalanceResponse addBalance(String cardId, BigDecimal balance) {
        AddBalanceResponse response = new AddBalanceResponse();
        Card card = cardRepository.findByCardNumber(cardId);
        BigDecimal currentBalance = card.getBalance();
        card.setBalance(currentBalance.add(balance));
        cardRepository.save(card);
        response.setNewBalance(card.getBalance());
        response.setStatus(Constants.SUCCESSFUL_RECHARGE);
        return response;
    }

    /**
     * This method is used to check the card balance
     *
     * @param cardId this is the card id
     * @return the balance of the card
     */
    public CheckBalanceResponse checkBalance(String cardId) {
        CheckBalanceResponse response = new CheckBalanceResponse();
        Card card = cardRepository.findByCardNumber(cardId);
        response.setBalance(card.getBalance());
        return response;
    }

    /**
     * this method return the new balance after a void transaction
     *
     * @param cardId the id of the card
     * @param amount the amount of return to the card
     */
    public void returnBalance(Long cardId, BigDecimal amount) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new RuntimeException(Constants.CARD_NOT_FOUND));
        BigDecimal currentBalance = card.getBalance();
        card.setBalance(currentBalance.add(amount));
        cardRepository.save(card);
    }
}

