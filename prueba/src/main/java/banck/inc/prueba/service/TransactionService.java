package banck.inc.prueba.service;
import banck.inc.prueba.constants.Constants;
import banck.inc.prueba.entity.Card;
import banck.inc.prueba.entity.Transaction;
import banck.inc.prueba.model.PurchaseResponse;
import banck.inc.prueba.model.VoidTransaction;
import banck.inc.prueba.repository.CardRepository;
import banck.inc.prueba.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The type Transaction service.
 */
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardService cardService;

    /**
     * Void transaction void transaction.
     *
     * @param transactionId the transaction id
     * @return the void transaction
     */
    public VoidTransaction voidTransaction(Long transactionId) {
        VoidTransaction response = new VoidTransaction();
        Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new RuntimeException(Constants.TRANSACTION_NOT_FOUND));

        if (isNullable(transaction)) {
            transaction.setStatus(Constants.ANNULLED);
            transactionRepository.save(transaction);

            cardService.returnBalance(transaction.getCard(), transaction.getAmount());

            response.setStatus(Constants.SUCCESSFUL_VOID_TRANSACTION);
        } else {
            response.setStatus(Constants.ERROR_VOID_TRANSACTION);
        }
        return response;
    }

    private boolean isNullable(Transaction transaction) {
        return Constants.APPROVED.equals(transaction.getStatus()) &&
            transaction.getTransactionDate().isAfter(LocalDateTime.now().minusHours(24));
    }

    /**
     * Find transaction transaction.
     *
     * @param transactionId the transaction id
     * @return the transaction
     */
    public Transaction findTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId)
            .orElseThrow(() -> new RuntimeException(Constants.TRANSACTION_NOT_FOUND));
    }

    /**
     * method for do a purchase.
     *
     * @param cardId the card id
     * @param price  the price
     * @return the purchase response
     */
    public PurchaseResponse purchase(String cardId, BigDecimal price) {
        PurchaseResponse response = new PurchaseResponse();
        Card card = cardRepository.findByCardNumber(cardId);
        BigDecimal currentBalance = card.getBalance();

        Transaction transaction = new Transaction();
        transaction.setCard(card.getCardId());
        transaction.setAmount(price);
        transaction.setTransactionDate(LocalDateTime.now());

        if (currentBalance.compareTo(price) >= 0) {
            card.setBalance(currentBalance.subtract(price));
            cardRepository.save(card);

            transaction.setStatus(Constants.APPROVED);

            response.setStatus(transaction.getStatus());
        } else {
            transaction.setStatus(Constants.REJECTED);

            response.setStatus(Constants.REJECTED_BALANCE);
        }
        transactionRepository.save(transaction);

        response.setNewBalance(card.getBalance());
        response.setTransactionId(transaction.getTransactionId().toString());
        return response;
    }
}
