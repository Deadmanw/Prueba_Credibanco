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
import banck.inc.prueba.service.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardControllerTests {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @Test
    void testGenerateCardNumber() {
        Long productId = 1L;
        GenerateCardNumberResponse expectedResponse = new GenerateCardNumberResponse();
        expectedResponse.setCardId("1234567890123456");

        when(cardService.generateCardNumber(productId)).thenReturn(expectedResponse);

        ResponseEntity<GenerateCardNumberResponse> responseEntity = cardController.generateCardNumber(productId);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testCardList() {
        List<Card> expectedCards = Collections.singletonList(new Card(/*...*/));

        when(cardService.cardList()).thenReturn(expectedCards);

        ResponseEntity<List<Card>> responseEntity = cardController.cardList();

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedCards, responseEntity.getBody());
    }

    @Test
    void testEnrollCard() {
        CardEnrollmentRequest request = new CardEnrollmentRequest();
        request.setCardId("1234567890123456");

        CardEnrollmentResponse expectedResponse = new CardEnrollmentResponse();
        expectedResponse.setActivated(true);

        when(cardService.enrollCard(eq(request.getCardId()))).thenReturn(expectedResponse);

        ResponseEntity<CardEnrollmentResponse> responseEntity = cardController.enrollCard(request);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testBlockCard() {
        String cardId = "1234567890123456";
        BlockCardResponse expectedResponse = new BlockCardResponse();
        expectedResponse.setBlocked(true);

        when(cardService.blockCard(eq(cardId))).thenReturn(expectedResponse);

        ResponseEntity<BlockCardResponse> responseEntity = cardController.blockCard(cardId);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testAddBalance() {
        AddBalanceRequest request = new AddBalanceRequest();
        request.setCardId("1234567890123456");
        request.setBalance(new BigDecimal("50.00"));

        AddBalanceResponse expectedResponse = new AddBalanceResponse();
        expectedResponse.setStatus(Constants.SUCCESSFUL_RECHARGE);
        expectedResponse.setNewBalance(new BigDecimal("150.00"));

        when(cardService.addBalance(eq(request.getCardId()), eq(request.getBalance()))).thenReturn(expectedResponse);

        ResponseEntity<AddBalanceResponse> responseEntity = cardController.addBalance(request);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testCheckBalance() {
        String cardId = "1234567890123456";
        CheckBalanceResponse expectedResponse = new CheckBalanceResponse();
        expectedResponse.setBalance(new BigDecimal("100.00"));

        when(cardService.checkBalance(eq(cardId))).thenReturn(expectedResponse);

        ResponseEntity<CheckBalanceResponse> responseEntity = cardController.checkBalance(cardId);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

}
