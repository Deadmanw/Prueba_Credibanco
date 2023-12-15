package banck.inc.prueba.controller;
import banck.inc.prueba.constants.Constants;
import banck.inc.prueba.entity.Transaction;
import banck.inc.prueba.model.*;
import banck.inc.prueba.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTests {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    public void testPurchase() {
        PurchaseRequest request = new PurchaseRequest();
        request.setCardId("1234567890123456");
        request.setPrice(new BigDecimal("50.00"));

        PurchaseResponse expectedResponse = new PurchaseResponse();
        expectedResponse.setStatus(Constants.APPROVED);

        when(transactionService.purchase(eq(request.getCardId()), eq(request.getPrice()))).thenReturn(expectedResponse);

        ResponseEntity<PurchaseResponse> responseEntity = transactionController.purchase(request);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
    @Test
    public void testFindTransaction() {
        Long transactionId = 1L;
        Transaction expectedTransaction = new Transaction(/*...*/);

        when(transactionService.findTransaction(eq(transactionId))).thenReturn(expectedTransaction);

        ResponseEntity<Transaction> responseEntity = transactionController.findTransaction(transactionId);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedTransaction, responseEntity.getBody());
    }
    @Test
    public void testVoidTransaction() {
        AnulationRequest request = new AnulationRequest();
        request.setTransactionId(1L);

        VoidTransaction expectedResponse = new VoidTransaction();
        expectedResponse.setStatus(Constants.SUCCESSFUL_VOID_TRANSACTION);

        when(transactionService.voidTransaction(eq(request.getTransactionId()))).thenReturn(expectedResponse);

        ResponseEntity<VoidTransaction> responseEntity = transactionController.voidTransaction(request);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
