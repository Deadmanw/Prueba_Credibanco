package banck.inc.prueba.controller;

import banck.inc.prueba.constants.Constants;
import banck.inc.prueba.entity.Transaction;
import banck.inc.prueba.model.AnulationRequest;
import banck.inc.prueba.model.PurchaseRequest;
import banck.inc.prueba.model.PurchaseResponse;
import banck.inc.prueba.model.VoidTransaction;
import banck.inc.prueba.service.TransactionService;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Transaction controller.
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    /**
     * logger is used to add logs to development
     */
    private static final Logger logger = LogManager.getLogger(TransactionController.class);
    /**
     *
     */
    @Autowired
    private TransactionService transactionService;

    /**
     * Purchase
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> purchase(@RequestBody PurchaseRequest request) {
        PurchaseResponse response = new PurchaseResponse();
        try {
            if (request != null && request.getPrice().compareTo(BigDecimal.ZERO) > 0
                && request.getCardId().length() == 16) {
                return ResponseEntity.ok(transactionService.purchase(request.getCardId(), request.getPrice()));
            } else {
                response.setStatus(Constants.ERROR_PURCHASE);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Find transaction
     * @param transactionId the transaction id
     * @return the response entity
     */
    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> findTransaction(@PathVariable Long transactionId) {
        try {
            if (transactionId > 0) {
                return ResponseEntity.ok(transactionService.findTransaction(transactionId));
            } else {
                return ResponseEntity.badRequest().body(new Transaction());
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }
    /**
     * Void transaction
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/anulation")
    public ResponseEntity<VoidTransaction> voidTransaction(@RequestBody AnulationRequest request) {
        VoidTransaction response = new VoidTransaction();
        try {
            if (request != null && request.getTransactionId()>0) {
                return ResponseEntity.ok(transactionService.voidTransaction(request.getTransactionId()));
            } else {
                response.setStatus(Constants.ERROR_VOID_TRANSACTION);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }
}
