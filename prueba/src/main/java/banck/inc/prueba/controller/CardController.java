package banck.inc.prueba.controller;

import banck.inc.prueba.constants.Constants;
import banck.inc.prueba.entity.Card;
import banck.inc.prueba.model.AddBalanceRequest;
import banck.inc.prueba.model.AddBalanceResponse;
import banck.inc.prueba.model.BlockCardResponse;
import banck.inc.prueba.model.CardEnrollmentRequest;
import banck.inc.prueba.model.CardEnrollmentResponse;
import banck.inc.prueba.model.CheckBalanceResponse;
import banck.inc.prueba.model.GenerateCardNumberResponse;
import banck.inc.prueba.model.GenericResponse;
import banck.inc.prueba.service.CardService;
import java.math.BigDecimal;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Card controller.
 */
@RestController
@RequestMapping("/card")
public class CardController {
    /**
     * logger is used to add logs to development
     */
    private static final Logger logger = LogManager.getLogger(CardController.class);
    /**
     * is the call to the service
     */
    @Autowired
    private CardService cardService;

    /**
     * This method generates the card number from the product identification
     *
     * @param productId id for the product
     * @return Card number
     */
    @GetMapping("/{productId}/number")
    public ResponseEntity<GenerateCardNumberResponse> generateCardNumber(@PathVariable Long productId) {
        GenerateCardNumberResponse response = new GenerateCardNumberResponse();
        logger.info(productId);
        try {
            if (productId > 0) {
                return ResponseEntity.ok(cardService.generateCardNumber(productId));
            } else {
                response.setMessage(Constants.GENERATE_CARD_NUMBER_ERROR);
                response.setCardId(Constants.EMPY_STRING);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * this method return the list of cards
     *
     * @return the list of cards
     */
    @GetMapping("/list")
    public ResponseEntity<List<Card>> cardList() {
        try {
            return ResponseEntity.ok(cardService.cardList());
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * This method will activate the card from the card ID
     *
     * @param request this object contains the card id will is that be activated
     * @return a boolean state of the operation
     */
    @PostMapping("/Activate")
    public ResponseEntity<CardEnrollmentResponse> enrollCard(@RequestBody CardEnrollmentRequest request) {
        CardEnrollmentResponse response = new CardEnrollmentResponse();
        try {
            if (request != null && request.getCardId() != null && request.getCardId().length() == 16) {
                return ResponseEntity.ok(cardService.enrollCard(request.getCardId()));
            } else {
                response.setActivated(false);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * This method will block the card from the card ID
     *
     * @param cardId this is the card id
     * @return a boolean state of the operation
     */
    @DeleteMapping("/{cardId}")
    public ResponseEntity<BlockCardResponse> blockCard(@PathVariable String cardId) {
        BlockCardResponse response = new BlockCardResponse();
        try {
            if (cardId != null && cardId.length() == 16) {
                return ResponseEntity.ok(cardService.blockCard(cardId));
            } else {
                response.setBlocked(false);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * this method add balance to the card
     *
     * @param request this object contains the balance and the card id who add balance to the card
     * @return the new balance and the status of the operation
     */
    @PostMapping("/balance")
    public ResponseEntity<AddBalanceResponse> addBalance(@RequestBody AddBalanceRequest request) {
        AddBalanceResponse response = new AddBalanceResponse();
        try {
            if (request != null  && request.getBalance().compareTo(BigDecimal.ZERO) > 0
                && request.getCardId().length() == 16) {
                return ResponseEntity.ok(cardService.addBalance(request.getCardId(),
                    request.getBalance()));
            } else {
                response.setStatus(Constants.FAILED_RECHARGE);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * This method is used to check the card balance
     *
     * @param cardId this is the card id
     * @return the balance of the card
     */
    @GetMapping("/balance/{cardId}")
    public ResponseEntity<CheckBalanceResponse> checkBalance(@PathVariable String cardId) {
        CheckBalanceResponse response = new CheckBalanceResponse();
        try {
            if (cardId != null && cardId.length() == 16) {
                return ResponseEntity.ok(cardService.checkBalance(cardId));
            } else {
                response.setBalance(null);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Handle http message not readable exception response entity.
     *
     * @param ex the exception generated
     * @return a handled exception response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        GenericResponse response = new GenericResponse();
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}

